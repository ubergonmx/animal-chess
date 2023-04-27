package animalchess.model;

/**
 * An interface that classifies if the animal can move in the river
 */
public interface RiverMove {
    public void setOnLand(boolean onLand);

    public boolean isOnLand();
}
