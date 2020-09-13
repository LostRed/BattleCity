package com.lostred.bc.model.tank;

import com.lostred.bc.controller.listener.Player1Ctrl;
import com.lostred.bc.controller.listener.Player2Ctrl;
import com.lostred.bc.controller.listener.PlayerCtrl;
import com.lostred.bc.controller.thread.animation.Shield;
import com.lostred.bc.controller.thread.animation.TankAppear;
import com.lostred.bc.model.Bullet;
import com.lostred.bc.model.Seat;
import com.lostred.bc.service.Service;
import com.lostred.bc.util.GameData;
import com.lostred.bc.util.localFile.GameImage;
import com.lostred.bc.util.setting.AllyTankLevel;
import com.lostred.bc.util.setting.Direction;

import java.awt.*;

/**
 * 盟军坦克模型
 */
public class AllyTank extends Tank {
    /**
     * 盟军坦克的等级
     */
    private AllyTankLevel allyTankLevel;
    /**
     * 绑定的控制器
     */
    private PlayerCtrl playerCtrl;
    /**
     * 盟军坦克的护盾
     */
    private Shield shield;
    /**
     * 播放移动音效的标志位
     */
    private boolean moveSoundFlag;

    /**
     * 构造盟军坦克，默认坦克方向向上
     *
     * @param allyTankLevel 盟军的坦克等级
     * @param playerCtrl    玩家控制器
     * @param service       后台控制
     */
    public AllyTank(AllyTankLevel allyTankLevel, PlayerCtrl playerCtrl, Service service) {
        this.allyTankLevel = allyTankLevel;
        this.playerCtrl = playerCtrl;
        super.setService(service);
        super.setAppearances(GameImage.packImages(this));
        super.setWidth(allyTankLevel.getWidth());
        super.setHeight(allyTankLevel.getHeight());
        super.setBulletNum(allyTankLevel.getBulletLimit());
        super.setHealth(GameData.ALLIES_TANK_HEALTH);
        super.setDirection(Direction.UP);
    }

    /**
     * 根据盟军坦克的方向，获取该坦克所在位置的一个矩形
     *
     * @return 返回该坦克的所在位置的一个矩形
     */
    public Rectangle getRectangle() {
        Rectangle rectangle = null;
        switch (super.getDirection()) {
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
     * 根据盟军坦克的方向，获取该坦克前方位置的一个以速度为高度的矩形
     *
     * @param speedRevise 速度修正
     * @return 返回该坦克前方位置的一个以速度为高度的矩形
     */
    @Override
    public Rectangle getMotionRectangle(int speedRevise) {
        Rectangle rectangle = null;
        switch (super.getDirection()) {
            case UP:
                rectangle = new Rectangle(super.getXp() - super.getWidth() / 2,
                        super.getYp() - super.getHeight() / 2 - (this.getAllyTankLevel().getSpeed() + speedRevise),
                        super.getWidth(), this.getAllyTankLevel().getSpeed() + speedRevise);
                break;
            case DOWN:
                rectangle = new Rectangle(super.getXp() - super.getWidth() / 2,
                        super.getYp() + super.getHeight() / 2,
                        super.getWidth(), this.getAllyTankLevel().getSpeed() + speedRevise);
                break;
            case LEFT:
                rectangle = new Rectangle(super.getXp() - super.getHeight() / 2 - (this.getAllyTankLevel().getSpeed() + speedRevise),
                        super.getYp() - super.getWidth() / 2,
                        this.getAllyTankLevel().getSpeed() + speedRevise, super.getWidth());
                break;
            case RIGHT:
                rectangle = new Rectangle(super.getXp() + super.getHeight() / 2,
                        super.getYp() - super.getWidth() / 2,
                        this.getAllyTankLevel().getSpeed() + speedRevise, super.getWidth());
                break;
        }
        return rectangle;
    }

    /**
     * 实现盟军坦克的出生，在出生位置放置一个Seat类对象
     */
    @Override
    public void birthing() {
        Seat seat = new Seat();
        seat.setXp(super.getXp());
        seat.setYp(super.getYp());
        super.getService().getCurrentAllyTanks().add(seat);
        //新建坦克出生的线程
        new TankAppear(super.getService().getGf(), this, seat).start();
    }

    /**
     * 实现盟军坦克发射子弹
     */
    @Override
    public void fire() {
        if (super.getBulletNum() > 0 && !super.isLoading()) {
            super.setLoading(true);
            super.setBulletNum(super.getBulletNum() - 1);
            super.getService().getGs().play(super.getService().getGs().getShotSound());
            Bullet bullet = new Bullet(this, super.getService());
            bullet.setSpeed(this.getAllyTankLevel().getBulletSpeed());
            setBulletLocation(bullet);
            super.getService().getBullets().add(bullet);
            //等待子弹消失
            Runnable waiting1 = () -> {
                while (super.getService().getBullets().contains(bullet)) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (this.getBulletNum() < this.getAllyTankLevel().getBulletLimit()) {
                    super.setBulletNum(super.getBulletNum() + 1);
                }
            };
            new Thread(waiting1).start();
            //子弹装填时间
            Runnable waiting2 = () -> {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.setLoading(false);
            };
            new Thread(waiting2).start();
        }
    }

    /**
     * 重置盟军坦克状态
     */
    public void resetTank() {
        if (this.getPlayerCtrl() instanceof Player1Ctrl) {
            this.setAllyTankLevel(GameData.PLAYER1_LEVEL);
            super.setWidth(GameData.PLAYER1_LEVEL.getWidth());
            super.setHeight(GameData.PLAYER1_LEVEL.getHeight());
            super.setBulletNum(GameData.PLAYER1_LEVEL.getBulletLimit());
            this.setXp(264);
        } else if (this.getPlayerCtrl() instanceof Player2Ctrl) {
            this.setAllyTankLevel(GameData.PLAYER2_LEVEL);
            super.setWidth(GameData.PLAYER2_LEVEL.getWidth());
            super.setHeight(GameData.PLAYER2_LEVEL.getHeight());
            super.setBulletNum(GameData.PLAYER2_LEVEL.getBulletLimit());
            this.setXp(456);
        }
        this.setYp(648);
        this.setAppearances(GameImage.packImages(this));
        this.setDirection(Direction.UP);
        this.setHealth(GameData.ALLIES_TANK_HEALTH);

    }

    /**
     * 更新盟军坦克的等级
     *
     * @param allyTankLevel 要更新的盟军坦克等级
     */
    public void updateLevel(AllyTankLevel allyTankLevel) {
        if (this.getPlayerCtrl() instanceof Player1Ctrl) {
            GameData.PLAYER1_LEVEL = allyTankLevel;
            this.setAllyTankLevel(GameData.PLAYER1_LEVEL);
            super.setBulletNum(GameData.PLAYER1_LEVEL.getBulletLimit());
            super.setWidth(GameData.PLAYER1_LEVEL.getWidth());
            super.setHeight(GameData.PLAYER1_LEVEL.getHeight());
        } else if (this.getPlayerCtrl() instanceof Player2Ctrl) {
            GameData.PLAYER2_LEVEL = allyTankLevel;
            this.setAllyTankLevel(GameData.PLAYER2_LEVEL);
            super.setBulletNum(GameData.PLAYER2_LEVEL.getBulletLimit());
            super.setWidth(GameData.PLAYER2_LEVEL.getWidth());
            super.setHeight(GameData.PLAYER2_LEVEL.getHeight());
        }
        this.setAppearances(GameImage.packImages(this));
    }

    //get和set方法
    public AllyTankLevel getAllyTankLevel() {
        return allyTankLevel;
    }

    public void setAllyTankLevel(AllyTankLevel allyTankLevel) {
        this.allyTankLevel = allyTankLevel;
    }

    public PlayerCtrl getPlayerCtrl() {
        return playerCtrl;
    }

    public void setPlayerCtrl(PlayerCtrl playerCtrl) {
        this.playerCtrl = playerCtrl;
    }

    public Shield getShield() {
        return shield;
    }

    public void setShield(Shield shield) {
        this.shield = shield;
    }

    public boolean isMoveSoundFlag() {
        return moveSoundFlag;
    }

    public void setMoveSoundFlag(boolean moveSoundFlag) {
        this.moveSoundFlag = moveSoundFlag;
    }
}
