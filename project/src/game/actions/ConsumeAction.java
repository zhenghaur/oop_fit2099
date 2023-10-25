package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Player;
import game.items.*;
import game.terrains.Fountain;
import game.utils.Status;

/**
 * Special Action for consuming an Item.
 */
public class ConsumeAction extends Action {

    /**
     * The Item that is to be consumed
     */
    private Consumable target;

    /**
     * Constructor to create an Action that will consume the Item.
     *
     * @param target the Item to consume
     */
    public ConsumeAction(Consumable target){
        this.target = target;
    }

    /**
     * Allow the Actor to consume the Item.
     *
     * Overrides Action.execute()
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the Action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map){
        String ret = "";
        if (this.target instanceof SuperMushroom){
            actor.increaseMaxHp(50);
            actor.addCapability(Status.TALL);
            actor.removeItemFromInventory((Item) this.target);
            map.locationOf(actor).removeItem((Item) this.target);
            ret += this.target + " consumed! " + actor + " maximum health +50";
        }
        else if (this.target instanceof PowerStar){
            actor.heal(200);
            ((Player) actor).setInvincibleRemaining(11);
            actor.addCapability(Status.INVINCIBLE);
            actor.removeItemFromInventory((Item) this.target);
            map.locationOf(actor).removeItem((Item) this.target);
            ret += this.target + " consumed! " + actor + " is invincible for 10 rounds";
        }
        else if (this.target instanceof Bottle){
            ret += ((Bottle) this.target).getName() + " consumed! " + ((Bottle) this.target).consume(actor, map);;
        }
        else if (this.target instanceof Fountain){
            if (((Fountain) this.target).getCapacity() > 0) {
                ret += "Fountain water consumed! " + ((Fountain) this.target).consume(actor, map);
            }
            else{
                ret += "Fountain is currently dry, come again later";
            }
        }
        else if(this.target instanceof FireFlower){
            ((Player) actor).setFireAttackRemaining(21);
            actor.addCapability(Status.FIRE_ATTACK);
            actor.removeItemFromInventory((Item) this.target);
            map.locationOf(actor).removeItem((Item) this.target);
            ret += this.target + " consumed! " + actor + " has fire attack for 20 rounds";
        }
        return ret;
    }

    /**
     * Returns a description of this action suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player consumes SuperMushroom"
     */
    @Override
    public String menuDescription(Actor actor){
        String ret = "";
        ret += actor + " consumes " + this.target;
        return ret;
    }

}