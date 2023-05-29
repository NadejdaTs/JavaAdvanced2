package command;

import static core.Main.*;

public class MoveUp implements Command{
    @Override
    public void execute() {
        player.decreaseRow();
    }
}
