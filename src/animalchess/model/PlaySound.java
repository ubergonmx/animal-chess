package animalchess.model;
/**
 * This class adds sound effects to the movements within the game
 */

import javax.sound.sampled.*;
import javax.swing.*;
import java.net.URL;
import java.util.Objects;

/**
 * A class that plays used to play game sounds.
 */

public class PlaySound {

    private static final int COUNT = 7;
    private static final Clip[] clips = new Clip[COUNT];

    /**
     * This plays the given Clip/sound
     *
     * @param clip
     */
    private static void play(Clip clip) {
        try {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(0.0f);
            clip.setFramePosition(0);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error in PlaySound");
        }
    }

    /**
     * This preloads the corresponding sound effects in the game
     * by playing it on mute.
     */
    public static void preload() {
        try {
            URL file = new URL(GameSounds.START);
            AudioInputStream audio = AudioSystem.getAudioInputStream(file);
            clips[0] = AudioSystem.getClip();
            clips[0].open(audio);


            file = new URL(GameSounds.CAPTURE);
            audio = AudioSystem.getAudioInputStream(file);
            clips[1] = AudioSystem.getClip();
            clips[1].open(audio);

            file = new URL(GameSounds.CAPTURE_RIVER);
            audio = AudioSystem.getAudioInputStream(file);
            clips[2] = AudioSystem.getClip();
            clips[2].open(audio);

            file = new URL(GameSounds.END);
            audio = AudioSystem.getAudioInputStream(file);
            clips[3] = AudioSystem.getClip();
            clips[3].open(audio);

            file = new URL(GameSounds.MOVE);
            audio = AudioSystem.getAudioInputStream(file);
            clips[4] = AudioSystem.getClip();
            clips[4].open(audio);

            file = new URL(GameSounds.MOVE_RIVER);
            audio = AudioSystem.getAudioInputStream(file);
            clips[5] = AudioSystem.getClip();
            clips[5].open(audio);

            file = new URL(GameSounds.MOVE_TRAP);
            audio = AudioSystem.getAudioInputStream(file);
            clips[6] = AudioSystem.getClip();
            clips[6].open(audio);

            FloatControl volume;
            for (Clip clip : clips) {
                Objects.requireNonNull(clip);
                volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                volume.setValue(-80.0f);
                clip.start();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This plays the sound for when the game starts
     */
    public static void start() {
        play(clips[0]);
    }

    /**
     * This plays the sound of capturing a piece
     */
    public static void capture() {
        play(clips[1]);
    }

    /**
     * This plays the sound of capturing a piece on a River
     */
    public static void captureRiver() {
        play(clips[2]);
    }

    /**
     * This plays the sound for when the game ends
     */
    public static void end() {
        play(clips[3]);
    }

    /**
     * This plays the sound of a piece move on land
     */
    public static void move() {
        play(clips[4]);
    }

    /**
     * This plays the sound of a piece move on river
     */
    public static void moveRiver() {
        play(clips[5]);
    }

    /**
     * This plays the sound of a piece move getting trapped
     */
    public static void moveTrap() {
        play(clips[6]);
    }
}
