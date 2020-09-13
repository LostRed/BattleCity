package com.lostred.bc.controller.thread;

import com.lostred.bc.model.tank.AllyTank;
import com.lostred.bc.util.GameData;
import com.lostred.bc.view.GameFrame;

import java.util.TimerTask;

/**
 * 盟军坦克出生线程
 */
public class AllyTankBirth extends TimerTask {
    /**
     * 所属的窗口
     */
    private final GameFrame gf;

    /**
     * 构造盟军坦克出生线程
     *
     * @param gf 游戏窗口
     */
    public AllyTankBirth(GameFrame gf) {
        this.gf = gf;
    }

    /**
     * 盟军坦克出生的线程任务
     */
    @Override
    public void run() {
        if (!GameData.PAUSE && !GameData.GAME_OVER) {
            if (gf.getService().getAllyTanks().size() != 0) {
                appear();
            }
        }
    }

    /**
     * 从等待队列集合中移出一辆盟军坦克
     */
    private void appear() {
        AllyTank allyTank = (AllyTank) gf.getService().getAllyTanks().get(0);
        allyTank.resetTank();
        allyTank.getPlayerCtrl().reset();
        if (allyTank.checkBirthPosition()) {
            allyTank.birthing();
        }
    }
}
