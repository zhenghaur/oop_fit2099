package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.actions.ConsumeAction;

/**
 * Class representing a Fire Flower in the game world.
 */
public class FireFlower extends Item implements Consumable {

    /**
     * Constructor to create a Fire Flower.
     */
    public FireFlower() {
        super("Fire Flower", 'f', false);
        this.addAction(new ConsumeAction(this));
    }
}
