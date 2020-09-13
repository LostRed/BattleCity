package com.lostred.bc.service;

import com.lostred.bc.controller.listener.Player1Ctrl;
import com.lostred.bc.controller.listener.Player2Ctrl;
import com.lostred.bc.model.*;
import com.lostred.bc.model.tank.AllyTank;
import com.lostred.bc.model.tank.EnemyTank;
import com.lostred.bc.util.GameData;
import com.lostred.bc.util.Record;
import com.lostred.bc.util.localFile.GameImage;
import com.lostred.bc.util.localFile.GameSound;
import com.lostred.bc.util.setting.*;
import com.lostred.bc.view.GameFrame;
import com.lostred.bc.view.panel.GamePanel;

import java.awt.*;
import java.util.Collections;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 后台控制
 */
public class Service {
    /**
     * 游戏窗口
     */
    private GameFrame gf;
    /**
     * 游戏音效
     */
    private GameSound gs;
    /**
     * 关卡
     */
    private Stage stage;
    /**
     * 基地铁墙的持续时间
     */
    private volatile int steelTime;
    /**
     * 基地
     */
    private EagleBase eagleBase;
    /**
     * 当前盟军坦克集合
     */
    private CopyOnWriteArrayList<Model> currentAllyTanks;
    /**
     * 等待队列盟军坦克集合
     */
    private CopyOnWriteArrayList<Model> allyTanks;
    /**
     * 当前敌军坦克集合
     */
    private CopyOnWriteArrayList<Model> currentEnemyTanks;
    /**
     * 等待队列敌军坦克集合
     */
    private CopyOnWriteArrayList<Model> enemyTanks;
    /**
     * 不可穿越的地形集合
     */
    private CopyOnWriteArrayList<Landform> impassableLandform;
    /**
     * 可穿越的地形集合
     */
    private CopyOnWriteArrayList<Landform> passableLandform;
    /**
     * 子弹集合
     */
    private CopyOnWriteArrayList<Bullet> bullets;
    /**
     * 道具包集合
     */
    private CopyOnWriteArrayList<Property> properties;
    /**
     * 进入游戏的等待时间任务
     */
    private Runnable loading;

    /**
     * 构造后台控制
     *
     * @param gf 游戏窗体
     */
    public Service(GameFrame gf) {
        this.gf = gf;
        this.gs = new GameSound();
        this.eagleBase = new EagleBase(this);
        this.currentAllyTanks = new CopyOnWriteArrayList<>();
        this.allyTanks = new CopyOnWriteArrayList<>();
        this.currentEnemyTanks = new CopyOnWriteArrayList<>();
        this.enemyTanks = new CopyOnWriteArrayList<>();
        this.impassableLandform = new CopyOnWriteArrayList<>();
        this.passableLandform = new CopyOnWriteArrayList<>();
        this.bullets = new CopyOnWriteArrayList<>();
        this.properties = new CopyOnWriteArrayList<>();
        this.loading = () -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //开幕
            gf.remove(gf.getProloguePanel());
            gf.add(gf.getGamePanel());
            gf.getService().start(GameData.STAGE_NO);
            GameData.PAUSE = false;
            GameData.GAME_OVER = false;
        };
    }

    /**
     * 开始关卡
     *
     * @param no 关卡编号
     */
    public void start(int no) {
        this.gs.play(this.gs.getStartSound());
        selectStage(no);
        setEagleBase();
        setLandform();
        setEnemyTank();
        gf.initCtrl();
        setAllyTank();
        gf.initTimer();
        gf.addPlayerName();
        gf.updateStatePanel();
    }

    /**
     * 下一关卡
     */
    public void nextStage() {
        //重置敌军坦克行动线程的等待时间
        gf.getEnemyTankAction().setStopTime(0);
        //重置基地周围的铁墙时间
        this.steelTime = 0;
        clearAllModelSet();
        this.gs.play(this.gs.getStartSound());
        selectStage(GameData.STAGE_NO);
        setEagleBase();
        setLandform();
        setEnemyTank();
        setAllyTank();
        gf.updateStatePanel();
    }

    /**
     * 选择关卡
     *
     * @param no 关卡编号
     */
    private void selectStage(int no) {
        switch (no) {
            case 0:
                this.stage = new Stage(18,
                        2, 0, 0, 3, Stage.MAP);
                break;
            case 1:
                this.stage = new Stage(18,
                        2, 0, 0, 3, Stage.MAP1);
                break;
            case 2:
                this.stage = new Stage(14,
                        4, 0, 2, 3, Stage.MAP2);
                break;
            case 3:
                this.stage = new Stage(11,
                        4, 3, 2, 3, Stage.MAP3);
                break;
            case 4:
                this.stage = new Stage(11,
                        5, 3, 1, 3, Stage.MAP4);
                break;
            case 5:
                this.stage = new Stage(7,
                        4, 7, 2, 3, Stage.MAP5);
                break;
        }
    }

    /**
     * 结束关卡
     */
    public void endStage() {
        clearAllModelSet();
        gf.clearStatePanel();
        gf.removePlayerName();
        gf.cancelCtrl();
        if (gf.getGameAction() != null) {
            gf.cancelTimer();
        }
    }

    /**
     * 清除所有模型集合
     */
    public void clearAllModelSet() {
        this.getGf().getGamePanel().removeAll();
        this.getGf().getEnemyTankBirth().setIndex(0);
        this.eagleBase = null;
        this.properties.clear();
        this.allyTanks.clear();
        this.enemyTanks.clear();
        this.bullets.clear();
        this.impassableLandform.clear();
        this.passableLandform.clear();
        this.currentAllyTanks.clear();
        this.currentEnemyTanks.clear();
    }

    /**
     * 设置基地
     */
    public void setEagleBase() {
        this.eagleBase = new EagleBase(this);
    }

    /**
     * 设置盟军坦克
     */
    public void setAllyTank() {
        if (GameData.GAME_MODE == GameMode.SINGLE) {
            //玩家1
            if (GameData.PLAYER1_LIFE >= 0) {
                AllyTank tank = new AllyTank(GameData.PLAYER1_LEVEL, gf.getPlayer1Ctrl(), this);
                tank.setXp(264);
                tank.setYp(648);
                this.allyTanks.add(tank);
                gf.getPlayer1Ctrl().setTank(tank);
                tank.birthing();
            }
        } else if (GameData.GAME_MODE == GameMode.DOUBLE) {
            //玩家1
            if (GameData.PLAYER1_LIFE >= 0) {
                AllyTank tank1 = new AllyTank(GameData.PLAYER1_LEVEL, gf.getPlayer1Ctrl(), this);
                tank1.setXp(264);
                tank1.setYp(648);
                this.allyTanks.add(tank1);
                gf.getPlayer1Ctrl().setTank(tank1);
                tank1.birthing();
            }
            //玩家2
            if (GameData.PLAYER2_LIFE >= 0) {
                AllyTank tank2 = new AllyTank(GameData.PLAYER2_LEVEL, gf.getPlayer2Ctrl(), this);
                tank2.setXp(456);
                tank2.setYp(648);
                this.allyTanks.add(tank2);
                gf.getPlayer2Ctrl().setTank(tank2);
                tank2.birthing();
            }
        }
    }

    /**
     * 设置敌军坦克
     */
    public void setEnemyTank() {
        int commonEnemyTank = stage.getCommonEnemyTank();
        int speedEnemyTank = stage.getSpeedEnemyTank();
        int fireEnemyTank = stage.getFireEnemyTank();
        int heavyEnemyTank = stage.getHeavyEnemyTank();
        int awardEnemyTank = stage.getAwardEnemyTank();
        for (int i = 0; i < commonEnemyTank; i++) {
            EnemyTank tank = new EnemyTank(EnemyTankType.COMMON_TYPE, this);
            tank.setYp(72);
            this.enemyTanks.add(tank);
        }
        for (int i = 0; i < speedEnemyTank; i++) {
            EnemyTank tank = new EnemyTank(EnemyTankType.SPEED_TYPE, this);
            tank.setYp(72);
            this.enemyTanks.add(tank);
        }
        for (int i = 0; i < fireEnemyTank; i++) {
            EnemyTank tank = new EnemyTank(EnemyTankType.FIRE_TYPE, this);
            tank.setYp(72);
            this.enemyTanks.add(tank);
        }
        for (int i = 0; i < heavyEnemyTank; i++) {
            EnemyTank tank = new EnemyTank(EnemyTankType.HEAVY_TYPE, this);
            tank.setYp(72);
            this.enemyTanks.add(tank);
        }
        //设置带道具包的坦克
        for (int i = 0; i < awardEnemyTank; i++) {
            Random random = new Random();
            int index;
            do {
                index = random.nextInt(this.enemyTanks.size());
            } while (((EnemyTank) this.enemyTanks.get(index)).isProperty());
            ((EnemyTank) this.enemyTanks.get(index)).setProperty(true);
            EnemyTank enemyTank = ((EnemyTank) this.enemyTanks.get(index));
            new Thread(enemyTank.getPropertyTank()).start();
        }
        //随机打乱顺序
        Collections.shuffle(this.enemyTanks);
        this.enemyTanks.get(0).setXp(360);
        ((EnemyTank) this.enemyTanks.get(0)).birthing();
    }

    /**
     * 设置地形
     */
    public void setLandform() {
        Landform[][] landforms = stage.getLandform();
        int x = Landform.WIDTH / 2 + 2 * GamePanel.CELL;
        int y = Landform.HEIGHT / 2 + 2 * GamePanel.CELL;
        for (Landform[] landform : landforms) {
            for (Landform value : landform) {
                if (value != null) {
                    value.setXp(x);
                    value.setYp(y);
                    if (value.getLandformType() == LandformType.WOOD
                            || value.getLandformType() == LandformType.GLACIER) {
                        this.passableLandform.add(value);
                    } else {
                        this.impassableLandform.add(value);
                    }
                }
                x += Landform.WIDTH;
            }
            y += Landform.HEIGHT;
            x = Landform.WIDTH / 2 + GamePanel.MARGIN;
        }
    }

    /**
     * 设置道具包
     */
    public void setProperty() {
        this.gs.play(this.gs.getPropertyAppearSound());
        Property property = new Property(PropertyType.randomPropertyType(), this);
        Random random = new Random();
        int x;
        int y;
        Rectangle rectangle;
        do {
            x = GamePanel.CELL * random.nextInt(24) + GamePanel.MARGIN + 3 * GamePanel.CELL / 2;
            y = GamePanel.CELL * random.nextInt(24) + GamePanel.MARGIN + 3 * GamePanel.CELL / 2;
            rectangle = new Rectangle(x - Property.WIDTH / 2, y - Property.HEIGHT / 2, Property.WIDTH, Property.HEIGHT);
        } while (rectangle.intersects(this.eagleBase.getRectangle()));
        property.setXp(x);
        property.setYp(y);
        if (this.properties.size() == 1) {
            this.properties.remove(0);
        }
        this.properties.add(property);
        //道具包的出现时间任务
        Runnable waiting = () -> {
            int time = 15000;
            while (time > 0) {
                property.setAppearances(GameImage.packNone());
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                property.setAppearances(GameImage.packImages(property));
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!GameData.PAUSE) {
                    time -= 500;
                }
            }
            this.properties.remove(property);
        };
        new Thread(waiting).start();
    }

    /**
     * 双人模式借用生命
     *
     * @param allyTank 盟军坦克对象
     */
    public void borrowLife(AllyTank allyTank) {
        if (allyTank.getPlayerCtrl() instanceof Player1Ctrl) {
            GameData.PLAYER1_LIFE++;
            GameData.PLAYER2_LIFE--;
            allyTank = new AllyTank(AllyTankLevel.O, gf.getPlayer1Ctrl(), this);
            allyTank.setXp(264);
            allyTank.setYp(648);
            this.allyTanks.add(allyTank);
            gf.getPlayer1Ctrl().setTank(allyTank);
        } else if (allyTank.getPlayerCtrl() instanceof Player2Ctrl) {
            GameData.PLAYER2_LIFE++;
            GameData.PLAYER1_LIFE--;
            allyTank = new AllyTank(AllyTankLevel.O, gf.getPlayer2Ctrl(), this);
            allyTank.setXp(264);
            allyTank.setYp(648);
            this.allyTanks.add(allyTank);
            gf.getPlayer2Ctrl().setTank(allyTank);
        }
    }

    /**
     * 记录最高分数
     *
     * @param name  玩家姓名
     * @param score 分数
     */
    public void writeRecord(String name, int score) {
        //读取配置文件
        String firstName = Record.readPropertiesValue("firstName");
        String secondName = Record.readPropertiesValue("secondName");
        String thirdName = Record.readPropertiesValue("thirdName");
        int firstScore;
        int secondScore;
        int thirdScore;
        if (!"-".equals(Record.readPropertiesValue("firstScore")) && Record.readPropertiesValue("firstScore") != null) {
            firstScore = Integer.parseInt(Objects.requireNonNull(Record.readPropertiesValue("firstScore")));
        } else {
            firstScore = -1;
        }
        if (!"-".equals(Record.readPropertiesValue("secondScore")) && Record.readPropertiesValue("secondScore") != null) {
            secondScore = Integer.parseInt(Objects.requireNonNull(Record.readPropertiesValue("secondScore")));
        } else {
            secondScore = -1;
        }
        if (!"-".equals(Record.readPropertiesValue("thirdScore")) && Record.readPropertiesValue("thirdScore") != null) {
            thirdScore = Integer.parseInt(Objects.requireNonNull(Record.readPropertiesValue("thirdScore")));
        } else {
            thirdScore = -1;
        }
        if (score > firstScore) {
            thirdName = secondName;
            thirdScore = secondScore;
            secondName = firstName;
            secondScore = firstScore;
            firstName = name;
            firstScore = score;
        } else if (score > secondScore) {
            thirdName = secondName;
            thirdScore = secondScore;
            secondName = name;
            secondScore = score;
        } else if (score > thirdScore) {
            thirdName = name;
            thirdScore = score;
        }
        //写入配置文件
        if ("-".equals(Record.readPropertiesValue("firstName")) || Record.readPropertiesValue("firstName") == null && firstName != null) {
            Record.writeProperties("firstName", firstName);
        }
        if ("-".equals(Record.readPropertiesValue("secondName")) || Record.readPropertiesValue("secondName") == null && secondName != null) {
            Record.writeProperties("secondName", secondName);
        }
        if ("-".equals(Record.readPropertiesValue("thirdName")) || Record.readPropertiesValue("thirdName") == null && thirdName != null) {
            Record.writeProperties("thirdName", thirdName);
        }
        if (firstScore != -1) {
            Record.writeProperties("firstScore", ((Integer) firstScore).toString());
        }
        if (secondScore != -1) {
            Record.writeProperties("secondScore", ((Integer) secondScore).toString());
        }
        if (thirdScore != -1) {
            Record.writeProperties("thirdScore", ((Integer) thirdScore).toString());
        }
    }

    //get和set方法
    public GameFrame getGf() {
        return gf;
    }

    public void setGf(GameFrame gf) {
        this.gf = gf;
    }

    public GameSound getGs() {
        return gs;
    }

    public void setGs(GameSound gs) {
        this.gs = gs;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public int getSteelTime() {
        return steelTime;
    }

    public void setSteelTime(int steelTime) {
        this.steelTime = steelTime;
    }

    public EagleBase getEagleBase() {
        return eagleBase;
    }

    public void setEagleBase(EagleBase eagleBase) {
        this.eagleBase = eagleBase;
    }

    public CopyOnWriteArrayList<Model> getCurrentAllyTanks() {
        return currentAllyTanks;
    }

    public void setCurrentAllyTanks(CopyOnWriteArrayList<Model> currentAllyTanks) {
        this.currentAllyTanks = currentAllyTanks;
    }

    public CopyOnWriteArrayList<Model> getAllyTanks() {
        return allyTanks;
    }

    public void setAllyTanks(CopyOnWriteArrayList<Model> allyTanks) {
        this.allyTanks = allyTanks;
    }

    public CopyOnWriteArrayList<Model> getCurrentEnemyTanks() {
        return currentEnemyTanks;
    }

    public void setCurrentEnemyTanks(CopyOnWriteArrayList<Model> currentEnemyTanks) {
        this.currentEnemyTanks = currentEnemyTanks;
    }

    public CopyOnWriteArrayList<Model> getEnemyTanks() {
        return enemyTanks;
    }

    public void setEnemyTanks(CopyOnWriteArrayList<Model> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public CopyOnWriteArrayList<Landform> getImpassableLandform() {
        return impassableLandform;
    }

    public void setImpassableLandform(CopyOnWriteArrayList<Landform> impassableLandform) {
        this.impassableLandform = impassableLandform;
    }

    public CopyOnWriteArrayList<Landform> getPassableLandform() {
        return passableLandform;
    }

    public void setPassableLandform(CopyOnWriteArrayList<Landform> passableLandform) {
        this.passableLandform = passableLandform;
    }

    public CopyOnWriteArrayList<Bullet> getBullets() {
        return bullets;
    }

    public void setBullets(CopyOnWriteArrayList<Bullet> bullets) {
        this.bullets = bullets;
    }

    public CopyOnWriteArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(CopyOnWriteArrayList<Property> properties) {
        this.properties = properties;
    }

    public Runnable getLoading() {
        return loading;
    }

    public void setLoading(Runnable loading) {
        this.loading = loading;
    }
}
