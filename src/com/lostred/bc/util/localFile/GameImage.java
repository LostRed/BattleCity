package com.lostred.bc.util.localFile;

import com.lostred.bc.controller.listener.Player1Ctrl;
import com.lostred.bc.controller.listener.Player2Ctrl;
import com.lostred.bc.model.Property;
import com.lostred.bc.model.tank.AllyTank;
import com.lostred.bc.model.tank.EnemyTank;
import com.lostred.bc.util.setting.LandformType;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * 游戏图片
 */
public class GameImage {
    /**
     * 将存放坦克图片集合中的上方向图片的索引定义为0
     */
    public static final int UP = 0;
    /**
     * 将存放坦克图片集合中的下方向图片的索引定义为1
     */
    public static final int DOWN = 1;
    /**
     * 将存放坦克图片集合中的左方向图片的索引定义为2
     */
    public static final int LEFT = 2;
    /**
     * 将存放坦克图片集合中的右方向图片的索引定义为3
     */
    public static final int RIGHT = 3;
    //主题面板背景
    public static final Image THEME_BACKGROUND = Toolkit.getDefaultToolkit().getImage("image/Theme_Background.png");
    //敌军坦克标识
    public static final Image ENEMY_FLAG = Toolkit.getDefaultToolkit().getImage("image/Enemy_Flag.png");
    //信息面板背景
    public static final Image INFO_BACKGROUND = Toolkit.getDefaultToolkit().getImage("image/Info_Background.png");
    //子弹
    public static final Image BULLET_UP = Toolkit.getDefaultToolkit().getImage("image/Bullet_Up.png");
    public static final Image BULLET_DOWN = Toolkit.getDefaultToolkit().getImage("image/Bullet_Down.png");
    public static final Image BULLET_LEFT = Toolkit.getDefaultToolkit().getImage("image/Bullet_Left.png");
    public static final Image BULLET_RIGHT = Toolkit.getDefaultToolkit().getImage("image/Bullet_Right.png");
    //盟军坦克
    public static final Image ALLY_TANK1_O_UP = Toolkit.getDefaultToolkit().getImage("image/AllyTank1_O_Up.png");
    public static final Image ALLY_TANK1_O_DOWN = Toolkit.getDefaultToolkit().getImage("image/AllyTank1_O_Down.png");
    public static final Image ALLY_TANK1_O_LEFT = Toolkit.getDefaultToolkit().getImage("image/AllyTank1_O_Left.png");
    public static final Image ALLY_TANK1_O_RIGHT = Toolkit.getDefaultToolkit().getImage("image/AllyTank1_O_Right.png");
    public static final Image ALLY_TANK1_I_UP = Toolkit.getDefaultToolkit().getImage("image/AllyTank1_I_Up.png");
    public static final Image ALLY_TANK1_I_DOWN = Toolkit.getDefaultToolkit().getImage("image/AllyTank1_I_Down.png");
    public static final Image ALLY_TANK1_I_LEFT = Toolkit.getDefaultToolkit().getImage("image/AllyTank1_I_Left.png");
    public static final Image ALLY_TANK1_I_RIGHT = Toolkit.getDefaultToolkit().getImage("image/AllyTank1_I_Right.png");
    public static final Image ALLY_TANK1_II_UP = Toolkit.getDefaultToolkit().getImage("image/AllyTank1_II_Up.png");
    public static final Image ALLY_TANK1_II_DOWN = Toolkit.getDefaultToolkit().getImage("image/AllyTank1_II_Down.png");
    public static final Image ALLY_TANK1_II_LEFT = Toolkit.getDefaultToolkit().getImage("image/AllyTank1_II_Left.png");
    public static final Image ALLY_TANK1_II_RIGHT = Toolkit.getDefaultToolkit().getImage("image/AllyTank1_II_Right.png");
    public static final Image ALLY_TANK1_III_UP = Toolkit.getDefaultToolkit().getImage("image/AllyTank1_III_Up.png");
    public static final Image ALLY_TANK1_III_DOWN = Toolkit.getDefaultToolkit().getImage("image/AllyTank1_III_Down.png");
    public static final Image ALLY_TANK1_III_LEFT = Toolkit.getDefaultToolkit().getImage("image/AllyTank1_III_Left.png");
    public static final Image ALLY_TANK1_III_RIGHT = Toolkit.getDefaultToolkit().getImage("image/AllyTank1_III_Right.png");
    public static final Image ALLY_TANK2_O_UP = Toolkit.getDefaultToolkit().getImage("image/AllyTank2_O_Up.png");
    public static final Image ALLY_TANK2_O_DOWN = Toolkit.getDefaultToolkit().getImage("image/AllyTank2_O_Down.png");
    public static final Image ALLY_TANK2_O_LEFT = Toolkit.getDefaultToolkit().getImage("image/AllyTank2_O_Left.png");
    public static final Image ALLY_TANK2_O_RIGHT = Toolkit.getDefaultToolkit().getImage("image/AllyTank2_O_Right.png");
    public static final Image ALLY_TANK2_I_UP = Toolkit.getDefaultToolkit().getImage("image/AllyTank2_I_Up.png");
    public static final Image ALLY_TANK2_I_DOWN = Toolkit.getDefaultToolkit().getImage("image/AllyTank2_I_Down.png");
    public static final Image ALLY_TANK2_I_LEFT = Toolkit.getDefaultToolkit().getImage("image/AllyTank2_I_Left.png");
    public static final Image ALLY_TANK2_I_RIGHT = Toolkit.getDefaultToolkit().getImage("image/AllyTank2_I_Right.png");
    public static final Image ALLY_TANK2_II_UP = Toolkit.getDefaultToolkit().getImage("image/AllyTank2_II_Up.png");
    public static final Image ALLY_TANK2_II_DOWN = Toolkit.getDefaultToolkit().getImage("image/AllyTank2_II_Down.png");
    public static final Image ALLY_TANK2_II_LEFT = Toolkit.getDefaultToolkit().getImage("image/AllyTank2_II_Left.png");
    public static final Image ALLY_TANK2_II_RIGHT = Toolkit.getDefaultToolkit().getImage("image/AllyTank2_II_Right.png");
    public static final Image ALLY_TANK2_III_UP = Toolkit.getDefaultToolkit().getImage("image/AllyTank2_III_Up.png");
    public static final Image ALLY_TANK2_III_DOWN = Toolkit.getDefaultToolkit().getImage("image/AllyTank2_III_Down.png");
    public static final Image ALLY_TANK2_III_LEFT = Toolkit.getDefaultToolkit().getImage("image/AllyTank2_III_Left.png");
    public static final Image ALLY_TANK2_III_RIGHT = Toolkit.getDefaultToolkit().getImage("image/AllyTank2_III_Right.png");
    //敌军坦克
    public static final Image ENEMY_TANK_COMMON_UP = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Common_Up.png");
    public static final Image ENEMY_TANK_COMMON_DOWN = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Common_Down.png");
    public static final Image ENEMY_TANK_COMMON_LEFT = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Common_Left.png");
    public static final Image ENEMY_TANK_COMMON_RIGHT = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Common_Right.png");
    public static final Image ENEMY_TANK_SPEED_UP = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Speed_Up.png");
    public static final Image ENEMY_TANK_SPEED_DOWN = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Speed_Down.png");
    public static final Image ENEMY_TANK_SPEED_LEFT = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Speed_Left.png");
    public static final Image ENEMY_TANK_SPEED_RIGHT = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Speed_Right.png");
    public static final Image ENEMY_TANK_FIRE_UP = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Fire_Up.png");
    public static final Image ENEMY_TANK_FIRE_DOWN = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Fire_Down.png");
    public static final Image ENEMY_TANK_FIRE_LEFT = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Fire_Left.png");
    public static final Image ENEMY_TANK_FIRE_RIGHT = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Fire_Right.png");
    public static final Image ENEMY_TANK_HEAVY_UP = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Heavy_Up.png");
    public static final Image ENEMY_TANK_HEAVY_DOWN = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Heavy_Down.png");
    public static final Image ENEMY_TANK_HEAVY_LEFT = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Heavy_Left.png");
    public static final Image ENEMY_TANK_HEAVY_RIGHT = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Heavy_Right.png");
    public static final Image ENEMY_TANK_COMMON_EX_UP = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Common_EX_Up.png");
    public static final Image ENEMY_TANK_COMMON_EX_DOWN = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Common_EX_Down.png");
    public static final Image ENEMY_TANK_COMMON_EX_LEFT = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Common_EX_Left.png");
    public static final Image ENEMY_TANK_COMMON_EX_RIGHT = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Common_EX_Right.png");
    public static final Image ENEMY_TANK_SPEED_EX_UP = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Speed_EX_Up.png");
    public static final Image ENEMY_TANK_SPEED_EX_DOWN = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Speed_EX_Down.png");
    public static final Image ENEMY_TANK_SPEED_EX_LEFT = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Speed_EX_Left.png");
    public static final Image ENEMY_TANK_SPEED_EX_RIGHT = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Speed_EX_Right.png");
    public static final Image ENEMY_TANK_FIRE_EX_UP = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Fire_EX_Up.png");
    public static final Image ENEMY_TANK_FIRE_EX_DOWN = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Fire_EX_Down.png");
    public static final Image ENEMY_TANK_FIRE_EX_LEFT = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Fire_EX_Left.png");
    public static final Image ENEMY_TANK_FIRE_EX_RIGHT = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Fire_EX_Right.png");
    public static final Image ENEMY_TANK_HEAVY_EX_UP = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Heavy_EX_Up.png");
    public static final Image ENEMY_TANK_HEAVY_EX_DOWN = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Heavy_EX_Down.png");
    public static final Image ENEMY_TANK_HEAVY_EX_LEFT = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Heavy_EX_Left.png");
    public static final Image ENEMY_TANK_HEAVY_EX_RIGHT = Toolkit.getDefaultToolkit().getImage("image/EnemyTank_Heavy_EX_Right.png");
    //地形
    public static final Image WOOD = Toolkit.getDefaultToolkit().getImage("image/Wood.png");
    public static final Image RIVER1 = Toolkit.getDefaultToolkit().getImage("image/River1.png");
    public static final Image RIVER2 = Toolkit.getDefaultToolkit().getImage("image/River2.png");
    public static final Image RIVER3 = Toolkit.getDefaultToolkit().getImage("image/River3.png");
    public static final Image BRICK = Toolkit.getDefaultToolkit().getImage("image/Brick.png");
    public static final Image STEEL = Toolkit.getDefaultToolkit().getImage("image/Steel.png");
    public static final Image GLACIER = Toolkit.getDefaultToolkit().getImage("image/Glacier.png");
    //基地
    public static final Image EAGLE = Toolkit.getDefaultToolkit().getImage("image/Eagle.png");
    public static final Image WHITE_FLAG = Toolkit.getDefaultToolkit().getImage("image/White_Flag.png");
    //爆炸
    public static final Image EXPLODE1 = Toolkit.getDefaultToolkit().getImage("image/Explode1.png");
    public static final Image EXPLODE2 = Toolkit.getDefaultToolkit().getImage("image/Explode2.png");
    public static final Image EXPLODE3 = Toolkit.getDefaultToolkit().getImage("image/Explode3.png");
    public static final Image EXPLODE4 = Toolkit.getDefaultToolkit().getImage("image/Explode4.png");
    public static final Image EXPLODE5 = Toolkit.getDefaultToolkit().getImage("image/Explode5.png");
    //坦克出生
    public static final Image BIRTHING1 = Toolkit.getDefaultToolkit().getImage("image/Birthing1.png");
    public static final Image BIRTHING2 = Toolkit.getDefaultToolkit().getImage("image/Birthing2.png");
    public static final Image BIRTHING3 = Toolkit.getDefaultToolkit().getImage("image/Birthing3.png");
    public static final Image BIRTHING4 = Toolkit.getDefaultToolkit().getImage("image/Birthing4.png");
    //道具包
    public static final Image HELMET = Toolkit.getDefaultToolkit().getImage("image/Helmet.png");
    public static final Image STAR = Toolkit.getDefaultToolkit().getImage("image/Star.png");
    public static final Image SHOVEL = Toolkit.getDefaultToolkit().getImage("image/Shovel.png");
    public static final Image GUN = Toolkit.getDefaultToolkit().getImage("image/Gun.png");
    public static final Image TANK = Toolkit.getDefaultToolkit().getImage("image/Tank.png");
    public static final Image BOMB = Toolkit.getDefaultToolkit().getImage("image/Bomb.png");
    public static final Image TIMER = Toolkit.getDefaultToolkit().getImage("image/Timer.png");
    //透明图片
    public static final Image NONE = Toolkit.getDefaultToolkit().getImage("image/None.png");
    //护盾
    public static final Image SHIELD1 = Toolkit.getDefaultToolkit().getImage("image/Shield1.png");
    public static final Image SHIELD2 = Toolkit.getDefaultToolkit().getImage("image/Shield2.png");
    //分数
    public static final Image SCORE100 = Toolkit.getDefaultToolkit().getImage("image/100.png");
    public static final Image SCORE200 = Toolkit.getDefaultToolkit().getImage("image/200.png");
    public static final Image SCORE300 = Toolkit.getDefaultToolkit().getImage("image/300.png");
    public static final Image SCORE400 = Toolkit.getDefaultToolkit().getImage("image/400.png");
    public static final Image SCORE500 = Toolkit.getDefaultToolkit().getImage("image/500.png");
    //关卡
    public static final Image STAGE = Toolkit.getDefaultToolkit().getImage("image/Stage.png");
    //暂停
    public static final Image PAUSE = Toolkit.getDefaultToolkit().getImage("image/Pause.png");
    //游戏结束
    public static final Image GAME_OVER = Toolkit.getDefaultToolkit().getImage("image/GameOver.png");
    //数字
    public static final Image ZERO = Toolkit.getDefaultToolkit().getImage("image/0.png");
    public static final Image ONE = Toolkit.getDefaultToolkit().getImage("image/1.png");
    public static final Image TWO = Toolkit.getDefaultToolkit().getImage("image/2.png");
    public static final Image THREE = Toolkit.getDefaultToolkit().getImage("image/3.png");
    public static final Image FOUR = Toolkit.getDefaultToolkit().getImage("image/4.png");
    public static final Image FIVE = Toolkit.getDefaultToolkit().getImage("image/5.png");
    public static final Image SIX = Toolkit.getDefaultToolkit().getImage("image/6.png");
    public static final Image SEVEN = Toolkit.getDefaultToolkit().getImage("image/7.png");
    public static final Image EIGHT = Toolkit.getDefaultToolkit().getImage("image/8.png");
    public static final Image NINE = Toolkit.getDefaultToolkit().getImage("image/9.png");
    //信息区遮挡
    public static final Image INFO_SHELTER = Toolkit.getDefaultToolkit().getImage("image/Info_Shelter.png");
    //游戏结束
    public static final Image END = Toolkit.getDefaultToolkit().getImage("image/End.png");

    /**
     * 返回盟军坦克的外观图片集合
     *
     * @param tank 盟军坦克对象
     * @return 该对象的外观图片集合
     */
    public static ArrayList<Image> packImages(AllyTank tank) {
        ArrayList<Image> images = new ArrayList<>();
        switch (tank.getAllyTankLevel()) {
            case O:
                if (tank.getPlayerCtrl() instanceof Player1Ctrl) {
                    images.add(ALLY_TANK1_O_UP);
                    images.add(ALLY_TANK1_O_DOWN);
                    images.add(ALLY_TANK1_O_LEFT);
                    images.add(ALLY_TANK1_O_RIGHT);
                } else if (tank.getPlayerCtrl() instanceof Player2Ctrl) {
                    images.add(ALLY_TANK2_O_UP);
                    images.add(ALLY_TANK2_O_DOWN);
                    images.add(ALLY_TANK2_O_LEFT);
                    images.add(ALLY_TANK2_O_RIGHT);
                }
                break;
            case I:
                if (tank.getPlayerCtrl() instanceof Player1Ctrl) {
                    images.add(ALLY_TANK1_I_UP);
                    images.add(ALLY_TANK1_I_DOWN);
                    images.add(ALLY_TANK1_I_LEFT);
                    images.add(ALLY_TANK1_I_RIGHT);
                } else if (tank.getPlayerCtrl() instanceof Player2Ctrl) {
                    images.add(ALLY_TANK2_I_UP);
                    images.add(ALLY_TANK2_I_DOWN);
                    images.add(ALLY_TANK2_I_LEFT);
                    images.add(ALLY_TANK2_I_RIGHT);
                }
                break;
            case II:
                if (tank.getPlayerCtrl() instanceof Player1Ctrl) {
                    images.add(ALLY_TANK1_II_UP);
                    images.add(ALLY_TANK1_II_DOWN);
                    images.add(ALLY_TANK1_II_LEFT);
                    images.add(ALLY_TANK1_II_RIGHT);
                } else if (tank.getPlayerCtrl() instanceof Player2Ctrl) {
                    images.add(ALLY_TANK2_II_UP);
                    images.add(ALLY_TANK2_II_DOWN);
                    images.add(ALLY_TANK2_II_LEFT);
                    images.add(ALLY_TANK2_II_RIGHT);
                }
                break;
            case III:
                if (tank.getPlayerCtrl() instanceof Player1Ctrl) {
                    images.add(ALLY_TANK1_III_UP);
                    images.add(ALLY_TANK1_III_DOWN);
                    images.add(ALLY_TANK1_III_LEFT);
                    images.add(ALLY_TANK1_III_RIGHT);
                } else if (tank.getPlayerCtrl() instanceof Player2Ctrl) {
                    images.add(ALLY_TANK2_III_UP);
                    images.add(ALLY_TANK2_III_DOWN);
                    images.add(ALLY_TANK2_III_LEFT);
                    images.add(ALLY_TANK2_III_RIGHT);
                }
                break;
        }
        return images;
    }

    /**
     * 返回敌军坦克的外观图片集合
     *
     * @param tank     敌军坦克对象
     * @param property 是否有道具包
     * @return 该对象的外观图片集合
     */
    public static ArrayList<Image> packImages(EnemyTank tank, boolean property) {
        ArrayList<Image> images = new ArrayList<>();
        switch (tank.getEnemyTankType()) {
            case COMMON_TYPE:
                if (!property) {
                    images.add(ENEMY_TANK_COMMON_UP);
                    images.add(ENEMY_TANK_COMMON_DOWN);
                    images.add(ENEMY_TANK_COMMON_LEFT);
                    images.add(ENEMY_TANK_COMMON_RIGHT);
                } else {
                    images.add(ENEMY_TANK_COMMON_EX_UP);
                    images.add(ENEMY_TANK_COMMON_EX_DOWN);
                    images.add(ENEMY_TANK_COMMON_EX_LEFT);
                    images.add(ENEMY_TANK_COMMON_EX_RIGHT);
                }
                break;
            case SPEED_TYPE:
                if (!property) {
                    images.add(ENEMY_TANK_SPEED_UP);
                    images.add(ENEMY_TANK_SPEED_DOWN);
                    images.add(ENEMY_TANK_SPEED_LEFT);
                    images.add(ENEMY_TANK_SPEED_RIGHT);
                } else {
                    images.add(ENEMY_TANK_SPEED_EX_UP);
                    images.add(ENEMY_TANK_SPEED_EX_DOWN);
                    images.add(ENEMY_TANK_SPEED_EX_LEFT);
                    images.add(ENEMY_TANK_SPEED_EX_RIGHT);
                }
                break;
            case FIRE_TYPE:
                if (!property) {
                    images.add(ENEMY_TANK_FIRE_UP);
                    images.add(ENEMY_TANK_FIRE_DOWN);
                    images.add(ENEMY_TANK_FIRE_LEFT);
                    images.add(ENEMY_TANK_FIRE_RIGHT);
                } else {
                    images.add(ENEMY_TANK_FIRE_EX_UP);
                    images.add(ENEMY_TANK_FIRE_EX_DOWN);
                    images.add(ENEMY_TANK_FIRE_EX_LEFT);
                    images.add(ENEMY_TANK_FIRE_EX_RIGHT);
                }
                break;
            case HEAVY_TYPE:
                if (!property) {
                    images.add(ENEMY_TANK_HEAVY_UP);
                    images.add(ENEMY_TANK_HEAVY_DOWN);
                    images.add(ENEMY_TANK_HEAVY_LEFT);
                    images.add(ENEMY_TANK_HEAVY_RIGHT);
                } else {
                    images.add(ENEMY_TANK_HEAVY_EX_UP);
                    images.add(ENEMY_TANK_HEAVY_EX_DOWN);
                    images.add(ENEMY_TANK_HEAVY_EX_LEFT);
                    images.add(ENEMY_TANK_HEAVY_EX_RIGHT);
                }
                break;
        }
        return images;
    }

    /**
     * 返回地形的外观图片集合
     *
     * @param landformType 地形类型
     * @return 该对象的外观图片集合
     */
    public static ArrayList<Image> packImages(LandformType landformType) {
        ArrayList<Image> images = new ArrayList<>();
        switch (landformType) {
            case WOOD:
                images.add(WOOD);
                break;
            case RIVER:
                images.add(RIVER1);
                images.add(RIVER2);
                images.add(RIVER3);
                break;
            case BRICK:
                images.add(BRICK);
                break;
            case STEEL:
                images.add(STEEL);
                break;
            case GLACIER:
                images.add(GLACIER);
                break;
        }
        return images;
    }

    /**
     * 返回子弹的外观图片集合
     *
     * @return 该对象的外观图片集合
     */
    public static ArrayList<Image> packBulletImages() {
        ArrayList<Image> images = new ArrayList<>();
        images.add(BULLET_UP);
        images.add(BULLET_DOWN);
        images.add(BULLET_LEFT);
        images.add(BULLET_RIGHT);
        return images;
    }

    /**
     * 返回基地的外观图片集合
     *
     * @return 该对象的外观图片集合
     */
    public static ArrayList<Image> packEagleBaseImages() {
        ArrayList<Image> images = new ArrayList<>();
        images.add(EAGLE);
        images.add(WHITE_FLAG);
        return images;
    }

    /**
     * 返回道具包的外观图片集合
     *
     * @param property 道具包对象
     * @return 该对象的外观图片集合
     */
    public static ArrayList<Image> packImages(Property property) {
        ArrayList<Image> images = new ArrayList<>();
        switch (property.getPropertyType()) {
            case HELMET:
                images.add(HELMET);
                break;
            case STAR:
                images.add(STAR);
                break;
            case SHOVEL:
                images.add(SHOVEL);
                break;
            case GUN:
                images.add(GUN);
                break;
            case TANK:
                images.add(TANK);
                break;
            case BOMB:
                images.add(BOMB);
                break;
            case TIMER:
                images.add(TIMER);
                break;
        }
        return images;
    }

    /**
     * 返回一张透明图片
     *
     * @return 一张透明图片
     */
    public static ArrayList<Image> packNone() {
        ArrayList<Image> images = new ArrayList<>();
        images.add(NONE);
        images.add(NONE);
        images.add(NONE);
        images.add(NONE);
        return images;
    }

    /**
     * 将Image类转换成自定大小的Icon类
     *
     * @param width  宽度
     * @param height 高度
     * @param image  要转换的Image对象
     * @return 返回Icon类对象
     */
    public static Icon transToIcon(int width, int height, Image image) {
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        return new ImageIcon(newImage);
    }
}
