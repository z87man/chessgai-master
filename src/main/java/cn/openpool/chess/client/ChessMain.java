package cn.openpool.chess.client;

import javax.sound.sampled.*;
import java.io.*;


public class ChessMain {
    public static Clip bgm;

    public static void main(String[] args) throws LineUnavailableException, IOException, UnsupportedAudioFileException {

        /**
         * 加载背景音乐
         * */
        bgm = AudioSystem.getClip();

        BufferedInputStream is = new BufferedInputStream(new FileInputStream("media/media.wav"));
        AudioInputStream ais = AudioSystem.getAudioInputStream(is);
        bgm.open(ais);

        FloatControl volume = (FloatControl) bgm.getControl(FloatControl.Type.MASTER_GAIN);
        volume.setValue(-1 * 20F);

        bgm.start();
        bgm.loop(Clip.LOOP_CONTINUOUSLY);

        /**
         * 登录
         * */
        new Login().draw();
    }
}

