package homework.Task26;

import java.util.Set;

public interface Task26 {

    /**
     * На плоскости задано N отрезков (2 <= N <= 20).
     * Найти точку (возможно несколько) пересечения двух отрезков, имеющую минимальную абсциссу.
     * Использовать класс TreeMap.
     * @param segments Множество отрезков.
     * @return Множество точек пересечения, имеющих минимальную абсциссу.
     */
    Set<I2DPoint> analyze(Set<ISegment> segments);

    /**
     * Отрезок.
     */
    interface ISegment {

        /**
         * @return Первая точка отрезка.
         */
        I2DPoint first();

        /**
         * @return Вторая точка отрезка.
         */
        I2DPoint second();
    }

    /**
     * Точка на двумерной плоскости.
     */
    interface I2DPoint {

        /** Значение абсциссы */
        double getX();

        /** Значение ординаты */
        double getY();
    }
}
