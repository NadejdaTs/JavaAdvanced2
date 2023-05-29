package core;

import command.CommandInterpreter;
import command.CommandListener;
import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<GameObject> gameObject;
    public static Player player; //for 2 ex
    public static Enemy enemy; //for 2 ex
    public static Field field;

    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        //DataStorage dataStorage = new DataStorage();

        gameObject = Initializer.init();

        //for 2 ex
        for (GameObject gameObject : gameObject) {
            if(gameObject instanceof Player){
                player = (Player) gameObject;
            }else if(gameObject instanceof Enemy){
                enemy = (Enemy) gameObject;
            }else if(gameObject instanceof Field){
                field = (Field) gameObject;
            }
        }


        CommandListener handler = new CommandInterpreter();
        //gameObject.add(new Player(dataStorage.loadData(Player.class)[0], dataStorage.loadData(Player.class)[1]));
        //gameObject.add(new Enemy(dataStorage.loadData(Enemy.class)[0], dataStorage.loadData(Enemy.class)[1]));

        String input = "";
        boolean gameOver = false;
        while(!gameOver){
            ArrayList<GameObject> copy = new ArrayList<>(gameObject);
            for (GameObject gameObj : copy) {
                gameObj.update();
                gameObj.render();
            }

            System.out.println();
            System.out.println();
            System.out.println("=============================");
            System.out.println("Enter new command:");

            Thread.sleep(500);
            input = sc.nextLine();
            try {
                handler.handleCommand(input.toUpperCase());
            }catch(IllegalArgumentException ex){
                System.out.println(ex.getMessage());
            }
            System.out.println();
            System.out.println();
            input = "";
        }
    }
}