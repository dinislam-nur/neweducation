package lambda.part3;

import lambda.lambdaData.Employee;
import lambda.lambdaData.JobHistory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise5 {
    private static class LazyCollectionHelper<T, R> {

        private final List<T> source;
        private Function<T, R> lazyFunction;

        private LazyCollectionHelper(List<T> source, Function<T, R> lazyFunction) {
            this.lazyFunction = lazyFunction;
            this.source = source;
        }

        public static <T> LazyCollectionHelper<T, T> from(List<T> source) {
            return new LazyCollectionHelper<>(source, UnaryOperator.identity());
        }

        public List<R> force() {
            ArrayList<R> result = new ArrayList<>();
            source.forEach(element -> result.add(lazyFunction.apply(element)));
            return result;
        }

        public <U> LazyCollectionHelper<T, U> map(Function<R, U> mapping) {
            return new LazyCollectionHelper<>(source, lazyFunction.andThen(mapping));
        }


        public <U> LazyCollectionHelper<U, U> flatMap(Function<R, List<U>> flatMapping) {
            ArrayList<U> result = new ArrayList<>();
            force().forEach(element -> result.addAll(flatMapping.apply(element)));
            return new LazyCollectionHelper<>(result, UnaryOperator.identity());
        }

    }

    @Test
    void mapEmployeesToCodesOfLetterTheirPositionsUsingLazyFlatMapHelper() {
        List<Employee> employees = Example1.getEmployees();

        List<Integer> codes = LazyCollectionHelper.from(employees)
                .flatMap(Employee::getJobHistories)
                .map(JobHistory::getPosition)
                .flatMap(this::getListCharactersFromString)
                .map(character -> (int) character)
                .force();
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
