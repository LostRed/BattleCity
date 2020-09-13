package com.lostred.bc.controller.thread.animation;

import com.lostred.bc.model.Model;
import com.lostred.bc.model.tank.EnemyTank;
import com.lostred.bc.util.localFile.GameImage;
import com.lostred.bc.view.GameFrame;

/**
 * 坦克爆炸动画特效线程
 */
public class TankExplode extends BulletExplode {
    /**
     * 播放该动画是否由炸弹道具产生
     */
    private final boolean bombEffect;

    /**
     * 构造坦克爆炸动画特效线程
     *
     * @param gf         游戏窗口
     * @param model      模型对象
     * @param bombEffect 是否因炸弹道具产生
     */
    public TankExplode(GameFrame gf, Model model, boolean bombEffect) {
        super(gf, model);
        this.bombEffect = bombEffect;
    }

    /**
     * 播放爆炸声效和动画特效的线程任务
     */
    @Override
    public void run() {
        //播放爆炸生效
        super.getGf().getService().getGs().play(super.getGf().getService().getGs().getExplodeSound());
        playFlash();
    }

    /**
     * 播放坦克爆炸动画特效
     */
    protected void playFlash() {
        super.playFlash();

        super.getLabel().setBounds(super.getModel().getXp() - 48, super.getModel().getYp() - 48, 96, 96);
        super.getLabel().setIcon(GameImage.transToIcon(super.getLabel().getWidth(), super.getLabel().getHeight(), GameImage.EXPLODE4));
        super.getGf().getGamePanel().add(super.getLabel());
        try {
            Thread.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.getLabel().setBounds(super.getModel().getXp() - 48, super.getModel().getYp() - 48, 96, 96);
        super.getLabel().setIcon(GameImage.transToIcon(super.getLabel().getWidth(), super.getLabel().getHeight(), GameImage.EXPLODE5));
        try {
            Thread.sleep(60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.getGf().getGamePanel().remove(super.getLabel());
        //当该坦克为敌军坦克时新建一个得分动画的线程
        if (super.getModel() instanceof EnemyTank && !this.bombEffect) {
            new Score(super.getGf(), super.getModel(), ((EnemyTank) super.getModel()).getEnemyTankType().getScore()).start();
        }
    }
}
