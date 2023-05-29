package command;


import static core.Main.player;

public class MoveFirstDiagonal implements Command{

    @Override
    public void execute() {
        player.increaseRow();
        player.increaseCol();
    }
}
