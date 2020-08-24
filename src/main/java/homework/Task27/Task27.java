package homework.Task27;

public interface Task27 {

    /**
     * Реализовать класс Graph, представляющий собой неориентированный граф (между двумя вершинами допустимо максимум одно ребро).
     * Методы должны поддерживать быстрое добавление и удаление ребер.
     * @param numberNodes Количество вершин в графе.
     * @return Граф указанной конфигурации.
     */
    AbstractGraph createGraph(int numberNodes);

    /**
     * Абстрактный граф.
     * При создании задается количество вершин.
     * Нумерация вершин начинается с 0.
     * Допустимы операции добавления, удаления и проверки существования ребер.
     */
    abstract class AbstractGraph {

        /** Количество вершин */
        protected final int NUMBER_NODES;

        public AbstractGraph(int numberNodes) {
            this.NUMBER_NODES = numberNodes;
        }

        /**
         * Добавление ребра в граф.
         * @param first Первая связываемая вершина.
         * @param second Вторая связываемая вершина.
         */
        public abstract void addEdge(int first, int second);

        /**
         * Удаление ребра из графа.
         * @param first Первая освобождаемая от связи вершина.
         * @param second Вторая освобождаемая от связи вершина.
         */
        public abstract void removeEdge(int first, int second);

        /**
         * Проверка наличия ребра.
         * @param first Первая вершина.
         * @param second Вторая вершина.
         */
        public abstract boolean isEdgeExists(int first, int second);
    }
}
