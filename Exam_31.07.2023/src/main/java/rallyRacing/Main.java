package rallyRacing;

import javax.xml.stream.events.Characters;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String carNumber = sc.nextLine();

        char[][] matrix = readMatrix(sc, n);

        int[] carPosition = new int[2];
        carPosition[0] = 0;
        carPosition[1] = 0;

        String command = sc.nextLine();
        boolean isFinished = false;
        int distance = 0;
        while(!command.equals("End") && !isFinished){
            switch (command){
                case "right":
                    distance += racingStage(matrix, carPosition, 0, 1);
                    break;
                case "left":
                    distance += racingStage(matrix, carPosition, 0, -1);
                    break;
                case "up":
                    distance += racingStage(matrix, carPosition, -1, 0);
                    break;
                case "down":
                    distance += racingStage(matrix, carPosition, 1, 0);
                    break;
            }
            if(distance % 10 == 1){
                isFinished = true;
                distance -= 1;
            }

            command = sc.nextLine();
        }

        if(isFinished) {
            System.out.printf("Racing car %s finished the stage!%n", carNumber);
        }else{
            System.out.printf("Racing car %s DNF.%n", carNumber);
        }
        System.out.printf("Distance covered %d km.%n", distance);
        printMatrix(matrix);
    }

    private static boolean isInBounds(char[][] matrix, int row, int col) {
        return (row >= 0 && row < matrix.length) && (col >= 0 && col < matrix[0].length);
    }

    private static int racingStage(char[][] matrix, int[] carPosition, int row, int col) {
        int distance = 0;
        int newRow = carPosition[0] + row;
        int newCol = carPosition[1] + col;
        if(!isInBounds(matrix, newRow, newCol)){
            distance += 1;
            newRow = carPosition[0];
            newCol = carPosition[1];
        }

        if(matrix[newRow][newCol] == 'T'){
            matrix[newRow][newCol] = '.';
            int[] positions = takeTunnelPlaces(matrix);
            newRow = positions[0];
            newCol= positions[1];
            distance += 20;
        }else if(matrix[newRow][newCol] == 'F'){
            distance += 1;
        }

        distance += 10;
        matrix[carPosition[0]][carPosition[1]] = '.';
        matrix[newRow][newCol] = 'C';
        carPosition[0] = newRow;
        carPosition[1] = newCol;

        return distance;
    }

    private static char[][] readMatrix(Scanner sc, int n) {
        char[][] currMatrix = new char[n][n];

        for (int i = 0; i < n; i++) {
            String[] line = sc.nextLine().split(" ");
            for (int j = 0; j < n; j++) {
                currMatrix[i][j] = line[j].charAt(0);
            }
        }
        return currMatrix;
    }

    private static int[] takeTunnelPlaces(char[][] matrix) {
        int[] positions = new int[2];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j] == 'T'){
                    positions[0] = i;
                    positions[1] = j;
                    matrix[i][j] = '.';
                }
            }
        }
        return positions;
    }

    private static void printMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println();
        }
    }

}
