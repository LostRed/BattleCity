package com.lostred.bc.model;

import com.lostred.bc.controller.listener.Player1Ctrl;
import com.lostred.bc.controller.listener.Player2Ctrl;
import com.lostred.bc.controller.thread.animation.BulletExplode;
import com.lostred.bc.controller.thread.animation.TankExplode;
import com.lostred.bc.model.tank.AllyTank;
import com.lostred.bc.model.tank.EnemyTank;
import com.lostred.bc.model.tank.Tank;
import com.lostred.bc.service.Service;
import com.lostred.bc.util.GameData;
import com.lostred.bc.util.localFile.GameImage;
import com.lostred.bc.util.setting.AllyTankLevel;
import com.lostred.bc.util.setting.Direction;
import com.lostred.bc.util.setting.LandformType;
import com.lostred.bc.view.panel.GamePanel;

import java.awt.*;

/**
 * 子弹模型
 */
public class Bullet extends Model implements Movable {
    /**
     * 子弹的宽度
     */
    public static final int WIDTH = 8;
    /**
     * 子弹的高度
     */
    public static final int HEIGHT = 12;
    /**
     * 子弹所属的坦克
     */
    private Tank owner;
    /**
     * 子弹移动的速度
     */
    private int speed;
    /**
     * 子弹移动的方向
     */
    private Direction direction;
    /**
     * 子弹能否破坏铁墙地形
     */
    private boolean enhance;

    /**
     * 构造子弹，同时设置所属坦克，设置子弹移动的方向
     *
     * @param owner   子弹所属的坦克
     * @param service 后台控制
     */
    public Bullet(Tank owner, Service service) {
        this.owner = owner;
        super.setService(service);
        super.setAppearances(GameImage.packBulletImages());
        super.setWidth(WIDTH);
        super.setHeight(HEIGHT);
        this.direction = owner.getDirection();
        if (owner instanceof AllyTank) {
            this.enhance = ((AllyTank) owner).getAllyTankLevel().isEnhance();
        } else if (owner instanceof EnemyTank) {
            this.enhance = ((EnemyTank) owner).getEnemyTankType().isEnhance();
        }
    }

    /**
     * 根据子弹的方向，获取该子弹所在位置的一个矩形(静止状态)
     *
     * @return 返回该子弹的所在位置的一个矩形
     */
    public Rectangle getRectangle() {
        Rectangle rectangle = null;
        switch (this.direction) {
            case UP:
            case DOWN:
                rectangle = new Rectangle(super.getXp() - super.getWidth() / 2, super.getYp() - super.getHeight() / 2,
                        super.getWidth(), super.getHeight());
                break;
            case LEFT:
            case RIGHT:
                rectangle = new Rectangle(super.getXp() - super.getHeight() / 2, super.getYp() - super.getWidth() / 2,
                        super.getHeight(), super.getWidth());
                break;
        }
        return rectangle;
    }

    /**
     * 根据子弹的方向，获取该子弹前方位置的一个以速度为高度的矩形
     *
     * @param speedRevise 速度修正
     * @return 返回该子弹前方位置的一个以速度为高度的矩形
     */
    @Override
    public Rectangle getMotionRectangle(int speedRevise) {
        Rectangle rectangle = null;
        switch (this.direction) {
            case UP:
                rectangle = new Rectangle(super.getXp() - super.getWidth() / 2,
                        super.getYp() - super.getHeight() / 2 - this.speed,
                        super.getWidth(), this.speed);
                break;
            case DOWN:
                rectangle = new Rectangle(super.getXp() - super.getWidth() / 2,
                        super.getYp() + super.getHeight() / 2,
                        super.getWidth(), this.speed);
                break;
            case LEFT:
                rectangle = new Rectangle(super.getXp() - super.getHeight() / 2 - this.speed,
                        super.getYp() - super.getWidth() / 2,
                        this.speed, super.getWidth());
                break;
            case RIGHT:
                rectangle = new Rectangle(super.getXp() + super.getHeight() / 2,
                        super.getYp() - super.getWidth() / 2,
                        this.speed, super.getWidth());
                break;
        }
        return rectangle;
    }

    /**
     * 根据子弹的方向，获取该子弹在下个单位时间所在位置的一个矩形(将宽度设为GameArea.CELL+1，用于和地形的判断)
     *
     * @return 返回该子弹的所在位置的一个矩形
     * @see GamePanel#CELL
     */
    public Rectangle getLandformRectangle() {
        Rectangle rectangle = null;
        switch (this.direction) {
            case UP:
            case DOWN:
                rectangle = new Rectangle(super.getXp() - (GamePanel.CELL + 1) / 2,
                        super.getYp() - super.getHeight() / 2, GamePanel.CELL + 1, super.getHeight());
                break;
            case LEFT:
            case RIGHT:
                rectangle = new Rectangle(super.getXp() - super.getHeight() / 2,
                        super.getYp() - (GamePanel.CELL + 1) / 2, super.getHeight(), GamePanel.CELL + 1);
                break;
        }
        return rectangle;
    }

    /**
     * 检查该子弹与其它模型的碰撞(排除特殊情况：敌军坦克子弹与敌军坦克，子弹与河流、树林、冰川地形，同阵营子弹)
     *
     * @return 碰撞返回true，未碰撞返回false
     */
    @Override
    public boolean collideToAnother() {
        Rectangle thisRect = getRectangle();
        Rectangle anotherRect;
        //子弹属于敌军阵营时
        if (this.owner instanceof EnemyTank) {
            //检查所有当前的盟军坦克
            for (Model model : super.getService().getCurrentAllyTanks()) {
                //跳过出生的坦克
                if (model instanceof Seat) {
                    continue;
                }
                anotherRect = model.getRectangle();
                //碰撞结算
                if (thisRect.intersects(anotherRect)) {
                    removeModel(this);
                    new BulletExplode(super.getService().getGf(), this).start();
                    AllyTank allyTank = (AllyTank) model;
                    if (allyTank.getShield() == null) {
                        if (allyTank.getHealth() > 1) {
                            super.getService().getGs().play(super.getService().getGs().getHitTankSound());
                        }
                        allyTank.setHealth(allyTank.getHealth() - 1);
                    }
                    if (allyTank.getHealth() <= 0) {
                        removeModel(model);
                        new TankExplode(super.getService().getGf(), model, false).start();
                    }
                    return true;
                }
            }
        }
        //子弹属于盟军阵营时
        else if (this.owner instanceof AllyTank) {
            //检查所有当前的敌军坦克
            for (Model model : super.getService().getCurrentEnemyTanks()) {
                //跳过出生的坦克
                if (model instanceof Seat) {
                    continue;
                }
                anotherRect = model.getRectangle();
                //碰撞结算
                if (thisRect.intersects(anotherRect)) {
                    removeModel(this);
                    new BulletExplode(super.getService().getGf(), this).start();
                    EnemyTank enemyTank = (EnemyTank) model;
                    if (enemyTank.getHealth() > 1) {
                        super.getService().getGs().play(super.getService().getGs().getHitTankSound());
                    }
                    enemyTank.setHealth(enemyTank.getHealth() - 1);
                    if (((EnemyTank) model).isProperty()) {
                        super.getService().setProperty();
                    }
                    if (enemyTank.getHealth() <= 0) {
                        removeModel(model);
                        new TankExplode(super.getService().getGf(), model, false).start();
                    }
                    return true;
                }
            }
            //检查所有当前的盟军坦克(双人模式)
            for (Model model : super.getService().getCurrentAllyTanks()) {
                //跳过出生的坦克和子弹所属坦克对象
                if (model instanceof Seat || model == this.owner) {
                    continue;
                }
                anotherRect = model.getRectangle();
                //碰撞结算
                if (thisRect.intersects(anotherRect)) {
                    removeModel(this);
                    AllyTank allyTank = (AllyTank) model;
                    if (allyTank.getShield() == null) {
                        if (allyTank.getPlayerCtrl().getStopTime() > 0) {
                            allyTank.getPlayerCtrl().setStopTime(3000);
                        } else {
                            allyTank.getPlayerCtrl().setStopTime(3000);
                            new Thread(allyTank.getPlayerCtrl().getWaiting()).start();
                        }
                    }
                    return true;
                }
            }
        }
        //检查所有对方阵营子弹
        for (Bullet bullet : super.getService().getBullets()) {
            //子弹与子弹是不同阵营时
            if ((this.owner instanceof AllyTank && bullet.owner instanceof EnemyTank)
                    || (this.owner instanceof EnemyTank && bullet.owner instanceof AllyTank)) {
                anotherRect = bullet.getRectangle();
                //碰撞结算
                if (thisRect.intersects(anotherRect)) {
                    removeModel(this);
                    removeModel(bullet);
                    return true;
                }
            }
        }
        //检查所有不可穿过的地形
        boolean collided = false;
        //更换碰撞矩形(地形用)
        thisRect = this.getLandformRectangle();
        for (Landform landform : super.getService().getImpassableLandform()) {
            if ((landform.getLandformType() == LandformType.BRICK)
                    || (landform.getLandformType() == LandformType.STEEL)) {
                anotherRect = landform.getRectangle();
                //碰撞结算
                if (thisRect.intersects(anotherRect)) {
                    collided = true;
                    removeModel(this);
                    new BulletExplode(super.getService().getGf(), this).start();
                    if (landform.getLandformType() == LandformType.BRICK) {
                        super.getService().getGs().play(super.getService().getGs().getHitBrickSound());
                        removeModel(landform);
                    } else if (landform.getLandformType() == LandformType.STEEL && this.enhance) {
                        super.getService().getGs().play(super.getService().getGs().getHitSteelSound());
                        removeModel(landform);
                    } else if (landform.getLandformType() == LandformType.STEEL) {
                        super.getService().getGs().play(super.getService().getGs().getHitSteelSound());
                    }
                }
            }
        }
        if (collided) {
            return true;
        }
        //检查基地
        anotherRect = super.getService().getEagleBase().getRectangle();
        //碰撞结算
        if (thisRect.intersects(anotherRect)) {
            removeModel(this);
            new BulletExplode(super.getService().getGf(), this).start();
            if (super.getService().getEagleBase().isAlive()) {
                super.getService().getGs().play(super.getService().getGs().getExplodeSound());
                super.getService().getEagleBase().setAlive(false);
            }
            return true;
        }
        return false;
    }

    /**
     * 检查该子弹与地图边界的碰撞
     *
     * @return 碰撞返回true，未碰撞返回false
     */
    @Override
    public boolean collideToBoundary() {
        Rectangle thisRect = getRectangle();
        Rectangle anotherRect;
        switch (this.direction) {
            case UP:
                anotherRect = new Rectangle(GamePanel.MARGIN, 0, GamePanel.MAP_WIDTH, GamePanel.MARGIN);
                break;
            case DOWN:
                anotherRect = new Rectangle(GamePanel.MARGIN, GamePanel.MARGIN + GamePanel.MAP_HEIGHT, GamePanel.MAP_WIDTH, GamePanel.MARGIN);
                break;
            case LEFT:
                anotherRect = new Rectangle(0, GamePanel.MARGIN, GamePanel.MARGIN, GamePanel.MAP_HEIGHT);
                break;
            case RIGHT:
                anotherRect = new Rectangle(GamePanel.MARGIN + GamePanel.MAP_WIDTH, GamePanel.MARGIN, GamePanel.MARGIN, GamePanel.MAP_HEIGHT);
                break;
            default:
                anotherRect = null;
                break;
        }
        if (thisRect.intersects(anotherRect)) {
            new BulletExplode(super.getService().getGf(), this).start();
            super.getService().getGs().play(super.getService().getGs().getHitBrickSound());
            removeModel(this);
            return true;
        }
        return false;
    }

    /**
     * 实现子弹的移动
     */
    @Override
    public void move(int speed) {
        switch (this.direction) {
            case UP:
                super.setYp(super.getYp() - speed);
                break;
            case DOWN:
                super.setYp(super.getYp() + speed);
                break;
            case LEFT:
                super.setXp(super.getXp() - speed);
                break;
            case RIGHT:
                super.setXp(super.getXp() + speed);
                break;
        }
    }

    /**
     * 移除模型
     *
     * @param model 模型对象
     */
    private void removeModel(Model model) {
        if (model instanceof AllyTank) {
            AllyTank tank = (AllyTank) model;
            if (tank.getPlayerCtrl() instanceof Player1Ctrl) {
                GameData.PLAYER1_LIFE--;
                if (GameData.PLAYER1_LIFE < 0) {
                    super.getService().getCurrentAllyTanks().remove(tank);
                } else {
                    super.getService().getCurrentAllyTanks().remove(tank);
                    if (!super.getService().getAllyTanks().contains(tank)) {
                        super.getService().getAllyTanks().add(tank);
                    }
                    GameData.PLAYER1_LEVEL = AllyTankLevel.O;
                }
            } else if (tank.getPlayerCtrl() instanceof Player2Ctrl) {
                GameData.PLAYER2_LIFE--;
                if (GameData.PLAYER2_LIFE < 0) {
                    super.getService().getCurrentAllyTanks().remove(tank);
                } else {
                    super.getService().getCurrentAllyTanks().remove(tank);
                    if (!super.getService().getAllyTanks().contains(tank)) {
                        super.getService().getAllyTanks().add(tank);
                    }
                    GameData.PLAYER2_LEVEL = AllyTankLevel.O;
                }
            }
        } else if (model instanceof EnemyTank && !GameData.GAME_OVER) {
            EnemyTank enemyTank = (EnemyTank) model;
            super.getService().getCurrentEnemyTanks().remove(model);
            if (((AllyTank) this.owner).getPlayerCtrl() instanceof Player1Ctrl) {
                GameData.PLAYER1_SCORE += enemyTank.getEnemyTankType().getScore();
            } else if (((AllyTank) this.owner).getPlayerCtrl() instanceof Player2Ctrl) {
                GameData.PLAYER2_SCORE += enemyTank.getEnemyTankType().getScore();
            }
            super.getService().getGf().updateStatePanel();
        } else if (model instanceof Landform) {
            super.getService().getImpassableLandform().remove(model);
        } else if (model instanceof Bullet) {
            super.getService().getBullets().remove(model);
        }
    }

    //get和set方法
    public Tank getOwner() {
        return owner;
    }

    public void setOwner(Tank owner) {
        this.owner = owner;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isEnhance() {
        return enhance;
    }

    public void setEnhance(boolean enhance) {
        this.enhance = enhance;
    }
}
