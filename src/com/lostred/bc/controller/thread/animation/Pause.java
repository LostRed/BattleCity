package com.lostred.bc.controller.thread.animation;

import com.lostred.bc.util.GameData;
import com.lostred.bc.util.localFile.GameImage;
import com.lostred.bc.view.GameFrame;

/**
 * 暂停动画特效
 */
public class Pause extends Animation {

    /**
     * 构造暂停动画特效线程
     *
     * @param gf 游戏窗口
     */
    public Pause(GameFrame gf) {
        super(gf, null);
    }

    /**
     * 播放暂停动画特效的线程任务
     */
    @Override
    public void run() {
        playFlash();
    }

    /**
     * 播放暂停动画特效
     */
    protected void playFlash() {
        super.getLabel().setBounds(302, 350, 116, 20);
        super.getLabel().setIcon(GameImage.transToIcon(super.getLabel().getWidth(), super.getLabel().getHeight(), GameImage.PAUSE));
        while (GameData.PAUSE) {
            super.getGf().getGamePanel().add(super.getLabel());
            super.getGf().getGamePanel().updateUI();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.getGf().getGamePanel().remove(super.getLabel());
            super.getGf().getGamePanel().updateUI();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
