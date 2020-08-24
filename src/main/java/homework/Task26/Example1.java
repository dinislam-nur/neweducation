package homework.Task26;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Example1 {

    public static void main(String[] args) {
        Map<Person, Integer> personMap = new TreeMap<>(Comparator.comparing(Person::getAge));
        Person person1 = new Person(1, 12);
        Person person2 = new Person(2, 12);

        personMap.put(person1, 1);
        System.out.println(personMap.put(person2, 2));

        System.out.println(personMap.get(person1));
        System.out.println(personMap.get(person2));

        for (Map.Entry<Person, Integer> personIntegerEntry : personMap.entrySet()) {
            System.out.println(personIntegerEntry.getKey());
        }
    }
}

@AllArgsConstructor
@ToString
class Person {
    private final int id;
    @Getter
    private final int age;
}
