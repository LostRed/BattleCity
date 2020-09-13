package com.lostred.bc.model;

import com.lostred.bc.service.Service;

import java.awt.*;
import java.util.ArrayList;

/**
 * 游戏模型：包括坦克、子弹、地形、道具包和基地
 */
public abstract class Model {
    /**
     * 模型所属的后台控制
     */
    private Service service;
    /**
     * 模型外观集合
     */
    private ArrayList<Image> appearances;
    /**
     * 模型中心横坐标
     */
    private volatile int xp;
    /**
     * 模型中心纵坐标
     */
    private volatile int yp;
    /**
     * 模型宽度
     */
    private int width;
    /**
     * 模型高度
     */
    private int height;

    /**
     * 无参构造
     */
    public Model() {
    }

    /**
     * 获取模型的所在位置的一个矩形
     *
     * @return 返回模型的所在位置的一个矩形
     */
    public Rectangle getRectangle() {
        return new Rectangle(this.xp - this.width / 2, this.yp - this.height / 2, this.width, this.height);
    }

    //get和set方法
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ArrayList<Image> getAppearances() {
        return appearances;
    }

    public void setAppearances(ArrayList<Image> appearances) {
        this.appearances = appearances;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public int getYp() {
        return yp;
    }

    public void setYp(int yp) {
        this.yp = yp;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
