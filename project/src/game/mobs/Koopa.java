package game.mobs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.items.SuperMushroom;
import game.resets.Resettable;
import game.utils.Status;
import game.weapons.Wrench;

import java.util.HashMap;
import java.util.Map;

/**
 * A little turtle guy.
 */
public class Koopa extends Enemy {

    /**
     * Constructor to create a Koopa.
     */
    public Koopa() {
        super("Koopa", 'K', 100);
        this.behaviours.put(10, new WanderBehaviour());
        this.addCapability(Status.CAPABLE_DORMANT);
        this.addItemToInventory(new SuperMushroom());
    }

    /**
     * Returns a new collection of the Actions that Koopa can do to the current Actor.
     *
     * Overrides Actor.allowableActions()
     *
     * @see Actor#allowableActions(Actor, String, GameMap)
     * @see Status#HOSTILE_TO_ENEMY
     * @see Status#DORMANT
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        // it can be attacked only by the HOSTILE opponent, and this action will not attack the HOSTILE enemy back.
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            if (!(this.hasCapability(Status.DORMANT)) || otherActor.getWeapon() instanceof Wrench) {
                actions.add(new AttackAction(this, direction));
            }
        }
        return actions;
    }

    /**
     * Select and return an action to perform on the current turn.
     *
     * Overrides Actor.playTurn()
     *
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        this.setMap(map);
        if (!(this.hasCapability(Status.DORMANT))) {
            for (game.behaviours.Behaviour Behaviour : this.getBehaviours().values()) {
                Action action = Behaviour.getAction(this, map);
                if (action != null)
                    return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * Creates and return an intrinsic weapon.
     *
     * the Actor 'punches' for 30 damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "punches");
    }




    /**
     * Return a display character for the Koopa
     * @return a char, e.g. 'D' if Koopa is in Dormant state
     */
    @Override
    public char getDisplayChar() {
        if (this.hasCapability(Status.DORMANT)){
            return 'D';
        }
        return super.getDisplayChar();
    }
}