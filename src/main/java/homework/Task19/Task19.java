package homework.Task19;

import java.util.*;

public class Task19 {

    public static void main(String[] args) {
        Launcher();
    }

    static void Launcher() {
        int[][] matrix = readMatrix();
        int length = matrix.length;

        Set<Integer> rowsToDelete = new HashSet<>(1);
        Set<Integer> columnsToDelete = new HashSet<>(1);

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) {
                            columnsToDelete.add(j);
                    }
                    if (j == 0) {
                            rowsToDelete.add(i);
                    }
                } else {
                    rowsToDelete.remove(i);
                    columnsToDelete.remove(j);
                }
            }
        }

        writeMatrix(modifiedMatrix(matrix, rowsToDelete, columnsToDelete));
    }

    private static int[][] modifiedMatrix(int[][] matrix, Set<Integer> rowsToDelete, Set<Integer> columnsToDelete) {
        int length = matrix.length;
        int[][] newMatrix = new int[length - rowsToDelete.size()][length - columnsToDelete.size()];
        int countRow = 0;
        for (int i = 0; i < length; i++) {
            int countColumn = 0;
            if (!rowsToDelete.contains(i)) {
                for (int j = 0; j < length; j++) {
                    if (!columnsToDelete.contains(j)) {
                        newMatrix[countRow][countColumn++] = matrix[i][j];
                    }
                }
                countRow++;
            }
        }
        return newMatrix;
    }

    private static int[][] readMatrix() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        return matrix;
    }

    private static void writeMatrix(int[][] matrix) {
        System.out.println(matrix.length);
        System.out.println(matrix[0].length);
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] row : matrix) {
            stringBuilder.setLength(0);
            for (int element : row) {
                stringBuilder.append(element).append(" ");
            }
            System.out.println(stringBuilder.toString().trim());
        }
    }
}
