package model;

import data.DataStorage;
import shared.*;

import java.util.ArrayList;
import java.util.List;

public class Initializer {
    public static DataStorage dataStorage = new DataStorage();

    //един обект, който меже да създаде множество обекти от различни типове
    public static ProduceMultiple multiple = new InitialGameObject();

    //различни факторита, които може да създадат по един обект от различни типове
    public static List<Factory> objectFactory = new ArrayList<>();

    public static List<GameObject> init(){
        objectFactory.add(new PlayerFactory());
        objectFactory.add(new EnemyFactory());
        multiple.setFactories(objectFactory);
        return multiple.produce();
    }
}
