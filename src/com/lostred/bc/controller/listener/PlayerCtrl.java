package com.lostred.bc.controller.listener;

import com.lostred.bc.model.tank.AllyTank;
import com.lostred.bc.util.GameData;
import com.lostred.bc.util.localFile.GameImage;
import com.lostred.bc.util.setting.Direction;
import com.lostred.bc.view.GameFrame;

import java.awt.event.KeyAdapter;

/**
 * 玩家控制器
 */
public abstract class PlayerCtrl extends KeyAdapter {
    /**
     * 所属游戏窗口
     */
    private GameFrame gf;
    /**
     * 控制的盟军坦克对象
     */
    private AllyTank tank;
    /**
     * 坦克是否移动
     */
    private boolean move;
    /**
     * 向上按键是否按下
     */
    private boolean up;
    /**
     * 向下按键是否按下
     */
    private boolean down;
    /**
     * 向左按键是否按下
     */
    private boolean left;
    /**
     * 向右按键是否按下
     */
    private boolean right;
    /**
     * 开火按键是否按下
     */
    private boolean fire;
    /**
     * 不能使用移动按键的时间
     */
    private int stopTime;
    /**
     * 恢复可以使用移动按键的时间任务
     */
    private Runnable waiting;

    /**
     * 构造玩家控制器
     */
    public PlayerCtrl() {
        this.stopTime = 0;
        this.waiting = () -> {
            while (this.stopTime > 0) {
                reset();
                tank.setAppearances(GameImage.packNone());
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tank.setAppearances(GameImage.packImages(tank));
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!GameData.PAUSE) {
                    this.stopTime -= 500;
                }
            }
        };
    }

    /**
     * 检查移动状态，释放按钮后修正方向
     */
    public void checkMove() {
        if (up || down || left || right) {
            if (up && !(down || left || right)) {
                tank.rotate(Direction.UP);
            } else if (down && !(up || left || right)) {
                tank.rotate(Direction.DOWN);
            } else if (left && !(up || down || right)) {
                tank.rotate(Direction.LEFT);
            } else if (right && !(up || down || left)) {
                tank.rotate(Direction.RIGHT);
            }
            this.move = true;
        } else {
            this.move = false;
        }
    }

    /**
     * 重置控制器
     */
    public void reset() {
        this.up = false;
        this.down = false;
        this.left = false;
        this.right = false;
        this.move = false;
        this.fire = false;
        this.tank.setMoveSoundFlag(false);
    }

    //get和set方法

    public GameFrame getGf() {
        return gf;
    }

    public void setGf(GameFrame gf) {
        this.gf = gf;
    }

    public AllyTank getTank() {
        return tank;
    }

    public void setTank(AllyTank tank) {
        this.tank = tank;
    }

    public boolean isMove() {
        return move;
    }

    public void setMove(boolean move) {
        this.move = move;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isFire() {
        return fire;
    }

    public void setFire(boolean fire) {
        this.fire = fire;
    }

    public int getStopTime() {
        return stopTime;
    }

    public void setStopTime(int stopTime) {
        this.stopTime = stopTime;
    }

    public Runnable getWaiting() {
        return waiting;
    }

    public void setWaiting(Runnable waiting) {
        this.waiting = waiting;
    }
}
