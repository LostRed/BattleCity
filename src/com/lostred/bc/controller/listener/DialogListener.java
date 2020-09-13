package com.lostred.bc.controller.listener;

import com.lostred.bc.util.GameData;
import com.lostred.bc.view.NameDialog;
import com.lostred.bc.view.OptionDialog;
import com.lostred.bc.view.RankListDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogListener implements ActionListener {
    /**
     * 该监听所属的对话框
     */
    private final JDialog owner;

    /**
     * 构造对话框监听
     *
     * @param owner 所属对话框
     */
    public DialogListener(JDialog owner) {
        this.owner = owner;
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
            case "ok":
                //选项对话框
                OptionDialog od = (OptionDialog) owner;
                od.settingGameData();
                od.setVisible(false);
                int temp = GameData.STAGE_NO;
                if (GameData.START) {
                    GameData.PAUSE = true;
                    od.getGf().getService().endStage();
                    GameData.clearScore();
                    GameData.initData();
                    GameData.STAGE_NO = temp;
                    od.getGf().getService().start(GameData.STAGE_NO);
                    GameData.PAUSE = false;
                    if (GameData.GAME_OVER) {
                        GameData.GAME_OVER = false;
                    }
                }
                break;
            case "back":
            case "cancel":
                //所有对话框的取消或返回
                owner.setVisible(false);
                break;
            case "clear":
                //排行榜对话框
                RankListDialog rd = (RankListDialog) owner;
                rd.clearRecord();
                break;
            case "start":
                //输入姓名对话框
                NameDialog nd = (NameDialog) this.owner;
                GameData.START = true;
                nd.getGf().enterGame();
                nd.recordPlayerName();
                new Thread(nd.getGf().getService().getLoading()).start();
                owner.setVisible(false);
                break;
        }
    }
}
