package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Player;
import game.terrains.WarpPipe;

import java.util.Map;

/**
 * Special action for teleporting to another map.
 */
public class TeleportAction extends Action {

    /**
     * The WarpPipe that is to be teleported back on
     */
    private WarpPipe target;

    /**
     * Constructor to create an Action that will teleport to another map.
     *
     * @param target The WarpPipe to teleport back to
     */
    public TeleportAction(WarpPipe target){
        this.target = target;
    }

    /**
     * Allow the actor to teleport to another map.
     *
     * Overrides Action.execute
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of the Action suitable for the menu
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Player player = (Player) actor;
        if (player.getTeleportLocation() != null){
            map.removeActor(player);
            if (player.getTeleportLocation().containsAnActor()) {
                player.getTeleportLocation().map().removeActor(player.getTeleportLocation().getActor());
            }
            player.getTeleportLocation().addActor(player);
            player.setTeleportLocation(null);
        }
        else{
            player.setTeleportLocation(map.locationOf(player));
            map.removeActor(player);
            if (this.target.getLocation().containsAnActor()) {
                this.target.getLocation().map().removeActor(this.target.getLocation().getActor());
            }
            this.target.getLocation().addActor(player);
        }
        return "Player has teleported to another map";
    }

    /**
     * Returns a description of this action suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Mario enters Warp Pipe"
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " enters Warp Pipe";
    }
}
