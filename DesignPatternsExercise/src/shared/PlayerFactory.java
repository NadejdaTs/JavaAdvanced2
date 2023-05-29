package shared;

import model.GameObject;
import model.Initializer;
import model.Player;

public class PlayerFactory implements Factory{
    @Override
    public GameObject produce() {
        int[] params = Initializer.dataStorage.loadData(Player.class);
        return new Player(params[0], params[1]);
    }
}
