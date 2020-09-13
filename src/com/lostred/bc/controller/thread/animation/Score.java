package com.lostred.bc.controller.thread.animation;

import com.lostred.bc.model.Model;
import com.lostred.bc.util.localFile.GameImage;
import com.lostred.bc.view.GameFrame;

/**
 * 得分动画特效线程
 */
public class Score extends Animation {
    /**
     * 分数
     */
    private final int score;

    /**
     * 构造得分动画特效线程
     *
     * @param gf    游戏窗口
     * @param model 模型对象
     * @param score 分数
     */
    public Score(GameFrame gf, Model model, int score) {
        super(gf, model);
        this.score = score;
    }

    /**
     * 播放得分动画特效的线程任务
     */
    @Override
    public void run() {
        playFlash();
    }

    /**
     * 播放得分动画特效
     */
    protected void playFlash() {
        super.getLabel().setBounds(super.getModel().getXp() - 22, super.getModel().getYp() - 13, 44, 26);
        switch (this.score) {
            case 100:
                super.getLabel().setIcon(GameImage.transToIcon(super.getLabel().getWidth(), super.getLabel().getHeight(), GameImage.SCORE100));
                break;
            case 200:
                super.getLabel().setIcon(GameImage.transToIcon(super.getLabel().getWidth(), super.getLabel().getHeight(), GameImage.SCORE200));
                break;
            case 300:
                super.getLabel().setIcon(GameImage.transToIcon(super.getLabel().getWidth(), super.getLabel().getHeight(), GameImage.SCORE300));
                break;
            case 400:
                super.getLabel().setIcon(GameImage.transToIcon(super.getLabel().getWidth(), super.getLabel().getHeight(), GameImage.SCORE400));
                break;
            case 500:
                super.getLabel().setIcon(GameImage.transToIcon(super.getLabel().getWidth(), super.getLabel().getHeight(), GameImage.SCORE500));
                break;
        }
        super.getGf().getGamePanel().add(super.getLabel());
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.getGf().getGamePanel().remove(super.getLabel());
    }
}
