package game.terrains;

import edu.monash.fit2099.engine.positions.Location;
import game.items.FireFlower;
import game.utils.Utils;
import game.mobs.Goomba;

/**
 * Class representing Sprout in the game world.
 */
public class Sprout extends Tree {

    /**
     * Number of rounds existed in the game
     */
    private int round = 0;

    /**
     * Constructor to create a Sprout.
     */
    public Sprout(){
        super('+');
    }

    /**
     * Sprout can also experience the joy of time.
     *
     * Overrides Tree.tick()
     *
     * @see Tree#tick(Location)
     * @param location The location of the Tree
     */
    @Override
    public void tick(Location location){
        super.tick(location);
        round += 1;
        // spawn goomba by chance
        if (!location.containsAnActor() && Utils.rollHundred() < 10){
            location.addActor(new Goomba());
        }
        // grow into sapling after 10 rounds
        if (round == 10){
            location.setGround(new Sapling());
            if (Utils.rollHundred() < 50){
                location.addItem(new FireFlower());
            }
        }
    }

}
