package com.lostred.bc.controller.thread.animation;

import com.lostred.bc.model.Model;
import com.lostred.bc.model.Property;
import com.lostred.bc.model.tank.AllyTank;
import com.lostred.bc.model.tank.EnemyTank;
import com.lostred.bc.util.localFile.GameImage;
import com.lostred.bc.util.setting.PropertyType;
import com.lostred.bc.view.GameFrame;
import com.lostred.bc.view.panel.GamePanel;

/**
 * 坦克出生动画特效
 */
public class TankAppear extends Animation {
    /**
     * 占位模型
     */
    private volatile Model seat;

    /**
     * 构造坦克出生
     *
     * @param gf    游戏窗口
     * @param model 需要播放动画特效的模型对象
     * @param seat  占用出生点的坦克对象
     */
    public TankAppear(GameFrame gf, Model model, Model seat) {
        super(gf, model);
        this.seat = seat;
    }

    /**
     * 播放坦克出生动画特效的线程任务
     */
    @Override
    public void run() {
        playFlash();
        //动画结束后将坦克添加至当前集合
        if (super.getModel() instanceof EnemyTank) {
            if (!super.getGf().getService().getCurrentEnemyTanks().contains(super.getModel())) {
                super.getGf().getService().getCurrentEnemyTanks().add(super.getModel());
                super.getGf().getService().getEnemyTanks().remove(super.getModel());
            }
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.getGf().getService().getCurrentEnemyTanks().remove(seat);
        } else if (super.getModel() instanceof AllyTank) {
            if (!super.getGf().getService().getCurrentAllyTanks().contains(super.getModel())) {
                super.getGf().getService().getCurrentAllyTanks().add(super.getModel());
                super.getGf().getService().getAllyTanks().remove(super.getModel());
                new Property(PropertyType.HELMET, super.getGf().getService()).getShield((AllyTank) super.getModel(), 2000);
            }
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.getGf().getService().getCurrentAllyTanks().remove(seat);
        }
    }

    /**
     * 播放坦克出生动画特效
     */
    protected void playFlash() {
        super.getGf().getGamePanel().add(super.getLabel());
        for (int i = 0; i < 3; i++) {
            super.getLabel().setBounds(super.getModel().getXp() - GamePanel.CELL, super.getModel().getYp() - GamePanel.CELL, 2 * GamePanel.CELL, 2 * GamePanel.CELL);
            super.getLabel().setIcon(GameImage.transToIcon(super.getLabel().getWidth(), super.getLabel().getHeight(), GameImage.BIRTHING4));
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.getLabel().setBounds(super.getModel().getXp() - GamePanel.CELL, super.getModel().getYp() - GamePanel.CELL, 2 * GamePanel.CELL, 2 * GamePanel.CELL);
            super.getLabel().setIcon(GameImage.transToIcon(super.getLabel().getWidth(), super.getLabel().getHeight(), GameImage.BIRTHING3));
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.getLabel().setBounds(super.getModel().getXp() - GamePanel.CELL, super.getModel().getYp() - GamePanel.CELL, 2 * GamePanel.CELL, 2 * GamePanel.CELL);
            super.getLabel().setIcon(GameImage.transToIcon(super.getLabel().getWidth(), super.getLabel().getHeight(), GameImage.BIRTHING2));
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.getLabel().setBounds(super.getModel().getXp() - GamePanel.CELL, super.getModel().getYp() - GamePanel.CELL, 2 * GamePanel.CELL, 2 * GamePanel.CELL);
            super.getLabel().setIcon(GameImage.transToIcon(super.getLabel().getWidth(), super.getLabel().getHeight(), GameImage.BIRTHING1));
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.getLabel().setBounds(super.getModel().getXp() - 22, super.getModel().getYp() - 22, 44, 44);
            super.getLabel().setIcon(GameImage.transToIcon(super.getLabel().getWidth(), super.getLabel().getHeight(), GameImage.BIRTHING2));
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            super.getLabel().setBounds(super.getModel().getXp() - 22, super.getModel().getYp() - 22, 44, 44);
            super.getLabel().setIcon(GameImage.transToIcon(super.getLabel().getWidth(), super.getLabel().getHeight(), GameImage.BIRTHING3));
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        super.getGf().getGamePanel().remove(super.getLabel());
    }
}
