package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;

/**
 * Class representing a Power Star in the game world.
 */
public class PowerStar extends Item implements Consumable, Tradable{

    /**
     * Number of rounds remaining before the effect runs out
     */
    private int roundLeft;

    /**
     * Constructor to create a Power Star.
     */
    public PowerStar(){
        super("Power Star", '*', true);
        this.addAction(new ConsumeAction(this));
        this.roundLeft = 10;
    }

    /**
     * Inform a carried Power Star of the passage of time.
     * This method is called once per turn, if the power star is being carried.
     *
     * Overrides Item.tick()
     *
     * @see Item#tick(Location, Actor)
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.roundLeft -= 1;
        if (this.roundLeft == 0){
            actor.removeItemFromInventory(this);
        }
    }

    /**
     * Inform the Power Star on the ground of the passage of time.
     * This method is called once per turn, if the power star rests upon the ground.
     *
     * Overrides Item.tick()
     *
     * @see Item#tick(Location)
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        this.roundLeft -= 1;
        if (this.roundLeft == 0){
            currentLocation.removeItem(this);
        }
    }

    /**
     * Returns the number of rounds remaining.
     *
     * @return an int representing the number of rounds remaining
     */
    public int getRoundLeft(){
        return roundLeft;
    }

    /**
     * Returns a description of this item suitable to display in the menu.
     *
     * @return a String, e.g. "Power Star - 10 turns remaining"
     */
    @Override
    public String toString(){
        return super.toString() + " - " + this.getRoundLeft() + " turns remaining";
    }
}