package game.weapons;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.items.Tradable;

/**
 * Class representing Wrench in the game world.
 */
public class Wrench extends WeaponItem implements Tradable {

    /**
     * Constant Name for Wrench.
     */
    private static final String NAME = "Wrench";

    /**
     * Constant Display Character for Wrench
     */
    private static final char DISPLAY_CHAR = 'W';

    /**
     * Constant Verb for Wrench
     */
    private static final String VERB = "smacks";

    /**
     * Constant Damange for Wrench
     */
    private static final int DAMAGE = 50;

    /**
     * Constant Hit Rate for Wrench
     */
    private static final int HIT_RATE = 50;

    /**
     * Constructor to create a Wrench.
     */
    public Wrench (){
        super(Wrench.NAME, Wrench.DISPLAY_CHAR, Wrench.DAMAGE, Wrench.VERB, Wrench.HIT_RATE);
    }
}
