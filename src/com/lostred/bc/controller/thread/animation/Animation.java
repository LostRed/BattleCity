package com.lostred.bc.controller.thread.animation;

import com.lostred.bc.model.Model;
import com.lostred.bc.view.GameFrame;

import javax.swing.*;

/**
 * 动画特效线程
 */
public abstract class Animation extends Thread {
    /**
     * 游戏窗口
     */
    private GameFrame gf;
    /**
     * 需要捕捉坐标的模型
     */
    private Model model;
    /**
     * 承载图片的标签
     */
    private volatile JLabel label;

    /**
     * 构造动画特效线程
     *
     * @param gf    游戏窗口
     * @param model 需要播放动画特效的模型对象
     */
    public Animation(GameFrame gf, Model model) {
        this.gf = gf;
        this.model = model;
        this.label = new JLabel();
    }

    /**
     * 播放动画特效
     */
    protected abstract void playFlash();

    //get和set方法
    public GameFrame getGf() {
        return gf;
    }

    public void setGf(GameFrame gf) {
        this.gf = gf;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public JLabel getLabel() {
        return label;
    }

    public void setLabel(JLabel label) {
        this.label = label;
    }
}
