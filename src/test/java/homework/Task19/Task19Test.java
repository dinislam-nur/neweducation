package homework.Task19;

import TestExecute.TestExecute;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task19Test {

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
        String inString = "4\n" +
                            "2  0  0 -1\n" +
                            "0  0  0  0\n" +
                            "0  0  0  3\n" +
                            "-3  0  0  1";
        int[][] answer = {{2, -1}, {0, 3}, {-3, 1}};

        //Execute
        int[][] result = parseToMatrix(TestExecute.result(inString, Task19::Launcher));

        //Assertion
        for (int i = 0; i < answer.length; i++) {
            assertArrayEquals(answer[i], result[i]);
        }
    }
}