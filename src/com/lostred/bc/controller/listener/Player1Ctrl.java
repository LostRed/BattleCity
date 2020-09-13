package com.lostred.bc.controller.listener;

import com.lostred.bc.controller.thread.animation.Pause;
import com.lostred.bc.util.GameData;
import com.lostred.bc.util.setting.Direction;
import com.lostred.bc.util.setting.GameMode;
import com.lostred.bc.view.GameFrame;

import java.awt.event.KeyEvent;

/**
 * 玩家1控制器
 */
public class Player1Ctrl extends PlayerCtrl {
    /**
     * 构造玩家1控制器
     *
     * @param gf 游戏窗口
     */
    public Player1Ctrl(GameFrame gf) {
        super.setGf(gf);
        super.setMove(false);
    }

    /**
     * 按下按键时，将对应按键的布尔值改为true，之后检查移动的按键状态
     *
     * @param e 键盘事件
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if (super.getTank() == null) {
            return;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                if (super.getStopTime() <= 0 && !GameData.PAUSE) {
                    super.getTank().rotate(Direction.UP);
                    super.setUp(true);
                }
                break;
            case KeyEvent.VK_S:
                if (super.getStopTime() <= 0 && !GameData.PAUSE) {
                    super.getTank().rotate(Direction.DOWN);
                    super.setDown(true);
                }
                break;
            case KeyEvent.VK_A:
                if (super.getStopTime() <= 0 && !GameData.PAUSE) {
                    super.getTank().rotate(Direction.LEFT);
                    super.setLeft(true);
                }
                break;
            case KeyEvent.VK_D:
                if (super.getStopTime() <= 0 && !GameData.PAUSE) {
                    super.getTank().rotate(Direction.RIGHT);
                    super.setRight(true);
                }
                break;
            case KeyEvent.VK_J:
                if (!GameData.PAUSE) {
                    if (GameData.GAME_MODE == GameMode.DOUBLE && GameData.PLAYER1_LIFE < 0 && GameData.PLAYER2_LIFE > 0) {
                        super.getTank().getService().borrowLife(super.getTank());
                    }
                    super.setFire(true);
                }
                break;
            case KeyEvent.VK_ESCAPE:
                if (GameData.START && !GameData.GAME_OVER) {
                    if (GameData.PAUSE) {
                        GameData.PAUSE = false;
                    } else {
                        GameData.PAUSE = true;
                        new Pause(super.getGf()).start();
                    }
                }
                break;
        }
        super.checkMove();
    }

    /**
     * 释放按键时，将对应按键的布尔值改为false，之后检查移动的按键状态
     *
     * @param e 键盘事件
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (super.getTank() == null) {
            return;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                super.setUp(false);
                break;
            case KeyEvent.VK_S:
                super.setDown(false);
                break;
            case KeyEvent.VK_A:
                super.setLeft(false);
                break;
            case KeyEvent.VK_D:
                super.setRight(false);
                break;
            case KeyEvent.VK_J:
                super.setFire(false);
                break;
        }
        super.checkMove();
    }
}
