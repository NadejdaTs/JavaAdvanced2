package stone;

import core.Main;
import shared.StoneFactory;

public class ThrowFast implements Throw{
    @Override
    public void throwStone() {
        Main.gameObject.add(new StoneFactory().produceFastStone());
    }
}
