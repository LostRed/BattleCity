package com.lostred.bc.view;

import com.lostred.bc.controller.listener.DialogListener;
import com.lostred.bc.util.Record;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * 排行榜对话框
 */
public class RankListDialog extends JDialog {
    /**
     * 所属游戏窗口
     */
    private GameFrame gf;
    /**
     * 第一玩家姓名
     */
    private JLabel lblFirstName;
    /**
     * 第一玩家得分
     */
    private JLabel lblFirstScore;
    /**
     * 第二玩家姓名
     */
    private JLabel lblSecondName;
    /**
     * 第二玩家得分
     */
    private JLabel lblSecondScore;
    /**
     * 第三玩家姓名
     */
    private JLabel lblThirdName;
    /**
     * 第三玩家得分
     */
    private JLabel lblThirdScore;
    /**
     * 事件监听
     */
    private DialogListener dialogListener;

    /**
     * 构造排行榜对话框
     *
     * @param gf 游戏窗口
     */
    public RankListDialog(GameFrame gf) {
        this.gf = gf;
        this.setTitle("排行榜");
        this.setResizable(false);
        this.setModal(true);
        //构造组件
        JPanel panel = new JPanel();
        JPanel checkPanel = new JPanel();
        JLabel lblTitle1 = new JLabel("玩家");
        JLabel lblTitle2 = new JLabel("得分");
        this.dialogListener = new DialogListener(this);
        this.lblFirstName = new JLabel("-");
        this.lblFirstScore = new JLabel("-");
        this.lblSecondName = new JLabel("-");
        this.lblSecondScore = new JLabel("-");
        this.lblThirdName = new JLabel("-");
        this.lblThirdScore = new JLabel("-");
        JButton btnClear = new JButton("清空(C)");
        JButton btnBack = new JButton("返回(B)");
        //设置大小
        this.lblFirstName.setPreferredSize(new Dimension(100, 30));
        this.lblSecondName.setPreferredSize(new Dimension(100, 30));
        this.lblThirdName.setPreferredSize(new Dimension(100, 30));
        //设置焦点框
        btnClear.setFocusPainted(false);
        btnBack.setFocusPainted(false);
        //设置按钮加速器
        btnClear.setMnemonic('C');
        btnBack.setMnemonic('B');
        //设置回车的默认按钮
        getRootPane().setDefaultButton(btnBack);
        //设置监听
        btnClear.addActionListener(dialogListener);
        btnBack.addActionListener(dialogListener);
        btnClear.setActionCommand("clear");
        btnBack.setActionCommand("back");
        //布局
        lblTitle1.setBorder(new EmptyBorder(5, 20, 5, 20));
        lblTitle2.setBorder(new EmptyBorder(5, 20, 5, 20));
        this.lblFirstName.setBorder(new EmptyBorder(5, 20, 5, 20));
        this.lblSecondName.setBorder(new EmptyBorder(5, 20, 5, 20));
        this.lblThirdName.setBorder(new EmptyBorder(5, 20, 5, 20));
        this.lblFirstScore.setBorder(new EmptyBorder(5, 20, 5, 20));
        this.lblSecondScore.setBorder(new EmptyBorder(5, 20, 5, 20));
        this.lblThirdScore.setBorder(new EmptyBorder(5, 20, 5, 20));
        panel.setLayout(new GridLayout(4, 2));
        panel.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 5, 10),
                new TitledBorder(null, "最高得分", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION)));
        checkPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
        //添加组件
        panel.add(lblTitle1);
        panel.add(lblTitle2);
        panel.add(lblFirstName);
        panel.add(lblFirstScore);
        panel.add(lblSecondName);
        panel.add(lblSecondScore);
        panel.add(lblThirdName);
        panel.add(lblThirdScore);
        checkPanel.add(btnClear);
        checkPanel.add(btnBack);
        this.add(panel);
        this.add(checkPanel, BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(gf);
    }

    /**
     * 清除排行版记录
     */
    public void clearRecord() {
        this.lblFirstName.setText("-");
        this.lblFirstScore.setText("-");
        this.lblSecondName.setText("-");
        this.lblSecondScore.setText("-");
        this.lblThirdName.setText("-");
        this.lblThirdScore.setText("-");
        Record.writeProperties("firstName", "-");
        Record.writeProperties("secondName", "-");
        Record.writeProperties("thirdName", "-");
        Record.writeProperties("firstScore", "-");
        Record.writeProperties("secondScore", "-");
        Record.writeProperties("thirdScore", "-");
    }

    /**
     * 读取排行榜记录
     */
    public void readRecord() {
        String firstName = Record.readPropertiesValue("firstName");
        String secondName = Record.readPropertiesValue("secondName");
        String thirdName = Record.readPropertiesValue("thirdName");
        String firstScore = Record.readPropertiesValue("firstScore");
        String secondScore = Record.readPropertiesValue("secondScore");
        String thirdScore = Record.readPropertiesValue("thirdScore");
        if (firstName != null) {
            this.lblFirstName.setText(firstName);
        }
        if (secondName != null) {
            this.lblSecondName.setText(secondName);
        }
        if (thirdName != null) {
            this.lblThirdName.setText(thirdName);
        }
        if (firstScore != null) {
            this.lblFirstScore.setText(firstScore);
        }
        if (secondScore != null) {
            this.lblSecondScore.setText(secondScore);
        }
        if (thirdScore != null) {
            this.lblThirdScore.setText(thirdScore);
        }
    }

    //get和set方法
    public GameFrame getGf() {
        return gf;
    }

    public void setGf(GameFrame gf) {
        this.gf = gf;
    }

    public JLabel getLblFirstName() {
        return lblFirstName;
    }

    public void setLblFirstName(JLabel lblFirstName) {
        this.lblFirstName = lblFirstName;
    }

    public JLabel getLblFirstScore() {
        return lblFirstScore;
    }

    public void setLblFirstScore(JLabel lblFirstScore) {
        this.lblFirstScore = lblFirstScore;
    }

    public JLabel getLblSecondName() {
        return lblSecondName;
    }

    public void setLblSecondName(JLabel lblSecondName) {
        this.lblSecondName = lblSecondName;
    }

    public JLabel getLblSecondScore() {
        return lblSecondScore;
    }

    public void setLblSecondScore(JLabel lblSecondScore) {
        this.lblSecondScore = lblSecondScore;
    }

    public JLabel getLblThirdName() {
        return lblThirdName;
    }

    public void setLblThirdName(JLabel lblThirdName) {
        this.lblThirdName = lblThirdName;
    }

    public JLabel getLblThirdScore() {
        return lblThirdScore;
    }

    public void setLblThirdScore(JLabel lblThirdScore) {
        this.lblThirdScore = lblThirdScore;
    }

    public DialogListener getDialogListener() {
        return dialogListener;
    }

    public void setDialogListener(DialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }
}
