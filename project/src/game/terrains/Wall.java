package game.terrains;

/**
 * Class representing a Wall in the game world
 */
public class Wall extends HighGround {

	/**
	 * Constructor to create a Wall.
	 */
	public Wall() {
		super('#');
	}

	/**
	 * Wall can block thrown objects
	 *
	 * @return true
	 */
	@Override
	public boolean blocksThrownObjects() {
		return true;
	}

}
