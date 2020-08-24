package PerformTask;

import java.io.InputStream;
import java.io.PrintStream;

public class PerformTask {

    public static void performTask(Runnable task) {
        InputStream oldIn = System.in;
        PrintStream oldOut = System.out;

        try {
            task.run();
        } finally {
            System.setIn(oldIn);
            System.setOut(oldOut);
        }
    }
}
