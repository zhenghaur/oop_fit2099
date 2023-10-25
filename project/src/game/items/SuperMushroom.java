package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

/**
 * Class representing a Super Mushroom in the game world.
 */
public class SuperMushroom extends Item implements Consumable, Tradable{

    /**
     * Constructor to create a Super Mushroom.
     */
    public SuperMushroom(){
        super("Super Mushroom", '^', true);
        this.addAction(new ConsumeAction(this));
    }
}