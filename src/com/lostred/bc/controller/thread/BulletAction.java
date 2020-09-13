package com.lostred.bc.controller.thread;

import com.lostred.bc.model.Bullet;
import com.lostred.bc.util.GameData;
import com.lostred.bc.view.GameFrame;

import java.util.TimerTask;

/**
 * 子弹行动线程
 */
public class BulletAction extends TimerTask {
    /**
     * 所属的游戏窗口
     */
    private final GameFrame gf;

    /**
     * 构造子弹行动线程
     *
     * @param gf 游戏窗口
     */
    public BulletAction(GameFrame gf) {
        this.gf = gf;
    }

    /**
     * 子弹行动的线程任务
     */
    @Override
    public void run() {
        if (!GameData.PAUSE) {
            for (Bullet bullet : gf.getService().getBullets()) {
                if (!bullet.collideToBoundary() && !bullet.collideToAnother()) {
                    bullet.move(bullet.getSpeed());
                }
            }
        }
    }
}

