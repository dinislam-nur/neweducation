package lambda.part3;

import lambda.lambdaData.Employee;
import lambda.lambdaData.JobHistory;
import lambda.lambdaData.Person;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Example1 {

    public static List<Employee> getEmployees() {
        return Arrays.asList(
                Employee.builder()
                        .person(new Person("Иван", "Мельников", 30))
                        .jobHistory(new JobHistory(2,"dev", "EPAM"))
                        .jobHistory(new JobHistory(1, "dev", "google"))
                        .build(),
                Employee.builder()
                        .person(new Person("Александр", "Дементьев", 28))
                        .jobHistory(new JobHistory(1,"tester", "EPAM"))
                        .jobHistory(new JobHistory(1,"dev", "EPAM"))
                        .jobHistory(new JobHistory(1, "dev", "google"))
                        .build(),
                Employee.builder()
                        .person(new Person("Дмитрий", "Осинов", 40))
                        .jobHistory(new JobHistory(3,"QA", "yandex"))
                        .jobHistory(new JobHistory(1,"QA", "mail.ru"))
                        .jobHistory(new JobHistory(1, "dev", "mail.ru"))
                        .build(),
                Employee.builder()
                        .person(new Person("Анна", "Светличная", 21))
                        .jobHistory(new JobHistory(1, "tester", "T-Systems"))
                        .build(),
                Employee.builder()
                        .person(new Person("Игорь", "Толмачев", 50))
                        .jobHistory(new JobHistory(5,"tester", "EPAM"))
                        .jobHistory(new JobHistory(6, "QA", "EPAM"))
                        .build(),
                Employee.builder()
                        .person(new Person("Иван", "Александров", 33))
                        .jobHistory(new JobHistory(2,"QA", "T-Systems"))
                        .jobHistory(new JobHistory(3, "QA", "EPAM"))
                        .jobHistory(new JobHistory(1, "dev", "EPAM"))
                        .build()
        );
    }

    @Test
    void findIvanWhichWorkInOnePositionMoreThanOneYearInEpamWithListFilter() {
        List<Employee> employees = getEmployees();
        List<Employee> result = ListFilter.from(employees)
                .filter(element -> "Иван".equals(element.getPerson().getName()))
                .filter(this::hasWorkedAsDeveloper)
                .filter(this::hadExperienceMoreThanOneYearInEpamAndInOnePosition)
                .getResult();

        assertEquals(Arrays.asList(employees.get(0), employees.get(5)), result);
    }

    private boolean hasWorkedAsDeveloper(Employee element) {
        return !ListFilter.from(element.getJobHistories())
                                    .filter(entry -> "dev".equals(entry.getPosition()))
                                    .getResult()
                                    .isEmpty();
    }

    private boolean hadExperienceMoreThanOneYearInEpamAndInOnePosition(Employee element) {
        return !ListFilter.from(element.getJobHistories())
                                    .filter(entry -> "EPAM".equals(entry.getEmployer()))
                                    .filter(entry -> entry.getDuration() >= 1)
                                    .getResult()
                                    .isEmpty();
    }

    @Test
    void findIvanWhichHasExperienceInOnePositionMoreThanOneYearWorkingInEPAMUsingLazyFilterUtil() {
        List<Employee> employees = getEmployees();
        List<Employee> result = LazyFilterUtil.from(employees)
                .filter(element -> "Иван".equals(element.getPerson().getName()))
                .filter(this::hasWorkedAsDeveloper)
                .filter(this::hadExperienceMoreThanOneYearInEpamAndInOnePosition)
                .getResult();

        assertEquals(Arrays.asList(employees.get(0), employees.get(5)), result);
    }
}

class ListFilter<T> {

    private final List<T> data;

    private ListFilter(List<T> data) {
        this.data = data;
    }

    static <T> ListFilter<T> from(List<T> data) {
        return new ListFilter<>(data);
    }

    ListFilter<T> filter(Predicate<T> condition) {
        ArrayList<T> filteredList = new ArrayList<>();
        data.forEach(element -> {
            if(condition.test(element)) {
                filteredList.add(element);
            }
        });
        return from(filteredList);
    }

    List<T> getResult() {
        return new ArrayList<>(data);
    }
}

class LazyFilterUtil<T> {
    private final List<T> data;
    private Predicate<T> condition;

    private LazyFilterUtil(List<T> data, Predicate<T> condition) {
        this.condition = condition;
        this.data = data;
    }

    static <T> LazyFilterUtil<T> from(List<T> data) {
        return new LazyFilterUtil<>(data, value -> true);
    }

    LazyFilterUtil<T> filter(Predicate<T> condition) {
        return new LazyFilterUtil<>(data, this.condition.and(condition));
    }

    List<T> getResult() {
        ArrayList<T> resultList = new ArrayList<>();
        data.forEach(element -> {
            if (condition.test(element)) {
                resultList.add(element);
            }
        });
        return resultList;
    }
}
