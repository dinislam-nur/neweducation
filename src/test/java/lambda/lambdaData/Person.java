package lambda.lambdaData;

import lombok.Value;

@Value
public class Person {
    String name;
    String surname;
    int age;

    public String getFullName() {
        return name + " " + surname;
    }
}
