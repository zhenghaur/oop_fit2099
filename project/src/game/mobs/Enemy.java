package game.mobs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.resets.Resettable;
import game.utils.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * Class for Enemy type.
 */
public abstract class Enemy extends Actor implements Resettable {

    /**
     * A mapping of integer to the behaviour
     */
    protected final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * The game map
     */
    private GameMap map = null;

    /**
     * Constructor.
     *
     * @param name the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
        this.registerInstance();
    }

    /**
     * Overrides Actor.playTurn()
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return null / to be overwritten
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return null;
    }

    /**
     * Returns a new collection of the Actions that Goomba can do to the current Actor.
     *
     * Overrides Actor.allowableActions()
     *
     * @see Actor#allowableActions(Actor, String, GameMap)
     * @see Status#HOSTILE_TO_ENEMY
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
            actions.add(new AttackAction(this,direction));
        }
        return actions;
    }

    /**
     * Set the map of the Enemy
     *
     * @param map the map containing the Actor
     */
    public void setMap(GameMap map) {
        this.map = map;
    }

    /**
     * Return the behaviours of Enemy
     * @return a mapping of integer to the behaviour
     */
    public Map<Integer, Behaviour> getBehaviours(){
        return this.behaviours;
    }

    /**
     * Reset the Enemy
     *
     * Overrides Resettable.resetInstance()
     *
     * @see Resettable#resetInstance()
     */
    @Override
    public void resetInstance() {
        if (this.map != null) {
            this.map.removeActor(this);
        }
    }
}
