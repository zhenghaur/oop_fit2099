package game.terrains;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.HealthWater;
import game.items.Water;

/**
 * Class representing a Health Fountain in a game world.
 */
public class HealthFountain extends Fountain{

    /**
     * Constructor to create a Health Fountain.
     */
    public HealthFountain() {
        super('H');
    }

    /**
     * Returns a description of this Terrain suitable to display in the menu.
     *
     * @return a String, e.g. "water from Health Fountain (10/10)"
     */
    @Override
    public String toString() {
        return "water from Health Fountain (" + this.capacity + "/10)";
    }

    /**
     * Refill the water from the Fountain.
     *
     * @return water type to refill
     */
    @Override
    public Water refill() {
        this.capacity -= 1;
        return new HealthWater();
    }

    /**
     * Allows current actor to consume Water from the Health Fountain.
     *
     * @param actor the actor consuming the water
     * @param map current GameMap
     * @return a description of this action suitable to display in the menu.
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        this.capacity -= 1;
        return new HealthWater().consume(actor, map);
    }
}
