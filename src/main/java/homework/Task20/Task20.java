package homework.Task20;

import java.util.Scanner;

public class Task20 {

    public static void main(String[] args) {
        Launcher();
    }

    static void Launcher() {

        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }
        transformedMatrix(matrix, x, y);
        writeMatrix(matrix);
    }

    private static void transformedMatrix(int[][] matrix, int x, int y) {
        int[] coordinatesOfMin = coordinatesMinElement(matrix);
        for (int[] row : matrix) {
            swap(row, y, coordinatesOfMin[1]);
        }
        swap(matrix, x, coordinatesOfMin[0]);
    }

    private static int[] coordinatesMinElement(int[][] matrix) {
        int x = 0;
        int y = 0;
        int minElement = Integer.MAX_VALUE;
        int length = matrix.length;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int temp = matrix[i][j];
                if (temp < minElement) {
                    x = i;
                    y = j;
                    minElement = temp;
                }
            }
        }
        return new int[]{x, y};
    }

    private static void swap (int[] data, int oldIndex, int newIndex) {
        int temp = data[oldIndex];
        data[oldIndex] = data[newIndex];
        data[newIndex] = temp;
    }

    private static void swap (int[][] data, int oldIndex, int newIndex) {
        int[] temp = data[oldIndex];
        data[oldIndex] = data[newIndex];
        data[newIndex] = temp;
    }

    private static void writeMatrix(int[][] matrix) {
        System.out.println(matrix.length);
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
