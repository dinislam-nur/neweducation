package bisnessTechnologyTask;

import java.util.ArrayList;
import java.util.Arrays;

public class Task1 {

    public static void main(String[] args) {
        int[] array = {5, 2, 4, 6, 2, 1, 1, 1, 1, 1, 1, 3, 2, 6, 2};
        sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    public static void sort(int[] array, int p, int r) {
        if (p < r) {
            int q = (p + r) / 2;
            sort(array, p, q);
            sort(array, q + 1, r);
            merge(array, p, q, r);
        }
    }

    private static void merge(int[] array, int p, int q, int r) {

        for (int i = p; i <= q; i++) {
            int count = q + 1;
            if (q < r && array[count] < array[i]) {
                int temp1 = array[count];
                System.arraycopy(Arrays.copyOfRange(array, i, count), 0, array, i + 1, count - i);
                array[i] = temp1;
                q++;
            }
        }
    }
}
