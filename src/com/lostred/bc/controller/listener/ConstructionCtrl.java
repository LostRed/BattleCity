package com.lostred.bc.controller.listener;

import com.lostred.bc.view.panel.ConstructionPanel;
import com.lostred.bc.view.panel.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 自定义地图控制器
 */
public class ConstructionCtrl extends KeyAdapter {
    /**
     * 所属自定义地图面板
     */
    private final ConstructionPanel cp;
    /**
     * 光标是否为刷子状态
     */
    private boolean brush;

    /**
     * 构造自定义地图控制器
     *
     * @param cp 自定义地图面板
     */
    public ConstructionCtrl(ConstructionPanel cp) {
        this.cp = cp;
        this.brush = false;
    }

    /**
     * 键盘按下时，改变光标的坐标；按下J键，将其更变为刷子状态
     *
     * @param e 键盘事件
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int x = cp.getSelection().getX();
        int y = cp.getSelection().getY();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                if (y > GamePanel.MARGIN) {
                    cp.getSelection().setBounds(x, y - GamePanel.CELL, GamePanel.CELL, GamePanel.CELL);
                }
                break;
            case KeyEvent.VK_S:
                if (y < GamePanel.MARGIN + GamePanel.MAP_HEIGHT - GamePanel.CELL) {
                    cp.getSelection().setBounds(x, y + GamePanel.CELL, GamePanel.CELL, GamePanel.CELL);
                }
                break;
            case KeyEvent.VK_A:
                if (x > GamePanel.MARGIN) {
                    cp.getSelection().setBounds(x - GamePanel.CELL, y, GamePanel.CELL, GamePanel.CELL);
                }
                break;
            case KeyEvent.VK_D:
                if (x < GamePanel.MARGIN + GamePanel.MAP_HEIGHT - GamePanel.CELL) {
                    cp.getSelection().setBounds(x + GamePanel.CELL, y, GamePanel.CELL, GamePanel.CELL);
                }
                break;
            case KeyEvent.VK_J:
                this.brush = true;
                break;
            case KeyEvent.VK_K:
                cp.changeLandform();
                break;
            case KeyEvent.VK_ENTER:
                cp.saveMap();
                cp.getGf().returnTheme();
                break;
            case KeyEvent.VK_ESCAPE:
                cp.getGf().returnTheme();
                break;
        }
        if (brush) {
            cp.setLandform();
        }
    }

    /**
     * 释放J键后，将光标更变为非刷子状态
     *
     * @param e 键盘事件
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_J) {
            this.brush = false;
        }
    }
}
