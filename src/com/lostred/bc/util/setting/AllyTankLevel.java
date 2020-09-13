package com.lostred.bc.util.setting;

/**
 * 盟军坦克等级
 */
public enum AllyTankLevel {
    /**
     * O级
     * I级
     * II级
     * II级
     */
    O(38, 38, 4, 1, 5, false),
    I(38, 46, 5, 1, 6, false),
    II(38, 44, 5, 2, 6, false),
    III(38, 44, 5, 2, 6, true);
    /**
     * 坦克的宽度
     */
    private final int width;
    /**
     * 坦克的高度
     */
    private final int height;
    /**
     * 坦克移动速度
     */
    private final int speed;
    /**
     * 子弹的数量
     */
    private final int bulletLimit;
    /**
     * 子弹移动速度
     */
    private final int bulletSpeed;
    /**
     * 子弹是否增强
     */
    private final boolean enhance;

    /**
     * 构造盟军坦克等级
     *
     * @param width       该等级下坦克的宽度
     * @param height      该等级下坦克的高度
     * @param speed       该等级下坦克的速度
     * @param bulletSpeed 该等级下坦克的子弹速度
     * @param enhance     该等级下坦克的子弹增强
     */
    AllyTankLevel(int width, int height, int speed, int bulletLimit, int bulletSpeed, boolean enhance) {
        this.width = width;
        this.height = height;
        this.speed = speed;
        this.bulletLimit = bulletLimit;
        this.bulletSpeed = bulletSpeed;
        this.enhance = enhance;
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
}
