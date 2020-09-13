package com.lostred.bc.util.setting;

import java.util.Random;

/**
 * 道具包类型
 */
public enum PropertyType {
    /**
     * 头盔
     * 五角星
     * 铁锹
     * 手枪
     * 坦克
     * 炸弹
     * 定时器
     */
    HELMET, STAR, SHOVEL, GUN, TANK, BOMB, TIMER;

    /**
     * 获得一个随机道具包类型
     *
     * @return 道具包类型
     */
    public static PropertyType randomPropertyType() {
        Random random = new Random();
        int randomProperty = random.nextInt(100);
        if (randomProperty < 20) {
            return HELMET;
        } else if (randomProperty < 35) {
            return STAR;
        } else if (randomProperty < 55) {
            return SHOVEL;
        } else if (randomProperty < 60) {
            return GUN;
        } else if (randomProperty < 70) {
            return TANK;
        } else if (randomProperty < 85) {
            return BOMB;
        } else {
            return TIMER;
        }
    }
}
