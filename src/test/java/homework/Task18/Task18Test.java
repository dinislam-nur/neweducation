package homework.Task18;

import TestExecute.TestExecute;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task18Test {

    static int[][] parseToMatrix(String input) {
        String[] data = input.split("\\s+");
        int n = Integer.parseInt(data[0]);
        int m = Integer.parseInt(data[1]);
        int[][] matrix = new int[n][m];
        int count = 2;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = Integer.parseInt(data[count++]);
            }
        }
        return matrix;
    }

    @Test
    void launcherTest1() {
        //Prepare
        String inString =   "3\n" +
                            "2  1 -3\n" +
                            "-2  3  2\n" +
                            "-1  0  0";
        int[][] answer = {{2, -3}, {-1, 0}};

        //Execute
        String result = TestExecute.result(inString, Task18::Launcher);
        int[][] resultMatrix = parseToMatrix(result);

        //Assertion
        for (int i = 0; i < answer.length; i++) {
            assertArrayEquals(answer[i], resultMatrix[i]);
        }
    }

    @Test
    void launcherTest2() {
        //Prepare
        String inString =   "4\n" +
                            "3 -2 -4  1\n" +
                            "1  4  4  2\n" +
                            "-1  0 -3  1\n" +
                            "0  2  1  3";
        int[][] answer = {{3, 1}, {-1, 1}, {0, 3}};

        //Execute
        String result = TestExecute.result(inString, Task18::Launcher);
        int[][] resultMatrix = parseToMatrix(result);

        //Assertion
        for (int i = 0; i < answer.length; i++) {
            assertArrayEquals(answer[i], resultMatrix[i]);
        }
    }
}