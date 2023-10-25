package game.terrains;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.JumpAction;
import game.utils.Status;

/**
 * A class that represents the High Ground type.
 */
public abstract class HighGround extends Ground {

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public HighGround(char displayChar) {
        super(displayChar);
    }

    /**
     * Returns a new collection of the Actions that HighGround can offer to the current Actor.
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
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList actions = new ActionList();
        if (!location.containsAnActor()) {
            actions.add(new JumpAction(location, direction));
        }
        return actions;
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
