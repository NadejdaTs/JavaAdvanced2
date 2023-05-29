package command;


import static core.Main.player;

public class MoveRight implements Command{
    @Override
    public void execute() {
        player.increaseCol();
    }
}
