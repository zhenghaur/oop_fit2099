package game.behaviours;

import java.util.ArrayList;
import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.mobs.FlyingKoopa;
import game.terrains.Floor;
import game.utils.Status;

/**
 * A class that figures out a MoveAction that will move the actor one step
 * to a random Location.
 */
public class WanderBehaviour extends Action implements Behaviour {

	/**
	 * Random number generator
	 */
	private final Random random = new Random();

	/**
	 * Returns a MoveAction to wander to a random location, if possible.  
	 * If no movement is possible, returns null.
	 * 
	 * @param actor the Actor enacting the behaviour
	 * @param map the map that actor is currently on
	 * @return an Action, or null if no MoveAction is possible
	 */
	@Override
	public Action getAction(Actor actor, GameMap map) {
		ArrayList<Action> actions = new ArrayList<Action>();
		
		for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
            	actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }
		
		if (!actions.isEmpty()) {
			return actions.get(random.nextInt(actions.size()));
		}
		else {
			return null;
		}

	}

	/**
	 * Allow the actor to wander around.
	 *
	 * Override Action.execute()
	 *
	 * @see Action#execute(Actor, GameMap)
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of the Behaviour suitable for the menu
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		return menuDescription(actor);
	}

	/**
	 * Returns a description of this Behaviour suitable to display in the menu.
	 *
	 * @param actor The actor performing the action.
	 * @return a String, e.g. "Raagrh..."
	 */
	@Override
	public String menuDescription(Actor actor) {
		return "Raagrh...";
	}
}
