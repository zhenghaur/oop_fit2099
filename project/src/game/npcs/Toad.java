package game.npcs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.TradeAction;
import game.items.PowerStar;
import game.items.SuperMushroom;
import game.weapons.Wrench;

/**
 * Class representing a Toad in the game
 */
public class Toad extends Actor {

    /**
     * Constructor to create a Toad.
     */
    public Toad(){
        super("Toad", 'O', 1);
    }

    /**
     * Returns a new collection of the Actions that Toad can do to the current Actor.
     *
     * Overrides Actor.allowableActions()
     *
     * @see Actor#allowableActions(Actor, String, GameMap)
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new TradeAction(new Wrench()));
        actions.add(new TradeAction(new SuperMushroom()));
        actions.add(new TradeAction(new PowerStar()));
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
        return new DoNothingAction();
    }
}