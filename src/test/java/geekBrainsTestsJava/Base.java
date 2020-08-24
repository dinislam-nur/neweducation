package geekBrainsTestsJava;

public interface Base {
    boolean m1();

    byte m2(short s);
}

abstract class Class2 implements Base {
    public boolean m1() {
        return true;
    }
}
