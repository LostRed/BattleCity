package com.lostred.bc.view.panel;

import com.lostred.bc.model.*;
import com.lostred.bc.model.tank.AllyTank;
import com.lostred.bc.model.tank.EnemyTank;
import com.lostred.bc.util.GameData;
import com.lostred.bc.util.localFile.GameImage;
import com.lostred.bc.util.setting.Direction;
import com.lostred.bc.util.setting.GameMode;
import com.lostred.bc.util.setting.LandformType;
import com.lostred.bc.view.GameFrame;

import javax.swing.*;
import java.awt.*;

/**
 * 游戏区域面板
 */
public class GamePanel extends JPanel {
    /**
     * 地图区地形单元格大小
     */
    public static final int CELL = 24;
    /**
     * 地图区宽度
     */
    public static final int MAP_WIDTH = 624;
    /**
     * 地图区高度
     */
    public static final int MAP_HEIGHT = 624;
    /**
     * 地图区四周的外边距
     */
    public static final int MARGIN = 48;
    /**
     * 信息区宽度
     */
    public static final int INFO_AREA_WIDTH = 96;
    /**
     * 面板高度
     */
    public static final int PANEL_HEIGHT = MAP_HEIGHT + 2 * MARGIN;
    /**
     * 面板宽度
     */
    public static final int PANEL_WIDTH = MAP_WIDTH + MARGIN + INFO_AREA_WIDTH;
    /**
     * 所属窗口
     */
    private final GameFrame gf;

    /**
     * 构造游戏区域面板
     *
     * @param gf 游戏窗口
     */
    public GamePanel(GameFrame gf) {
        this.gf = gf;
        this.setLayout(null);
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
        g.setColor(Color.BLACK);
        g.fillRect(MARGIN, MARGIN, MAP_WIDTH, MAP_HEIGHT);
        g.setColor(new Color(99, 99, 99));
        g.drawImage(GameImage.INFO_BACKGROUND, PANEL_WIDTH - INFO_AREA_WIDTH, 0, INFO_AREA_WIDTH, PANEL_HEIGHT, this);
        if (GameData.START) {
            paintEnemyFlag(g);
            paintPlayerLife(g);
            paintStageNo(g);
            paintEagleBase(g);
            paintGlacier(g);
            paintImpassableObs(g);
            paintBullet(g);
            paintAllyTank(g);
            paintEnemyTank(g);
            paintWood(g);
            paintProperty(g);
            if (GameData.SHOW_HEALTH) {
                paintHealth(g);
            }
            if (GameData.GAME_OVER) {
                paintGameOver(g);
            }
            this.repaint();
        }
    }

    /**
     * 绘制游戏结束标志
     *
     * @param g 图形
     */
    private void paintGameOver(Graphics g) {
        g.drawImage(GameImage.GAME_OVER, 314, 338, 92, 44, this);
    }

    /**
     * 绘制剩余敌军坦克标志
     *
     * @param g 图形
     */
    private void paintEnemyFlag(Graphics g) {
        int x = 696;
        int y = 72;
        for (int i = 0; i < gf.getService().getEnemyTanks().size(); i++) {
            g.drawImage(GameImage.ENEMY_FLAG, x, y, 24, 24, this);
            if (i % 2 == 0) {
                x += 24;
            } else {
                y += 24;
                x -= 24;
            }
        }
    }

    /**
     * 绘制玩家生命值
     *
     * @param g 图形
     */
    private void paintPlayerLife(Graphics g) {
        switch (GameData.PLAYER1_LIFE) {
            case -1:
            case 0:
                g.drawImage(GameImage.ZERO, 720, 432, 24, 24, this);
                break;
            case 1:
                g.drawImage(GameImage.ONE, 720, 432, 24, 24, this);
                break;
            case 2:
                g.drawImage(GameImage.TWO, 720, 432, 24, 24, this);
                break;
            case 3:
                g.drawImage(GameImage.THREE, 720, 432, 24, 24, this);
                break;
            case 4:
                g.drawImage(GameImage.FOUR, 720, 432, 24, 24, this);
                break;
            case 5:
                g.drawImage(GameImage.FIVE, 720, 432, 24, 24, this);
                break;
            case 6:
                g.drawImage(GameImage.SIX, 720, 432, 24, 24, this);
                break;
            case 7:
                g.drawImage(GameImage.SEVEN, 720, 432, 24, 24, this);
                break;
            case 8:
                g.drawImage(GameImage.EIGHT, 720, 432, 24, 24, this);
                break;
            default:
                g.drawImage(GameImage.NINE, 720, 432, 24, 24, this);
                break;
        }
        if (GameData.GAME_MODE == GameMode.SINGLE) {
            g.drawImage(GameImage.INFO_SHELTER, 696, 480, 24, 24, this);
            g.drawImage(GameImage.INFO_SHELTER, 720, 480, 24, 24, this);
            g.drawImage(GameImage.INFO_SHELTER, 696, 504, 24, 24, this);
            g.drawImage(GameImage.INFO_SHELTER, 720, 504, 24, 24, this);
        } else if (GameData.GAME_MODE == GameMode.DOUBLE) {
            switch (GameData.PLAYER2_LIFE) {
                case -1:
                case 0:
                    g.drawImage(GameImage.ZERO, 720, 504, 24, 24, this);
                    break;
                case 1:
                    g.drawImage(GameImage.ONE, 720, 504, 24, 24, this);
                    break;
                case 2:
                    g.drawImage(GameImage.TWO, 720, 504, 24, 24, this);
                    break;
                case 3:
                    g.drawImage(GameImage.THREE, 720, 504, 24, 24, this);
                    break;
                case 4:
                    g.drawImage(GameImage.FOUR, 720, 504, 24, 24, this);
                    break;
                case 5:
                    g.drawImage(GameImage.FIVE, 720, 504, 24, 24, this);
                    break;
                case 6:
                    g.drawImage(GameImage.SIX, 720, 504, 24, 24, this);
                    break;
                case 7:
                    g.drawImage(GameImage.SEVEN, 720, 504, 24, 24, this);
                    break;
                case 8:
                    g.drawImage(GameImage.EIGHT, 720, 504, 24, 24, this);
                    break;
                default:
                    g.drawImage(GameImage.NINE, 720, 504, 24, 24, this);
                    break;
            }
        }
    }

    /**
     * 绘制关卡编号
     *
     * @param g 图形
     */
    private void paintStageNo(Graphics g) {
        switch (GameData.STAGE_NO) {
            case 1:
                g.drawImage(GameImage.ONE, 720, 600, 24, 24, this);
                g.drawImage(GameImage.INFO_SHELTER, 696, 600, 24, 24, this);
                break;
            case 2:
                g.drawImage(GameImage.TWO, 720, 600, 24, 24, this);
                g.drawImage(GameImage.INFO_SHELTER, 696, 600, 24, 24, this);
                break;
            case 3:
                g.drawImage(GameImage.THREE, 720, 600, 24, 24, this);
                g.drawImage(GameImage.INFO_SHELTER, 696, 600, 24, 24, this);
                break;
            case 4:
                g.drawImage(GameImage.FOUR, 720, 600, 24, 24, this);
                g.drawImage(GameImage.INFO_SHELTER, 696, 600, 24, 24, this);
                break;
            case 5:
                g.drawImage(GameImage.FIVE, 720, 600, 24, 24, this);
                g.drawImage(GameImage.INFO_SHELTER, 696, 600, 24, 24, this);
                break;
            case 6:
                g.drawImage(GameImage.SIX, 720, 600, 24, 24, this);
                g.drawImage(GameImage.INFO_SHELTER, 696, 600, 24, 24, this);
                break;
            case 7:
                g.drawImage(GameImage.SEVEN, 720, 600, 24, 24, this);
                g.drawImage(GameImage.INFO_SHELTER, 696, 600, 24, 24, this);
                break;
            case 8:
                g.drawImage(GameImage.EIGHT, 720, 600, 24, 24, this);
                g.drawImage(GameImage.INFO_SHELTER, 696, 600, 24, 24, this);
                break;
            case 9:
                g.drawImage(GameImage.NINE, 720, 600, 24, 24, this);
                g.drawImage(GameImage.INFO_SHELTER, 696, 600, 24, 24, this);
                break;
            case 10:
                g.drawImage(GameImage.ZERO, 720, 600, 24, 24, this);
                g.drawImage(GameImage.ONE, 696, 600, 24, 24, this);
                break;
        }
    }

    /**
     * 绘制基地
     *
     * @param g 图形
     */
    private void paintEagleBase(Graphics g) {
        EagleBase eagleBase = gf.getService().getEagleBase();
        if (eagleBase != null) {
            if (eagleBase.isAlive()) {
                g.drawImage(eagleBase.getAppearances().get(0), eagleBase.getXp() - eagleBase.getWidth() / 2,
                        eagleBase.getYp() - eagleBase.getHeight() / 2, eagleBase.getWidth(), eagleBase.getHeight(), this);
            } else {
                g.drawImage(eagleBase.getAppearances().get(1), eagleBase.getXp() - eagleBase.getWidth() / 2,
                        eagleBase.getYp() - eagleBase.getHeight() / 2, eagleBase.getWidth(), eagleBase.getHeight(), this);
            }
        }
    }

    /**
     * 绘制冰川
     *
     * @param g 图形
     */
    private void paintGlacier(Graphics g) {
        for (Landform landform : gf.getService().getPassableLandform()) {
            if (landform.getLandformType() == LandformType.GLACIER) {
                g.drawImage(landform.getAppearances().get(0), landform.getXp() - landform.getWidth() / 2,
                        landform.getYp() - landform.getHeight() / 2, landform.getWidth(), landform.getHeight(), this);
            }
        }
    }

    /**
     * 绘制不可穿过的地形
     *
     * @param g 图形
     */
    private void paintImpassableObs(Graphics g) {
        for (Landform landform : gf.getService().getImpassableLandform()) {
            g.drawImage(landform.getAppearances().get(0), landform.getXp() - landform.getWidth() / 2,
                    landform.getYp() - landform.getHeight() / 2, landform.getWidth(), landform.getHeight(), this);
        }
    }

    /**
     * 绘制子弹
     *
     * @param g 图形
     */
    private void paintBullet(Graphics g) {
        for (Bullet bullet : gf.getService().getBullets()) {
            paintByDirection(g, bullet.getDirection(), bullet);
        }
    }

    /**
     * 绘制当前盟军坦克
     *
     * @param g 图形
     */
    private void paintAllyTank(Graphics g) {
        for (Model model : gf.getService().getCurrentAllyTanks()) {
            if (model instanceof Seat) {
                continue;
            }
            paintByDirection(g, ((AllyTank) model).getDirection(), model);
        }
    }

    /**
     * 绘制当前敌军坦克
     *
     * @param g 图形
     */
    private void paintEnemyTank(Graphics g) {
        for (Model model : gf.getService().getCurrentEnemyTanks()) {
            if (model instanceof Seat) {
                continue;
            }
            paintByDirection(g, ((EnemyTank) model).getDirection(), model);
        }
    }

    /**
     * 绘制树林
     *
     * @param g 图形
     */
    private void paintWood(Graphics g) {
        for (Landform landform : gf.getService().getPassableLandform()) {
            if (landform.getLandformType() == LandformType.WOOD) {
                g.drawImage(landform.getAppearances().get(0), landform.getXp() - landform.getWidth() / 2,
                        landform.getYp() - landform.getHeight() / 2, landform.getWidth(), landform.getHeight(), this);
            }
        }
    }

    /**
     * 绘制道具包
     *
     * @param g 图形
     */
    private void paintProperty(Graphics g) {
        for (Property property : gf.getService().getProperties()) {
            g.drawImage(property.getAppearances().get(0), property.getXp() - property.getWidth() / 2,
                    property.getYp() - property.getHeight() / 2, property.getWidth(), property.getHeight(), this);
        }
    }

    /**
     * 绘制生命值
     *
     * @param g 图形
     */
    private void paintHealth(Graphics g) {
        for (Model model : gf.getService().getCurrentAllyTanks()) {
            if (model instanceof Seat) {
                continue;
            }
            AllyTank allyTank = (AllyTank) model;
            g.setColor(new Color(225, 225, 225));
            g.fillRect(allyTank.getXp() - 18 - 2, allyTank.getYp() - 18 - 16, 40, 9);
            g.setColor(new Color(60, 160, 60));
            int health = GameData.ALLIES_TANK_HEALTH;
            int currentHealth = allyTank.getHealth();
            int width = 36 * currentHealth / health;
            g.fillRect(allyTank.getXp() - 18, allyTank.getYp() - 18 - 14, width, 5);

        }
        for (Model model : gf.getService().getCurrentEnemyTanks()) {
            if (model instanceof Seat) {
                continue;
            }
            EnemyTank enemyTank = (EnemyTank) model;
            g.setColor(new Color(225, 225, 225));
            g.fillRect(enemyTank.getXp() - 18 - 2, enemyTank.getYp() - 18 - 16, 40, 9);
            g.setColor(new Color(160, 60, 60));
            int health = enemyTank.getEnemyTankType().getHealth() + GameData.ENEMY_TANK_HEALTH_REVISE;
            int currentHealth = enemyTank.getHealth();
            int width = 36 * currentHealth / health;
            g.fillRect(enemyTank.getXp() - 18, enemyTank.getYp() - 18 - 14, width, 5);
        }
    }

    /**
     * 根据模型方向绘制模型
     *
     * @param g         图形
     * @param direction 模型方向
     * @param model     模型对象
     */
    private void paintByDirection(Graphics g, Direction direction, Model model) {
        int width = model.getWidth();
        int height = model.getHeight();
        int xv = model.getXp() - model.getWidth() / 2;
        int yv = model.getYp() - model.getHeight() / 2;
        int xh = model.getXp() - model.getHeight() / 2;
        int yh = model.getYp() - model.getWidth() / 2;
        switch (direction) {
            case UP:
                g.drawImage(model.getAppearances().get(GameImage.UP), xv, yv, width, height, this);
                break;
            case DOWN:
                g.drawImage(model.getAppearances().get(GameImage.DOWN), xv, yv, width, height, this);
                break;
            case LEFT:
                g.drawImage(model.getAppearances().get(GameImage.LEFT), xh, yh, height, width, this);
                break;
            case RIGHT:
                g.drawImage(model.getAppearances().get(GameImage.RIGHT), xh, yh, height, width, this);
                break;
        }
    }

    //get和set方法
    public GameFrame getGf() {
        return gf;
    }
}
