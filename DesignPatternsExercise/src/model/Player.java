package model;

import core.Main;
import shared.Factory;
import shared.Movable;
import shared.StoneFactory;
import stone.Throw;

import java.util.concurrent.ThreadLocalRandom;

public class Player implements GameObject, Movable {
    private int healthPoints;
    private int damage;
    private Factory factory;
    private int row;
    private int col;
    private int prevRow;
    private int prevCol;

    public Player(int healthPoints, int damage) {
        this.healthPoints = healthPoints;
        this.damage = damage;
        this.factory = new StoneFactory();
        this.row = 15;
        this.col = 7;
        this.prevRow = 15;
        this.prevCol = 7;
    }

    @Override
    public void update() {

        int nextInt = ThreadLocalRandom.current().nextInt(1000);

        if(nextInt % 3 == 0){
            //produce може да взима през datastorage зависимостите
            Main.gameObject.add(this.factory.produce());
        }else if(nextInt % 7 == 0){
            Main.gameObject.removeIf(g -> g.getClass().getSimpleName().equals("Stone"));
        }

        //use for first exercise
        //System.out.println("Player updated");
    }

    @Override
    public void render() {
        //use for first exercise
        //System.out.println("Player rendered");
        Main.field.field[this.prevRow][this.prevCol] = '-';
        Main.field.field[this.row][this.col] = 'P';
        this.prevRow = this.row;
        this.prevCol = this.col;
    }

    @Override
    public int getRow() {
        return this.row = row;
    }

    @Override
    public int getCol() {
        return this.col = col;
    }

    @Override
    public void increaseRow() {
        this.prevRow = this.row;
        this.row = this.row != 15 ? this.row + 1: 15;
    }

    @Override
    public void increaseCol() {
        this.prevCol = this.col;
        this.col = this.col != 15 ? this.col + 1: 15;
    }

    @Override
    public void decreaseRow() {
        this.prevRow = this.row;
        this.row = this.row != 0 ? this.row - 1: 0;
    }

    @Override
    public void decreaseCol() {
        this.prevCol = this.col;
        this.col = this.col != 0 ? this.col - 1: 0;
    }

    //for 1 and 2 ex
    /*public void throwStone(){
        Main.gameObject.add(factory.produce());
    }*/

    public void throwStone(Throw th){
        th.throwStone();
    }
}
