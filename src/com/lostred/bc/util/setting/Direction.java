package com.lostred.bc.util.setting;

import java.util.Random;

/**
 * 方向
 */
public enum Direction {
    /**
     * 上
     * 下
     * 左
     * 右
     */
    UP, DOWN, LEFT, RIGHT;

    /**
     * 获得一个随机方向
     *
     * @return 返回一个随机方向
     */
    public static Direction randomDirection() {
        Random random = new Random();
        switch (random.nextInt(5)) {
            case 0:
                return UP;
            case 1:
            case 2:
                return DOWN;
            case 3:
                return LEFT;
            case 4:
                return RIGHT;
            default:
                return null;
        }
    }

    /**
     * 返回一个与当前方向垂直的方向
     *
     * @param direction 当前方向
     * @return 与当前方向垂直的方向
     */
    public static Direction crossDirection(Direction direction) {
        Random random = new Random();
        switch (direction) {
            case UP:
            case DOWN:
                if (random.nextInt(2) == 0) {
                    return LEFT;
                }
                return RIGHT;
            case LEFT:
            case RIGHT:
                if (random.nextInt(2) == 0) {
                    return UP;
                }
                return DOWN;
        }
        return null;
    }

    /**
     * 返回一个与当前方向相反的方向
     *
     * @param direction 当前方向
     * @return 与当前方向相反的方向
     */
    public static Direction reverseDirection(Direction direction) {
        switch (direction) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
        }
        return null;
    }
}
