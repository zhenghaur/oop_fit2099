package game.terrains;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.mobs.Enemy;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

	/**
	 * Constructor to create a Floor.
	 */
	public Floor() {
		super('_');
	}

	/**
	 * Indicate if actor can enter the Ground.
	 * 
	 * Overrides Ground.canActorEnter()
	 * 
	 * @see Ground#canActorEnter(Actor) 
	 * @param actor the Actor to check
	 * @return true if actor is not instance of Enemy, false otherwise.
	 */
	@Override
	public boolean canActorEnter(Actor actor) {
		return !(actor instanceof Enemy);
	}
}
