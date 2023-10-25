package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Player;

/**
 * Class representing Power Water in the game world.
 */
public class PowerWater extends Water{

    /**
     * Returns a description of this item suitable to display in the menu.
     *
     * @return a String, "Power Water"
     */
    @Override
    public String toString() {
        return "Power Water";
    }

    /**
     * Allows current actor to consume Power Water.
     *
     * @param actor the actor carrying the Item
     * @param map current GameMap
     * @return a description of this action suitable to display in the menu.
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        ((Player) actor).increaseDamage(15);
        return actor + " base damage +15";
    }
}
