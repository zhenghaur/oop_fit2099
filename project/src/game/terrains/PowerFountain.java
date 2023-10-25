package game.terrains;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.PowerWater;
import game.items.Water;

/**
 * Class representing a Power Fountain in a game world.
 */
public class PowerFountain extends Fountain{

    /**
     * Constructor to create a Power Fountain
     */
    public PowerFountain(){
        super('A');
    }

    /**
     * Returns a description of this Terrain suitable to display in the menu.
     *
     * @return a String, e.g. "water from Power Fountain (10/10)"
     */
    @Override
    public String toString() {
        return "water from Power Fountain (" + this.capacity + "/10)";
    }

    /**
     * Refill the water from the Fountain.
     *
     * @return water type to refill
     */
    @Override
    public Water refill() {
        this.capacity -= 1;
        return new PowerWater();
    }

    /**
     * Allows current actor to consume Water from the Power Fountain.
     *
     * @param actor the actor consuming the water
     * @param map current GameMap
     * @return a description of this action suitable to display in the menu.
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        this.capacity -= 1;
        return new PowerWater().consume(actor, map);
    }
}
