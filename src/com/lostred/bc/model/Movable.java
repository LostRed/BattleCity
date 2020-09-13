package com.lostred.bc.model;

import java.awt.*;

/**
 * 实现模型的移动，提供碰撞检查的方法，以及提供碰撞判断用的矩形
 */
public interface Movable {
    /**
     * 获取运动状态下模型前方位置的一个矩形
     *
     * @param speedRevise 速度修正
     * @return 返回模型前方位置的一个矩形
     */
    Rectangle getMotionRectangle(int speedRevise);

    /**
     * 检查改模型与其他模型集合中每一个模型的碰撞
     *
     * @return 碰撞时true，未碰撞返回false
     */
    boolean collideToAnother();

    /**
     * 检查改模型与边界的碰撞
     *
     * @return 碰撞时true，未碰撞返回false
     */
    boolean collideToBoundary();

    /**
     * 按照模型的方向改变模型的坐标
     *
     * @param speed 模型移动的速度
     */
    void move(int speed);
}
