package selling;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        char[][] matrix = new char[n][n];
        int[] positions = new int[2];

        for (int r = 0; r < n; r++) {
            String line = sc.nextLine();
            for (int c = 0; c < n; c++) {
                matrix[r][c] = line.charAt(c);
                if(matrix[r][c] == 'S'){
                    positions[0]= r;
                    positions[1] = c;
                }
            }
        }

        boolean isDead = false;
        int winPoints = 0;
        while(!isDead && winPoints < 50){
            String input = sc.nextLine();
            int currPoints = 0;
            switch (input){
                case "up":
                    currPoints += matrixAction(matrix, positions, -1, 0);
                    break;
                case "down":
                    currPoints += matrixAction(matrix, positions, 1, 0);
                    break;
                case "left":
                    currPoints += matrixAction(matrix, positions, 0, -1);
                    break;
                case "right":
                    currPoints += matrixAction(matrix, positions, 0, 1);
                    break;
            }
            if(currPoints == -1){
                isDead = true;
            }else {
                winPoints += currPoints;
            }
        }

        if(isDead){
            System.out.println("Bad news, you are out of the bakery.");
        }else{
            System.out.println("Good news! You succeeded in collecting enough money!");
        }
        System.out.println("Money: " + winPoints);

        printMatrix(matrix);
    }

    private static int matrixAction(char[][] matrix, int[] positions, int rowPosition, int colPosition) {
        int row = positions[0];
        int col = positions[1];
        int newRow = row + rowPosition;
        int newCol = col + colPosition;
        matrix[row][col] = '-';
        if(!isInBounds(matrix, newRow, newCol)){
            return -1;
        }

        int cnt = 0;
        if(Character.isDigit(matrix[newRow][newCol])){
            cnt += Character.getNumericValue(matrix[newRow][newCol]);
        }else if(matrix[newRow][newCol] == 'O'){
            matrix[newRow][newCol] = '-';
            int[] resultPositions = getNewOPosition(matrix, newRow, newCol);
            newRow = resultPositions[0];
            newCol = resultPositions[1];
        }

        matrix[newRow][newCol] = 'S';
        positions[0] = newRow;
        positions[1] = newCol;

        return cnt;
    }

    private static int[] getNewOPosition(char[][] matrix, int currRow, int currCol) {
        int[] resultPositions = new int[2];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                if(matrix[r][c] == 'O' && currRow != r && currCol != c){
                    resultPositions[0]= r;
                    resultPositions[1] = c;
                }
            }
        }
        return resultPositions;
    }

    public static boolean isInBounds(char[][] matrix, int row, int col){
        return (row >= 0 && row < matrix.length) && (col >= 0 && col < matrix[0].length);
    }

    private static void printMatrix(char[][] matrix) {
        Arrays.stream(matrix)
                .forEach(System.out::println);
    }
}
