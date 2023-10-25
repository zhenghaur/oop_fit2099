package game.terrains;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;

/**
 * Class representing Lava in the game world.
 */
public class Lava extends Ground {

    /**
     * Constructor to create Lava.
     */
    public Lava() {
        super('L');
    }

    /**
     * Inform the Lava on the ground of the passage of time.
     * This method is called once per turn, if the lava rests upon the ground.
     *
     * Overrides Ground.tick()
     *
     * @see Ground#tick(Location)
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor()){
            Actor actor = location.getActor();
            actor.hurt(15);
            Display display = new Display();
            display.println("Lava burns " + actor + " for 15 damage.");
            if (!(actor.isConscious())){
                location.map().removeActor(location.getActor());
            }
        }
    }

    /**
     * Indicate if actor can enter the Ground.
     *
     * Overrides Ground.canActorEnter()
     *
     * @see Ground#canActorEnter(Actor)
     * @param actor the Actor to check
     * @return true is actor is a Player, false otherwise.
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        if ((actor instanceof Player)){
            return true;
        }
        return false;
    }
}
