package utilities;

import game.Controller;
import utilities.Action;

public class PointNShootController implements Controller {
    Action action;
    public PointNShootController() {
        action = new utilities.Action();
    }

    @Override
    public Action action() {
        return action;
    }
}
