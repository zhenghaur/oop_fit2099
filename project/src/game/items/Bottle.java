package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.ConsumeAction;

import java.util.ArrayList;

/**
 * Class representing a Water Bottle in the game world.
 */
public class Bottle extends Item implements Consumable {

    /**
     * The content of the Water
     */
    private ArrayList<Water> content;

    /**
     * Constructor to create Water Bottle
     */
    public Bottle() {
        super("Bottle", 'b', false);
        this.content = new ArrayList<>();
    }

    /**
     * Refill the water to the Water Bottle.
     *
     * @param water type of Water to refill
     */
    public void refill(Water water){
        this.content.add(water);
        if (this.content.size() == 1){
            this.addAction(new ConsumeAction(this));
        }
    }

    /**
     * Allows current actor to consume the water in the Bottle.
     *
     * @param actor The actor carrying the Item
     * @param map current GameMap
     * @return a description of this action suitable to display in the menu.
     */
    public String consume(Actor actor, GameMap map){
        String ret = this.content.get(this.content.size() - 1).consume(actor, map);
        this.content.remove(this.content.size() - 1);
        if (this.content.size() == 0){
            this.removeAction(this.getAllowableActions().get(0));
        }
        return ret;
    }

    /**
     * Returns a description of this item suitable to display in the menu.
     *
     * @return a String, e.g. "Bottle - empty"
     */
    @Override
    public String toString(){
        String ret = super.toString() + " - ";
        if (this.content.size() > 0) {
            ret += this.content;
        }
        else{
            ret += "empty";
        }
        return ret;
    }

    /**
     * Returns the name of the Bottle.
     *
     * @return a String, e.g. "Bottle"
     */
    public String getName(){
        return super.toString();
    }
}
