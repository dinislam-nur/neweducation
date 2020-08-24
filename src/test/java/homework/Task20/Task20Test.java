package homework.Task20;

import TestExecute.TestExecute;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task20Test {

    static int[][] parseToMatrix(String input) {
        String[] data = input.split("\\s+");
        int n = Integer.parseInt(data[0]);
        int[][] matrix = new int[n][n];
        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = Integer.parseInt(data[count++]);
            }
        }
        return matrix;
    }

    @Test
    void launcherTest1() {
        //Prepare
        String inString =   "1\n" +
                            "2\n" +
                            "4\n" +
                            "2  4 -2 -3\n" +
                            "0  1  3 -1\n" +
                            "-1  0  2  3\n" +
                            "-2  1 -1  4";
        int[][] answer = {{0, 1, -1, 3}, {2, 4, -3, -2}, {-1, 0, 3, 2}, {-2, 1, 4, -1}};

        //Execute
        int[][] result = parseToMatrix(TestExecute.result(inString, Task20::Launcher));

        //Assertion
        for (int i = 0; i < answer.length; i++) {
            assertArrayEquals(answer[i], result[i]);
        }
    }
}