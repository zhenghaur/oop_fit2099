package game.mobs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.behaviours.Behaviour;
import game.behaviours.WanderBehaviour;
import game.resets.Resettable;
import game.utils.Status;
import game.utils.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * A little fungus guy.
 */
public class Goomba extends Enemy{

	/**
	 * Constructor to create a Goomba.
	 */
	public Goomba() {
		super("Goomba", 'g', 20);
		this.behaviours.put(10, new WanderBehaviour());
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
		if (Utils.rollHundred() < 10){
			map.removeActor(this);
		}
		else {
			for (Behaviour Behaviour : this.getBehaviours().values()) {
				Action action = Behaviour.getAction(this, map);
				if (action != null)
					return action;
			}
		}
		return new DoNothingAction();
	}

	/**
	 * Creates and returns an intrinsic weapon.
	 *
	 * the Actor 'kicks' for 5 damage.
	 *
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(5, "kicks");
	}

}