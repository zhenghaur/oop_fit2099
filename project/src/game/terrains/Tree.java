package game.terrains;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.resets.Resettable;
import game.utils.Status;
import game.utils.Utils;
import game.actions.JumpAction;

/**
 * Class representing Tree type.
 */
public abstract class Tree extends HighGround implements Resettable {

    /**
     * The char of the Tree
     */
    protected char displayChar;

    /**
     * The location of the Tree
     */
    private Location location = null;

    /**
     * Constructor to create a Tree
     *
     * @param displayChar the char of the Tree
     */
    public Tree(char displayChar) {
        super(displayChar);
        this.registerInstance();
    }

    /**
     * Returns a new collection of the Actions that Tree can offer to the current Actor.
     *
     * Overrides Ground.allowableActions()
     *
     * @see Ground#allowableActions(Actor, Location, String)
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Tree from the Actor
     * @return a new collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if (!location.containsAnActor()) {
            actions.add(new JumpAction(location, direction));
        }
        return actions;
    }

    /**
     * Tree can also experience the joy of time.
     *
     * @param location The location of the Tree
     */
    @Override
    public void tick(Location location) {
        this.location = location;
    }

    /**
     * Reset the Tree to Dirt by chance.
     *
     * Overrides Resettable.resetInstance()
     *
     * @see Resettable#resetInstance()
     */
    @Override
    public void resetInstance() {
        if (this.location != null && Utils.rollHundred() < 50){
            this.location.setGround(new Dirt());
        }
    }

    /**
     * Indicate if actor can enter the Ground.
     *
     * Overrides Ground.canActorEnter()
     *
     * @see Ground#canActorEnter(Actor)
     * @param actor the Actor to check
     * @return true if actor have fly Status, false otherwise.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasCapability(Status.FLY)){
            return true;
        }
        return false;
    }
}
