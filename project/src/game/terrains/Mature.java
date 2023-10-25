package game.terrains;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.mobs.FlyingKoopa;
import game.utils.Utils;
import game.mobs.Koopa;

import java.util.ArrayList;

/**
 * Class representing a Mature Tree in the game world.
 */
public class Mature extends Tree{

    /**
     * Number of rounds existed in the game
     */
    private int round = 0;

    /**
     * Constructor to create a Mature Tree.
     */
    public Mature(){
        super('T');
    }

    /**
     * Mature Tree can also experience the joy of time.
     *
     * Overrides Tree.tick()
     *
     * @see Tree#tick(Location)
     * @param location The location of the Mature Tree
     */
    @Override
    public void tick(Location location){
        super.tick(location);
        round += 1;
        // spawn koopa by chance
        if (!location.containsAnActor() && Utils.rollHundred() < 15){
            if (Utils.rollHundred() < 50) {
                location.addActor(new Koopa());
            }
            else{
                location.addActor(new FlyingKoopa());
            }
        }
        // spawn sprout on fertile ground
        if (round % 5 == 0){
            ArrayList<Location> fertileGrounds = new ArrayList<Location>();
            for (Exit exit: location.getExits()){
                Ground exitGround = exit.getDestination().getGround();
                if(exitGround instanceof Fertile){
                    fertileGrounds.add(exit.getDestination());
                }
            }
            if (fertileGrounds.size() > 0) {
                fertileGrounds.get(Utils.roll(fertileGrounds.size())).setGround(new Sprout());
            }
        }
        // die on chance
        if(Utils.rollHundred() < 20){
            location.setGround(new Dirt());
        }
    }

}
