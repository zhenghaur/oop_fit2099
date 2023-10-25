package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.resets.Resettable;

/**
 * Class representing a Coin in the game world.
 */
public class Coin extends Item implements Resettable {

    /**
     * The value of the Coin
     */
    private final int value;

    /**
     * The Location of the Coin
     */
    public Location location = null;

    /**
     * Indicates whether the coin is on ground or not
     */
    public boolean onGround;

    /**
     * Constructor to create a Coin.
     *
     * @param value the value of the Coin.
     */
    public Coin(int value) {
        super("Coin", '$', true);
        this.value = value;
        this.onGround = true;
        this.registerInstance();
    }

    /**
     * Inform a carried Coin of the passage of time.
     * This method is called once per turn, if the Coin is being carried.
     *
     * Overrides Item.tick()
     *
     * @see Item#tick(Location, Actor)
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.onGround = false;
        if (actor.getInventory().contains(this)){
            ((Player) actor).changeBalance(this.value);
            actor.removeItemFromInventory(this);
        }
    }

    /**
     * Inform the Coin on the ground of the passage of time.
     * This method is called once per turn, if the coin rests upon the ground.
     *
     * Overrides Item.tick()
     *
     * @see Item#tick(Location)
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        this.location = currentLocation;
    }

    /**
     * Reset the Coin if on ground.
     *
     * Overrides Resettable.resetInstance()
     *
     * @see Resettable#resetInstance()
     */
    @Override
    public void resetInstance() {
        if (this.location != null && this.onGround){
            this.location.removeItem(this);
        }
    }
}