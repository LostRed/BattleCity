package com.lostred.bc.view;

import com.lostred.bc.controller.listener.DialogListener;
import com.lostred.bc.util.GameData;
import com.lostred.bc.util.localFile.GameImage;
import com.lostred.bc.util.setting.GameMode;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * 输入玩家姓名对话框
 */
public class NameDialog extends JDialog {

    /**
     * 所属游戏窗口
     */
    private GameFrame gf;
    /**
     * 玩家1姓名
     */
    private JTextField tfdPlayer1Name;
    /**
     * 玩家2姓名
     */
    private JTextField tfdPlayer2Name;

    /**
     * 构造输入姓名对话框
     *
     * @param gf 游戏窗口
     */
    public NameDialog(GameFrame gf) {
        this.gf = gf;
        this.setTitle("提示");
        this.setResizable(false);
        this.setModal(true);
        //构造组件
        DialogListener dialogListener = new DialogListener(this);
        JPanel panel = new JPanel();
        JPanel checkPanel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JLabel lblPlayer1 = new JLabel("请输入玩家1姓名：");
        JLabel lblPlayer2 = new JLabel("请输入玩家2姓名：");
        JLabel lblIcon1 = new JLabel();
        JLabel lblIcon2 = new JLabel();
        this.tfdPlayer1Name = new JTextField();
        this.tfdPlayer2Name = new JTextField();
        JButton btnStart = new JButton("开始(S)");
        JButton btnBack = new JButton("返回(B)");
        //设置颜色
        lblPlayer1.setForeground(new Color(225, 225, 225));
        lblPlayer2.setForeground(new Color(225, 225, 225));
        btnStart.setForeground(new Color(225, 225, 225));
        btnBack.setForeground(new Color(225, 225, 225));
        panel.setBackground(Color.BLACK);
        panel1.setBackground(Color.BLACK);
        panel2.setBackground(Color.BLACK);
        checkPanel.setBackground(Color.BLACK);
        btnStart.setBackground(Color.BLACK);
        btnBack.setBackground(Color.BLACK);
        //设置大小
        Icon icon1 = GameImage.transToIcon(13, 13, GameImage.ALLY_TANK1_O_UP);
        Icon icon2 = GameImage.transToIcon(13, 13, GameImage.ALLY_TANK2_O_UP);
        lblIcon1.setIcon(icon1);
        lblIcon2.setIcon(icon2);
        this.tfdPlayer1Name.setPreferredSize(new Dimension(150, 12));
        this.tfdPlayer2Name.setPreferredSize(new Dimension(150, 12));
        //设置焦点框
        btnStart.setFocusPainted(false);
        btnBack.setFocusPainted(false);
        //设置按钮加速器
        btnStart.setMnemonic('S');
        btnBack.setMnemonic('B');
        //设置回车的默认按钮
        getRootPane().setDefaultButton(btnStart);
        //设置文本对齐
        lblPlayer1.setHorizontalAlignment(JTextField.LEFT);
        lblPlayer2.setHorizontalAlignment(JTextField.LEFT);
        //设置监听
        btnStart.addActionListener(dialogListener);
        btnBack.addActionListener(dialogListener);
        btnStart.setActionCommand("start");
        btnBack.setActionCommand("back");
        //布局
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        lblPlayer1.setBorder(new EmptyBorder(10, 0, 5, 20));
        lblPlayer2.setBorder(new EmptyBorder(10, 0, 5, 20));
        lblIcon1.setBorder(new EmptyBorder(10, 0, 5, 20));
        lblIcon2.setBorder(new EmptyBorder(10, 0, 5, 20));
        panel1.setBorder(new EmptyBorder(0, 20, 5, 20));
        panel2.setBorder(new EmptyBorder(0, 20, 5, 20));
        checkPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
        this.setLayout(new BorderLayout());
        panel.setLayout(new BorderLayout());
        panel1.setLayout(new BorderLayout());
        panel2.setLayout(new BorderLayout());
        //添加组件
        panel1.add(lblPlayer1, BorderLayout.NORTH);
        panel1.add(lblIcon1, BorderLayout.WEST);
        panel1.add(tfdPlayer1Name, BorderLayout.CENTER);
        panel2.add(lblPlayer2, BorderLayout.NORTH);
        panel2.add(lblIcon2, BorderLayout.WEST);
        panel2.add(tfdPlayer2Name, BorderLayout.CENTER);
        panel.add(panel1, BorderLayout.NORTH);
        if (GameData.GAME_MODE == GameMode.DOUBLE) {
            panel.add(panel2, BorderLayout.CENTER);
        }
        checkPanel.add(btnStart);
        checkPanel.add(btnBack);
        this.add(panel);
        this.add(checkPanel, BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(gf);
    }

    /**
     * 记录玩家姓名
     */
    public void recordPlayerName() {
        String name = this.tfdPlayer1Name.getText();
        this.gf.getStatePanel().getTfdPlayer1Name().setText(name);
        if (GameData.GAME_MODE == GameMode.DOUBLE) {
            String name2 = this.tfdPlayer2Name.getText();
            this.gf.getStatePanel().getTfdPlayer2Name().setText(name2);
        }

    }

    //get和set方法
    public GameFrame getGf() {
        return gf;
    }

    public void setGf(GameFrame gf) {
        this.gf = gf;
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
}
