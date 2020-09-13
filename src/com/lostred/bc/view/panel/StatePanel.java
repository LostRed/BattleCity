package com.lostred.bc.view.panel;

import com.lostred.bc.util.GameData;
import com.lostred.bc.util.localFile.GameImage;
import com.lostred.bc.util.setting.AllyTankLevel;
import com.lostred.bc.util.setting.EnemyTankType;
import com.lostred.bc.util.setting.GameMode;
import com.lostred.bc.view.GameFrame;

import javax.swing.*;
import java.awt.*;

/**
 * 统计区域面板
 */
public class StatePanel extends JPanel {
    /**
     * 面板宽度
     */
    public static final int PANEL_WIDTH = 192;
    /**
     * 面板高度
     */
    public static final int PANEL_HEIGHT = GamePanel.MAP_HEIGHT + 2 * GamePanel.MARGIN;
    /**
     * 游戏窗口
     */
    private GameFrame gf;
    /**
     * 普通型敌军坦克剩余数量
     */
    private JTextField tfdCommonType;
    /**
     * 速度型敌军坦克剩余数量
     */
    private JTextField tfdSpeedType;
    /**
     * 火力型敌军坦克剩余数量
     */
    private JTextField tfdFireType;
    /**
     * 重装型敌军坦克剩余数量
     */
    private JTextField tfdHeavyType;
    /**
     * 玩家1姓名
     */
    private JTextField tfdPlayer1Name;
    /**
     * 玩家2姓名
     */
    private JTextField tfdPlayer2Name;
    /**
     * 得分合计
     */
    private JTextField tfdTotalName;
    /**
     * 玩家1得分
     */
    private JTextField tfdPlayer1Score;
    /**
     * 玩家2得分
     */
    private JTextField tfdPlayer2Score;
    /**
     * 得分合计
     */
    private JTextField tfdTotalScore;

    /**
     * 构造信息区域面板
     *
     * @param gf 游戏窗口
     */
    public StatePanel(GameFrame gf) {
        this.gf = gf;
        //面板设置
        this.setLayout(null);
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        //初始化成员
        this.tfdCommonType = new JTextField();
        this.tfdSpeedType = new JTextField();
        this.tfdFireType = new JTextField();
        this.tfdHeavyType = new JTextField();
        this.tfdPlayer1Name = new JTextField("LostRed");
        this.tfdPlayer2Name = new JTextField("LostRed");
        this.tfdTotalName = new JTextField("Total");
        this.tfdPlayer1Score = new JTextField();
        this.tfdPlayer2Score = new JTextField();
        this.tfdTotalScore = new JTextField();
        //设置文本对齐
        this.tfdPlayer1Name.setHorizontalAlignment(JTextField.CENTER);
        this.tfdPlayer2Name.setHorizontalAlignment(JTextField.CENTER);
        this.tfdTotalName.setHorizontalAlignment(JTextField.CENTER);
        this.tfdPlayer1Score.setHorizontalAlignment(JTextField.RIGHT);
        this.tfdPlayer2Score.setHorizontalAlignment(JTextField.RIGHT);
        this.tfdTotalScore.setHorizontalAlignment(JTextField.RIGHT);
        //设置文本字体
        this.tfdCommonType.setFont(new Font("Arial", Font.BOLD, 24));
        this.tfdSpeedType.setFont(new Font("Arial", Font.BOLD, 24));
        this.tfdFireType.setFont(new Font("Arial", Font.BOLD, 24));
        this.tfdHeavyType.setFont(new Font("Arial", Font.BOLD, 24));
        this.tfdPlayer1Name.setFont(new Font("Arial", Font.BOLD, 18));
        this.tfdPlayer2Name.setFont(new Font("Arial", Font.BOLD, 18));
        this.tfdTotalName.setFont(new Font("Arial", Font.BOLD, 18));
        this.tfdPlayer1Score.setFont(new Font("Arial", Font.BOLD, 24));
        this.tfdPlayer2Score.setFont(new Font("Arial", Font.BOLD, 24));
        this.tfdTotalScore.setFont(new Font("Arial", Font.BOLD, 24));
        //设置文本字体颜色
        this.tfdCommonType.setDisabledTextColor(new Color(225, 225, 225));
        this.tfdSpeedType.setDisabledTextColor(new Color(225, 225, 225));
        this.tfdFireType.setDisabledTextColor(new Color(225, 225, 225));
        this.tfdHeavyType.setDisabledTextColor(new Color(225, 225, 225));
        this.tfdPlayer1Name.setDisabledTextColor(new Color(225, 225, 225));
        this.tfdPlayer2Name.setDisabledTextColor(new Color(225, 225, 225));
        this.tfdTotalName.setDisabledTextColor(new Color(225, 225, 225));
        this.tfdPlayer1Score.setDisabledTextColor(new Color(225, 225, 225));
        this.tfdPlayer2Score.setDisabledTextColor(new Color(225, 225, 225));
        this.tfdTotalScore.setDisabledTextColor(new Color(225, 225, 225));
        //设置不可获取焦点
        this.tfdCommonType.setFocusable(false);
        this.tfdSpeedType.setFocusable(false);
        this.tfdFireType.setFocusable(false);
        this.tfdHeavyType.setFocusable(false);
        this.tfdPlayer1Name.setFocusable(false);
        this.tfdPlayer2Name.setFocusable(false);
        this.tfdTotalName.setFocusable(false);
        this.tfdPlayer1Score.setFocusable(false);
        this.tfdPlayer2Score.setFocusable(false);
        this.tfdTotalScore.setFocusable(false);
        //设置不可用
        this.tfdCommonType.setEnabled(false);
        this.tfdSpeedType.setEnabled(false);
        this.tfdFireType.setEnabled(false);
        this.tfdHeavyType.setEnabled(false);
        this.tfdPlayer1Name.setEnabled(false);
        this.tfdPlayer2Name.setEnabled(false);
        this.tfdTotalName.setEnabled(false);
        this.tfdPlayer1Score.setEnabled(false);
        this.tfdPlayer2Score.setEnabled(false);
        this.tfdTotalScore.setEnabled(false);
        //设置不可编辑
        this.tfdCommonType.setEditable(false);
        this.tfdSpeedType.setEditable(false);
        this.tfdFireType.setEditable(false);
        this.tfdHeavyType.setEditable(false);
        this.tfdPlayer1Name.setEditable(false);
        this.tfdPlayer2Name.setEditable(false);
        this.tfdTotalName.setEditable(false);
        this.tfdPlayer1Score.setEditable(false);
        this.tfdPlayer2Score.setEditable(false);
        this.tfdTotalScore.setEditable(false);
        //设置透明背景
        this.tfdCommonType.setOpaque(false);
        this.tfdSpeedType.setOpaque(false);
        this.tfdFireType.setOpaque(false);
        this.tfdHeavyType.setOpaque(false);
        this.tfdPlayer1Name.setOpaque(false);
        this.tfdPlayer2Name.setOpaque(false);
        this.tfdTotalName.setOpaque(false);
        this.tfdPlayer1Score.setOpaque(false);
        this.tfdPlayer2Score.setOpaque(false);
        this.tfdTotalScore.setOpaque(false);
        //设置无边框
        this.tfdCommonType.setBorder(null);
        this.tfdSpeedType.setBorder(null);
        this.tfdFireType.setBorder(null);
        this.tfdHeavyType.setBorder(null);
        this.tfdPlayer1Name.setBorder(null);
        this.tfdPlayer2Name.setBorder(null);
        this.tfdTotalName.setBorder(null);
        this.tfdPlayer1Score.setBorder(null);
        this.tfdPlayer2Score.setBorder(null);
        this.tfdTotalScore.setBorder(null);
        //定位
        this.tfdCommonType.setBounds(108, 51, 54, 44);
        this.tfdSpeedType.setBounds(108, 122, 54, 44);
        this.tfdFireType.setBounds(108, 193, 54, 44);
        this.tfdHeavyType.setBounds(108, 264, 54, 44);

        this.tfdPlayer1Name.setBounds(48, 384, 96, 24);
        this.tfdPlayer1Score.setBounds(88, 420, 66, 38);
        this.tfdPlayer2Name.setBounds(48, 494, 96, 24);
        this.tfdPlayer2Score.setBounds(88, 530, 66, 38);
        this.tfdTotalName.setBounds(48, 604, 96, 24);
        this.tfdTotalScore.setBounds(88, 640, 66, 38);
        //添加控件
        this.add(tfdCommonType);
        this.add(tfdSpeedType);
        this.add(tfdFireType);
        this.add(tfdHeavyType);
        this.add(tfdPlayer1Score);
        this.add(tfdPlayer2Score);
        this.add(tfdTotalScore);
    }

    /**
     * 重绘该面板
     *
     * @param g 图形
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!GameData.START) {
            paintPanelCenter(g);
        } else if (GameData.START) {
            paintPanelBorder(g);
            paintEnemyTankIcon(g);
            paintAllyTankIcon(g);
        }
        this.repaint();
    }

    /**
     * 用砖墙铺满面板
     *
     * @param g 图形
     */
    private void paintPanelCenter(Graphics g) {
        for (int x = 0; x < PANEL_WIDTH; x += 24) {
            for (int y = 0; y < PANEL_HEIGHT; y += 24) {
                g.drawImage(GameImage.BRICK, x, y, 24, 24, this);
            }
        }
    }

    /**
     * 绘制面板边框
     *
     * @param g 图形
     */
    private void paintPanelBorder(Graphics g) {
        for (int x = 0; x < PANEL_WIDTH; x += 24) {
            g.drawImage(GameImage.BRICK, x, 0, 24, 24, this);
        }
        for (int x = 0; x < PANEL_WIDTH; x += 24) {
            g.drawImage(GameImage.BRICK, x, 696, 24, 24, this);
        }
        for (int y = 24; y < PANEL_HEIGHT; y += 24) {
            g.drawImage(GameImage.BRICK, 0, y, 24, 24, this);
        }
        for (int y = 24; y < PANEL_HEIGHT; y += 24) {
            g.drawImage(GameImage.BRICK, PANEL_WIDTH - 24, y, 24, 24, this);
        }
        for (int x = 24; x < PANEL_WIDTH; x += 24) {
            g.drawImage(GameImage.BRICK, x, 336, 24, 24, this);
        }
    }

    /**
     * 绘制敌军坦克的图标
     *
     * @param g 图形
     */
    private void paintEnemyTankIcon(Graphics g) {
        g.drawImage(GameImage.ENEMY_TANK_COMMON_UP, 48, 51,
                EnemyTankType.COMMON_TYPE.getWidth(), EnemyTankType.COMMON_TYPE.getHeight(), this);
        g.drawImage(GameImage.ENEMY_TANK_SPEED_UP, 48, 122,
                EnemyTankType.SPEED_TYPE.getWidth(), EnemyTankType.SPEED_TYPE.getHeight(), this);
        g.drawImage(GameImage.ENEMY_TANK_FIRE_UP, 48, 193,
                EnemyTankType.FIRE_TYPE.getWidth(), EnemyTankType.FIRE_TYPE.getHeight(), this);
        g.drawImage(GameImage.ENEMY_TANK_HEAVY_UP, 48, 264,
                EnemyTankType.HEAVY_TYPE.getWidth(), EnemyTankType.HEAVY_TYPE.getHeight(), this);
    }

    /**
     * 绘制盟军坦克的图标
     *
     * @param g 图形
     */
    private void paintAllyTankIcon(Graphics g) {
        if (GameData.GAME_MODE == GameMode.SINGLE) {
            g.drawImage(GameImage.ALLY_TANK1_O_UP, 42, 420,
                    AllyTankLevel.O.getWidth(), AllyTankLevel.O.getHeight(), this);
        } else if (GameData.GAME_MODE == GameMode.DOUBLE) {
            g.drawImage(GameImage.ALLY_TANK1_O_UP, 42, 420,
                    AllyTankLevel.O.getWidth(), AllyTankLevel.O.getHeight(), this);
            g.drawImage(GameImage.ALLY_TANK2_O_UP, 42, 530,
                    AllyTankLevel.O.getWidth(), AllyTankLevel.O.getHeight(), this);
        }
    }

    //get和set方法
    public GameFrame getGf() {
        return gf;
    }

    public void setGf(GameFrame gf) {
        this.gf = gf;
    }

    public JTextField getTfdCommonType() {
        return tfdCommonType;
    }

    public void setTfdCommonType(JTextField tfdCommonType) {
        this.tfdCommonType = tfdCommonType;
    }

    public JTextField getTfdSpeedType() {
        return tfdSpeedType;
    }

    public void setTfdSpeedType(JTextField tfdSpeedType) {
        this.tfdSpeedType = tfdSpeedType;
    }

    public JTextField getTfdFireType() {
        return tfdFireType;
    }

    public void setTfdFireType(JTextField tfdFireType) {
        this.tfdFireType = tfdFireType;
    }

    public JTextField getTfdHeavyType() {
        return tfdHeavyType;
    }

    public void setTfdHeavyType(JTextField tfdHeavyType) {
        this.tfdHeavyType = tfdHeavyType;
    }

    public JTextField getTfdPlayer1Name() {
        return tfdPlayer1Name;
    }

    public void setTfdPlayer1Name(JTextField tfdPlayer1Name) {
        this.tfdPlayer1Name = tfdPlayer1Name;
    }

    public JTextField getTfdPlayer2Name() {
        return tfdPlayer2Name;
    }

    public void setTfdPlayer2Name(JTextField tfdPlayer2Name) {
        this.tfdPlayer2Name = tfdPlayer2Name;
    }

    public JTextField getTfdTotalName() {
        return tfdTotalName;
    }

    public void setTfdTotalName(JTextField tfdTotalName) {
        this.tfdTotalName = tfdTotalName;
    }

    public JTextField getTfdPlayer1Score() {
        return tfdPlayer1Score;
    }

    public void setTfdPlayer1Score(JTextField tfdPlayer1Score) {
        this.tfdPlayer1Score = tfdPlayer1Score;
    }

    public JTextField getTfdPlayer2Score() {
        return tfdPlayer2Score;
    }

    public void setTfdPlayer2Score(JTextField tfdPlayer2Score) {
        this.tfdPlayer2Score = tfdPlayer2Score;
    }

    public JTextField getTfdTotalScore() {
        return tfdTotalScore;
    }

    public void setTfdTotalScore(JTextField tfdTotalScore) {
        this.tfdTotalScore = tfdTotalScore;
    }
}
