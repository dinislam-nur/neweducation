package geekBrainsTestsJava;

public class Fruit {

    public Fruit() {
        System.out.println("fruit");
    }

    void method() {
        System.out.println("method fruit");

    }

    public static void main(String[] args) {
        Fruit apple = new Apple();
        apple.method();
    }
}

class Apple extends Fruit {
    public Apple() {
        System.out.println("apple");
    }

    @Override
    protected void method() {
        System.out.println("method apple");
    }
}
