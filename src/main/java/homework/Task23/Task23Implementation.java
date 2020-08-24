package homework.Task23;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Task23Implementation implements Task23 {

    @Override
    public Map<Integer, Integer> addPolynomials(Map<Integer, Integer> first, Map<Integer, Integer> second) {
        Map<Integer, Integer> resultPolynomial = new HashMap<>(first);

        for (Map.Entry<Integer, Integer> secondEntries : second.entrySet()) {
            resultPolynomial.merge(secondEntries.getKey(), secondEntries.getValue(), Integer::sum);
        }

        return resultPolynomial;
    }
}
