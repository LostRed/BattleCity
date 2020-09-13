package com.lostred.bc.controller.listener;

import com.lostred.bc.util.GameData;
import com.lostred.bc.util.setting.GameMode;
import com.lostred.bc.view.NameDialog;
import com.lostred.bc.view.panel.ThemePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 全局控制器
 */
public class ThemeCtrl extends KeyAdapter {
    /**
     * 控制的游戏窗口
     */
    private final ThemePanel tp;

    /**
     * 构造全局控制器
     *
     * @param tp 主界面面板
     */
    public ThemeCtrl(ThemePanel tp) {
        this.tp = tp;
    }

    /**
     * 键盘按下时，改变光标的坐标，同时切换游戏模式；按下回车进入游戏
     *
     * @param e 键盘事件
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                if (!GameData.START && tp.getGf().getThemePanel().getSelection().getY() != 398) {
                    //播放音效
                    tp.getGf().getService().getGs().play(tp.getGf().getService().getGs().getMenuSound());
                    if (tp.getGf().getThemePanel().getSelection().getY() == 446) {
                        tp.getGf().getThemePanel().getSelection().setBounds(195, 398, 40, 40);
                        GameData.GAME_MODE = GameMode.SINGLE;
                    } else if (tp.getGf().getThemePanel().getSelection().getY() == 494) {
                        tp.getGf().getThemePanel().getSelection().setBounds(195, 446, 40, 40);
                        GameData.GAME_MODE = GameMode.DOUBLE;
                    }
                }
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                if (!GameData.START && tp.getGf().getThemePanel().getSelection().getY() != 494) {
                    //播放音效
                    tp.getGf().getService().getGs().play(tp.getGf().getService().getGs().getMenuSound());
                    if (tp.getGf().getThemePanel().getSelection().getY() == 398) {
                        tp.getGf().getThemePanel().getSelection().setBounds(195, 446, 40, 40);
                        GameData.GAME_MODE = GameMode.DOUBLE;
                    } else if (tp.getGf().getThemePanel().getSelection().getY() == 446) {
                        tp.getGf().getThemePanel().getSelection().setBounds(195, 494, 40, 40);
                        GameData.GAME_MODE = GameMode.CONSTRUCTION;
                    }
                }
                break;
            case KeyEvent.VK_J:
            case KeyEvent.VK_ENTER:
                if (!GameData.START) {
                    if (GameData.GAME_MODE == GameMode.SINGLE || GameData.GAME_MODE == GameMode.DOUBLE) {
                        new NameDialog(tp.getGf()).setVisible(true);
                    } else {
                        tp.getGf().enterConstruction();
                    }
                }
                break;
        }
    }
}
