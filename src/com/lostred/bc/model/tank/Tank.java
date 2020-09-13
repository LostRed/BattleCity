package com.lostred.bc.model.tank;

import com.lostred.bc.model.*;
import com.lostred.bc.util.setting.Direction;
import com.lostred.bc.util.setting.LandformType;
import com.lostred.bc.view.panel.GamePanel;

import java.awt.*;
import java.util.ArrayList;

/**
 * 坦克模型：包括盟军坦克和敌军坦克
 */
public abstract class Tank extends Model implements Rotatable, Movable {
    /**
     * 坦克的生命值
     */
    private int health;
    /**
     * 坦克子弹数量
     */
    private int bulletNum;
    /**
     * 坦克是否在准备子弹
     */
    private boolean loading;
    /**
     * 坦克的方向
     */
    private Direction direction;
    /**
     * 坦克在冰川上
     */
    private boolean onGlacier;

    /**
     * 构造坦克
     */
    public Tank() {
    }

    /**
     * 检查出生位置是否被其它坦克占用
     *
     * @return 未被占用返回true，否则为false
     */
    public boolean checkBirthPosition() {
        Rectangle thisRect = new Rectangle(super.getXp() - GamePanel.CELL, super.getYp() - GamePanel.CELL,
                2 * GamePanel.CELL, 2 * GamePanel.CELL);
        Rectangle anotherRect;
        // 检查所有敌军坦克
        for (Model anotherModel : super.getService().getCurrentEnemyTanks()) {
            anotherRect = anotherModel.getRectangle();
            if (thisRect.intersects(anotherRect)) {
                return false;
            }
        }
        // 检查所有盟军坦克
        for (Model anotherModel : super.getService().getCurrentAllyTanks()) {
            anotherRect = anotherModel.getRectangle();
            if (thisRect.intersects(anotherRect)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 实现坦克出生的方法
     */
    public abstract void birthing();

    /**
     * 根据坦克的方向，获取该坦克所在位置的一个矩形(静止状态)
     *
     * @return 返回该坦克的所在位置的一个矩形
     */
    public abstract Rectangle getRectangle();

    /**
     * 根据坦克的方向，获取该坦克前方位置的一个以速度为高度的矩形
     *
     * @param speedRevise 速度修正
     * @return 返回获取该坦克前方位置的一个以速度为高度的矩形
     */
    public abstract Rectangle getMotionRectangle(int speedRevise);

    /**
     * 设置子弹初始位置
     *
     * @param bullet 子弹对象
     */
    public void setBulletLocation(Bullet bullet) {
        switch (this.direction) {
            case UP:
                bullet.setXp(bullet.getOwner().getXp());
                bullet.setYp(bullet.getOwner().getYp() - bullet.getOwner().getHeight() / 2 + bullet.getHeight() / 2);
                break;
            case DOWN:
                bullet.setXp(bullet.getOwner().getXp());
                bullet.setYp(bullet.getOwner().getYp() + bullet.getOwner().getHeight() / 2 - bullet.getHeight() / 2);
                break;
            case LEFT:
                bullet.setXp(bullet.getOwner().getXp() - bullet.getOwner().getHeight() / 2 + bullet.getHeight() / 2);
                bullet.setYp(bullet.getOwner().getYp());
                break;
            case RIGHT:
                bullet.setXp(bullet.getOwner().getXp() + bullet.getOwner().getHeight() / 2 - bullet.getHeight() / 2);
                bullet.setYp(bullet.getOwner().getYp());
                break;
        }
    }

    /**
     * 实现坦克发射子弹
     */
    public abstract void fire();

    /**
     * 实现坦克的换向
     *
     * @param direction 坦克改成的方向
     */
    @Override
    public void rotate(Direction direction) {
        if (this.direction != direction) {
            this.direction = direction;
        }
    }

    /**
     * 坦克与其它模型发生微小碰撞(像素不超过10)时，修正其坐标
     *
     * @param model 碰撞到的模型对象
     */
    private boolean tolerantLandform(Model model) {
        Rectangle thisRect;
        if (this.onGlacier) {
            thisRect = getMotionRectangle(1);
        } else {
            thisRect = getMotionRectangle(0);
        }
        Rectangle anotherRect = model.getRectangle();
        Rectangle intersectRect = thisRect.intersection(anotherRect);
        switch (this.direction) {
            case UP:
            case DOWN:
                if (intersectRect.getWidth() > 10) {
                    return false;
                }
                if (intersectRect.getX() < super.getXp()) {
                    //右侧将要位移的矩形区域内是否有坦克
                    Rectangle rightRect = new Rectangle(super.getXp() + super.getWidth() / 2,
                            super.getYp() - super.getHeight() / 2,
                            (int) intersectRect.getWidth(), super.getHeight());
                    for (Model tank : super.getService().getCurrentAllyTanks()) {
                        if (rightRect.intersects(tank.getRectangle())) {
                            return false;
                        }
                    }
                    for (Model tank : super.getService().getCurrentEnemyTanks()) {
                        if (rightRect.intersects(tank.getRectangle())) {
                            return false;
                        }
                    }
                    //将坦克向右位移碰撞矩形的宽
                    super.setXp(super.getXp() + (int) intersectRect.getWidth());
                } else if (intersectRect.getX() > super.getXp()) {
                    //左侧将要位移的矩形区域内是否有坦克
                    Rectangle leftRect = new Rectangle(super.getXp() - super.getWidth() / 2 - (int) intersectRect.getWidth(),
                            super.getYp() - super.getHeight() / 2,
                            (int) intersectRect.getWidth(), super.getHeight());
                    for (Model tank : super.getService().getCurrentAllyTanks()) {
                        if (leftRect.intersects(tank.getRectangle())) {
                            return false;
                        }
                    }
                    for (Model tank : super.getService().getCurrentEnemyTanks()) {
                        if (leftRect.intersects(tank.getRectangle())) {
                            return false;
                        }
                    }
                    //将坦克向左位移碰撞矩形的宽
                    super.setXp(super.getXp() - (int) intersectRect.getWidth());
                }
                break;
            case LEFT:
            case RIGHT:
                if (intersectRect.getHeight() > 10) {
                    return false;
                }
                if (intersectRect.getY() < super.getYp()) {
                    //下侧将要位移的矩形区域内是否有坦克
                    Rectangle rightRect = new Rectangle(super.getXp() - super.getWidth() / 2,
                            super.getYp() + super.getHeight() / 2,
                            super.getHeight(), (int) intersectRect.getHeight());
                    for (Model tank : super.getService().getCurrentAllyTanks()) {
                        if (rightRect.intersects(tank.getRectangle())) {
                            return false;
                        }
                    }
                    for (Model tank : super.getService().getCurrentEnemyTanks()) {
                        if (rightRect.intersects(tank.getRectangle())) {
                            return false;
                        }
                    }
                    //将坦克向下位移碰撞矩形的高
                    super.setYp(super.getYp() + (int) intersectRect.getHeight());
                } else if (intersectRect.getY() > super.getYp()) {
                    //上侧将要位移的矩形区域内是否有坦克
                    Rectangle rightRect = new Rectangle(super.getXp() - super.getWidth() / 2,
                            super.getYp() - super.getHeight() / 2 - (int) intersectRect.getHeight() - 2,
                            super.getHeight(), (int) intersectRect.getHeight());
                    for (Model tank : super.getService().getCurrentAllyTanks()) {
                        if (rightRect.intersects(tank.getRectangle())) {
                            return false;
                        }
                    }
                    for (Model tank : super.getService().getCurrentEnemyTanks()) {
                        if (rightRect.intersects(tank.getRectangle())) {
                            return false;
                        }
                    }
                    //将坦克向上位移碰撞矩形的高
                    super.setYp(super.getYp() - (int) intersectRect.getHeight());
                }
                break;
        }
        return true;
    }

    /**
     * 检查该坦克与其他模型的碰撞
     *
     * @return 碰撞返回true，为碰撞返回false
     */
    @Override
    public boolean collideToAnother() {
        Rectangle thisRect;
        if (this.onGlacier) {
            thisRect = getMotionRectangle(1);
        } else {
            thisRect = getMotionRectangle(0);
        }
        Rectangle anotherRect;
        //检查所有其它当前的盟军坦克
        for (Model model : super.getService().getCurrentAllyTanks()) {
            //排除自己
            if (model != this) {
                anotherRect = model.getRectangle();
                if (thisRect.intersects(anotherRect)) {
                    return true;
                }
            }
        }
        //检查所有其它当前的敌军坦克
        for (Model model : super.getService().getCurrentEnemyTanks()) {
            //排除自己
            if (model != this) {
                anotherRect = model.getRectangle();
                if (thisRect.intersects(anotherRect)) {
                    return true;
                }
            }
        }
        //检查所有不可穿过的地形
        ArrayList<Landform> list = new ArrayList<>();
        for (Landform obs : super.getService().getImpassableLandform()) {
            if (obs.getLandformType() == LandformType.BRICK
                    || obs.getLandformType() == LandformType.STEEL
                    || obs.getLandformType() == LandformType.RIVER) {
                anotherRect = obs.getRectangle();
                if (thisRect.intersects(anotherRect)) {
                    list.add(obs);
                }
            }
        }
        if (list.size() == 1) {
            if (!tolerantLandform(list.get(0))) {
                return true;
            }
        } else if (list.size() > 1) {
            return true;
        }
        //检查所有道具包
        if (this instanceof AllyTank) {
            for (Property property : super.getService().getProperties()) {
                anotherRect = property.getRectangle();
                if (thisRect.intersects(anotherRect)) {
                    super.getService().getProperties().remove(property);
                    property.selectAward((AllyTank) this);
                    return false;
                }
            }
        }
        //检查基地
        anotherRect = super.getService().getEagleBase().getRectangle();
        return thisRect.intersects(anotherRect);
    }

    /**
     * 检查该坦克与地图边界的碰撞
     *
     * @return 碰撞返回true，为碰撞返回false
     */
    @Override
    public boolean collideToBoundary() {
        Rectangle thisRect;
        if (this.onGlacier) {
            thisRect = getMotionRectangle(1);
        } else {
            thisRect = getMotionRectangle(0);
        }
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
        }
        return thisRect.intersects(anotherRect);
    }

    /**
     * 实现该坦克的移动
     *
     * @param speed 坦克移动的速度
     */
    @Override
    public void move(int speed) {
        if (this.onGlacier) {
            speed++;
        }
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
     * 检查冰川地形
     *
     * @return 在冰川地形上返回true，否则返回false
     */
    public boolean checkGlacier() {
        Rectangle thisRect = getRectangle();
        Rectangle anotherRect;
        for (Landform obs : super.getService().getPassableLandform()) {
            if (obs.getLandformType() == LandformType.GLACIER) {
                anotherRect = obs.getRectangle();
                if (thisRect.intersects(anotherRect)) {
                    return true;
                }
            }
        }
        return false;
    }

    //get和set方法
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getBulletNum() {
        return bulletNum;
    }

    public void setBulletNum(int bulletNum) {
        this.bulletNum = bulletNum;
    }

    public boolean isLoading() {
        return loading;
    }

    public void setLoading(boolean loading) {
        this.loading = loading;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean isOnGlacier() {
        return onGlacier;
    }

    public void setOnGlacier(boolean onGlacier) {
        this.onGlacier = onGlacier;
    }
}
