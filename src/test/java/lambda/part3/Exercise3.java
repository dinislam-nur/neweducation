package lambda.part3;

import lambda.lambdaData.Employee;
import lambda.lambdaData.JobHistory;
import org.junit.jupiter.api.Test;

import javax.swing.event.ChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise3 {

    private static class MapHelper<T> {

        private final List<T> source;

        private MapHelper(List<T> source) {
            this.source = source;
        }

        public static <T> MapHelper<T> from(List<T> source) {
            return new MapHelper<>(source);
        }

        public List<T> getMapped() {
            return new ArrayList<>(source);
        }

        // ([T], (T -> R)) -> [R]
        public <R> MapHelper<R> map(Function<T, R> mapping) {
            ArrayList<R> result = new ArrayList<>();
            source.forEach(element -> result.add(mapping.apply(element)));
            return from(result);
        }

        // ([T], (T -> [R])) -> [R]
        public <R> MapHelper<R> flatMap(Function<T, List<R>> flatMapping) {
            ArrayList<R> result = new ArrayList<>();
            source.forEach(element -> result.addAll(flatMapping.apply(element)));
            return from(result);
        }
    }

    @Test
    public void mapEmployeeToLengthOfTheirFullNamesUsingMapHelper() {
        List<Employee> employees = Example1.getEmployees();
        List<Integer> lengths = MapHelper.from(employees)
                .map(Employee::getPerson)
                .map(person -> person.getSurname() + " " + person.getName())
                .map(String::length)
                .getMapped();
        // TODO                 MapHelper.from(employees)
        // TODO                          .map(Employee -> Person)
        // TODO                          .map(Person -> String(full name))
        // TODO                          .map(String -> Integer(length of string))
        // TODO                          .getMapped()


        assertEquals(Arrays.asList(14, 19, 14, 15, 14, 16), lengths);
    }

    @Test
    void mapEmployeesToCodesOfLetterTheirPositionsUsingMapHelper() {
        List<Employee> employees = Example1.getEmployees();

        List<Integer> codes = MapHelper.from(employees)
                                        .flatMap(Employee::getJobHistories)
                                        .map(JobHistory::getPosition)
                                        .flatMap(this::getListCharactersFromString)
                                        .map(character -> (int) character)
                                        .getMapped();
        // TODO             LazyCollectionHelper.from(employees)
        // TODO                                 .flatMap(Employee -> JobHistoryEntry)
        // TODO                                 .map(JobHistory -> String(position))
        // TODO                                 .flatMap(String -> Character(letter))
        // TODO                                 .map(Character -> Integer(code letter))
        // TODO                                 .force()

        assertEquals(calsCodes("dev", "dev", "tester", "dev", "dev", "QA", "QA", "dev", "tester", "tester", "QA", "QA", "QA", "dev"), codes);
    }

    private List<Character> getListCharactersFromString(String string) {
        ArrayList<Character> characters = new ArrayList<>();
        for (char ch : string.toCharArray()) {
            characters.add(ch);
        }
        return characters;
    }

    private static List<Integer> calsCodes(String...strings) {
        List<Integer> codes = new ArrayList<>();
        for (String string : strings) {
            for (char letter : string.toCharArray()) {
                codes.add((int) letter);
            }
        }
        return codes;
    }
}