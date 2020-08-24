package geekBrainsTestsJava;

import org.junit.jupiter.api.Test;

public class Example1 {

    @Test
    void test1() {
        Integer a1 = 10;
        Integer a2 = 10;

        Double b1 = 10d;
        Double b2 = 10d;
        System.out.println(a1 == a2);
        System.out.println(b1 == b2);
    }
}

class A {
    private static String a = "abc";

    A() {
        System.out.println("I'm from A");
    }

    void main() {
        return;
    }
}
