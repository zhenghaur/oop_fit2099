package game.mobs;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Player;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.items.Key;
import game.resets.Resettable;
import game.utils.Status;
import game.utils.Utils;

import java.util.List;
import java.util.Map;

/**
 * A big turtle guy.
 */
public class Bowser extends Enemy implements Resettable {

	/**
	 * The Location where Bowser spawns.
	 */
	private Location spawn;

	/**
	 * The game map.
	 */
	private GameMap map;

	/**
	 * Constructor to create a Bowser.
	 */
	public Bowser(Location spawn) {
		super("Bowser", 'B', 500);
		this.addItemToInventory(new Key());
		this.spawn = spawn;
		this.addCapability(Status.FIRE_ATTACK);
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
		this.map = map;
		List<Exit> exits = map.locationOf(this).getExits();
		for (Exit exit : exits){
			if (exit.getDestination().containsAnActor() && exit.getDestination().getActor() instanceof Player){
				this.behaviours.put(8, new AttackBehaviour(exit.getDestination().getActor()));
				this.behaviours.put(9, new FollowBehaviour(exit.getDestination().getActor()));
				break;
			}
		}
		for (Behaviour Behaviour : this.getBehaviours().values()) {
			Action action = Behaviour.getAction(this, map);
			if (action != null)
				return action;
		}
		return new DoNothingAction();
	}

	/**
	 * Creates and returns an intrinsic weapon.
	 *
	 * the Actor 'punches' for 80 damage.
	 *
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(80, "punches");
	}

	/**
	 * Reset the Bowser.
	 *
	 * Overrides Enemy.resetInstance()
	 *
	 * @see Enemy#resetInstance()
	 */
	@Override
	public void resetInstance() {
		super.resetInstance();
		map.moveActor(this, this.spawn);
		this.heal(this.getMaxHp());
	}
}