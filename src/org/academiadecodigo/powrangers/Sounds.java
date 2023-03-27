package org.academiadecodigo.powrangers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;

public class Sounds {


    Clip clip;
    URL[] soundURl = new URL[10];
    File[] soundList = new File[10];


    public Sounds() {


        soundList[0] = new File(Program.prefix + "gameover.wav");
        soundList[1] = new File(Program.prefix + "hit1.wav");
        soundList[2] = new File(Program.prefix + "hit2.wav");
        soundList[3] = new File(Program.prefix + "jump1.wav");
        soundList[4] = new File(Program.prefix + "jump2.wav");
        soundList[5] = new File(Program.prefix + "jump3.wav");
        soundList[6] = new File(Program.prefix + "music1.wav");
        soundList[7] = new File(Program.prefix + "music2.wav");
        soundList[8] = new File(Program.prefix + "music3.wav");

        //System.out.println(soundList[1]);


    }


    public void setFile(int i) {

        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundList[i]);

            clip = AudioSystem.getClip();
            clip.open(ais);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    public void play() {

        clip.start();


    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();


    }

    public void playMusic(int i) {
        setFile(i);
        play();
        loop();


    }

    public void stopMusic() {
        stop();
    }

    public void playSoundEffect(int i) {
        setFile(i);
        play();
    }


}