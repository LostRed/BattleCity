package com.lostred.bc.model;

import com.lostred.bc.controller.listener.Player1Ctrl;
import com.lostred.bc.controller.listener.Player2Ctrl;
import com.lostred.bc.controller.thread.animation.Score;
import com.lostred.bc.controller.thread.animation.Shield;
import com.lostred.bc.controller.thread.animation.TankExplode;
import com.lostred.bc.model.tank.AllyTank;
import com.lostred.bc.model.tank.EnemyTank;
import com.lostred.bc.service.Service;
import com.lostred.bc.util.GameData;
import com.lostred.bc.util.localFile.GameImage;
import com.lostred.bc.util.setting.AllyTankLevel;
import com.lostred.bc.util.setting.LandformType;
import com.lostred.bc.util.setting.PropertyType;

/**
 * 道具包模型
 */
public class Property extends Model {
    /**
     * 道具包的宽度
     */
    public static final int WIDTH = 40;
    /**
     * 道具包的高度
     */
    public static final int HEIGHT = 40;
    /**
     * 道具包的类型
     */
    private PropertyType propertyType;
    /**
     * 基地周围的地形
     */
    private Landform one = null;
    /**
     * 基地周围的地形
     */
    private Landform two = null;
    /**
     * 基地周围的地形
     */
    private Landform three = null;
    /**
     * 基地周围的地形
     */
    private Landform four = null;
    /**
     * 基地周围的地形
     */
    private Landform five = null;
    /**
     * 基地周围的地形
     */
    private Landform six = null;
    /**
     * 基地周围的地形
     */
    private Landform seven = null;
    /**
     * 基地周围的地形
     */
    private Landform eight = null;

    /**
     * 构造道具包，设置模型默认宽度和高度
     *
     * @param propertyType 道具包的类型
     * @param service      后台控制
     */
    public Property(PropertyType propertyType, Service service) {
        this.propertyType = propertyType;
        super.setService(service);
        super.setAppearances(GameImage.packImages(this));
        super.setWidth(WIDTH);
        super.setHeight(HEIGHT);
    }

    /**
     * 选择奖励，对应盟军坦克的玩家获得相应的分数
     *
     * @param allyTank 盟军坦克对象
     */
    public void selectAward(AllyTank allyTank) {
        switch (this.propertyType) {
            case HELMET:
                getShield(allyTank);
                super.getService().getGs().play(super.getService().getGs().getGetAwardSound());
                if (allyTank.getPlayerCtrl() instanceof Player1Ctrl) {
                    GameData.PLAYER1_SCORE += 500;
                } else if (allyTank.getPlayerCtrl() instanceof Player2Ctrl) {
                    GameData.PLAYER2_SCORE += 500;
                }
                new Score(super.getService().getGf(), allyTank, 500).start();
                break;
            case STAR:
                levelup(allyTank);
                super.getService().getGs().play(super.getService().getGs().getGetAwardSound());
                if (allyTank.getPlayerCtrl() instanceof Player1Ctrl) {
                    GameData.PLAYER1_SCORE += 500;
                } else if (allyTank.getPlayerCtrl() instanceof Player2Ctrl) {
                    GameData.PLAYER2_SCORE += 500;
                }
                new Score(super.getService().getGf(), allyTank, 500).start();
                break;
            case SHOVEL:
                protectEagleBase();
                super.getService().getGs().play(super.getService().getGs().getGetAwardSound());
                if (allyTank.getPlayerCtrl() instanceof Player1Ctrl) {
                    GameData.PLAYER1_SCORE += 500;
                } else if (allyTank.getPlayerCtrl() instanceof Player2Ctrl) {
                    GameData.PLAYER2_SCORE += 500;
                }
                new Score(super.getService().getGf(), allyTank, 500).start();
                break;
            case GUN:
                levelupToMax(allyTank);
                super.getService().getGs().play(super.getService().getGs().getGetAwardSound());
                if (allyTank.getPlayerCtrl() instanceof Player1Ctrl) {
                    GameData.PLAYER1_SCORE += 500;
                } else if (allyTank.getPlayerCtrl() instanceof Player2Ctrl) {
                    GameData.PLAYER2_SCORE += 500;
                }
                new Score(super.getService().getGf(), allyTank, 500).start();
                break;
            case TANK:
                plusLife(allyTank);
                super.getService().getGs().play(super.getService().getGs().getPlusLifeSound());
                if (allyTank.getPlayerCtrl() instanceof Player1Ctrl) {
                    GameData.PLAYER1_SCORE += 500;
                } else if (allyTank.getPlayerCtrl() instanceof Player2Ctrl) {
                    GameData.PLAYER2_SCORE += 500;
                }
                new Score(super.getService().getGf(), allyTank, 500).start();
                break;
            case BOMB:
                clearAllEnemy();
                super.getService().getGs().play(super.getService().getGs().getGetAwardSound());
                if (allyTank.getPlayerCtrl() instanceof Player1Ctrl) {
                    GameData.PLAYER1_SCORE += 500;
                } else if (allyTank.getPlayerCtrl() instanceof Player2Ctrl) {
                    GameData.PLAYER2_SCORE += 500;
                }
                new Score(super.getService().getGf(), allyTank, 500).start();
                break;
            case TIMER:
                freezeAllEnemy();
                super.getService().getGs().play(super.getService().getGs().getGetAwardSound());
                if (allyTank.getPlayerCtrl() instanceof Player1Ctrl) {
                    GameData.PLAYER1_SCORE += 500;
                } else if (allyTank.getPlayerCtrl() instanceof Player2Ctrl) {
                    GameData.PLAYER2_SCORE += 500;
                }
                new Score(super.getService().getGf(), allyTank, 500).start();
                break;
        }
        super.getService().getGf().updateStatePanel();
    }

    /**
     * 盟军坦克获得给定时间次数的护盾(盟军坦克重生时使用)
     *
     * @param tank 盟军坦克对象
     * @param time 护盾持续时间
     */
    public void getShield(AllyTank tank, int time) {
        Shield shield = new Shield(super.getService().getGf(), tank, time);
        tank.setShield(shield);
        shield.start();
    }

    /**
     * 盟军坦克获得护盾，如果盟军坦克已有护盾，重置护盾循环次数
     *
     * @param tank 盟军坦克对象
     */
    public void getShield(AllyTank tank) {
        if (tank.getShield() != null && tank.getShield().getTime() > 0) {
            tank.getShield().setTime(10000);
        } else {
            Shield shield = new Shield(super.getService().getGf(), tank, 10000);
            tank.setShield(shield);
            shield.start();
        }
    }

    /**
     * 盟军坦克获得升级
     *
     * @param tank 盟军坦克对象
     */
    public void levelup(AllyTank tank) {
        switch (tank.getAllyTankLevel()) {
            case O:
                tank.updateLevel(AllyTankLevel.I);
                break;
            case I:
                tank.updateLevel(AllyTankLevel.II);
                break;
            case II:
                tank.updateLevel(AllyTankLevel.III);
                break;
            default:
                break;
        }
    }

    /**
     * 将基地周围的地形替换为铁墙，再次获得该道具时，重置持续时间
     */
    public void protectEagleBase() {
        if (super.getService().getSteelTime() > 0) {
            super.getService().setSteelTime(20000);
        } else {
            super.getService().setSteelTime(20000);
            for (Landform landform : super.getService().getImpassableLandform()) {
                if (landform.getXp() == 324 && landform.getYp() == 636) {
                    one = landform;
                } else if (landform.getXp() == 324 && landform.getYp() == 660) {
                    two = landform;
                } else if (landform.getXp() == 324 && landform.getYp() == 612) {
                    three = landform;
                } else if (landform.getXp() == 348 && landform.getYp() == 612) {
                    four = landform;
                } else if (landform.getXp() == 372 && landform.getYp() == 612) {
                    five = landform;
                } else if (landform.getXp() == 396 && landform.getYp() == 612) {
                    six = landform;
                } else if (landform.getXp() == 396 && landform.getYp() == 636) {
                    seven = landform;
                } else if (landform.getXp() == 396 && landform.getYp() == 660) {
                    eight = landform;
                }
            }
            if (one != null) {
                one.setLandformType(LandformType.STEEL);
            } else {
                Landform newLandform = new Landform(LandformType.STEEL);
                newLandform.setXp(324);
                newLandform.setYp(636);
                super.getService().getImpassableLandform().add(newLandform);
                one = newLandform;
            }
            if (two != null) {
                two.setLandformType(LandformType.STEEL);
            } else {
                Landform newLandform = new Landform(LandformType.STEEL);
                newLandform.setXp(324);
                newLandform.setYp(660);
                super.getService().getImpassableLandform().add(newLandform);
                two = newLandform;
            }
            if (three != null) {
                three.setLandformType(LandformType.STEEL);
            } else {
                Landform newLandform = new Landform(LandformType.STEEL);
                newLandform.setXp(324);
                newLandform.setYp(612);
                super.getService().getImpassableLandform().add(newLandform);
                three = newLandform;
            }
            if (four != null) {
                four.setLandformType(LandformType.STEEL);
            } else {
                Landform newLandform = new Landform(LandformType.STEEL);
                newLandform.setXp(348);
                newLandform.setYp(612);
                super.getService().getImpassableLandform().add(newLandform);
                four = newLandform;
            }
            if (five != null) {
                five.setLandformType(LandformType.STEEL);
            } else {
                Landform newLandform = new Landform(LandformType.STEEL);
                newLandform.setXp(372);
                newLandform.setYp(612);
                super.getService().getImpassableLandform().add(newLandform);
                five = newLandform;
            }
            if (six != null) {
                six.setLandformType(LandformType.STEEL);
            } else {
                Landform newLandform = new Landform(LandformType.STEEL);
                newLandform.setXp(396);
                newLandform.setYp(612);
                super.getService().getImpassableLandform().add(newLandform);
                six = newLandform;
            }
            if (seven != null) {
                seven.setLandformType(LandformType.STEEL);
            } else {
                Landform newLandform = new Landform(LandformType.STEEL);
                newLandform.setXp(396);
                newLandform.setYp(636);
                super.getService().getImpassableLandform().add(newLandform);
                seven = newLandform;
            }
            if (eight != null) {
                eight.setLandformType(LandformType.STEEL);
            } else {
                Landform newLandform = new Landform(LandformType.STEEL);
                newLandform.setXp(396);
                newLandform.setYp(660);
                super.getService().getImpassableLandform().add(newLandform);
                eight = newLandform;
            }
            Runnable waiting = () -> {
                while (super.getService().getSteelTime() > 0) {
                    one.setAppearances(GameImage.packImages(LandformType.STEEL));
                    two.setAppearances(GameImage.packImages(LandformType.STEEL));
                    three.setAppearances(GameImage.packImages(LandformType.STEEL));
                    four.setAppearances(GameImage.packImages(LandformType.STEEL));
                    five.setAppearances(GameImage.packImages(LandformType.STEEL));
                    six.setAppearances(GameImage.packImages(LandformType.STEEL));
                    seven.setAppearances(GameImage.packImages(LandformType.STEEL));
                    eight.setAppearances(GameImage.packImages(LandformType.STEEL));
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (super.getService().getSteelTime() < 5) {
                        one.setAppearances(GameImage.packImages(LandformType.BRICK));
                        two.setAppearances(GameImage.packImages(LandformType.BRICK));
                        three.setAppearances(GameImage.packImages(LandformType.BRICK));
                        four.setAppearances(GameImage.packImages(LandformType.BRICK));
                        five.setAppearances(GameImage.packImages(LandformType.BRICK));
                        six.setAppearances(GameImage.packImages(LandformType.BRICK));
                        seven.setAppearances(GameImage.packImages(LandformType.BRICK));
                        eight.setAppearances(GameImage.packImages(LandformType.BRICK));
                    }
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!GameData.PAUSE) {
                        super.getService().setSteelTime(super.getService().getSteelTime() - 500);
                    }
                }
                one.setLandformType(LandformType.BRICK);
                two.setLandformType(LandformType.BRICK);
                three.setLandformType(LandformType.BRICK);
                four.setLandformType(LandformType.BRICK);
                five.setLandformType(LandformType.BRICK);
                six.setLandformType(LandformType.BRICK);
                seven.setLandformType(LandformType.BRICK);
                eight.setLandformType(LandformType.BRICK);
                one.setAppearances(GameImage.packImages(LandformType.BRICK));
                two.setAppearances(GameImage.packImages(LandformType.BRICK));
                three.setAppearances(GameImage.packImages(LandformType.BRICK));
                four.setAppearances(GameImage.packImages(LandformType.BRICK));
                five.setAppearances(GameImage.packImages(LandformType.BRICK));
                six.setAppearances(GameImage.packImages(LandformType.BRICK));
                seven.setAppearances(GameImage.packImages(LandformType.BRICK));
                eight.setAppearances(GameImage.packImages(LandformType.BRICK));
            };
            new Thread(waiting).start();
        }
    }

    /**
     * 盟军坦克升至最大等级
     *
     * @param tank 盟军坦克对象
     */
    public void levelupToMax(AllyTank tank) {
        tank.updateLevel(AllyTankLevel.III);
    }

    /**
     * 玩家获得一辆备用坦克
     *
     * @param tank 盟军坦克对象
     */
    public void plusLife(AllyTank tank) {
        if (tank.getPlayerCtrl() instanceof Player1Ctrl) {
            GameData.PLAYER1_LIFE++;
        } else if (tank.getPlayerCtrl() instanceof Player2Ctrl) {
            GameData.PLAYER2_LIFE++;
        }
    }

    /**
     * 消灭所有敌军坦克和它们的子弹
     */
    public void clearAllEnemy() {
        for (Model model : super.getService().getCurrentEnemyTanks()) {
            super.getService().getCurrentEnemyTanks().remove(model);
            new TankExplode(super.getService().getGf(), model, true).start();
        }
        for (Bullet bullet : super.getService().getBullets()) {
            if (bullet.getOwner() instanceof EnemyTank) {
                super.getService().getBullets().remove(bullet);
            }
        }
    }

    /**
     * 冻结所有敌军坦克，如果敌军坦克已被冻结，重置等待时间
     */
    public void freezeAllEnemy() {
        if (super.getService().getGf().getEnemyTankAction().getStopTime() > 0) {
            super.getService().getGf().getEnemyTankAction().setStopTime(10000);
        } else {
            super.getService().getGf().getEnemyTankAction().setStopTime(10000);
            new Thread(super.getService().getGf().getEnemyTankAction().getWaiting()).start();
        }
    }

    // get和set方法
    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }
}
