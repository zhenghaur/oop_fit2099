package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;

/**
 * A class that figures out an AttackAction that actor will attack the target Actor.
 */
public class AttackBehaviour implements Behaviour {

    /**
     * The Actor that is to be attacked
     */
    private final Actor target;

    /**
     * Constructor to create an AttackBehaviour to the target Actor.
     *
     * @param subject the Actor to attack
     */
    public AttackBehaviour(Actor subject) {
        this.target = subject;
    }

    // TODO: develop and use it to attack the player automatically.
    /**
     * Create and return the AttackAction if the target Actor is close by.
     * If target is not close by, returns null
     *
     * Overrides Behaviour.getAction()
     *
     * @see Behaviour#getAction(Actor, GameMap)
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an AttackAction, or null if target is not nearby
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if(!map.contains(target) || !map.contains(actor))
            return null;

        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        boolean isNear = distance(here, there);

        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination == there && isNear) {
                return new AttackAction(this.target, exit.getName());
            }
        }
        return null;
    }


    /**
     * Compute if two locations are within 1 step.
     *
     * @param a the first location
     * @param b the second location
     * @return a boolean of true if two locations are within 1 step, false otherwise.
     */
    private boolean distance(Location a, Location b){
        return Math.max(Math.abs(a.x() - b.x()), Math.abs(a.y() - b.y())) <= 1;
    }
}