package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Class representing Water type.
 */
public abstract class Water {

    /**
     * Returns a description of this item suitable to display in the menu.
     *
     * @return a String
     */
    public abstract String toString();

    /**
     * Allows current actor to consume Water.
     *
     * @param actor the actor carrying the item
     * @param map current GameMap
     * @return a description of this action suitable to display in the menu.
     */
    public abstract String consume(Actor actor, GameMap map);
}
