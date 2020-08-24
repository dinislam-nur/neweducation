package homework.Task17;

import PerformTask.PerformTask;
import TestExecute.TestExecute;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Task17Test {

    @Test
    void submatrixTest1() {
        //Prepare
        int[][] matrix = {{2, 4}, {3, 1}};
        int[][] answer = {{3}};

        //Execute
        int[][] result = Task17.submatrix(matrix, 1);

        //Assertion
        assertArrayEquals(answer[0], result[0]);

    }

    @Test
    void launcherTest1() {
        //Prepare
        String inString = "3\n" +
                        "-2  1  2\n" +
                        "0 -1  0\n" +
                        "1 -2  3";
        int answer = 8;

        //Execute
        int result = Integer.parseInt(TestExecute.result(inString, Task17::Launcher));

        //Assertion
        assertEquals(answer, result);
    }

    @Test
    void launcherTest2() {
        //Prepare
        String inString = "4\n" +
                            "6 4 0 1\n" +
                            "8 7 0 3\n" +
                            "1 3 0 9\n" +
                            "7 5 1 2";
        int answer = -65;

        //Execute
        int result = Integer.parseInt(TestExecute.result(inString, Task17::Launcher));

        //Assertion
        assertEquals(answer, result);
    }
}