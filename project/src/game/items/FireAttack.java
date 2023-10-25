package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Class representing a Fire Attack in the game world.
 */
public class FireAttack extends Item {

    /**
     * Number of rounds remaining before the effect runs out
     */
    private int duration;

    /**
     * Constructor to create a Fire Attack.
     */
    public FireAttack() {
        super("Fire", 'v', false);
        this.duration = 3;
    }

    /**
     * Inform the Fire on the ground of the passage of time.
     * This method is called once per turn, if the fire rests upon the ground.
     *
     * Overrides Item.tick()
     *
     * @see Item#tick(Location)
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (currentLocation.containsAnActor()){
            currentLocation.getActor().hurt(20);
            Display display = new Display();
            display.println("Fire on the ground burns " + currentLocation.getActor() + " for 20 damage");
            if (!currentLocation.getActor().isConscious()){
                display.println("Fire on the ground killed " + currentLocation.getActor());
                ActionList dropActions = new ActionList();
                // drop all items
                for (Item item : currentLocation.getActor().getInventory())
                    dropActions.add(item.getDropAction(currentLocation.getActor()));
                for (Action drop : dropActions)
                    drop.execute(currentLocation.getActor(), currentLocation.map());
                currentLocation.map().removeActor(currentLocation.getActor());
            }
        }
        this.duration -= 1;
        if (this.duration == 0){
            currentLocation.removeItem(this);
        }
    }
}
