package com.lostred.bc.controller.thread;

import com.lostred.bc.util.GameData;
import com.lostred.bc.util.setting.GameMode;
import com.lostred.bc.view.GameFrame;

import java.util.TimerTask;

/**
 * 游戏流程线程
 */
public class GameAction extends TimerTask {
    /**
     * 所属的游戏窗口
     */
    private final GameFrame gf;
    /**
     * 进入下一关的等待时间任务
     */
    private final Runnable loading;
    /**
     * 该线程是否等待
     */
    private boolean wait;

    /**
     * 构造游戏流程线程
     *
     * @param gf 游戏窗口
     */
    public GameAction(GameFrame gf) {
        this.gf = gf;
        this.loading = () -> {
            do {
                //等待时间
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (GameData.GAME_OVER) {
                    return;
                }
            } while (GameData.PAUSE);
            GameData.PAUSE = true;
            //改变关卡编号
            if (GameData.STAGE_NO == 0) {
                GameData.STAGE_NO += 2;
            } else {
                GameData.STAGE_NO++;
            }
            //通关条件
            if (GameData.STAGE_NO > 5) {
                GameData.GAME_OVER = true;
                GameData.PAUSE = false;
                //播放通关音乐
                gf.getService().getGs().playTheEnd();
                //进入通关画面
                gf.enterTheEnd();
                //显示排行
                gf.showRankList();
                //结束关卡
                gf.getService().endStage();
                //初始化游戏数据
                GameData.initData();
            } else {
                //落幕
                gf.remove(gf.getGamePanel());
                gf.add(gf.getProloguePanel());
                gf.getProloguePanel().updateUI();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //开幕
                gf.remove(gf.getProloguePanel());
                gf.add(gf.getGamePanel());
                gf.getService().nextStage();
                gf.getGameMenuBar().getPause().setEnabled(true);
                GameData.PAUSE = false;
            }
            this.wait = false;
        };
    }

    /**
     * 游戏流程的线程任务
     */
    @Override
    public void run() {
        if (!GameData.PAUSE && !GameData.GAME_OVER) {
            if (gf.getService().getCurrentEnemyTanks().size() == 0 && gf.getService().getEnemyTanks().size() == 0 && !this.wait) {
                this.wait = true;
                //播放完成关卡音乐
                gf.getService().getGs().play(gf.getService().getGs().getCompleteSound());
                gf.getGameMenuBar().getPause().setEnabled(false);
                new Thread(loading).start();
            } else if ((GameData.GAME_MODE == GameMode.SINGLE && GameData.PLAYER1_LIFE < 0)
                    || (GameData.GAME_MODE == GameMode.DOUBLE && GameData.PLAYER1_LIFE < 0 && GameData.PLAYER2_LIFE < 0)
                    || !gf.getService().getEagleBase().isAlive()) {
                GameData.GAME_OVER = true;
                //停止播放开始关卡音乐
                gf.getService().getGs().stop(gf.getService().getGs().getStartSound());
                //播放失败音乐
                gf.getService().getGs().play(gf.getService().getGs().getGameOverSound());
                gf.getGameMenuBar().getPause().setEnabled(false);
                gf.getGameAction().cancel();
                gf.getGameActionTimer().cancel();
                gf.getAllyTankAction().cancel();
                gf.getAllyTankActionTimer().cancel();
                gf.cancelCtrl();
                gf.showRankList();
            }
        }
    }
}
