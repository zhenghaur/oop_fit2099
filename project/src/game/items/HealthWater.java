package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Class representing a Health Water in the game world.
 */
public class HealthWater extends Water{

    /**
     * Returns a description of this item suitable to display in the menu.
     *
     * @return a String, "Health Water"
     */
    @Override
    public String toString() {
        return "Health Water";
    }

    /**
     * Allows current actor to consume Health Water.
     *
     * @param actor The actor carrying the Item
     * @param map current GameMap
     * @return a description of this action suitable to display in the menu.
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        actor.heal(50);
        return actor + " heals for 50 health";
    }
}
