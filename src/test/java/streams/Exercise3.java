package streams;

import lambda.lambdaData.Employee;
import lambda.lambdaData.JobHistory;
import lambda.lambdaData.Person;
import lambda.part3.Example1;
import lombok.Value;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise3 {

    private final List<Employee> employees = Example1.getEmployees();

    @Test
    void getCoolestEmployeeForEveryOnePositionUsingToMap() {
        Map<String, Person> result = employees.stream()
                .flatMap(employee -> employee.getJobHistories()
                        .stream()
                        .collect(toMap(JobHistory::getPosition,
                                jobHistory -> new PersonDurationPair(employee.getPerson(), jobHistory.getDuration()),
                                (pairLeft, pairRight) -> new PersonDurationPair(pairLeft.getPerson(), pairLeft.getDuration() + pairRight.getDuration())))
                        .entrySet()
                        .stream())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (left, right) -> Integer.compare(left.getDuration(), right.getDuration()) > -1 ? left : right))
                .entrySet()
                .stream()
                .collect(toMap(Map.Entry::getKey, entry -> entry.getValue().getPerson()));

        assertEquals(prepareExpected(employees), result);
    }

    @Test
    void getCoolestEmployeeForEveryOnePositionUsingGroupingBy() {
        Map<String, Person> result = employees.stream()
                .flatMap(employee -> employee.getJobHistories()
                        .stream()
                        .collect(groupingBy(JobHistory::getPosition, summingInt(JobHistory::getDuration)))
                        .entrySet()
                        .stream()
                        .map(entry -> new PositionPersonDuration(employee.getPerson(), entry.getKey(), entry.getValue())))
                .collect(groupingBy(PositionPersonDuration::getPosition,
                        collectingAndThen(maxBy(Comparator.comparingInt(PositionPersonDuration::getDuration)),
                                optional -> optional.map(PositionPersonDuration::getPerson)
                                        .orElseThrow(IllegalArgumentException::new))));

        assertEquals(prepareExpected(employees), result);
    }

    private static Map<String, Person> prepareExpected(List<Employee> employees) {
        Map<String, Person> expected = new HashMap<>();
        expected.put("dev", employees.get(0).getPerson());
        expected.put("tester", employees.get(4).getPerson());
        expected.put("QA", employees.get(4).getPerson());
        return expected;
    }
}

@Value
class PositionPersonDuration {
    Person person;
    String position;
    int duration;
}

@Value
class PersonDurationPair {
    Person person;
    int duration;
}
