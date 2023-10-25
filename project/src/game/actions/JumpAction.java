package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.utils.Status;
import game.utils.Utils;
import game.terrains.Wall;
import game.items.Coin;
import game.terrains.*;

/**
 * Special Action for jumping on to a high ground.
 */
public class JumpAction extends Action {

    /**
     * The Location to jump to
     */
    private Location target;

    /**
     * The direction of target Location
     */
    private String direction;

    /**
     * The command key for this Action
     */
    private String hotkey;

    /**
     * Constructor to create an Action that will let the Actor jump to a high ground in a given Direction.
     * A currently-unused menu hotkey will be assigned.
     *
     * @param target the Location to jump to
     * @param direction the direction to jump to
     */
    public JumpAction(Location target, String direction){
        this.target = target;
        this.direction = direction;
        this.hotkey = null;
    }

    /**
     * Allow the Actor to jump to a high ground.
     *
     * Overrides Action.execute()
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the Action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map){
        String ret = "";
        // if is invincible, destroy higher grounds and drop coin
        if (actor.hasCapability(Status.INVINCIBLE)){
            map.moveActor(actor, this.target);
            if (this.target.getGround() instanceof Wall){
                this.target.setGround(new Dirt());
                this.target.addItem(new Coin(5));
                ret += "Mario squashed the Wall into Dirt!";
            }
            else if (this.target.getGround() instanceof Sprout){
                this.target.setGround(new Dirt());
                this.target.addItem(new Coin(5));
                ret += "Mario squashed the Sprout into Dirt!";
            }
            else if (this.target.getGround() instanceof Sapling){
                this.target.setGround(new Dirt());
                this.target.addItem(new Coin(5));
                ret += "Mario squashed the Sapling into Dirt!";
            }
            else if (this.target.getGround() instanceof Mature){
                this.target.setGround(new Dirt());
                this.target.addItem(new Coin(5));
                ret += "Mario squashed the Mature Tree into Dirt!";
            }
            else if (this.target.getGround() instanceof WarpPipe){
                ret += "Mario jumps onto the Warp Pipe. ";
            }
        }
        else{
            if (target.getGround() instanceof Wall){
                ret += "Mario tries to jump on to a Wall. ";
                if (actor.hasCapability(Status.TALL) || Utils.rollHundred() < 80){
                    map.moveActor(actor, this.target);
                    ret += "The jump is successful! ";
                }
                else{
                    actor.hurt(20);
                    ret += "Unfortunately, the jump fails and Mario receives 20 damage! ";
                }
            }
            else if (target.getGround() instanceof Sprout){
                ret += "Mario tries to jump on to a Sprout. ";
                if (actor.hasCapability(Status.TALL) || Utils.rollHundred() < 90){
                    map.moveActor(actor, this.target);
                    ret += "The jump is successful! ";
                }
                else{
                    actor.hurt(10);
                    ret += "Unfortunately, the jump fails and Mario receives 10 damage! ";
                }
            }
            else if (target.getGround() instanceof Sapling){
                ret += "Mario tries to jump on to a Sapling. ";
                if (actor.hasCapability(Status.TALL) || Utils.rollHundred() < 80){
                    map.moveActor(actor, this.target);
                    ret += "The jump is successful! ";
                }
                else{
                    actor.hurt(20);
                    ret += "Unfortunately, the jump fails and Mario receives 20 damage! ";
                }

            }
            else if (target.getGround() instanceof Mature){
                ret += "Mario tries to jump on to a Mature Tree. ";
                if (actor.hasCapability(Status.TALL) || Utils.rollHundred() < 70){
                    map.moveActor(actor, this.target);
                    ret += "The jump is successful! ";
                }
                else{
                    actor.hurt(30);
                    ret += "Unfortunately, the jump fails and Mario receives 30 damage! ";
                }
            }
            else if (this.target.getGround() instanceof WarpPipe){
                ret += "Mario jumps onto the Warp Pipe. ";
                map.moveActor(actor, this.target);
            }
        }

        // if player died of fall damage
        if (!(actor.isConscious())){
            ActionList dropActions = new ActionList();
            // drop all items
            for (Item item : actor.getInventory())
                dropActions.add(item.getDropAction(actor));
            for (Action drop : dropActions)
                drop.execute(actor, map);
            // remove actor
            map.removeActor(actor);
        }
        return ret;
    }

    /**
     * Returns a description of this jump suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Player jumps on to Wall at (42, 10)."
     */
    @Override
    public String menuDescription(Actor actor){
        String ret = "" + actor;
        String name = "";
        if (target.getGround().getDisplayChar() == '#'){
            name += "Wall";
        }
        else if (target.getGround().getDisplayChar() == '+'){
            name += "Sprout";
        }
        else if (target.getGround().getDisplayChar() == 't'){
            name += "Sapling";
        }
        else if (target.getGround().getDisplayChar() == 'T'){
            name += "Mature Tree";
        }
        else if (target.getGround().getDisplayChar() == 'C'){
            name += "Warp Pipe";
        }
        String coordinate = " (" + this.target.x() + ", " + this.target.y() + ")";
        if (actor.hasCapability(Status.INVINCIBLE)){
            ret += " moves on to ";
        }
        else {
            ret += " jumps on to ";
        }
        ret += name + " at " + this.direction + coordinate;
        return ret;
    }
}