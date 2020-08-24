package homework.Task18;

import java.util.*;

public class Task18 {

    public static void main(String[] args) {
        Launcher();
    }

    static void Launcher() {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];
        int maxValue = Integer.MIN_VALUE;
        Set<Integer> rows = new TreeSet<>();
        Set<Integer> columns = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int temp = scanner.nextInt();
                matrix[i][j] = temp;
                if (temp >= maxValue) {
                    if (temp != maxValue) {
                        rows.clear();
                        columns.clear();
                    }
                    rows.add(i);
                    columns.add(j);
                    maxValue = temp;
                }
            }
        }

        int[][] trueMatrix = submatrix(matrix, rows, columns);
        System.out.println(trueMatrix.length);
        System.out.println(trueMatrix[0].length);
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] row : trueMatrix) {
            stringBuilder.setLength(0);
            for (int element : row) {
                stringBuilder.append(element).append(" ");
            }
            System.out.println(stringBuilder.toString().trim());
        }
    }

    static int[][] submatrix(int[][] matrix, Set<Integer> rows, Set<Integer> columns) {
        int length = matrix.length;
        int[][] newMatrix = new int[length - rows.size()][length - columns.size()];
        int countRow = 0;
        Iterator<Integer> rowsIterator = rows.iterator();
        int rowToRemove = rowsIterator.next();
        for (int i = 0; i < length; i++) {
            if (rowToRemove == i) {
                if (rowsIterator.hasNext()) {
                    rowToRemove = rowsIterator.next();
                }
            } else {
                int countColumn = 0;
                Iterator<Integer> columnsIterator = columns.iterator();
                int columnToRemove = columnsIterator.next();
                for (int j = 0; j < length; j++) {
                    if (columnToRemove != j) {
                        newMatrix[countRow][countColumn++] = matrix[i][j];
                    } else {
                        if (columnsIterator.hasNext()) {
                            columnToRemove = columnsIterator.next();
                        }
                    }
                }
                countRow++;
            }
        }
        return newMatrix;
    }
}
