package homework.Task22;

import java.util.*;

public class Task22Implementation implements Task22 {

    @Override
    public List<String> sortPoems(Set<IPoem> poems, String author) {
        List<String> listPoemsToSort = new ArrayList<>();
        for (IPoem temp : poems) {
            if (author.equals(temp.getAuthor())) {
                listPoemsToSort.addAll(temp.getLines());
                break;
            }
        }
        listPoemsToSort.sort(Comparator.comparingInt(String::length));
        return listPoemsToSort;
    }
}
