package game.terrains;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ConsumeAction;
import game.actions.RefillAction;
import game.items.Consumable;
import game.items.Water;

/**
 * A class that represents the Fountain type.
 */
public abstract class Fountain extends Ground implements Consumable {

    /**
     * The capacity of the Fountain.
     */
    protected int capacity;

    /**
     * A counter to refill the fountain.
     */
    protected int refillTurnCounter;

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Fountain(char displayChar) {
        super(displayChar);
        this.capacity = 10;
        this.refillTurnCounter = 0;
    }

    /**
     * Returns a new collection of the Actions that Fountain can offer to the current Actor.
     *
     * Overrides Ground.allowableActions()
     *
     * @see Ground#allowableActions(Actor, Location, String)
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (location.getActor() == actor){
            actions.add(new RefillAction(this));
            actions.add(new ConsumeAction(this));
        }
        return actions;
    }

    /**
     * Inform the Fountain on the ground of the passage of time.
     * This method is called once per turn, if the fountain rests upon the ground.
     *
     * Overrides Ground.tick()
     *
     * @see Ground#tick(Location)
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (this.refillTurnCounter == 5){
            this.capacity = 10;
        }
        if (this.capacity == 0){
            this.refillTurnCounter += 1;
        }
        else{
            this.refillTurnCounter = 0;
        }
    }

    /**
     * Returns the capacity of the Fountain.
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * refill the water from the Fountain.
     * @return Water type
     */
    public abstract Water refill();

    /**
     * Allows current actor to consume Water from the Fountain.
     *
     * @param actor the actor consuming the water
     * @param map current GameMap
     * @return a description of this action suitable to display in the menu.
     */
    public abstract String consume(Actor actor, GameMap map);
}
