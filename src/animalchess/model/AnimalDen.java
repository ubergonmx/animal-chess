package animalchess.model;

/**
 * A class for declaring the animal den of the given player.
 */
public class AnimalDen extends Tile {
    public final PlayerType OWNER;

    /**
     * The animal den to be created.
     *
     * @param player the owner of the animal den
     */

    public AnimalDen(PlayerType player) {
        super(TileType.AnimalDen);
        OWNER = player;
    }

    /**
     * This will return the symbol for the animal den.
     *
     * @return returns the symbol for the animal den
     * @see GameImages
     */

    @Override
    public String toString() {
        return GameImages.ANIMAL_DEN;//+ "," + super.getTileType() + "," + OWNER;
    }
}
