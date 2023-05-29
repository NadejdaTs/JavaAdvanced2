package model;

import core.Main;
import model.GameObject;
import shared.Movable;

public class Stone implements GameObject, Movable {
    private int row;
    private int col;
    private int prevRow;
    //private int prevCol;
    private int speed;

    public Stone(){
        this.row = Main.player.getRow();
        this.col = Main.player.getCol();
        this.prevRow = this.row;
        //this.prevCol = this.col;
        this.speed = 1;
    }
    @Override
    public void update() {
        //use for first exercise
        //System.out.println("I am stone and I do nothing.");
        if(this.row >= 0) {
            this.prevRow = this.row;
            //for 1 and 2 ex
            //this.row--;
            this.row -= speed;
        }
    }

    @Override
    public void render() {
        //use for first exercise
        //System.out.println("See stone");
        if(this.row != -1 && this.row < 15){
            //Main.field.field[this.prevRow][this.prevCol] = '-';
            Main.field.field[this.prevRow][this.col] = '-';
            Main.field.field[this.row][this.col] = 'S';
            this.prevRow = this.row;
            //this.prevCol = this.col;
        }
    }

    @Override
    public int getRow() {
        return 0;
    }

    @Override
    public int getCol() {
        return 0;
    }

    @Override
    public void increaseRow() {

    }

    @Override
    public void increaseCol() {

    }

    @Override
    public void decreaseRow() {

    }

    @Override
    public void decreaseCol() {

    }

    public void setSpeed(int newSpeed) {
        this.speed = newSpeed;
    }
}
