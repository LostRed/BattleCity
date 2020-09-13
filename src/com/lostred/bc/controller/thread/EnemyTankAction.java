package com.lostred.bc.controller.thread;

import com.lostred.bc.model.Model;
import com.lostred.bc.model.Seat;
import com.lostred.bc.model.tank.EnemyTank;
import com.lostred.bc.util.GameData;
import com.lostred.bc.util.setting.Direction;
import com.lostred.bc.view.GameFrame;

import java.util.Random;
import java.util.TimerTask;

/**
 * 敌军坦克行动线程
 */
public class EnemyTankAction extends TimerTask {
    /**
     * 所属的窗口
     */
    private final GameFrame gf;
    /**
     * 行动指令
     */
    private int action;
    /**
     * 停止敌军坦克行动的时间
     */
    private int stopTime;
    /**
     * 恢复可以行动的时间任务
     */
    private Runnable waiting;
    /**
     * 随机数
     */
    private Random random;

    /**
     * 构造敌军坦克行动线程
     *
     * @param gf 游戏窗口
     */
    public EnemyTankAction(GameFrame gf) {
        this.gf = gf;
        this.stopTime = 0;
        this.random = new Random();
        this.waiting = () -> {
            while (this.stopTime > 0) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!GameData.PAUSE) {
                    this.stopTime -= 500;
                }
            }
        };
    }

    /**
     * 敌军坦克行动的线程任务
     */
    @Override
    public void run() {
        if (!GameData.PAUSE && this.stopTime <= 0) {
            for (Model model : gf.getService().getCurrentEnemyTanks()) {
                //跳过出生的坦克
                if (model instanceof Seat) {
                    continue;
                }
                EnemyTank enemyTank = (EnemyTank) model;
                //检查是否位于冰川地形
                enemyTank.setOnGlacier(enemyTank.checkGlacier());
                this.action = random.nextInt(100);
                if (action < 10) {
                    if (!enemyTank.collideToBoundary() && !enemyTank.collideToAnother()) {
                        enemyTank.move(enemyTank.getEnemyTankType().getSpeed());
                    }
                    enemyTank.fire();
                } else if (action < 95) {
                    if (!enemyTank.collideToBoundary() && !enemyTank.collideToAnother()) {
                        enemyTank.move(enemyTank.getEnemyTankType().getSpeed());
                    } else {
                        enemyTank.collidedAction();
                    }
                } else {
                    enemyTank.rotate(Direction.randomDirection());
                }
            }
        }
    }

    //get和set方法
    public GameFrame getGf() {
        return gf;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public int getStopTime() {
        return stopTime;
    }

    public void setStopTime(int stopTime) {
        this.stopTime = stopTime;
    }

    public Runnable getWaiting() {
        return waiting;
    }

    public void setWaiting(Runnable waiting) {
        this.waiting = waiting;
    }

    public Random getRandom() {
        return random;
    }

    public void setRandom(Random random) {
        this.random = random;
    }
}
