package com.lostred.bc.controller.thread.animation;

import com.lostred.bc.model.Model;
import com.lostred.bc.util.localFile.GameImage;
import com.lostred.bc.view.GameFrame;

/**
 * 子弹爆炸动画特效线程
 */
public class BulletExplode extends Animation {
    /**
     * 构造子弹爆炸动画特效线程
     *
     * @param gf    游戏窗口
     * @param model 需要播放动画特效的模型对象
     */
    public BulletExplode(GameFrame gf, Model model) {
        super(gf, model);
    }

    /**
     * 播放子弹爆炸动画特效的线程任务
     */
    @Override
    public void run() {
        playFlash();
    }

    /**
     * 播放子弹爆炸动画特效
     */
    protected void playFlash() {
        super.getLabel().setBounds(super.getModel().getXp() - 24, super.getModel().getYp() - 24, 48, 48);
        super.getLabel().setIcon(GameImage.transToIcon(super.getLabel().getWidth(), super.getLabel().getHeight(), GameImage.EXPLODE1));
        super.getGf().getGamePanel().add(super.getLabel());
        try {
            Thread.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.getLabel().setBounds(super.getModel().getXp() - 24, super.getModel().getYp() - 24, 48, 48);
        super.getLabel().setIcon(GameImage.transToIcon(super.getLabel().getWidth(), super.getLabel().getHeight(), GameImage.EXPLODE2));
        try {
            Thread.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.getLabel().setBounds(super.getModel().getXp() - 24, super.getModel().getYp() - 24, 48, 48);
        super.getLabel().setIcon(GameImage.transToIcon(super.getLabel().getWidth(), super.getLabel().getHeight(), GameImage.EXPLODE3));
        try {
            Thread.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.getGf().getGamePanel().remove(super.getLabel());
    }
}
