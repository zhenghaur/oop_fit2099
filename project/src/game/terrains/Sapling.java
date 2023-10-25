package game.terrains;

import edu.monash.fit2099.engine.positions.Location;
import game.items.FireFlower;
import game.utils.Utils;
import game.items.Coin;

/**
 * Class representing Sapling in the game world.
 */
public class Sapling extends Tree{

    /**
     * Number of rounds existed in the game
     */
    private int round = 0;

    /**
     * Constructor to create a Sapling.
     */
    public Sapling(){
        super('t');
    }

    /**
     * Sapling can also experience the joy of time.
     *
     * Overrides Tree.tick()
     *
     * @see Tree#tick(Location)
     * @param location The location of the Sapling
     */
    @Override
    public void tick(Location location){
        super.tick(location);
        round += 1;
        // drop coin on chance
        if (Utils.rollHundred() < 10){
            location.addItem(new Coin(20));
        }
        // grow into mature after 10 rounds
        if (round == 10) {
            location.setGround(new Mature());
            if (Utils.rollHundred() < 50){
                location.addItem(new FireFlower());
            }
        }
    }
}