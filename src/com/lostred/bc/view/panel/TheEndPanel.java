package com.lostred.bc.view.panel;

import com.lostred.bc.util.localFile.GameImage;

import javax.swing.*;
import java.awt.*;

public class TheEndPanel extends JPanel {
    /**
     * 面板宽度
     */
    public static final int PANEL_WIDTH = GamePanel.MAP_WIDTH + GamePanel.MARGIN + GamePanel.INFO_AREA_WIDTH + StatePanel.PANEL_WIDTH;
    /**
     * 面板高度
     */
    public static final int PANEL_HEIGHT = GamePanel.MAP_HEIGHT + 2 * GamePanel.MARGIN;

    /**
     * 构造序幕面板
     */
    public TheEndPanel() {
        this.setBackground(new Color(99, 99, 99));
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
    }

    /**
     * 重绘改面板
     *
     * @param g 图形
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(GameImage.END, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, this);
    }
}
