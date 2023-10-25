package game.mobs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Player;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.resets.Resettable;

import java.util.ArrayList;
import java.util.List;

/**
 * A little venus flytrap plant.
 */
public class PiranhaPlant extends Enemy{

    /**
     * Number of rounds existed in the game
     */
    private int round;

    /**
     * Constructor to create a Piranha Plant.
     */
    public PiranhaPlant() {
        super("Piranha Plant", 'Y', 150);
        this.round = 0;
    }

    /**
     * Creates and return an intrinsic weapon.
     *
     * the Actor 'chomps' for 90 damage.
     *
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    protected IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(90, "chomps");
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
        List<Exit> exits = map.locationOf(this).getExits();
        for (Exit exit : exits){
            if (exit.getDestination().containsAnActor() && exit.getDestination().getActor() instanceof Player){
                this.behaviours.put(8, new AttackBehaviour(exit.getDestination().getActor()));
                break;
            }
        }
        for (game.behaviours.Behaviour Behaviour : this.getBehaviours().values()) {
            Action action = Behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Reset the Piranha Plant.
     *
     * Overrides Enemy.resetInstance()
     *
     * @see Enemy#resetInstance()
     */
    @Override
    public void resetInstance() {
        if (this.round > 0) {
            this.increaseMaxHp(50);
        }
    }
}
