package TestExecute;

import PerformTask.PerformTask;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestExecute {

    private static String result = null;

    public static String result(String input, Runnable task) {
        PerformTask.performTask(() -> {
            byte[] inputBytes = input.getBytes();
            ByteArrayInputStream in = new ByteArrayInputStream(inputBytes);
            System.setIn(in);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));

            task.run();
            result = out.toString().trim();
        });
        return result;
    }
}
