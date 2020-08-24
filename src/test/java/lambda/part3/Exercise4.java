package lambda.part3;

import lambda.lambdaData.Employee;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise4 {

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

        public <R2> LazyCollectionHelper<T, R2> map(Function<R, R2> mapping) {
            return new LazyCollectionHelper<>(source, lazyFunction.andThen(mapping));
        }
    }

    @Test
    public void mapEmployeesToLengthOfTheirFullNamesUsingLazyMapHelper() {
        List<Employee> employees = Example1.getEmployees();
        List<Integer> lengths = LazyCollectionHelper.from(employees)
                                                    .map(Employee::getPerson)
                                                    .map(person -> person.getSurname() + " " + person.getName())
                                                    .map(String::length)
                                                    .force();
        // TODO                 MapHelper.from(employees)
        // TODO                          .map(Employee -> Person)
        // TODO                          .map(Person -> String(full name))
        // TODO                          .map(String -> Integer(length of string))
        // TODO                          .getMapped()
        assertEquals(Arrays.asList(14, 19, 14, 15, 14, 16), lengths);
    }
}
