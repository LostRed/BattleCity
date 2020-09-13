package com.lostred.bc.util;

import com.lostred.bc.util.setting.AllyTankLevel;
import com.lostred.bc.util.setting.GameMode;

/**
 * 游戏数据
 */
public class GameData {
    /**
     * 游戏模式
     */
    public static GameMode GAME_MODE = GameMode.SINGLE;
    /**
     * 当前关卡编号
     */
    public static int STAGE_NO = 1;
    /**
     * 玩家1分数
     */
    public static int PLAYER1_SCORE = 0;
    /**
     * 玩家2分数
     */
    public static int PLAYER2_SCORE = 0;
    /**
     * 玩家1备用坦克数量
     */
    public static int PLAYER1_LIFE = 2;
    /**
     * 玩家2备用坦克数量
     */
    public static int PLAYER2_LIFE = 2;
    /**
     * 玩家1坦克的等级
     */
    public static AllyTankLevel PLAYER1_LEVEL = AllyTankLevel.O;
    /**
     * 玩家2坦克的等级
     */
    public static AllyTankLevel PLAYER2_LEVEL = AllyTankLevel.O;
    /**
     * 游戏开始
     */
    public volatile static boolean START = false;
    /**
     * 游戏暂停
     */
    public volatile static boolean PAUSE = false;
    /**
     * 游戏结束
     */
    public volatile static boolean GAME_OVER = true;
    /**
     * 盟军坦克生命值
     */
    public static int ALLIES_TANK_HEALTH = 3;
    /**
     * 敌军坦克生命值补正
     */
    public static int ENEMY_TANK_HEALTH_REVISE = 0;
    /**
     * 显示坦克生命值
     */
    public static boolean SHOW_HEALTH = true;

    /**
     * 初始化游戏数据
     */
    public static void initData() {
        STAGE_NO = 1;
        PLAYER1_SCORE = 0;
        PLAYER2_SCORE = 0;
        PLAYER1_LIFE = 2;
        PLAYER2_LIFE = 2;
        PLAYER1_LEVEL = AllyTankLevel.O;
        PLAYER2_LEVEL = AllyTankLevel.O;
    }

    /**
     * 清空玩家得分
     */
    public static void clearScore() {
        PLAYER1_SCORE = 0;
        PLAYER2_SCORE = 0;
    }
}
