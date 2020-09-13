package com.lostred.bc.controller.thread;

import com.lostred.bc.controller.listener.Player1Ctrl;
import com.lostred.bc.controller.listener.Player2Ctrl;
import com.lostred.bc.model.Model;
import com.lostred.bc.model.Seat;
import com.lostred.bc.model.tank.AllyTank;
import com.lostred.bc.util.GameData;
import com.lostred.bc.view.GameFrame;

import java.util.TimerTask;

/**
 * 盟军坦克行动线程
 */
public class AllyTankAction extends TimerTask {
    /**
     * 所属的游戏窗口
     */
    private final GameFrame gf;

    /**
     * 构造盟军坦克行动线程
     *
     * @param gf 游戏窗口
     */
    public AllyTankAction(GameFrame gf) {
        this.gf = gf;
    }

    /**
     * 根据控制器操纵每一辆盟军坦克
     */
    @Override
    public void run() {
        if (!GameData.PAUSE && !GameData.GAME_OVER) {
            for (Model model : gf.getService().getCurrentAllyTanks()) {
                //跳过出生的坦克
                if (model instanceof Seat) {
                    continue;
                }
                AllyTank allyTank = (AllyTank) model;
                //检查冰川地形
                allyTank.setOnGlacier(allyTank.checkGlacier());
                //玩家1
                if (allyTank.getPlayerCtrl() instanceof Player1Ctrl) {
                    if (gf.getPlayer1Ctrl().isMove() && !gf.getPlayer1Ctrl().isFire()) {
                        if (!allyTank.collideToBoundary() && !allyTank.collideToAnother()) {
                            allyTank.move(allyTank.getAllyTankLevel().getSpeed());
                        }
                    } else if (gf.getPlayer1Ctrl().isFire() && !gf.getPlayer1Ctrl().isMove()) {
                        allyTank.fire();
                    } else if (gf.getPlayer1Ctrl().isFire() && gf.getPlayer1Ctrl().isMove()) {
                        if (!allyTank.collideToBoundary() && !allyTank.collideToAnother()) {
                            allyTank.move(allyTank.getAllyTankLevel().getSpeed());
                        }
                        allyTank.fire();
                    }
                }
                //玩家2
                else if (allyTank.getPlayerCtrl() instanceof Player2Ctrl) {
                    if (gf.getPlayer2Ctrl().isMove() && !gf.getPlayer2Ctrl().isFire()) {
                        if (!allyTank.collideToBoundary() && !allyTank.collideToAnother()) {
                            allyTank.move(allyTank.getAllyTankLevel().getSpeed());
                        }
                    } else if (gf.getPlayer2Ctrl().isFire() && !gf.getPlayer2Ctrl().isMove()) {
                        allyTank.fire();
                    } else if (gf.getPlayer2Ctrl().isFire() && gf.getPlayer2Ctrl().isMove()) {
                        if (!allyTank.collideToBoundary() && !allyTank.collideToAnother()) {
                            allyTank.move(allyTank.getAllyTankLevel().getSpeed());
                        }
                        allyTank.fire();
                    }
                }
            }
        }
    }
}
