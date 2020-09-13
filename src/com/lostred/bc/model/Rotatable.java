package com.lostred.bc.model;

import com.lostred.bc.util.setting.Direction;

/**
 * 实现模型的旋转
 */
public interface Rotatable {
    /**
     * 根据给定的方向改变模型的方向
     *
     * @param direction 给定的方向
     */
    void rotate(Direction direction);
}
