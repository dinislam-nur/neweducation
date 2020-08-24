package lambda.part3;

import com.sun.tools.xjc.model.CElement;
import lambda.lambdaData.Employee;
import lambda.lambdaData.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Example2 {

    @Test
    public void mapEmployeesToLengthOfTheirFullNames() {
        List<Employee> employee = Example1.getEmployees();
        List<Integer> lengths = new ArrayList<>();

        // TODO функция извлечения информации о человеке из объекта сотрудника personExtractor: Employee -> Person
        // TODO функция извлечения полного имени из информации о человеке fullNameExtractor: Person -> String
        // TODO функция извлечения длины из строки stringLengthExtractor: String -> Integer
        // TODO функция извлечения длины полного имени из сотрудника fullNameLengthExtractor: Employee -> Integer
        // TODO преобразование списка  employees в lengths, используя fullNameLengthExtractor

        Function<Employee, Person> personExtractor = Employee::getPerson;
        Function<String, Function<String, Function<String, String>>> summator = left -> delimiter -> right -> delimiterSum(left, delimiter, right);
        Function<String, Function<String, String>> concatString = concatWithDelimiter(summator, " ");
        Function<Person, String> fullNameExtractor = person -> concatString.apply(person.getName()).apply(person.getSurname());
        Function<String, Integer> stringLengthExtractor = String::length;
        Function<Employee, Integer> fullNameLengthExtractor = stringLengthExtractor.compose(fullNameExtractor).compose(personExtractor);

        employee.forEach(element -> {
            lengths.add(fullNameLengthExtractor.apply(element));
        });

        employee.forEach(element -> {
            System.out.println(fullNameExtractor.compose(personExtractor).apply(element));
        });

        assertEquals(Arrays.asList(14, 19, 14, 15, 14, 16), lengths);
    }

    private static String delimiterSum(String left, String delimiter, String right) {
        return left + delimiter + right;
    }

    private static Function<String, Function<String, String>> concatWithDelimiter(
            Function<String, Function<String, Function<String, String>>> summator,
            String delimiter) {
        return left -> right -> summator.apply(right).apply(delimiter).apply(left);
    }

}
