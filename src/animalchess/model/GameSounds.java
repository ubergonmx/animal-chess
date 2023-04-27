package animalchess.model;

/**
 * A class used to get the sound file path
 */

public class GameSounds {
    public static final String EXT = ".wav";

    public static final String CAPTURE = GameSounds.class.getResource("/sfx/capture" + EXT).toString();
    public static final String CAPTURE_RIVER = GameSounds.class.getResource("/sfx/captureRiver" + EXT).toString();

    public static final String MOVE = GameSounds.class.getResource("/sfx/move" + EXT).toString();
    public static final String MOVE_RIVER = GameSounds.class.getResource("/sfx/moveRiver" + EXT).toString();
    public static final String MOVE_TRAP = GameSounds.class.getResource("/sfx/moveTrap" + EXT).toString();

    public static final String START = GameSounds.class.getResource("/sfx/start" + EXT).toString();
    public static final String END = GameSounds.class.getResource("/sfx/end" + EXT).toString();
}
