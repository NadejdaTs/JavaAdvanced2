package command;

import static core.Main.player;

public class MoveSecondDiagonal implements Command{

    @Override
    public void execute() {
        player.decreaseRow();
        player.increaseCol();
    }
}
