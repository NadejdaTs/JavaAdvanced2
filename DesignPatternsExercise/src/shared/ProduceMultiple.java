package shared;

import model.GameObject;
import shared.Factory;

import java.util.List;

public interface ProduceMultiple {

    List<GameObject> produce();

    void setFactories(List<Factory> objectFactory);
}
