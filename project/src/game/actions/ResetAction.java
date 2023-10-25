package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.resets.ResetManager;
import game.utils.Status;

/**
 * Special Action for resetting the game.
 */
public class ResetAction extends Action {

    /**
     * Allow the game to reset.
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
        ResetManager.getInstance().run();
        actor.addCapability(Status.RESET);
        return "Game is reset.";
    }

    /**
     * Returns a description of this reset suitable to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a String, e.g. "Reset the game. "
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset the game. ";
    }

    /**
     * Returns this Action's hotkey.
     *
     * @return the hotkey, "r"
     */
    @Override
    public String hotkey() {
        return "r";
    }
}