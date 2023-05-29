package model;

import core.Main;
import shared.Movable;

public class Enemy implements GameObject, Movable {
    private int healthPoints;
    private int damage;
    private int row;
    private int col;

    public Enemy(int healthPoints, int damage) {
        this.healthPoints = healthPoints;
        this.damage = damage;
        this.row = 0;
        this.col = 7;
    }
    @Override
    public void update() {
        //use for first exercise
        //System.out.println("Enemy updated");
    }

    @Override
    public void render() {
        //use for first exercise
        //System.out.println("Enemy rendered");
        Main.field.field[this.row][this.col] = 'E';
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
}
