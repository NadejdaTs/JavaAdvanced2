package core;

import model.GameObject;

import static core.Main.*;

public class Field implements GameObject {
    public char[][] field; //if it is private, we will need a setters

    public Field(){
        this.field = new char[16][16];
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                this.field[i][j] = '-';
            }
        }
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        for (int r = 0; r < 16; r++) {
            for (int c = 0; c < 16; c++) {
                /*if(checkCellMatch(r, c, player.getRow(), player.getCol())){
                    this.field[r][c] = 'P';
                }else if(checkCellMatch(r, c, enemy.getRow(), enemy.getCol())){
                    this.field[r][c] = 'E';
                }else{
                    this.field[r][c] = '-';
                }*/
                System.out.print(this.field[r][c] + " ");
            }
            System.out.println();
        }
    }

    /*private boolean checkCellMatch(int r, int c, int outSideRow, int outSideCol){
        return r ==outSideRow && c == outSideCol;
    }*/
}
