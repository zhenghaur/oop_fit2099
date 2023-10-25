package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Player;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.items.Tradable;
import game.weapons.Wrench;

/**
 * Special Action for Trading Items.
 */
public class TradeAction extends Action {

    /**
     * The price of the Item
     */
    private int price;

    /**
     * The Item that is to be traded
     */
    private Item target;

    /**
     * Constructor to create an Action that will trade Item with the Toad.
     *
     * @param target the Item to trade
     */
    public TradeAction(Tradable target){
        this.target = (Item) target;
        if (this.target instanceof Wrench){
            this.price = 200;
        }
        else if (this.target instanceof SuperMushroom){
            this.target.togglePortability();
            this.price = 400;
        }
        else if (this.target instanceof PowerStar){
            this.target.togglePortability();
            this.price = 600;
        }
    }

    /**
     * Allow the Actor to trade with the Toad.
     *
     * Overrides Action.execute()
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the Action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String ret = "";

        if (((Player) actor).spend(price)){
            actor.addItemToInventory(this.target);
            ret += "Wallet balance = " + ((Player) actor).getWallet() + ", " + this.target + " obtained! ";
        }
        else{
            ret += "You don't have enough coins!";
        }
        return ret;
    }

    /**
     * Returns a description of this trade suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player buys Power Star ($600)"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + this.target + " ($" + this.price + ")";
    }
}