import java.util.Arrays;
import java.util.Scanner;

public class Selling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        int[] positions = new int[2];
        char[][] matrix = new char[n][n];

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

        String input = sc.nextLine();
        boolean isDead = false;
        int winPoints = 0;

        while(!isDead){
            switch (input){
                case "up":
                    winPoints += matrixAction(matrix, positions, -1, 0);
                    break;
                case "down":
                    winPoints += matrixAction(matrix, positions, 1, 0);
                    break;
                case "left":
                    winPoints += matrixAction(matrix, positions, 0, -1);
                    break;
                case "right":
                    winPoints += matrixAction(matrix, positions, 0, 1);
                    break;
            }
            if(winPoints < 0){
                isDead = true;
            }else if(winPoints >= 50){
                break;
            }else{
                input = sc.nextLine();
            }
        }

        if(isDead){
            System.out.println("Bad news, you are out of the bakery.");
            winPoints = 0;
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
            return Integer.MIN_VALUE;
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
