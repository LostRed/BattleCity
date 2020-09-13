package com.lostred.bc.controller.listener;

import com.lostred.bc.controller.thread.animation.Pause;
import com.lostred.bc.util.GameData;
import com.lostred.bc.view.GameFrame;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * 游戏窗口监听
 */
public class GameFrameListener extends WindowAdapter implements MouseListener {
    /**
     * 监听的游戏窗口
     */
    private final GameFrame gf;

    /**
     * 构造游戏窗口监听
     *
     * @param gf 游戏窗口
     */
    public GameFrameListener(GameFrame gf) {
        this.gf = gf;
    }

    /**
     * 鼠标点击后，启动游戏暂停线程
     *
     * @param e 鼠标事件
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        if (!GameData.PAUSE && !GameData.GAME_OVER) {
            GameData.PAUSE = true;
            new Pause(gf).start();
        } else {
            gf.getThemePanel().requestFocus();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * 窗口正在关闭时，弹出对话框
     *
     * @param e 窗口事件
     */
    @Override
    public void windowClosing(WindowEvent e) {
        int select = JOptionPane.showConfirmDialog(gf, "确定要退出吗？", "提示", JOptionPane.OK_CANCEL_OPTION);
        if (select == JOptionPane.OK_OPTION) {
            System.exit(0);
        }
    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    /**
     * 窗口位于非活动状态，启动游戏暂停线程
     *
     * @param e 窗口事件
     */
    @Override
    public void windowDeactivated(WindowEvent e) {
        if (!GameData.PAUSE && !GameData.GAME_OVER) {
            GameData.PAUSE = true;
            new Pause(gf).start();
        }
    }
}
