package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Key;

import java.util.List;

/**
 * Special action for interacting with Princess Peach.
 */
public class InteractAction extends Action {

    /**
     * Allows the actor to interact with Princess Peach.
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
        String ret = "You do not have the key to unlock Princess Peach's cuffs. ";
        List<Item> inventory = actor.getInventory();
        for (Item item: inventory){
            if (item instanceof Key){
                ret = "You have saved Princess Peach! ";
                map.removeActor(actor);
                break;
            }
        }
        return ret;
    }

    /**
     * Returns a description of this action suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Interact with Princess Peach"
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Interact with Princess Peach";
    }
}
