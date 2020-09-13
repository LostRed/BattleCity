package com.lostred.bc.util.localFile;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 游戏音效
 */
public class GameSound {
    /**
     * 开始关卡音效
     */
    private AudioClip startSound;
    /**
     * 发射子弹音效
     */
    private AudioClip shotSound;
    /**
     * 爆炸音效
     */
    private AudioClip explodeSound;
    /**
     * 子弹击中砖墙音效
     */
    private AudioClip hitBrickSound;
    /**
     * 子弹击中铁墙音效
     */
    private AudioClip hitSteelSound;
    /**
     * 子弹击中坦克音效
     */
    private AudioClip hitTankSound;
    /**
     * 道具包出现音效
     */
    private AudioClip propertyAppearSound;
    /**
     * 获得奖励音效
     */
    private AudioClip getAwardSound;
    /**
     * 玩家生命增加音效
     */
    private AudioClip plusLifeSound;
    /**
     * 选择菜单音效
     */
    private AudioClip menuSound;
    /**
     * 游戏结束音效
     */
    private AudioClip gameOverSound;
    /**
     * 完成关卡音效
     */
    private AudioClip completeSound;
    /**
     * 通关音乐
     */
    private AudioStream theEndSound;

    /**
     * 构造游戏音效
     */
    public GameSound() {
        File start = new File("sound/start.wav");
        File shot = new File("sound/shot.wav");
        File explode = new File("sound/explode.wav");
        File hitBrick = new File("sound/hitBrick.wav");
        File hitSteel = new File("sound/hitSteel.wav");
        File hitTank = new File("sound/hitTank.wav");
        File propertyAppear = new File("sound/propertyAppear.wav");
        File getAward = new File("sound/getAward.wav");
        File plusLife = new File("sound/plusLife.wav");
        File menu = new File("sound/menu.wav");
        File gameOver = new File("sound/gameOver.wav");
        File complete = new File("sound/complete.wav");
        try {
            startSound = Applet.newAudioClip(start.toURI().toURL());
            shotSound = Applet.newAudioClip(shot.toURI().toURL());
            explodeSound = Applet.newAudioClip(explode.toURI().toURL());
            hitBrickSound = Applet.newAudioClip(hitBrick.toURI().toURL());
            hitSteelSound = Applet.newAudioClip(hitSteel.toURI().toURL());
            hitTankSound = Applet.newAudioClip(hitTank.toURI().toURL());
            propertyAppearSound = Applet.newAudioClip(propertyAppear.toURI().toURL());
            getAwardSound = Applet.newAudioClip(getAward.toURI().toURL());
            plusLifeSound = Applet.newAudioClip(plusLife.toURI().toURL());
            menuSound = Applet.newAudioClip(menu.toURI().toURL());
            gameOverSound = Applet.newAudioClip(gameOver.toURI().toURL());
            completeSound = Applet.newAudioClip(complete.toURI().toURL());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 播放通关音乐
     */
    public void playTheEnd() {
        try {
            InputStream theEnd = new FileInputStream("sound/adventure.wav");
            theEndSound = new AudioStream(theEnd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AudioPlayer.player.start(theEndSound);
    }

    /**
     * 停止通关音乐
     */
    public void stopTheEnd() {
        if (theEndSound != null) {
            AudioPlayer.player.stop(theEndSound);
        }
    }

    /**
     * 播放指定的音频剪辑
     *
     * @param audioClip 要播放的音频剪辑
     */
    public void play(AudioClip audioClip) {
        audioClip.play();
    }

    /**
     * 循环播放指定的音频剪辑
     *
     * @param audioClip 要循环播放的音频剪辑
     */
    public void loop(AudioClip audioClip) {
        audioClip.loop();
    }

    /**
     * 停止播放指定的音频剪辑
     *
     * @param audioClip 要停止的音频剪辑
     */
    public void stop(AudioClip audioClip) {
        audioClip.stop();
    }

    //get和set方法
    public AudioClip getStartSound() {
        return startSound;
    }

    public AudioClip getShotSound() {
        return shotSound;
    }

    public AudioClip getExplodeSound() {
        return explodeSound;
    }

    public AudioClip getHitBrickSound() {
        return hitBrickSound;
    }

    public AudioClip getHitSteelSound() {
        return hitSteelSound;
    }

    public AudioClip getHitTankSound() {
        return hitTankSound;
    }

    public AudioClip getPropertyAppearSound() {
        return propertyAppearSound;
    }

    public AudioClip getGetAwardSound() {
        return getAwardSound;
    }

    public AudioClip getPlusLifeSound() {
        return plusLifeSound;
    }

    public AudioClip getMenuSound() {
        return menuSound;
    }

    public AudioClip getGameOverSound() {
        return gameOverSound;
    }

    public AudioClip getCompleteSound() {
        return completeSound;
    }
}
