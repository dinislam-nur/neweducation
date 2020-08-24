package lombokTest;

import lombok.Value;

public class Example1 {

    public static void main(String[] args) {
        Person person = new Person("Ivan", "Ivanov");
        System.out.println(person);
    }
}

@Value
class Person {
    String name;
    String surname;
}
