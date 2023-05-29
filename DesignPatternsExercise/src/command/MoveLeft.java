package command;

import static core.Main.player;

public class MoveLeft implements Command{
    @Override
    public void execute() {
        player.decreaseCol();
    }
}
