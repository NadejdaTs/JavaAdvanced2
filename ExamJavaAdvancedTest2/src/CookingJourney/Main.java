package CookingJourney;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        char[][] matrix = new char[n][n];
        int[] positions = new int[2];
        for (int i = 0; i < n; i++) {
            String input = sc.nextLine();
            for (int j = 0; j < n; j++) {
                matrix[i][j] = input.charAt(j);
                if (matrix[i][j] == 'S') {
                    positions[0] = i;
                    positions[1] = j;
                }
            }
        }

        boolean isDead = false;
        int winPoints = 0;
        while (!isDead && winPoints < 50) {
            String command = sc.nextLine();
            int currPoints = 0;
            switch (command) {
                case "up":
                    currPoints += takeMatrixPoints(matrix, positions, -1, 0);
                    break;
                case "down":
                    currPoints += takeMatrixPoints(matrix, positions, 1, 0);
                    break;
                case "left":
                    currPoints += takeMatrixPoints(matrix, positions, 0, -1);
                    break;
                case "right":
                    currPoints += takeMatrixPoints(matrix, positions, 0, 1);
                    break;
            }
            if(currPoints == -1){
                isDead = true;
            }else {
                winPoints += currPoints;
            }
        }

        if(winPoints >= 50){
            System.out.println("Good news! You succeeded in collecting enough money!");
        }else{
            System.out.println("Bad news! You are out of the pastry shop.");
        }
        System.out.println("Money: " + winPoints);

        printMatrix(matrix);
    }

    private static int takeMatrixPoints(char[][] matrix, int[] positions, int row, int col) {
        int currRow = positions[0];
        int currCol = positions[1];
        row += currRow;
        col += currCol;
        matrix[currRow][currCol] = '-';
        int sum = 0;
        if(!isInBounds(matrix.length, row, col)){
            return -1;
        }

        char currChar = matrix[row][col];
        if(Character.isDigit(currChar)){
            int clientPoints = Character.getNumericValue(currChar);
            sum += clientPoints;
        }else if(currChar == 'P'){
            matrix[row][col] = '-';
            int[] newPositions = getNewPositions(matrix, row, col);
            row = newPositions[0];
            col = newPositions[1];
        }
        matrix[row][col] = 'S';
        positions[0] = row;
        positions[1] = col;

        return sum;
    }

    private static boolean isInBounds(int length, int row, int col) {
        return row >= 0 && row < length && col >= 0 && col < length;
    }

    private static int[] getNewPositions(char[][] matrix, int row, int col) {
        int[] positions = new int[2];
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                if(matrix[r][c] == 'P' && r != row && c != col){
                    positions[0] = r;
                    positions[1] = c;
                }
            }
        }
        return positions;
    }

    private static void printMatrix(char[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = 0; c < matrix[0].length; c++) {
                System.out.print(matrix[r][c]);
            }
            System.out.println();
        }
    }
}
