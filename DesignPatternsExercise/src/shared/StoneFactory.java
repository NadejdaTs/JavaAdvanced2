package shared;

import model.GameObject;
import model.Stone;

public class StoneFactory implements Factory{
    @Override
    public GameObject produce() {
        return new Stone();
    }

    public GameObject produceFastStone(){
        Stone stone = new Stone();
        stone.setSpeed(4);
        return stone;
    }

    public GameObject produceSlowStone(){
        Stone stone = new Stone();
        stone.setSpeed(1);
        return stone;
    }
}
