package com.lostred.bc.view;

import com.lostred.bc.controller.listener.GameFrameListener;
import com.lostred.bc.controller.listener.MenuItemListener;

import javax.swing.*;

public class GameMenuBar extends JMenuBar {
    /**
     * 所属主窗口
     */
    private GameFrame gf;
    /**
     * 菜单项监听
     */
    private MenuItemListener menuItemListener;
    /**
     * 游戏菜单
     */
    private JMenu game;
    /**
     * 帮助菜单
     */
    private JMenu help;
    /**
     * 新游戏菜单项
     */
    private JMenuItem newGame;
    /**
     * 重新开始菜单项
     */
    private JMenuItem restart;
    /**
     * 暂停菜单项
     */
    private JMenuItem pause;
    /**
     * 退出菜单项
     */
    private JMenuItem quit;
    /**
     * 关于游戏菜单项
     */
    private JMenuItem about;

    /**
     * 构造菜单栏
     *
     * @param gf 所属的窗体
     */
    public GameMenuBar(GameFrame gf) {
        this.gf = gf;
        // 初始化成员
        this.menuItemListener = new MenuItemListener(gf);
        this.game = new JMenu("游戏 (G)");
        this.help = new JMenu("帮助 (H)");
        this.newGame = new JMenuItem("新游戏");
        this.restart = new JMenuItem("重新开始");
        this.pause = new JMenuItem("暂停/继续");
        JMenuItem option = new JMenuItem("选项");
        JMenuItem rankList = new JMenuItem("排行榜");
        this.quit = new JMenuItem("退出");
        JMenuItem guide = new JMenuItem("按键说明");
        this.about = new JMenuItem("关于游戏");
        GameFrameListener gameFrameListener = new GameFrameListener(gf);
        // 添加一级菜单
        this.add(game);
        this.add(help);
        // 添加菜单项
        this.game.add(newGame);
        this.game.addSeparator();//添加分割线
        this.game.add(restart);
        this.game.add(pause);
        this.game.addSeparator();//添加分割线
        this.game.add(option);
        this.game.add(rankList);
        this.game.addSeparator();//添加分割线
        this.game.add(quit);
        this.help.add(guide);
        this.help.addSeparator();//添加分割线
        this.help.add(about);
        //设置快捷键
        this.game.setMnemonic('G');
        this.help.setMnemonic('H');
        this.newGame.setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
        this.restart.setAccelerator(KeyStroke.getKeyStroke("ctrl R"));
        this.pause.setAccelerator(KeyStroke.getKeyStroke("ctrl P"));
        option.setAccelerator(KeyStroke.getKeyStroke("ctrl O"));
        rankList.setAccelerator(KeyStroke.getKeyStroke("ctrl L"));
        this.quit.setAccelerator(KeyStroke.getKeyStroke("ctrl Q"));
        guide.setAccelerator(KeyStroke.getKeyStroke("ctrl G"));
        this.about.setAccelerator(KeyStroke.getKeyStroke("ctrl A"));
        // 设置监听
        this.addMouseListener(gameFrameListener);
        this.game.addMouseListener(gameFrameListener);
        this.help.addMouseListener(gameFrameListener);
        this.newGame.addActionListener(menuItemListener);
        this.restart.addActionListener(menuItemListener);
        this.pause.addActionListener(menuItemListener);
        option.addActionListener(menuItemListener);
        rankList.addActionListener(menuItemListener);
        this.quit.addActionListener(menuItemListener);
        guide.addActionListener(menuItemListener);
        this.about.addActionListener(menuItemListener);
        this.newGame.setActionCommand("newGame");
        this.restart.setActionCommand("restart");
        this.pause.setActionCommand("pause");
        option.setActionCommand("option");
        rankList.setActionCommand("rankList");
        this.quit.setActionCommand("quit");
        guide.setActionCommand("guide");
        this.about.setActionCommand("about");
        // 设置菜单项
        this.restart.setEnabled(false);
        this.pause.setEnabled(false);
    }

    // get和set方法
    public GameFrame getGf() {
        return gf;
    }

    public void setGf(GameFrame gf) {
        this.gf = gf;
    }

    public MenuItemListener getMenuItemListener() {
        return menuItemListener;
    }

    public void setMenuItemListener(MenuItemListener menuItemListener) {
        this.menuItemListener = menuItemListener;
    }

    public JMenu getGame() {
        return game;
    }

    public void setGame(JMenu game) {
        this.game = game;
    }

    public JMenu getHelp() {
        return help;
    }

    public void setHelp(JMenu help) {
        this.help = help;
    }

    public JMenuItem getNewGame() {
        return newGame;
    }

    public void setNewGame(JMenuItem newGame) {
        this.newGame = newGame;
    }

    public JMenuItem getRestart() {
        return restart;
    }

    public void setRestart(JMenuItem restart) {
        this.restart = restart;
    }

    public JMenuItem getPause() {
        return pause;
    }

    public void setPause(JMenuItem pause) {
        this.pause = pause;
    }

    public JMenuItem getQuit() {
        return quit;
    }

    public void setQuit(JMenuItem quit) {
        this.quit = quit;
    }

    public JMenuItem getAbout() {
        return about;
    }

    public void setAbout(JMenuItem about) {
        this.about = about;
    }
}
