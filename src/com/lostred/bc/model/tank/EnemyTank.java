package com.lostred.bc.model.tank;

import com.lostred.bc.controller.thread.animation.TankAppear;
import com.lostred.bc.model.Bullet;
import com.lostred.bc.model.Seat;
import com.lostred.bc.service.Service;
import com.lostred.bc.util.GameData;
import com.lostred.bc.util.localFile.GameImage;
import com.lostred.bc.util.setting.Direction;
import com.lostred.bc.util.setting.EnemyTankType;

import java.awt.*;
import java.util.Random;

/**
 * 敌军坦克模型
 */
public class EnemyTank extends Tank {
    /**
     * 敌军坦克的类型
     */
    private EnemyTankType enemyTankType;
    /**
     * 敌军坦克被击中后是否有道具
     */
    private boolean property;
    /**
     * 带道具的敌军坦克闪烁线程任务
     */
    private Runnable propertyTank;

    /**
     * 构造敌军坦克，默认坦克方向向下
     *
     * @param enemyTankType 敌军坦克的类型
     * @param service       后台控制
     */
    public EnemyTank(EnemyTankType enemyTankType, Service service) {
        this.enemyTankType = enemyTankType;
        super.setService(service);
        super.setAppearances(GameImage.packImages(this, false));
        super.setWidth(enemyTankType.getWidth());
        super.setHeight(enemyTankType.getHeight());
        super.setBulletNum(enemyTankType.getBulletLimit());
        super.setHealth(enemyTankType.getHealth() + GameData.ENEMY_TANK_HEALTH_REVISE);
        super.setDirection(Direction.randomDirection());
        this.propertyTank = () -> {
            while (true) {
                super.setAppearances(GameImage.packImages(this, true));
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                super.setAppearances(GameImage.packImages(this, false));
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    /**
     * 根据敌军坦克的方向，获取该坦克所在位置的一个矩形
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
     * 根据敌军坦克的方向，获取该坦克前方位置的一个以速度为高度的矩形
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
                        super.getYp() - super.getHeight() / 2 - (this.getEnemyTankType().getSpeed() + speedRevise),
                        super.getWidth(), this.getEnemyTankType().getSpeed() + speedRevise);
                break;
            case DOWN:
                rectangle = new Rectangle(super.getXp() - super.getWidth() / 2,
                        super.getYp() + super.getHeight() / 2,
                        super.getWidth(), this.getEnemyTankType().getSpeed() + speedRevise);
                break;
            case LEFT:
                rectangle = new Rectangle(super.getXp() - super.getHeight() / 2 - (this.getEnemyTankType().getSpeed() + speedRevise),
                        super.getYp() - super.getWidth() / 2,
                        this.getEnemyTankType().getSpeed() + speedRevise, super.getWidth());
                break;
            case RIGHT:
                rectangle = new Rectangle(super.getXp() + super.getHeight() / 2,
                        super.getYp() - super.getWidth() / 2,
                        this.getEnemyTankType().getSpeed() + speedRevise, super.getWidth());
                break;
        }
        return rectangle;
    }

    /**
     * 实现敌军坦克的出生，在出生位置放置一个Seat类对象
     */
    @Override
    public void birthing() {
        Seat seat = new Seat();
        seat.setXp(super.getXp());
        seat.setYp(super.getYp());
        super.getService().getCurrentEnemyTanks().add(seat);
        //新建坦克出生的线程
        new TankAppear(super.getService().getGf(), this, seat).start();
    }

    /**
     * 实现敌军坦克发射子弹
     */
    @Override
    public void fire() {
        if (super.getBulletNum() > 0 && !super.isLoading()) {
            super.setLoading(true);
            super.setBulletNum(super.getBulletNum() - 1);
            Bullet bullet = new Bullet(this, super.getService());
            bullet.setSpeed(this.getEnemyTankType().getBulletSpeed());
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
                if (this.getBulletNum() < this.getEnemyTankType().getBulletLimit()) {
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
     * 实现敌军坦克碰撞后的行动方法
     */
    public void collidedAction() {
        if (this.collideToBoundary()) {
            this.rotate(Direction.randomDirection());
        } else if (this.collideToAnother()) {
            Random random = new Random();
            int action = random.nextInt(100);
            if (action < 5) {
                this.fire();
            } else {
                this.rotate(Direction.randomDirection());
            }
        }
    }

    //get和set方法
    public EnemyTankType getEnemyTankType() {
        return enemyTankType;
    }

    public void setEnemyTankType(EnemyTankType enemyTankType) {
        this.enemyTankType = enemyTankType;
    }

    public boolean isProperty() {
        return property;
    }

    public void setProperty(boolean property) {
        this.property = property;
    }

    public Runnable getPropertyTank() {
        return propertyTank;
    }

    public void setPropertyTank(Runnable propertyTank) {
        this.propertyTank = propertyTank;
    }
}
