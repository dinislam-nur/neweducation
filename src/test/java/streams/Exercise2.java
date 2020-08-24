package streams;

import lambda.lambdaData.Employee;
import lambda.lambdaData.Person;
import lambda.part3.Example1;
import lombok.Value;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Exercise2 {

    List<Employee> employees = Example1.getEmployees();

    @Test
    void splitPersonsByPositionExperienceUsingReduce() {
        Map<String, Set<Person>> result = employees.stream()
                                                   .flatMap(this::getPersonPositionStreamFromEmployee)
                                                   .reduce(new HashMap<>(), this::positionAccumulator, this::mergeMaps);
        assertEquals(prepareExpected(employees), result);
    }

    private Map<String, Set<Person>> mergeMaps(Map<String, Set<Person>> stringListMap, Map<String, Set<Person>> stringListMap2) {
        Map<String, Set<Person>> hashMap = new HashMap<>(stringListMap);
        stringListMap2.forEach((keyRight, valueRight) -> hashMap.merge(keyRight, valueRight, (left, right) -> {
            left.addAll(right);
            return left;
        }));
        return hashMap;
    }

    private Map<String, Set<Person>> positionAccumulator(Map<String, Set<Person>> identity, PersonPosition personPosition) {
        Map<String, Set<Person>> personsInSamePosition = new HashMap<>(identity);
        String position = personPosition.getPosition();
        Person person = personPosition.getPerson();
        personsInSamePosition.compute(position, (key, value) -> {
            Set<Person> result;
            result = value == null ? new HashSet<>() : value;
            result.add(person);
            return result;
        });
        return personsInSamePosition;
    }


    private Stream<PersonPosition> getPersonPositionStreamFromEmployee(Employee employee) {
        List<PersonPosition> personPositions = new ArrayList<>();
        employee.getJobHistories().forEach(jobHistory -> personPositions.add(new PersonPosition(employee.getPerson(), jobHistory.getPosition())));
        return personPositions.stream();
    }

    @Test
    void splitPersonsByPositionExperienceUsingCollect() {
        Map<String, Set<Person>> result = employees.stream()
                                                   .flatMap(this::getPersonPositionStreamFromEmployee)
                                                   .collect(HashMap::new, Exercise2::addMapMutable, Exercise2::mergeMapsMutable);

        assertEquals(prepareExpected(employees), result);
    }

    private static Map<String, Set<Person>> addMapMutable(Map<String, Set<Person>> container, PersonPosition pair) {
        container.compute(pair.getPosition(), (position, person) -> {
            Set<Person> result = person == null ? new HashSet<>() : person;
            result.add(pair.getPerson());
            return result;
        });
        return container;
    }

    private static Map<String, Set<Person>> mergeMapsMutable(Map<String, Set<Person>> left, Map<String, Set<Person>> right) {
        right.forEach((key, value) ->
                left.merge(key, value, (resultValue, rightValue) -> {
                    resultValue.addAll(rightValue);
                    return resultValue;
                }));
        return left;
    }

    @Test
    void splitPersonsByPositionExperienceUsingGroupingBy() {
        Map<String, Set<Person>> result = employees.stream()
                                                   .flatMap(this::getPersonPositionStreamFromEmployee)
                                                   .collect(groupingBy(PersonPosition::getPosition, mapping(PersonPosition::getPerson, toSet())));

        assertEquals(prepareExpected(employees), result);
    }

    private static Map<String, Set<Person>> prepareExpected(List<Employee> employees) {
        Map<String, Set<Person>> expected = new HashMap<>();
        expected.put("dev", new HashSet<>(Arrays.asList(
                employees.get(0).getPerson(),
                employees.get(1).getPerson(),
                employees.get(2).getPerson(),
                employees.get(5).getPerson())));
        expected.put("tester", new HashSet<>(Arrays.asList(
                employees.get(1).getPerson(),
                employees.get(3).getPerson(),
                employees.get(4).getPerson())));
        expected.put("QA", new HashSet<>(Arrays.asList(
                employees.get(2).getPerson(),
                employees.get(4).getPerson(),
                employees.get(5).getPerson())));
        return expected;
    }

}

@Value
class PersonPosition {
    Person person;
    String position;
}
