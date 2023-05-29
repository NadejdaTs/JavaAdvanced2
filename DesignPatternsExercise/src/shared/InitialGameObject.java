package shared;

import core.Field;
import model.GameObject;

import java.util.ArrayList;
import java.util.List;

public class InitialGameObject implements ProduceMultiple {
    private List<Factory> objectFactories;

    public InitialGameObject(){
        this.objectFactories = new ArrayList<>();
    }
    @Override
    public List<GameObject> produce() {
        List<GameObject> gameObjects = new ArrayList<>();
        for (Factory objectFactory : objectFactories) {
            gameObjects.add(objectFactory.produce());
        }
        gameObjects.add(new Field()); //anti pattern

        return gameObjects;
    }

    @Override
    public void setFactories(List<Factory> objectFactories) {
        this.objectFactories = objectFactories;
    }
}
