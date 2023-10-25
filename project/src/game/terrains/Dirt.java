package game.terrains;

import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents bare dirt in the game world.
 */
public class Dirt extends Ground implements Fertile{

	/**
	 * Constructor to create a Dirt.
	 */
	public Dirt() {
		super('.');
	}
}