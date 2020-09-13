package com.lostred.bc.test;

import com.lostred.bc.view.GameFrame;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;
import java.awt.*;
import java.util.Enumeration;

public class TestDemo {
    public static void main(String[] args) {
        initGlobalFont(new Font("微软雅黑", Font.BOLD, 12));
        EventQueue.invokeLater(() -> {
            GameFrame gf = new GameFrame();
            gf.setVisible(true);
        });
    }

    /**
     * 设置全局字体
     *
     * @param font 字体格式
     */
    private static void initGlobalFont(Font font) {
        FontUIResource fontRes = new FontUIResource(font);
        for (Enumeration<Object> keys = UIManager.getDefaults().keys(); keys.hasMoreElements(); ) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof FontUIResource) {
                UIManager.put(key, fontRes);
            }
        }
    }
}
