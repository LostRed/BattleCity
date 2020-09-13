package com.lostred.bc.view;

import com.lostred.bc.controller.listener.DialogListener;
import com.lostred.bc.util.GBC;
import com.lostred.bc.util.GameData;
import com.lostred.bc.util.Record;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.Objects;

public class OptionDialog extends JDialog {
    /**
     * 所属游戏窗口
     */
    private GameFrame gf;
    /**
     * 选择关卡下拉框
     */
    private JComboBox<String> stageNo;
    /**
     * 盟军坦克生命值下拉框
     */
    private JComboBox<String> allyTankHealth;
    /**
     * 敌军坦克生命值修正下拉框
     */
    private JComboBox<String> enemyTankHealthRevise;
    /**
     * 是否显示生命值下拉框
     */
    private JComboBox<String> showHealth;
    /**
     * 事件监听
     */
    private DialogListener dialogListener;

    /**
     * 构造选项对话框
     *
     * @param gf 游戏窗口
     */
    public OptionDialog(GameFrame gf) {
        this.gf = gf;
        this.setTitle("选项");
        this.setResizable(false);
        this.setModal(true);
        //构造控件
        JPanel panel = new JPanel();
        JPanel checkPanel = new JPanel();
        String[] stageNo = new String[]{"1", "2", "3", "4", "5"};
        String[] allyTankHealth = new String[]{"1", "2", "3"};
        String[] enemyTankHealthRevise = new String[]{"0", "+1", "+2", "+3"};
        String[] showHealth = new String[]{"是", "否"};
        this.dialogListener = new DialogListener(this);
        this.stageNo = new JComboBox<>(stageNo);
        this.allyTankHealth = new JComboBox<>(allyTankHealth);
        this.enemyTankHealthRevise = new JComboBox<>(enemyTankHealthRevise);
        this.showHealth = new JComboBox<>(showHealth);
        JLabel lblStageNo = new JLabel("选择开始关卡：");
        JLabel lblAllyTankHealth = new JLabel("盟军坦克生命值：");
        JLabel lblEnemyTankHealthRevise = new JLabel("敌军坦克生命值修正：");
        JLabel lblShowHealth = new JLabel("是否显示坦克生命值：");
        JButton btnOK = new JButton("确定(O)");
        JButton btnCancel = new JButton("取消(C)");
        //设置焦点框
        btnOK.setFocusPainted(false);
        btnCancel.setFocusPainted(false);
        //设置按钮加速器
        btnOK.setMnemonic('O');
        btnCancel.setMnemonic('C');
        //设置回车的默认按钮
        getRootPane().setDefaultButton(btnOK);
        //设置监听
        btnOK.addActionListener(dialogListener);
        btnCancel.addActionListener(dialogListener);
        btnOK.setActionCommand("ok");
        btnCancel.setActionCommand("cancel");
        //布局
        lblStageNo.setBorder(new EmptyBorder(5, 20, 5, 5));
        lblAllyTankHealth.setBorder(new EmptyBorder(5, 20, 5, 5));
        lblEnemyTankHealthRevise.setBorder(new EmptyBorder(5, 20, 5, 5));
        lblShowHealth.setBorder(new EmptyBorder(5, 20, 5, 5));
        this.stageNo.setBorder(new EmptyBorder(5, 5, 5, 20));
        this.allyTankHealth.setBorder(new EmptyBorder(5, 5, 5, 20));
        this.enemyTankHealthRevise.setBorder(new EmptyBorder(5, 5, 5, 20));
        this.showHealth.setBorder(new EmptyBorder(5, 5, 5, 20));
        panel.setLayout(new GridBagLayout());
        panel.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 5, 10),
                new TitledBorder(null, "自定义游戏", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION)));
        checkPanel.setBorder(new EmptyBorder(0, 5, 5, 5));
        //添加组件
        panel.add(lblStageNo, new GBC(0, 0, 2, 1).setFill(GBC.HORIZONTAL).setInsets(5));
        panel.add(this.stageNo, new GBC(2, 0, 1, 1).setFill(GBC.HORIZONTAL).setInsets(5));
        panel.add(lblAllyTankHealth, new GBC(0, 1, 2, 1).setFill(GBC.HORIZONTAL).setInsets(5));
        panel.add(this.allyTankHealth, new GBC(2, 1, 1, 1).setFill(GBC.HORIZONTAL).setInsets(5));
        panel.add(lblEnemyTankHealthRevise, new GBC(0, 2, 2, 1).setFill(GBC.HORIZONTAL).setInsets(5));
        panel.add(this.enemyTankHealthRevise, new GBC(2, 2, 1, 1).setFill(GBC.HORIZONTAL).setInsets(5));
        panel.add(lblShowHealth, new GBC(0, 3, 2, 1).setFill(GBC.HORIZONTAL).setInsets(5));
        panel.add(this.showHealth, new GBC(2, 3, 1, 1).setFill(GBC.HORIZONTAL).setInsets(5));
        checkPanel.add(btnOK);
        checkPanel.add(btnCancel);
        this.add(panel);
        this.add(checkPanel, BorderLayout.SOUTH);
        this.pack();
        this.setLocationRelativeTo(gf);
    }

    /**
     * 设置游戏数据
     */
    public void settingGameData() {
        GameData.STAGE_NO = Integer.parseInt((String) Objects.requireNonNull(this.stageNo.getSelectedItem()));
        GameData.ALLIES_TANK_HEALTH = Integer.parseInt((String) Objects.requireNonNull(this.allyTankHealth.getSelectedItem()));
        GameData.ENEMY_TANK_HEALTH_REVISE = Integer.parseInt((String) Objects.requireNonNull(this.enemyTankHealthRevise.getSelectedItem()));
        if (this.showHealth.getSelectedItem().equals("是")) {
            GameData.SHOW_HEALTH = true;
        } else {
            GameData.SHOW_HEALTH = false;
        }
        //写入配置文件
        Record.writeProperties("ALLIES_TANK_HEALTH", Objects.requireNonNull(gf.getOptionDialog().getAllyTankHealth().getSelectedItem()).toString());
        Record.writeProperties("ENEMY_TANK_HEALTH_REVISE", Objects.requireNonNull(gf.getOptionDialog().getEnemyTankHealthRevise().getSelectedItem()).toString());
        Record.writeProperties("SHOW_HEALTH", Objects.requireNonNull(gf.getOptionDialog().getShowHealth().getSelectedItem()).toString());
    }

    /**
     * 读取选项设置
     */
    public void readConfig() {
        if (Record.readPropertiesValue("ALLIES_TANK_HEALTH") != null) {
            GameData.ALLIES_TANK_HEALTH = Integer.parseInt(Objects.requireNonNull(Record.readPropertiesValue("ALLIES_TANK_HEALTH")));
            switch (GameData.ALLIES_TANK_HEALTH) {
                case 1:
                    this.allyTankHealth.setSelectedItem("1");
                    break;
                case 2:
                    this.allyTankHealth.setSelectedItem("2");
                    break;
                case 3:
                    this.allyTankHealth.setSelectedItem("3");
                    break;
            }
        }
        if (Record.readPropertiesValue("ENEMY_TANK_HEALTH_REVISE") != null) {
            GameData.ENEMY_TANK_HEALTH_REVISE = Integer.parseInt(Objects.requireNonNull(Record.readPropertiesValue("ENEMY_TANK_HEALTH_REVISE")));
            switch (GameData.ENEMY_TANK_HEALTH_REVISE) {
                case 0:
                    this.enemyTankHealthRevise.setSelectedItem("0");
                    break;
                case 1:
                    this.enemyTankHealthRevise.setSelectedItem("+1");
                    break;
                case 2:
                    this.enemyTankHealthRevise.setSelectedItem("+2");
                    break;
                case 3:
                    this.enemyTankHealthRevise.setSelectedItem("+3");
                    break;
            }
        }
        if ("是".equals(Record.readPropertiesValue("SHOW_HEALTH"))) {
            GameData.SHOW_HEALTH = true;
            this.showHealth.setSelectedItem("是");
        } else if ("否".equals(Record.readPropertiesValue("SHOW_HEALTH"))) {
            GameData.SHOW_HEALTH = false;
            this.showHealth.setSelectedItem("否");
        }
    }

    //get和set方法
    public GameFrame getGf() {
        return gf;
    }

    public void setGf(GameFrame gf) {
        this.gf = gf;
    }

    public JComboBox<String> getStageNo() {
        return stageNo;
    }

    public void setStageNo(JComboBox<String> stageNo) {
        this.stageNo = stageNo;
    }

    public JComboBox<String> getAllyTankHealth() {
        return allyTankHealth;
    }

    public void setAllyTankHealth(JComboBox<String> allyTankHealth) {
        this.allyTankHealth = allyTankHealth;
    }

    public JComboBox<String> getEnemyTankHealthRevise() {
        return enemyTankHealthRevise;
    }

    public void setEnemyTankHealthRevise(JComboBox<String> enemyTankHealthRevise) {
        this.enemyTankHealthRevise = enemyTankHealthRevise;
    }

    public JComboBox<String> getShowHealth() {
        return showHealth;
    }

    public void setShowHealth(JComboBox<String> showHealth) {
        this.showHealth = showHealth;
    }

    public DialogListener getDialogListener() {
        return dialogListener;
    }

    public void setDialogListener(DialogListener dialogListener) {
        this.dialogListener = dialogListener;
    }
}
