package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import game.items.Bottle;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.PowerWater;
import game.terrains.Fountain;
import game.terrains.HealthFountain;
import game.terrains.PowerFountain;

import java.util.List;

/**
 * Special action for refilling the water bottle.
 */
public class RefillAction extends Action {

    /**
     * The fountain that is to be used to refill water.
     */
    private Fountain target;

    /**
     * Constructor to create an Action that will refill the water bottle from the fountain.
     *
     * @param target The fountain for refilling water
     */
    public RefillAction(Fountain target){
        this.target = target;
    }

    /**
     * Allows the actor to refill the water bottle.
     *
     * Overrides Action.execute
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the Action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String ret = "";
        List<Item> inventory = actor.getInventory();
        if (this.target.getCapacity() > 0) {
            for (Item item : inventory) {
                if (item instanceof Bottle) {
                    Bottle bottle = (Bottle) item;
                    bottle.refill(this.target.refill());
                    ret += "Mario refills bottle from fountain ";
                    break;
                }
            }
        }
        else{
            ret += "Fountain is currently dry, come again later";
        }
        return ret;
    }

    /**
     * Returns a description of this action suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Mario refills bottle with Power Fountain"
     */
    @Override
    public String menuDescription(Actor actor) {
        String ret = "";
        ret += actor + " refills bottle with " + this.target;
        return ret;
    }
}
