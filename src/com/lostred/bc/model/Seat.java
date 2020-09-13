package com.lostred.bc.model;

import com.lostred.bc.view.panel.GamePanel;

/**
 * 占位模型：用于占据坦克出生位置
 */
public class Seat extends Model {
    public Seat() {
        super.setWidth(2 * GamePanel.CELL);
        super.setHeight(2 * GamePanel.CELL);
    }
}
