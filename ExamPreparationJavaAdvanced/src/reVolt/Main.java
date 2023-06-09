package reVolt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int size = Integer.parseInt(sc.nextLine());

        int linesCount = Integer.parseInt(sc.nextLine());

        char[][] matrix = new char[size][size];

        int[] position = new int[2];

        for (int i = 0; i < size; i++) {
            String line = sc.nextLine();
            matrix[i] = line.toCharArray();
            if(line.contains("f")){
                position[0] = i;
                position[1] = line.indexOf("f");
            }
        }

        boolean playerWon = false;
        while(linesCount-- > 0 && !playerWon){
            String command = sc.nextLine();

            switch(command){
                case "up":
                    playerWon = movePlayer(position, matrix, -1, 0);
                    break;
                case "down":
                    playerWon = movePlayer(position, matrix, 1, 0);
                    break;
                case "left":
                    playerWon = movePlayer(position, matrix, 0, -1);
                    break;
                case "right":
                    playerWon = movePlayer(position, matrix, 0, 1);
                    break;
            }
        }
        if(playerWon){
            System.out.println("Player won!");
        }else{
            System.out.println("Player lost!");
        }
        printMatrix(matrix);
    }

    private static boolean movePlayer(int[] position, char[][] matrix, int rowModification, int colModification) {
        //up = row -1
        int row = position[0];
        int col = position[1];
        int newRow = ensureIndexIsInBounds(row + rowModification, matrix.length);
        int newCol = ensureIndexIsInBounds(col + colModification, matrix.length);

        boolean hasWon = false;
        if(matrix[newRow][newCol] == 'B'){
            newRow = ensureIndexIsInBounds(newRow + rowModification, matrix.length);
            newCol = ensureIndexIsInBounds(newCol + colModification, matrix.length);
        }else if(matrix[newRow][newCol] == 'T'){
            newRow = row;
            newCol = col;
        }

        if(matrix[newRow][newCol] == 'F'){
            hasWon = true;
        }

        position[0] = newRow;
        position[1] = newCol;
        matrix[row][col] = '-';
        matrix[newRow][newCol] = 'f';
        return hasWon;
    }

    public static int ensureIndexIsInBounds(int index, int bounds){
        if(index < 0){
            index = bounds - 1;
        }else if(index >= bounds){
            index = 0;
        }

        //return Math.min(bounds -1, Math.max(index, 0));?!?
        return index;
    }

    private static void printMatrix(char[][] matrix){
        for (char[] arr : matrix) {
            for (char symbol : arr) {
                System.out.print(symbol);
            }
            System.out.println();
        }
    }
}
