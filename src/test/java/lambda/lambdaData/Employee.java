package lambda.lambdaData;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

import java.util.List;

@Value
@Builder
public class Employee {
    Person person;
    @Singular
    List<JobHistory> jobHistories;
}
