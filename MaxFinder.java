import java.util.List;

public class MaxFinder {
    public static double findMax(List<Box<? extends Number>> boxes) {
        if (boxes == null || boxes.isEmpty()) {
            throw new IllegalArgumentException("Список коробок пуст или null");
        }

        double max = Double.NEGATIVE_INFINITY;

        for (Box<? extends Number> box : boxes) {
            if (box.isFull()) {
                Number value = box.getItem(); // извлекаем значение
                double num = value.doubleValue();
                if (num > max) {
                    max = num;
                }
            }
        }

        if (max == Double.NEGATIVE_INFINITY) {
            throw new IllegalArgumentException("Все коробки пусты");
        }

        return max;
    }
}
