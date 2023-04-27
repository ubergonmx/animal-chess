package animalchess.model;

/**
 * A class used to get the images file path
 */

public class GameImages {
    // Terrain or Object Symbols
    public static final String RIVER = GameImages.class.getResource("/river.png").toString();
    public static final String ANIMAL_DEN = GameImages.class.getResource("/animalDen.png").toString();

    // Trap animals
    public static final String TRAP = GameImages.class.getResource("/trap.png").toString();
    public static final String MOUSE_TRAP = GameImages.class.getResource("/mouseTrap.png").toString();
    public static final String CAT_TRAP = GameImages.class.getResource("/catTrap.png").toString();
    public static final String WOLF_TRAP = GameImages.class.getResource("/wolfTrap.png").toString();
    public static final String DOG_TRAP = GameImages.class.getResource("/dogTrap.png").toString();
    public static final String LEOPARD_TRAP = GameImages.class.getResource("/leopardTrap.png").toString();
    public static final String TIGER_TRAP = GameImages.class.getResource("/tigerTrap.png").toString();
    public static final String LION_TRAP = GameImages.class.getResource("/lionTrap.png").toString();
    public static final String ELEPHANT_TRAP = GameImages.class.getResource("/elephantTrap.png").toString();

    // River Animals
    public static final String MOUSE_RIVER = GameImages.class.getResource("/mouseRiver.png").toString();
    public static final String ELEPHANT_RIVER = GameImages.class.getResource("/elephantRiver.png").toString();

    // Animals
    public static final String MOUSE = GameImages.class.getResource("/mouse.png").toString();
    public static final String CAT = GameImages.class.getResource("/cat.png").toString();
    public static final String WOLF = GameImages.class.getResource("/wolf.png").toString();
    public static final String DOG = GameImages.class.getResource("/dog.png").toString();
    public static final String LEOPARD = GameImages.class.getResource("/leopard.png").toString();
    public static final String TIGER = GameImages.class.getResource("/tiger.png").toString();
    public static final String LION = GameImages.class.getResource("/lion.png").toString();
    public static final String ELEPHANT = GameImages.class.getResource("/elephant.png").toString();
}