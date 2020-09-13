package com.lostred.bc.util;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * 记录分数
 */
public class Record {
    /**
     * 读取配置文件
     *
     * @param keyName 配置文件中的键值
     * @return 键值对应的值
     */
    public static String readPropertiesValue(String keyName) {
        Properties pps = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream("data/config.properties"));
            pps.load(in);
            return pps.getProperty(keyName);
        } catch (FileNotFoundException e1) {
            try {
                OutputStream out = new FileOutputStream("data/config.properties");
                pps.store(out, "Copyright(c) by LostRed");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /**
     * 写入配置文件
     *
     * @param keyName  键值
     * @param keyValue 值
     */
    public static void writeProperties(String keyName, String keyValue) {
        Properties pps = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream("data/config.properties"));
            pps.load(in);
            Set<?> keys = pps.keySet();
            Map<String, String> toSaveMap = new HashMap<>();
            for (Object object : keys) {
                String key = (String) object;
                String value = (String) pps.get(key);
                toSaveMap.put(key, value);
            }
            toSaveMap.put(keyName, keyValue);
            pps.putAll(toSaveMap);
            OutputStream out = new FileOutputStream("data/config.properties");
            pps.store(out, "Copyright(c) by LostRed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
