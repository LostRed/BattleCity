package com.lostred.bc.util.setting;

/**
 * 敌军坦克类型
 */
public enum EnemyTankType {
    /**
     * 普通型
     * 速度型
     * 火力型
     * 重装型
     */
    COMMON_TYPE(38, 44, 3, 1, 5, false, 1, 100),
    SPEED_TYPE(38, 44, 5, 1, 5, false, 1, 200),
    FIRE_TYPE(38, 44, 3, 2, 6, false, 1, 300),
    HEAVY_TYPE(38, 44, 2, 1, 5, false, 4, 400);
    /**
     * 坦克的宽度
     */
    private final int width;
    /**
     * 坦克的高度
     */
    private final int height;
    /**
     * 坦克移动的速度
     */
    private final int speed;
    /**
     * 子弹的数量
     */
    private final int bulletLimit;
    /**
     * 子弹移动的速度
     */
    private final int bulletSpeed;
    /**
     * 子弹是否增强
     */
    private final boolean enhance;
    /**
     * 生命值
     */
    private final int health;
    /**
     * 分数
     */
    private final int score;

    /**
     * 构造敌军坦克类型
     *
     * @param width       该类型坦克的宽度
     * @param height      该类型坦克的高度
     * @param speed       该类型坦克的速度
     * @param bulletLimit 该类型坦克的子弹上限
     * @param bulletSpeed 该类型坦克的子弹速度
     * @param enhance     该类型坦克的子弹增强
     * @param health      该类型坦克的血量
     * @param score       该类型坦克的分数
     */
    EnemyTankType(int width, int height, int speed, int bulletLimit, int bulletSpeed, boolean enhance, int health, int score) {
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.bulletLimit = bulletLimit;
        this.bulletSpeed = bulletSpeed;
        this.enhance = enhance;
        this.health = health;
        this.score = score;
    }

    //get和set方法
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getSpeed() {
        return speed;
    }

    public int getBulletLimit() {
        return bulletLimit;
    }

    public int getBulletSpeed() {
        return bulletSpeed;
    }

    public boolean isEnhance() {
        return enhance;
    }

    public int getHealth() {
        return health;
    }

    public int getScore() {
        return score;
    }
}
