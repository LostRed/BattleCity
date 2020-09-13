package com.lostred.bc.model;

import com.lostred.bc.service.Service;
import com.lostred.bc.util.localFile.GameImage;

/**
 * 基地，基地被子弹击中时，游戏结束
 */
public class EagleBase extends Model {
    /**
     * 基地的宽度
     */
    public static final int WIDTH = 48;
    /**
     * 基地的高度
     */
    public static final int HEIGHT = 48;
    /**
     * 基地是否存活
     */
    private boolean alive;

    /**
     * 构造基地
     *
     * @param service 后台控制
     */
    public EagleBase(Service service) {
        super.setService(service);
        super.setAppearances(GameImage.packEagleBaseImages());
        super.setXp(360);
        super.setYp(648);
        super.setWidth(WIDTH);
        super.setHeight(HEIGHT);
        this.alive = true;
    }

    //get和set方法
    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
