package geekBrainsTestsJava;

public class Clazz {
    int x = 2;
    public int getX() {
        return x;
    }

    public static void main(String[] args) {
        Clazz s = new SubClass();
        System.out.println(s.x + " " + s.getX());
    }
}

class SubClass extends Clazz {
    int x = 1;

    @Override
    public int getX() {
        return x;
    }
}
