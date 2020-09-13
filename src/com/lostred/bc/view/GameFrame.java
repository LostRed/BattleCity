package com.lostred.bc.view;

import com.lostred.bc.controller.listener.*;
import com.lostred.bc.controller.thread.*;
import com.lostred.bc.model.Model;
import com.lostred.bc.model.tank.EnemyTank;
import com.lostred.bc.service.Service;
import com.lostred.bc.util.GameData;
import com.lostred.bc.util.setting.EnemyTankType;
import com.lostred.bc.util.setting.GameMode;
import com.lostred.bc.view.panel.*;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;

/**
 * 游戏窗口
 */
public class GameFrame extends JFrame {
    //线程
    private GameAction gameAction;
    private AllyTankAction allyTankAction;
    private AllyTankBirth allyTankBirth;
    private EnemyTankAction enemyTankAction;
    private EnemyTankBirth enemyTankBirth;
    private BulletAction bulletAction;
    //定时器
    private Timer gameActionTimer;
    private Timer allyTankActionTimer;
    private Timer allyTankBirthTimer;
    private Timer enemyTankActionTimer;
    private Timer enemyTankBirthTimer;
    private Timer bulletActionTimer;
    //监听
    private GameFrameListener gameFrameListener;
    private PlayerCtrl player1Ctrl;
    private PlayerCtrl player2Ctrl;
    //窗体内容
    private GameMenuBar gameMenuBar;
    private JPanel panel;
    private ThemePanel themePanel;
    private ProloguePanel prologuePanel;
    private GamePanel gamePanel;
    private StatePanel statePanel;
    private TheEndPanel theEndPanel;
    private ConstructionPanel constructionPanel;
    private OptionDialog optionDialog;
    private RankListDialog rankListDialog;
    //后台控制
    private Service service;
    //主界面面板飞入
    private Runnable themePanelFlyIn;

    /**
     * 构造游戏窗口
     */
    public GameFrame() {
        //监听初始化
        this.gameFrameListener = new GameFrameListener(this);
        //窗体内容初始化
        this.gameMenuBar = new GameMenuBar(this);
        this.panel = new JPanel();
        this.themePanel = new ThemePanel(this);
        this.prologuePanel = new ProloguePanel();
        this.gamePanel = new GamePanel(this);
        this.statePanel = new StatePanel(this);
        this.theEndPanel = new TheEndPanel();
        this.constructionPanel = new ConstructionPanel(this);
        this.optionDialog = new OptionDialog(this);
        this.rankListDialog = new RankListDialog(this);
        this.optionDialog.readConfig();
        this.rankListDialog.readRecord();
        //后台控制
        this.service = new Service(this);
        //窗体设置
        this.setTitle("坦克大战 Battle City");
        this.setIconImage(new ImageIcon("image/Title.png").getImage());
        this.setJMenuBar(gameMenuBar);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.panel.setLayout(null);
        this.panel.setBackground(Color.BLACK);
        this.panel.setPreferredSize(new Dimension(ThemePanel.PANEL_WIDTH, ThemePanel.PANEL_HEIGHT));
        //添加面板
        this.panel.add(themePanel);
        this.add(panel);
        this.add(statePanel, BorderLayout.EAST);
        //添加监听
        this.addWindowListener(gameFrameListener);
        this.addMouseListener(gameFrameListener);
        //调整窗口位置
        this.pack();
        this.setLocationRelativeTo(null);
        this.themePanelFlyIn = () -> {
            for (int x = ThemePanel.PANEL_WIDTH; x > 0; x--) {
                this.themePanel.setBounds(x, 0, ThemePanel.PANEL_WIDTH, ThemePanel.PANEL_HEIGHT);
                try {
                    Thread.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                GameData.START = false;
            }
            this.themePanel.requestFocus();
        };
        new Thread(this.themePanelFlyIn).start();
    }

    /**
     * 初始化控制器
     */
    public void initCtrl() {
        if (GameData.GAME_MODE == GameMode.SINGLE) {
            this.player1Ctrl = new Player1Ctrl(this);
            this.addKeyListener(player1Ctrl);
        } else if (GameData.GAME_MODE == GameMode.DOUBLE) {
            this.player1Ctrl = new Player1Ctrl(this);
            this.addKeyListener(player1Ctrl);
            this.player2Ctrl = new Player2Ctrl(this);
            this.addKeyListener(player2Ctrl);
        }
    }

    /**
     * 解除控制器
     */
    public void cancelCtrl() {
        if (GameData.GAME_MODE == GameMode.SINGLE) {
            this.removeKeyListener(player1Ctrl);
        } else if (GameData.GAME_MODE == GameMode.DOUBLE) {
            this.removeKeyListener(player1Ctrl);
            this.removeKeyListener(player2Ctrl);
        }
    }

    /**
     * 初始化线程
     */
    public void initTimer() {
        //线程初始化
        this.gameAction = new GameAction(this);
        this.allyTankAction = new AllyTankAction(this);
        this.allyTankBirth = new AllyTankBirth(this);
        this.bulletAction = new BulletAction(this);
        this.enemyTankAction = new EnemyTankAction(this);
        this.enemyTankBirth = new EnemyTankBirth(this);
        //定时器初始化
        this.gameActionTimer = new Timer();
        this.allyTankActionTimer = new Timer();
        this.allyTankBirthTimer = new Timer();
        this.bulletActionTimer = new Timer();
        this.enemyTankActionTimer = new Timer();
        this.enemyTankBirthTimer = new Timer();
        //线程启动
        this.gameActionTimer.schedule(this.gameAction, 500, 60);
        this.allyTankActionTimer.scheduleAtFixedRate(this.allyTankAction, 0, 30);
        this.bulletActionTimer.scheduleAtFixedRate(this.bulletAction, 0, 10);
        this.enemyTankActionTimer.scheduleAtFixedRate(this.enemyTankAction, 0, 30);
        this.allyTankBirthTimer.schedule(this.allyTankBirth, 1000, 1000);
        this.enemyTankBirthTimer.schedule(this.enemyTankBirth, 500, 1500);
    }

    /**
     * 解除线程
     */
    public void cancelTimer() {
        this.gameActionTimer.cancel();
        this.allyTankActionTimer.cancel();
        this.bulletActionTimer.cancel();
        this.enemyTankActionTimer.cancel();
        this.enemyTankBirthTimer.cancel();
        this.gameAction.cancel();
        this.allyTankAction.cancel();
        this.bulletAction.cancel();
        this.enemyTankAction.cancel();
        this.enemyTankBirth.cancel();
    }

    /**
     * 进入游戏的转场：切换面板
     */
    public void enterGame() {
        this.gameMenuBar.getNewGame().setEnabled(false);
        this.gameMenuBar.getRestart().setEnabled(true);
        this.gameMenuBar.getPause().setEnabled(true);
        this.remove(this.panel);
        this.add(this.prologuePanel);
        this.prologuePanel.updateUI();
    }

    /**
     * 进入自定义地图的转场：切换面板
     */
    public void enterConstruction() {
        this.gameMenuBar.getNewGame().setEnabled(false);
        this.gameMenuBar.getRestart().setEnabled(true);
        this.remove(this.panel);
        this.add(this.constructionPanel);
        this.constructionPanel.updateUI();
        this.constructionPanel.requestFocus();
    }

    /**
     * 进入通关画面：切换面板
     */
    public void enterTheEnd() {
        this.gameMenuBar.getNewGame().setEnabled(true);
        this.gameMenuBar.getRestart().setEnabled(false);
        this.gameMenuBar.getPause().setEnabled(false);
        this.remove(this.gamePanel);
        this.remove(this.statePanel);
        this.add(this.theEndPanel);
        this.theEndPanel.updateUI();
    }

    /**
     * 返回主界面的转场：切换面板
     */
    public void returnTheme() {
        this.gameMenuBar.getNewGame().setEnabled(true);
        this.gameMenuBar.getRestart().setEnabled(false);
        this.gameMenuBar.getPause().setEnabled(false);
        this.remove(this.theEndPanel);
        this.remove(this.gamePanel);
        this.remove(this.constructionPanel);
        this.add(this.panel);
        this.add(this.statePanel, BorderLayout.EAST);
        this.panel.updateUI();
        this.statePanel.updateUI();
        new Thread(this.themePanelFlyIn).start();
    }

    /**
     * 更新状态面板
     */
    public void updateStatePanel() {
        int commonEnemyTank = 0;
        int speedEnemyTank = 0;
        int fireEnemyTank = 0;
        int heavyEnemyTank = 0;
        for (Model model : this.service.getEnemyTanks()) {
            EnemyTank enemyTank = (EnemyTank) model;
            if (enemyTank.getEnemyTankType() == EnemyTankType.COMMON_TYPE) {
                commonEnemyTank++;
            } else if (enemyTank.getEnemyTankType() == EnemyTankType.SPEED_TYPE) {
                speedEnemyTank++;
            } else if (enemyTank.getEnemyTankType() == EnemyTankType.FIRE_TYPE) {
                fireEnemyTank++;
            } else if (enemyTank.getEnemyTankType() == EnemyTankType.HEAVY_TYPE) {
                heavyEnemyTank++;
            }
        }
        this.statePanel.getTfdCommonType().setText("× " + commonEnemyTank);
        this.statePanel.getTfdSpeedType().setText("× " + speedEnemyTank);
        this.statePanel.getTfdFireType().setText("× " + fireEnemyTank);
        this.statePanel.getTfdHeavyType().setText("× " + heavyEnemyTank);
        this.statePanel.getTfdPlayer1Score().setText(GameData.PLAYER1_SCORE + "");
        if (GameData.GAME_MODE == GameMode.DOUBLE) {
            this.statePanel.getTfdPlayer2Score().setText(GameData.PLAYER2_SCORE + "");
        }
        this.statePanel.getTfdTotalScore().setText(GameData.PLAYER1_SCORE + GameData.PLAYER2_SCORE + "");
    }

    /**
     * 清空状态面板
     */
    public void clearStatePanel() {
        this.statePanel.getTfdCommonType().setText("");
        this.statePanel.getTfdSpeedType().setText("");
        this.statePanel.getTfdFireType().setText("");
        this.statePanel.getTfdHeavyType().setText("");
        this.statePanel.getTfdPlayer1Score().setText("");
        this.statePanel.getTfdPlayer2Score().setText("");
        this.statePanel.getTfdTotalScore().setText("");
    }

    /**
     * 添加玩家姓名
     */
    public void addPlayerName() {
        this.statePanel.add(this.statePanel.getTfdPlayer1Name());
        if (GameData.GAME_MODE == GameMode.DOUBLE) {
            this.statePanel.add(this.statePanel.getTfdPlayer2Name());
        }
        this.statePanel.add(this.statePanel.getTfdTotalName());
    }

    /**
     * 移除玩家姓名
     */
    public void removePlayerName() {
        this.statePanel.remove(this.statePanel.getTfdPlayer1Name());
        if (GameData.GAME_MODE == GameMode.DOUBLE) {
            this.statePanel.remove(this.statePanel.getTfdPlayer2Name());
        }
        this.statePanel.remove(this.statePanel.getTfdTotalName());
    }

    /**
     * 显示排行榜
     */
    public void showRankList() {
        String name1 = this.statePanel.getTfdPlayer1Name().getText();
        int score1 = GameData.PLAYER1_SCORE;
        this.service.writeRecord(name1, score1);
        if (GameData.GAME_MODE == GameMode.DOUBLE) {
            String name2 = this.statePanel.getTfdPlayer2Name().getText();
            int score2 = GameData.PLAYER2_SCORE;
            this.service.writeRecord(name2, score2);
        }
        if (this.rankListDialog == null) {
            this.rankListDialog = new RankListDialog(this);
        }
        this.rankListDialog.readRecord();
        this.rankListDialog.setVisible(true);
    }

    //get和set方法
    public GameAction getGameAction() {
        return gameAction;
    }

    public void setGameAction(GameAction gameAction) {
        this.gameAction = gameAction;
    }

    public AllyTankAction getAllyTankAction() {
        return allyTankAction;
    }

    public void setAllyTankAction(AllyTankAction allyTankAction) {
        this.allyTankAction = allyTankAction;
    }

    public AllyTankBirth getAllyTankBirth() {
        return allyTankBirth;
    }

    public void setAllyTankBirth(AllyTankBirth allyTankBirth) {
        this.allyTankBirth = allyTankBirth;
    }

    public EnemyTankAction getEnemyTankAction() {
        return enemyTankAction;
    }

    public void setEnemyTankAction(EnemyTankAction enemyTankAction) {
        this.enemyTankAction = enemyTankAction;
    }

    public EnemyTankBirth getEnemyTankBirth() {
        return enemyTankBirth;
    }

    public void setEnemyTankBirth(EnemyTankBirth enemyTankBirth) {
        this.enemyTankBirth = enemyTankBirth;
    }

    public BulletAction getBulletAction() {
        return bulletAction;
    }

    public void setBulletAction(BulletAction bulletAction) {
        this.bulletAction = bulletAction;
    }

    public Timer getGameActionTimer() {
        return gameActionTimer;
    }

    public void setGameActionTimer(Timer gameActionTimer) {
        this.gameActionTimer = gameActionTimer;
    }

    public Timer getAllyTankActionTimer() {
        return allyTankActionTimer;
    }

    public void setAllyTankActionTimer(Timer allyTankActionTimer) {
        this.allyTankActionTimer = allyTankActionTimer;
    }

    public Timer getAllyTankBirthTimer() {
        return allyTankBirthTimer;
    }

    public void setAllyTankBirthTimer(Timer allyTankBirthTimer) {
        this.allyTankBirthTimer = allyTankBirthTimer;
    }

    public Timer getEnemyTankActionTimer() {
        return enemyTankActionTimer;
    }

    public void setEnemyTankActionTimer(Timer enemyTankActionTimer) {
        this.enemyTankActionTimer = enemyTankActionTimer;
    }

    public Timer getEnemyTankBirthTimer() {
        return enemyTankBirthTimer;
    }

    public void setEnemyTankBirthTimer(Timer enemyTankBirthTimer) {
        this.enemyTankBirthTimer = enemyTankBirthTimer;
    }

    public Timer getBulletActionTimer() {
        return bulletActionTimer;
    }

    public void setBulletActionTimer(Timer bulletActionTimer) {
        this.bulletActionTimer = bulletActionTimer;
    }

    public GameFrameListener getGameFrameListener() {
        return gameFrameListener;
    }

    public void setGameFrameListener(GameFrameListener gameFrameListener) {
        this.gameFrameListener = gameFrameListener;
    }

    public PlayerCtrl getPlayer1Ctrl() {
        return player1Ctrl;
    }

    public void setPlayer1Ctrl(PlayerCtrl player1Ctrl) {
        this.player1Ctrl = player1Ctrl;
    }

    public PlayerCtrl getPlayer2Ctrl() {
        return player2Ctrl;
    }

    public void setPlayer2Ctrl(PlayerCtrl player2Ctrl) {
        this.player2Ctrl = player2Ctrl;
    }

    public GameMenuBar getGameMenuBar() {
        return gameMenuBar;
    }

    public void setGameMenuBar(GameMenuBar gameMenuBar) {
        this.gameMenuBar = gameMenuBar;
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    public ThemePanel getThemePanel() {
        return themePanel;
    }

    public void setThemePanel(ThemePanel themePanel) {
        this.themePanel = themePanel;
    }

    public ProloguePanel getProloguePanel() {
        return prologuePanel;
    }

    public void setProloguePanel(ProloguePanel prologuePanel) {
        this.prologuePanel = prologuePanel;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public StatePanel getStatePanel() {
        return statePanel;
    }

    public void setStatePanel(StatePanel statePanel) {
        this.statePanel = statePanel;
    }

    public TheEndPanel getTheEndPanel() {
        return theEndPanel;
    }

    public void setTheEndPanel(TheEndPanel theEndPanel) {
        this.theEndPanel = theEndPanel;
    }

    public ConstructionPanel getConstructionPanel() {
        return constructionPanel;
    }

    public void setConstructionPanel(ConstructionPanel constructionPanel) {
        this.constructionPanel = constructionPanel;
    }

    public OptionDialog getOptionDialog() {
        return optionDialog;
    }

    public void setOptionDialog(OptionDialog optionDialog) {
        this.optionDialog = optionDialog;
    }

    public RankListDialog getRankListDialog() {
        return rankListDialog;
    }

    public void setRankListDialog(RankListDialog rankListDialog) {
        this.rankListDialog = rankListDialog;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Runnable getThemePanelFlyIn() {
        return themePanelFlyIn;
    }

    public void setThemePanelFlyIn(Runnable themePanelFlyIn) {
        this.themePanelFlyIn = themePanelFlyIn;
    }
}
