package homework.Task23;

import java.util.Map;

public interface Task23 {

    /**
     * Осуществляет сложение двух многочленов.
     * Коэффициенты многочленов хранятся в объекте Map.
     * Степень элемента многочлена – ключ, коэффициент элемента многочлена – значение.
     * Коэффициенты многочлена, ключи для которых отсутствуют в карте, равны нулю.
     * @param first Первый многочлен.
     * @param second Второй многочлен.
     * @return Многочлен, полученный в результате сложения.
     */
    Map<Integer, Integer> addPolynomials(Map<Integer, Integer> first, Map<Integer, Integer> second);
}
