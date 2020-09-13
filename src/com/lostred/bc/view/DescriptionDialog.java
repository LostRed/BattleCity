package com.lostred.bc.view;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * 按键说明对话框
 */
public class DescriptionDialog extends JDialog {
    /**
     * 构造关于游戏对话框
     *
     * @param gf 游戏窗口
     */
    public DescriptionDialog(GameFrame gf) {
        this.setTitle("按键说明");
        this.setResizable(false);
        this.setModal(true);
        //构造控件
        JPanel panel = new JPanel();
        JLabel lblTitle1 = new JLabel("动作");
        JLabel lblTitle2 = new JLabel("玩家1");
        JLabel lblTitle3 = new JLabel("玩家2");
        JLabel lblUp = new JLabel("向上移动");
        JLabel lblDown = new JLabel("向下移动");
        JLabel lblLeft = new JLabel("向左移动");
        JLabel lblRight = new JLabel("向右移动");
        JLabel lblFire = new JLabel("<html>发射子弹<br>借用其它玩家生命(双人模式)<br>设置地形(自定义地图模式)</html>");
        JLabel lblChangeLandform = new JLabel("<html>切换地形样式(自定义地图模式)</html>");
        JLabel lblPause = new JLabel("<html>暂停/继续<br>返回主界面(自定义地图模式)</html>");
        JLabel lblSure = new JLabel("<html>确定<br>保存地图(自定义地图模式)</html>");
        //玩家1
        JLabel lbl1 = new JLabel("W");
        JLabel lbl2 = new JLabel("S");
        JLabel lbl3 = new JLabel("A");
        JLabel lbl4 = new JLabel("D");
        JLabel lbl5 = new JLabel("J");
        JLabel lbl6 = new JLabel("K");
        JLabel lbl7 = new JLabel("Esc");
        JLabel lbl8 = new JLabel("Enter");
        //玩家2
        JLabel lbl9 = new JLabel("↑");
        JLabel lbl10 = new JLabel("↓");
        JLabel lbl11 = new JLabel("←");
        JLabel lbl12 = new JLabel("→");
        JLabel lbl13 = new JLabel("NumPad_3");
        JLabel lbl14 = new JLabel("-");
        JLabel lbl15 = new JLabel("-");
        JLabel lbl16 = new JLabel("-");
        //设置文本居中
        lblTitle2.setHorizontalAlignment(JTextField.CENTER);
        lblTitle3.setHorizontalAlignment(JTextField.CENTER);
        lbl1.setHorizontalAlignment(JTextField.CENTER);
        lbl2.setHorizontalAlignment(JTextField.CENTER);
        lbl3.setHorizontalAlignment(JTextField.CENTER);
        lbl4.setHorizontalAlignment(JTextField.CENTER);
        lbl5.setHorizontalAlignment(JTextField.CENTER);
        lbl6.setHorizontalAlignment(JTextField.CENTER);
        lbl7.setHorizontalAlignment(JTextField.CENTER);
        lbl8.setHorizontalAlignment(JTextField.CENTER);
        lbl9.setHorizontalAlignment(JTextField.CENTER);
        lbl10.setHorizontalAlignment(JTextField.CENTER);
        lbl11.setHorizontalAlignment(JTextField.CENTER);
        lbl12.setHorizontalAlignment(JTextField.CENTER);
        lbl13.setHorizontalAlignment(JTextField.CENTER);
        lbl14.setHorizontalAlignment(JTextField.CENTER);
        lbl15.setHorizontalAlignment(JTextField.CENTER);
        lbl16.setHorizontalAlignment(JTextField.CENTER);
        //布局
        lblTitle1.setBorder(new EmptyBorder(5, 20, 5, 5));
        lblTitle2.setBorder(new EmptyBorder(5, 5, 5, 5));
        lblTitle3.setBorder(new EmptyBorder(5, 5, 5, 5));
        lblUp.setBorder(new EmptyBorder(5, 20, 5, 5));
        lblDown.setBorder(new EmptyBorder(5, 20, 5, 5));
        lblLeft.setBorder(new EmptyBorder(5, 20, 5, 5));
        lblRight.setBorder(new EmptyBorder(5, 20, 5, 5));
        lblFire.setBorder(new EmptyBorder(5, 20, 5, 5));
        lblChangeLandform.setBorder(new EmptyBorder(5, 20, 5, 5));
        lblPause.setBorder(new EmptyBorder(5, 20, 5, 5));
        lblSure.setBorder(new EmptyBorder(5, 20, 5, 5));
        lbl1.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbl2.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbl3.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbl4.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbl6.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbl7.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbl8.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbl9.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbl10.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbl11.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbl12.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbl13.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbl14.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbl15.setBorder(new EmptyBorder(5, 5, 5, 5));
        lbl16.setBorder(new EmptyBorder(5, 5, 5, 5));
        panel.setLayout(new GridLayout(9, 3));
        panel.setBorder(new CompoundBorder(new EmptyBorder(10, 10, 10, 10),
                new TitledBorder(null, "按键说明", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION)));
        //添加组件
        panel.add(lblTitle1);
        panel.add(lblTitle2);
        panel.add(lblTitle3);
        panel.add(lblUp);
        panel.add(lbl1);
        panel.add(lbl9);
        panel.add(lblDown);
        panel.add(lbl2);
        panel.add(lbl10);
        panel.add(lblLeft);
        panel.add(lbl3);
        panel.add(lbl11);
        panel.add(lblRight);
        panel.add(lbl4);
        panel.add(lbl12);
        panel.add(lblFire);
        panel.add(lbl5);
        panel.add(lbl13);
        panel.add(lblChangeLandform);
        panel.add(lbl6);
        panel.add(lbl14);
        panel.add(lblPause);
        panel.add(lbl7);
        panel.add(lbl15);
        panel.add(lblSure);
        panel.add(lbl8);
        panel.add(lbl16);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(gf);
    }
}
