package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.Bottle;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ResetAction;
import game.resets.Resettable;
import game.utils.Status;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable {

	/**
	 * Number of rounds for the remaining FIRE_ATTACK status
	 */
	private int fireAttackRemaining;

	/**
	 * Base damage for player
	 */
	private int baseDamage;

	/**
	 * The teleport location on GameMap
	 */
	private Location teleportLocation = null;

	/**
	 * Number of rounds for the remaining INVINCIBLE status
	 */
	private int invincibleRemaining;

	/**
	 * Player's money/Coins
	 */
	private int wallet;

	/**
	 * A game menu
	 */
	private final Menu menu = new Menu();

	/**
	 * Constructor to create a Player.
	 *
	 * @param name        Name to call the player in the UI
	 * @param displayChar Character to represent the player in the UI
	 * @param hitPoints   Player's starting number of hitpoints
	 */
	public Player(String name, char displayChar, int hitPoints) {
		super(name, displayChar, hitPoints);
		this.addCapability(Status.HOSTILE_TO_ENEMY);
		this.registerInstance();
		this.addItemToInventory(new Bottle());
		this.baseDamage = 5;
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
	 * @return the Menu and actions player is available to perform
	 */
	@Override
	public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
		// add reset ability if game is not reset yet
		if (!(this.hasCapability(Status.RESET))){
			actions.add(new ResetAction());
		}

		// reduce invincible duration counter and remove invincible status at 0
		if (super.hasCapability(Status.INVINCIBLE)){
			this.invincibleRemaining -= 1;
			if (this.invincibleRemaining == 0) {
				super.removeCapability(Status.INVINCIBLE);
			}
		}
		// reduce fire attack duration counter and remove fire attack status at 0
		if (super.hasCapability(Status.FIRE_ATTACK)){
			this.fireAttackRemaining -= 1;
			if (this.fireAttackRemaining == 0) {
				super.removeCapability(Status.FIRE_ATTACK);
			}
		}

		// Handle multi-turn Actions
		if (lastAction.getNextAction() != null)
			return lastAction.getNextAction();

		// display the current player status
		display.println("=======================================");
		// display current player hp
		display.println("Health Points: " + this.printHp());
		// display wallet balance
		display.println("");
		display.println("$ Balance: " + this.wallet);
		// display inventory
		if (this.getInventory().size() > 0){
			display.println("");
			display.println("Inventory: ");
			for (Item item: this.getInventory()){
				display.println(item.toString());
			}
		}
		// display buff/status
		if (this.hasCapability(Status.TALL)){
			display.println("");
			display.println("Jump Rate + 100%");
		}
		if (this.hasCapability(Status.INVINCIBLE)){
			display.println("");
			display.println("MARIO IS INVINCIBLE!!! - " + this.invincibleRemaining + " turns left");
		}
		if (this.hasCapability(Status.FIRE_ATTACK)){
			display.println("");
			display.println("Mario has fire attack - " + this.fireAttackRemaining + " turns left");
		}
		display.println("=======================================");

		return menu.showMenu(this, actions, display);
	}

	/**
	 * Return the display char of Player.
	 *
	 * Overrides Actor.getDisplayChar()
	 *
	 * @see Actor#getDisplayChar()
	 * @return char represent the Tall status of Player
	 */
	@Override
	public char getDisplayChar(){
		return this.hasCapability(Status.TALL) ? Character.toUpperCase(super.getDisplayChar()): super.getDisplayChar();
	}

	/**
	 * Deduct the hitpoints of Player.
	 *
	 * @param points number of hitpoints to deduct.
	 */
	@Override
	public void hurt(int points) {
		super.hurt(points);
		this.removeCapability(Status.TALL);
	}

	/**
	 * A setter of remaining INVINCIBLE status
	 *
	 * @param duration number of rounds
	 */
	public void setInvincibleRemaining(int duration){
		this.invincibleRemaining = duration;
	}

	/**
	 * Update balance of Player's wallet.
	 *
	 * @param change amount of coins/money gained
	 */
	public void changeBalance(int change){
		this.wallet += change;
	}

	/**
	 * Validate if Player could spend an amount
	 *
	 * @param price the price of Item
	 * @return true if player have sufficient balance; false otherwise
	 */
	public boolean spend(int price){
		if (this.wallet >= price){
			this.changeBalance(-price);
			return true;
		}
		return false;
	}

	/**
	 * Return the Player's wallet balance
	 * @return player's money/coins
	 */
	public int getWallet(){
		return this.wallet;
	}

	/**
	 * Reset the Player.
	 *
	 * Overrides Resettable.resetInstance()
	 *
	 * @see Resettable#resetInstance()
	 */
	@Override
	public void resetInstance() {
		// remove SuperMushroom buff
		this.removeCapability(Status.TALL);
		// remove PowerStar buff
		this.removeCapability(Status.INVINCIBLE);
		// heal player to maximum
		this.heal(this.getMaxHp());
	}

	/**
	 * Set teleport location in GameMap1
	 *
	 * @param teleportLocation the location in GameMap1
	 */
	public void setTeleportLocation(Location teleportLocation){
		this.teleportLocation = teleportLocation;
	}

	/**
	 * Returns the location in GameMap1
	 *
	 * @return Location coordinates
	 */
	public Location getTeleportLocation(){
		return this.teleportLocation;
	}

	/**
	 * Creates and returns an intrinsic weapon.
	 *
	 * the Actor 'punches' for 5 damage.
	 *
	 * @return a freshly-instantiated IntrinsicWeapon
	 */
	@Override
	protected IntrinsicWeapon getIntrinsicWeapon() {
		return new IntrinsicWeapon(this.baseDamage, "punches");
	}

	/**
	 * Increase the damage after receiving special buffs.
	 *
	 * @param damage the amount to increase
	 */
	public void increaseDamage(int damage){
		this.baseDamage += damage;
	}

	/**
	 * Set the number of rounds for the remaining FIRE_ATTACK status.
	 *
	 * @param fireAttackRemaining number of rounds remaining
	 */
	public void setFireAttackRemaining(int fireAttackRemaining) {
		this.fireAttackRemaining = fireAttackRemaining;
	}
}
