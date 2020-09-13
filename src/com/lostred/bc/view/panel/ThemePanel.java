package com.lostred.bc.view.panel;

import com.lostred.bc.controller.listener.ThemeCtrl;
import com.lostred.bc.util.localFile.GameImage;
import com.lostred.bc.view.GameFrame;

import javax.swing.*;
import java.awt.*;

public class ThemePanel extends JPanel {
    /**
     * 面板宽度
     */
    public static final int PANEL_WIDTH = GamePanel.MAP_WIDTH + GamePanel.MARGIN + GamePanel.INFO_AREA_WIDTH;
    /**
     * 面板高度
     */
    public static final int PANEL_HEIGHT = GamePanel.MAP_HEIGHT + 2 * GamePanel.MARGIN;
    /**
     * 游戏窗口
     */
    private GameFrame gf;
    /**
     * 光标
     */
    private JLabel selection;

    /**
     * 构造开始菜单面板
     *
     * @param gf 游戏窗口
     */
    public ThemePanel(GameFrame gf) {
        this.gf = gf;
        this.setLayout(null);
        this.setBackground(Color.BLACK);
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        //初始化控件
        this.selection = new JLabel();
        this.selection.setFocusable(false);
        //设置控件
        this.selection.setBounds(195, 398, 40, 40);
        Image newImage = GameImage.ALLY_TANK1_O_RIGHT.getScaledInstance(selection.getWidth(), selection.getHeight(), Image.SCALE_DEFAULT);
        Icon icon = new ImageIcon(newImage);
        this.selection.setIcon(icon);
        //添加监听
        this.addKeyListener(new ThemeCtrl(this));
        //添加控件
        this.add(selection);
    }

    /**
     * 重绘该面板
     *
     * @param g 图形
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(GameImage.THEME_BACKGROUND, 0, 0, PANEL_WIDTH, PANEL_HEIGHT, this);
    }

    //get和set方法
    public GameFrame getGf() {
        return gf;
    }

    public void setGf(GameFrame gf) {
        this.gf = gf;
    }

    public JLabel getSelection() {
        return selection;
    }

    public void setSelection(JLabel selection) {
        this.selection = selection;
    }
}
