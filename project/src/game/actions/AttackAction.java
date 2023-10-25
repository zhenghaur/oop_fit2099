package game.actions;

import java.util.List;
import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.items.FireAttack;
import game.mobs.Enemy;
import game.mobs.PiranhaPlant;
import game.utils.Status;
import game.utils.Utils;
import game.mobs.Goomba;
import game.mobs.Koopa;

/**
 * Special Action for attacking another Actor.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor to create an Action that will attack the other Actor in a given Direction.
	 * 
	 * @param target the Actor to attack
	 * @param direction the direction of incoming attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Allow the Actor to attack another Actor.
	 *
	 * Overrides Action.execute()
	 *
	 * @see Action#execute(Actor, GameMap)
	 * @param actor The actor performing the action.
	 * @param map The map the actor is on.
	 * @return a description of Action suitable for the menu
	 */
	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();
		String result = "";
		if (actor.hasCapability(Status.FIRE_ATTACK)){
			Location location = map.locationOf(this.target);
			List<Item> items = location.getItems();
			for (int i = 0; i < items.size(); i++){
				if (items.get(i) instanceof FireAttack){
					location.removeItem(items.get(i));
				}
			}
			location.addItem(new FireAttack());

		}

		// if target is invincible = cannot hit
		if (this.target.hasCapability(Status.INVINCIBLE)){
			result += this.target + " is invincible and could not receive damage! ";
			return result;
		}

		// normal attack
		else {

			// miss
			if (!(Utils.rollHundred() <= weapon.chanceToHit())) {
				result += actor + " misses " + target + ".";
			}

			// no miss
			else {

				// if invincible, target die
				if (actor.hasCapability(Status.INVINCIBLE)){
					this.target.resetMaxHp(0);
					result += actor + " instantly killed " + this.target + ".";
				}
				// else if dormant, destroy shell
				else if (this.target.hasCapability(Status.DORMANT)){
					result += actor + " destroyed " + this.target + "'s shell!";
					this.target.hurt(1);
				}
				// normal damage
				else {
					int damage = weapon.damage();
					result += actor + " " + weapon.verb() + " " + target + " for " + damage + " damage";
					if (actor.hasCapability(Status.FIRE_ATTACK)){
						result += " with fire!";
					}
					this.target.hurt(damage);
					if (this.target instanceof PiranhaPlant) {
						((Enemy) this.target).getBehaviours().put(8, new AttackBehaviour(actor));
					} else if (this.target instanceof Enemy){
						((Enemy) this.target).getBehaviours().put(8, new AttackBehaviour(actor));
						((Enemy) this.target).getBehaviours().put(9, new FollowBehaviour(actor));
					}
				}

			}
		}

		// if target died
		if (!(this.target.isConscious())){
			// if Koopa and not dormant yet, turn into dormant mode
			if (this.target.hasCapability(Status.CAPABLE_DORMANT) && !(this.target.hasCapability(Status.DORMANT))){
				this.target.addCapability(Status.DORMANT);
				this.target.heal(1);
				result += System.lineSeparator() + this.target + " hides into its shell!";
			}
			// else
			else {
				ActionList dropActions = new ActionList();
				// drop all items
				for (Item item : this.target.getInventory())
					dropActions.add(item.getDropAction(actor));
				for (Action drop : dropActions)
					drop.execute(this.target, map);
				// remove actor
				map.removeActor(this.target);
				result += System.lineSeparator() + this.target + " is killed.";
			}
		}

		return result;
	}

	/**
	 * Returns a description of this action suitable to display in the menu.
	 *
	 * @param actor The actor performing the action.
	 * @return a String, e.g. "Player attacks Goomba at (42, 10)"
	 */
	@Override
	public String menuDescription(Actor actor) {
		return actor + " attacks " + target + " at " + direction;
	}
}