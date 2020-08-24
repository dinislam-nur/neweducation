package homework.Task21;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Task21Implementation implements Task21 {

    @Override
    public List<String> reverseFile(File input, File output) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(input);
        byte[] data = new byte[1024];
        fileInputStream.read(data);

        return null;
    }
}

interface Task21 {
    /**
     * Читает строки из исходного файла и сохраняет в выходной в обратном порядке.
     * @param input Файл с входными данными.
     * @param output Файл с выходными данными.
     * @return Список строк, прочитанных из входного файла в прямом порядке.
     */
    List<String> reverseFile(File input, File output) throws IOException;
}
