package homework.Task17;

import java.util.Scanner;

public class Task17 {

    public static void main(String[] args) {
        Launcher();
    }

    static void Launcher() {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        System.out.println(determinant(matrix));
    }

    private static int determinant(int[][] matrix) {
        int length = matrix.length;
        if (length > 1) {
            int count = 1;
            int det = 0;
            for (int i = 0; i < length; i++) {
                det += matrix[0][i] * count * determinant(submatrix(matrix, i));
                count *= -1;
            }
            return det;
        } else {
            return matrix[0][0];
        }
    }

    static int[][] submatrix(int[][] matrix, int index) {
        int length = matrix.length;
        int newLength = length - 1;
        int[][] subm = new int[newLength][newLength];
        for (int i = 1; i < length; i++) {
            int column = 0;
            int row = i - 1;
            for (int j = 0; j < length; j++) {
                if (j != index) {
                    subm[row][column++] = matrix[i][j];
                }
            }
        }
        return subm;
    }
}

