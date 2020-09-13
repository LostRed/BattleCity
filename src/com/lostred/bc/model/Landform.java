package com.lostred.bc.model;

import com.lostred.bc.util.localFile.GameImage;
import com.lostred.bc.util.setting.LandformType;

import java.awt.*;

/**
 * 地形模型
 */
public class Landform extends Model {
    /**
     * 地形的宽度
     */
    public static final int WIDTH = 24;
    /**
     * 地形的高度
     */
    public static final int HEIGHT = 24;
    /**
     * 地形的类型
     */
    private LandformType landformType;

    /**
     * 构造地形，设置模型默认的宽度和高度
     *
     * @param landformType 地形的类型
     */
    public Landform(LandformType landformType) {
        this.landformType = landformType;
        super.setAppearances(GameImage.packImages(this.landformType));
        super.setWidth(WIDTH);
        super.setHeight(HEIGHT);
        if (landformType == LandformType.RIVER) {
            Runnable river = () -> {
                while (true) {
                    Image image = super.getAppearances().get(0);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    super.getAppearances().remove(image);
                    super.getAppearances().add(image);
                }
            };
            new Thread(river).start();
        }
    }

    //get和set方法
    public LandformType getLandformType() {
        return landformType;
    }

    public void setLandformType(LandformType landformType) {
        this.landformType = landformType;
    }
}
