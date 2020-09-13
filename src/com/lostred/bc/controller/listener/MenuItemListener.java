package com.lostred.bc.controller.listener;

import com.lostred.bc.controller.thread.animation.Pause;
import com.lostred.bc.util.GameData;
import com.lostred.bc.util.setting.GameMode;
import com.lostred.bc.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 游戏窗口菜单栏监听
 */
public class MenuItemListener implements ActionListener {
    /**
     * 监听的游戏窗口
     */
    private final GameFrame gf;

    /**
     * 构造游戏窗口菜单栏监听
     *
     * @param gf 游戏窗口
     */
    public MenuItemListener(GameFrame gf) {
        this.gf = gf;
    }

    /**
     * 按照事件命令触发调用对应的方法
     *
     * @param e 动作事件
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "newGame":
                if (!GameData.START && GameData.GAME_MODE == GameMode.SINGLE || GameData.GAME_MODE == GameMode.DOUBLE) {
                    new NameDialog(gf).setVisible(true);
                } else if (GameData.GAME_MODE == GameMode.CONSTRUCTION) {
                    gf.enterConstruction();
                } else {
                    //停止通关音乐
                    gf.getService().getGs().stopTheEnd();
                    gf.returnTheme();
                    GameData.START = false;
                    GameData.GAME_OVER = false;
                }
                break;
            case "restart":
                if (GameData.START) {
                    //停止关卡开始音乐
                    gf.getService().getGs().stop(gf.getService().getGs().getStartSound());
                    //结束关卡
                    gf.getService().endStage();
                    //初始化游戏数据
                    GameData.initData();
                }
                //返回主界面
                gf.returnTheme();
                GameData.START = false;
                GameData.GAME_OVER = false;
                break;
            case "pause":
                if (GameData.PAUSE) {
                    GameData.PAUSE = false;
                } else {
                    GameData.PAUSE = true;
                    new Pause(gf).start();
                }
                break;
            case "option":
                if (gf.getOptionDialog() == null) {
                    gf.setOptionDialog(new OptionDialog(gf));
                    gf.getOptionDialog().readConfig();
                }
                gf.getOptionDialog().setVisible(true);
                break;
            case "rankList":
                if (gf.getRankListDialog() == null) {
                    RankListDialog rd = new RankListDialog(gf);
                    rd.readRecord();
                    gf.setRankListDialog(rd);
                }
                gf.getRankListDialog().setVisible(true);
                break;
            case "quit":
                int selection = JOptionPane.showConfirmDialog(gf, "确定要退出吗？", "提示", JOptionPane.OK_CANCEL_OPTION);
                if (selection == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
                break;
            case "guide":
                new DescriptionDialog(gf).setVisible(true);
                break;
            case "about":
                new AboutGameDialog(gf).setVisible(true);
                break;
        }
    }
}
