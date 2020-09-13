package com.lostred.bc.controller.thread.animation;

import com.lostred.bc.model.Model;
import com.lostred.bc.model.tank.AllyTank;
import com.lostred.bc.util.GameData;
import com.lostred.bc.util.localFile.GameImage;
import com.lostred.bc.view.GameFrame;
import com.lostred.bc.view.panel.GamePanel;

/**
 * 护盾动画特效线程
 */
public class Shield extends Animation {
    /**
     * 护盾动画循环次数
     */
    private int time;

    /**
     * 构造护盾动画特效线程
     *
     * @param gf    游戏窗口
     * @param model 模型对象
     * @param time  动画循环次数
     */
    public Shield(GameFrame gf, Model model, int time) {
        super(gf, model);
        this.time = time;
    }

    /**
     * 播放护盾动画特效的线程任务
     */
    @Override
    public void run() {
        playFlash();
        //动画结束后取消盟军坦克的护盾
        ((AllyTank) super.getModel()).setShield(null);
    }

    /**
     * 播放护盾动画特效
     */
    protected void playFlash() {
        super.getGf().getGamePanel().add(super.getLabel());
        while (this.time > 0) {
            super.getLabel().setBounds(super.getModel().getXp() - GamePanel.CELL, super.getModel().getYp() - GamePanel.CELL, 2 * GamePanel.CELL, 2 * GamePanel.CELL);
            super.getLabel().setIcon(GameImage.transToIcon(super.getLabel().getWidth(), super.getLabel().getHeight(), GameImage.SHIELD1));
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.getLabel().setBounds(super.getModel().getXp() - GamePanel.CELL, super.getModel().getYp() - GamePanel.CELL, 2 * GamePanel.CELL, 2 * GamePanel.CELL);
            super.getLabel().setIcon(GameImage.transToIcon(super.getLabel().getWidth(), super.getLabel().getHeight(), GameImage.SHIELD2));
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (!GameData.PAUSE) {
                this.time -= 50;
            }
        }
        super.getGf().getGamePanel().remove(super.getLabel());
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
