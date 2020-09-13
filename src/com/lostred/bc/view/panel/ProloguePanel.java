package com.lostred.bc.view.panel;

import com.lostred.bc.util.GameData;
import com.lostred.bc.util.localFile.GameImage;

import javax.swing.*;
import java.awt.*;

/**
 * 序幕面板
 */
public class ProloguePanel extends JPanel {
    /**
     * 面板宽度
     */
    public static final int PANEL_WIDTH = GamePanel.MAP_WIDTH + GamePanel.MARGIN + GamePanel.INFO_AREA_WIDTH;
    /**
     * 面板高度
     */
    public static final int PANEL_HEIGHT = GamePanel.MAP_HEIGHT + 2 * GamePanel.MARGIN;

    /**
     * 构造序幕面板
     */
    public ProloguePanel() {
        this.setBackground(new Color(99, 99, 99));
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    }

    /**
     * 重绘该面板
     *
     * @param g 图形
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(GameImage.STAGE, 276, 350, 120, 20, this);
        switch (GameData.STAGE_NO) {
            case 0:
            case 1:
                g.drawImage(GameImage.ONE, 456, 346, 24, 24, this);
                g.drawImage(GameImage.ZERO, 432, 346, 24, 24, this);
                break;
            case 2:
                g.drawImage(GameImage.TWO, 456, 346, 24, 24, this);
                g.drawImage(GameImage.ZERO, 432, 346, 24, 24, this);
                break;
            case 3:
                g.drawImage(GameImage.THREE, 456, 346, 24, 24, this);
                g.drawImage(GameImage.ZERO, 432, 346, 24, 24, this);
                break;
            case 4:
                g.drawImage(GameImage.FOUR, 456, 346, 24, 24, this);
                g.drawImage(GameImage.ZERO, 432, 346, 24, 24, this);
                break;
            case 5:
                g.drawImage(GameImage.FIVE, 456, 346, 24, 24, this);
                g.drawImage(GameImage.ZERO, 432, 346, 24, 24, this);
                break;
            case 6:
                g.drawImage(GameImage.SIX, 456, 346, 24, 24, this);
                g.drawImage(GameImage.ZERO, 432, 346, 24, 24, this);
                break;
            case 7:
                g.drawImage(GameImage.SEVEN, 456, 346, 24, 24, this);
                g.drawImage(GameImage.ZERO, 432, 346, 24, 24, this);
                break;
            case 8:
                g.drawImage(GameImage.EIGHT, 456, 346, 24, 24, this);
                g.drawImage(GameImage.ZERO, 432, 346, 24, 24, this);
                break;
            case 9:
                g.drawImage(GameImage.NINE, 456, 346, 24, 24, this);
                g.drawImage(GameImage.ZERO, 432, 346, 24, 24, this);
                break;
            case 10:
                g.drawImage(GameImage.ZERO, 456, 346, 24, 24, this);
                g.drawImage(GameImage.ONE, 432, 346, 24, 24, this);
                break;
        }
    }
}
