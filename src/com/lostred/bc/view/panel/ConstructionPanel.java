package com.lostred.bc.view.panel;

import com.lostred.bc.controller.listener.ConstructionCtrl;
import com.lostred.bc.model.EagleBase;
import com.lostred.bc.model.Stage;
import com.lostred.bc.util.GameData;
import com.lostred.bc.util.localFile.GameImage;
import com.lostred.bc.view.GameFrame;

import javax.swing.*;
import java.awt.*;

/**
 * 自定义地图面板
 */
public class ConstructionPanel extends JPanel {
    /**
     * 面板高度
     */
    public static final int PANEL_HEIGHT = GamePanel.MAP_HEIGHT + 2 * GamePanel.MARGIN;
    /**
     * 面板宽度
     */
    public static final int PANEL_WIDTH = GamePanel.MAP_WIDTH + GamePanel.MARGIN + GamePanel.INFO_AREA_WIDTH;
    /**
     * 所属游戏窗口
     */
    private GameFrame gf;
    /**
     * 光标
     */
    private JLabel selection;
    /**
     * 基地位置
     */
    private JLabel eagleBase;
    /**
     * 当前光标的地形类型：0为空地，1为树林，2为河流，3为砖墙，4为铁墙，5为冰川
     */
    private int landformType;
    /**
     * 标签索引
     */
    private int index;
    /**
     * 地图
     */
    private int[][] map;

    /**
     * 构造自定义地图面板
     *
     * @param gf 游戏窗口
     */
    public ConstructionPanel(GameFrame gf) {
        this.gf = gf;
        this.setLayout(null);
        this.setBackground(new Color(99, 99, 99));
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.selection = new JLabel();
        this.eagleBase = new JLabel();
        this.landformType = 0;
        this.index = 2;
        this.map = new int[26][26];
        //设置控件
        this.selection.setBounds(264, 648, GamePanel.CELL, GamePanel.CELL);
        this.eagleBase.setBounds(336, 624, EagleBase.WIDTH, EagleBase.HEIGHT);
        Icon selectionIcon = GameImage.transToIcon(selection.getWidth(), selection.getHeight(), GameImage.ALLY_TANK1_O_UP);
        Icon eagleIcon = GameImage.transToIcon(eagleBase.getWidth(), eagleBase.getHeight(), GameImage.EAGLE);
        this.selection.setIcon(selectionIcon);
        this.eagleBase.setIcon(eagleIcon);
        //添加监听
        this.addKeyListener(new ConstructionCtrl(this));
        //添加控件
        this.add(selection);
        this.add(eagleBase);
    }

    /**
     * 重绘该面板
     *
     * @param g 图形
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(GamePanel.MARGIN, GamePanel.MARGIN, GamePanel.MAP_WIDTH, GamePanel.MAP_HEIGHT);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                switch (map[i][j]) {
                    case 1:
                        g.drawImage(GameImage.WOOD, j * GamePanel.CELL + GamePanel.MARGIN, i * GamePanel.CELL + GamePanel.MARGIN, GamePanel.CELL, GamePanel.CELL, this);
                        break;
                    case 2:
                        g.drawImage(GameImage.RIVER1, j * GamePanel.CELL + GamePanel.MARGIN, i * GamePanel.CELL + GamePanel.MARGIN, GamePanel.CELL, GamePanel.CELL, this);
                        break;
                    case 3:
                        g.drawImage(GameImage.BRICK, j * GamePanel.CELL + GamePanel.MARGIN, i * GamePanel.CELL + GamePanel.MARGIN, GamePanel.CELL, GamePanel.CELL, this);
                        break;
                    case 4:
                        g.drawImage(GameImage.STEEL, j * GamePanel.CELL + GamePanel.MARGIN, i * GamePanel.CELL + GamePanel.MARGIN, GamePanel.CELL, GamePanel.CELL, this);
                        break;
                    case 5:
                        g.drawImage(GameImage.GLACIER, j * GamePanel.CELL + GamePanel.MARGIN, i * GamePanel.CELL + GamePanel.MARGIN, GamePanel.CELL, GamePanel.CELL, this);
                        break;
                }
            }
        }
        this.repaint();
    }

    /**
     * 设置地形
     */
    public void setLandform() {
        int x = this.selection.getX();
        int y = this.selection.getY();
        //敌军坦克出生位置1不可编辑
        if ((x == 48 && y == 48)
                || (x == 72 && y == 48)
                || (x == 48 && y == 72)
                || (x == 72 && y == 72)
                //敌军坦克出生位置2不可编辑
                || (x == 336 && y == 48)
                || (x == 360 && y == 48)
                || (x == 336 && y == 72)
                || (x == 360 && y == 72)
                //敌军坦克出生位置3不可编辑
                || (x == 624 && y == 48)
                || (x == 648 && y == 48)
                || (x == 624 && y == 72)
                || (x == 648 && y == 72)
                //玩家1位置不可编辑
                || (x == 240 && y == 624)
                || (x == 264 && y == 624)
                || (x == 240 && y == 648)
                || (x == 264 && y == 648)
                //玩家2位置不可编辑
                || (x == 432 && y == 624)
                || (x == 456 && y == 624)
                || (x == 432 && y == 648)
                || (x == 456 && y == 648)
                //基地位置不可编辑
                || (x == 336 && y == 624)
                || (x == 360 && y == 624)
                || (x == 336 && y == 648)
                || (x == 360 && y == 648)) {
            return;
        }
        map[(y - GamePanel.MARGIN) / GamePanel.CELL][(x - GamePanel.MARGIN) / GamePanel.CELL] = this.landformType;
    }

    /**
     * 更换光标的地形样式
     */
    public void changeLandform() {
        Icon icon = null;
        switch (this.landformType) {
            case 0:
                this.landformType = 1;
                icon = GameImage.transToIcon(selection.getWidth(), selection.getHeight(), GameImage.WOOD);
                break;
            case 1:
                this.landformType = 2;
                icon = GameImage.transToIcon(selection.getWidth(), selection.getHeight(), GameImage.RIVER1);
                break;
            case 2:
                this.landformType = 3;
                icon = GameImage.transToIcon(selection.getWidth(), selection.getHeight(), GameImage.BRICK);
                break;
            case 3:
                this.landformType = 4;
                icon = GameImage.transToIcon(selection.getWidth(), selection.getHeight(), GameImage.STEEL);
                break;
            case 4:
                this.landformType = 5;
                icon = GameImage.transToIcon(selection.getWidth(), selection.getHeight(), GameImage.GLACIER);
                break;
            case 5:
                this.landformType = 0;
                icon = GameImage.transToIcon(selection.getWidth(), selection.getHeight(), GameImage.ALLY_TANK1_O_UP);
                break;
        }
        this.selection.setIcon(icon);
    }

    /**
     * 保存地图
     */
    public void saveMap() {
        Stage.MAP = this.map.clone();
        GameData.STAGE_NO = 0;
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

    public JLabel getEagleBase() {
        return eagleBase;
    }

    public void setEagleBase(JLabel eagleBase) {
        this.eagleBase = eagleBase;
    }

    public int getLandformType() {
        return landformType;
    }

    public void setLandformType(int landformType) {
        this.landformType = landformType;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }
}
