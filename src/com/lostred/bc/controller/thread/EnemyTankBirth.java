package com.lostred.bc.controller.thread;

import com.lostred.bc.model.tank.EnemyTank;
import com.lostred.bc.util.GameData;
import com.lostred.bc.util.setting.GameMode;
import com.lostred.bc.view.GameFrame;

import java.util.TimerTask;

/**
 * 敌军坦克出生线程
 */
public class EnemyTankBirth extends TimerTask {
    /**
     * 所属的窗口
     */
    private final GameFrame gf;
    /**
     * 敌军坦克出生位置的x坐标
     */
    private int[] xs;
    /**
     * 敌军坦克出生位置索引
     */
    private int index;
    /**
     * 当前敌军坦克的最大数量
     */
    private int currentMax;

    /**
     * 构造敌军坦克出生线程
     *
     * @param gf 游戏窗口
     */
    public EnemyTankBirth(GameFrame gf) {
        this.gf = gf;
        this.xs = new int[]{360, 72, 648};
        this.index = 0;
        if (GameData.GAME_MODE == GameMode.SINGLE) {
            this.currentMax = 3;
        } else {
            this.currentMax = 5;
        }
    }

    /**
     * 敌军坦克出生的线程任务
     */
    @Override
    public void run() {
        if (!GameData.PAUSE && !GameData.GAME_OVER) {
            if (gf.getService().getCurrentEnemyTanks().size() < this.currentMax && gf.getService().getEnemyTanks().size() != 0) {
                appear();
            }
        }
    }

    /**
     * 从等待队列集合中移出一辆敌军坦克
     */
    private void appear() {
        EnemyTank enemyTank = (EnemyTank) gf.getService().getEnemyTanks().get(0);
        enemyTank.setXp(nextBirthPosition());
        if (enemyTank.checkBirthPosition()) {
            enemyTank.birthing();
        }
    }

    /**
     * 获取敌军坦克出生位置的下一个x坐标
     */
    private int nextBirthPosition() {
        int x;
        if (index == 0) {
            x = xs[0];
        } else if (index == 1) {
            x = xs[1];
        } else {
            x = xs[2];
        }
        if (index == 2) {
            index = 0;
        } else {
            index++;
        }
        return x;
    }

    //get和set方法
    public GameFrame getGf() {
        return gf;
    }

    public int[] getXs() {
        return xs;
    }

    public void setXs(int[] xs) {
        this.xs = xs;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCurrentMax() {
        return currentMax;
    }

    public void setCurrentMax(int currentMax) {
        this.currentMax = currentMax;
    }
}
