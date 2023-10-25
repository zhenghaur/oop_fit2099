package game.terrains;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.actions.TeleportAction;
import game.mobs.PiranhaPlant;
import game.resets.Resettable;

/**
 * Class representing a Warp Pipe in the game world
 */
public class WarpPipe extends HighGround implements Resettable {

    /**
     * The target WarpPipe for the Player to teleport.
     */
    private WarpPipe target = null;

    /**
     * Location of the Warp Pipe.
     */
    private Location location;

    /**
     * Indicate if Piranha Plant is spawned.
     */
    private boolean isSpawn = false;

    /**
     * Constructor to create a Warp Pipe to teleport to GameMap2.
     */
    public WarpPipe(){
        super('C');
        this.registerInstance();
    }

    /**
     * Constructo to create a Warp Pipe to teleport back to GameMap1.
     * @param target the WarpPipe to teleport onto
     */
    public WarpPipe(WarpPipe target) {
        super('C');
        this.target = target;
        this.registerInstance();
    }

    /**
     *
     * @param location The location of the Ground
     */

    /**
     * Inform the WarpPipe on the ground of the passage of time.
     * This method is called once per turn, if the warp pipe rests upon the ground.
     *
     * Overrides Ground.tick()
     *
     * @see Ground#tick(Location)
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        this.location = location;
        if (!(this.isSpawn) && !(location.containsAnActor())) {
            this.location.addActor(new PiranhaPlant());
            this.isSpawn = true;
        }
    }

    /**
     * Returns a new collection of the Actions that WarpPipe can offer to the current Actor.
     *
     * Overrides Ground.allowableActions()
     *
     * @see Ground#allowableActions(Actor, Location, String)
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);
        if (location.containsAnActor() && location.getActor() instanceof Player){
            actions.add(new TeleportAction(this.target));
        }
        return actions;
    }

    /**
     * Returns the location of the Warp Pipe.
     *
     * @return location
     */
    public Location getLocation() {
        return this.location;
    }

    /**
     * Reset the Warp Pipe.
     */
    @Override
    public void resetInstance() {
        this.isSpawn = false;
    }

    /**
     * Indicate if actor can enter the HighGround.
     *
     * Overrides HighGround.canActorEnter()
     *
     * @see HighGround#canActorEnter(Actor)
     * @param actor the Actor to check
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
