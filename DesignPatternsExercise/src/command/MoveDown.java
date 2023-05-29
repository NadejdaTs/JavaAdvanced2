package command;

import static core.Main.player;

public class MoveDown implements Command{
    @Override
    public void execute() {
        player.increaseRow();
    }
}
