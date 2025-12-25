import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== ДЕМОНСТРАЦИЯ ВСЕХ ЗАДАНИЙ ===");

        while (true) {
            System.out.println("\nВыберите задание:");
            System.out.println("1. Обобщенная коробка (Box) и Storage");
            System.out.println("2. Поиск максимума в коробках");
            System.out.println("3. Обобщенные методы (map, filter, reduce, collect)");
            System.out.println("4. Выход");
            System.out.print("Ваш выбор: ");

            int choice = readInt(1, 4);
            switch (choice) {
                case 1 -> demoTask1();
                case 2 -> demoTask2();
                case 3 -> demoTask3();
                case 4 -> { System.out.println("Выход."); return; }
            }
        }
    }

    // =================== ЗАДАНИЕ 1 ===================
    private static void demoTask1() {
        System.out.println("\n=== Box ===");
        Box<Integer> box = new Box<>();
        box.setItem(3);
        System.out.println("Коробка после добавления числа 3: " + box);
        Integer value = box.getItem();
        System.out.println("Извлечено из коробки: " + value);
        System.out.println("Состояние коробки после извлечения: " + box);

        System.out.println("\n=== Storage ===");
        Storage<Integer> s1 = new Storage<>(null, 0);
        System.out.println("Извлечение из Storage (null -> 0): " + s1.getItem());

        Storage<Integer> s2 = new Storage<>(99, -1);
        System.out.println("Извлечение из Storage (99 -> -1): " + s2.getItem());

        Storage<String> s3 = new Storage<>(null, "default");
        System.out.println("Извлечение из Storage (null -> default): " + s3.getItem());

        Storage<String> s4 = new Storage<>("hello", "hello world");
        System.out.println("Извлечение из Storage (hello -> hello world): " + s4.getItem());
    }

    // =================== ЗАДАНИЕ 2 ===================
    private static void demoTask2() {
        System.out.println("\n=== Поиск максимума в коробках ===");
        List<Box<? extends Number>> boxes = new ArrayList<>();

        System.out.print("Сколько коробок создать? (1-5): ");
        int count = readInt(1, 5);

        for (int i = 0; i < count; i++) {
            System.out.println("Коробка " + (i+1) + ":");
            System.out.print("Введите число: ");
            double num = readDouble();
            boxes.add(new Box<>(num));
        }

        double max = MaxFinder.findMax(boxes);
        System.out.println("Максимальное значение: " + max);
    }

    // =================== ЗАДАНИЕ 3 ===================
    private static void demoTask3() {
        while (true) {
            System.out.println("\nВыберите метод:");
            System.out.println("1. Map");
            System.out.println("2. Filter");
            System.out.println("3. Reduce");
            System.out.println("4. Collect");
            System.out.println("5. Назад");
            int choice = readInt(1, 5);

            switch (choice) {
                case 1 -> demoMap();
                case 2 -> demoFilter();
                case 3 -> demoReduce();
                case 4 -> demoCollect();
                case 5 -> { return; }
            }
        }
    }

    private static void demoMap() {
        System.out.println("\n=== Map ===");
        List<String> strings = Arrays.asList("qwerty","asdfg","zx");
        List<Integer> lengths = GenericMethods.map(strings, String::length);
        System.out.println("Длины строк: " + lengths);

        List<Integer> numbers = Arrays.asList(1,-3,7);
        List<Integer> absValues = GenericMethods.map(numbers, Math::abs);
        System.out.println("Абсолютные числа: " + absValues);

        List<int[]> arrays = Arrays.asList(new int[]{1,2,3}, new int[]{-5,0,5}, new int[]{10,20,30,40});
        List<Integer> maxValues = GenericMethods.map(arrays, arr -> {
            int max = Integer.MIN_VALUE;
            for (int n : arr) if (n > max) max = n;
            return max;
        });
        System.out.println("Максимальные значения массивов: " + maxValues);
    }

    private static void demoFilter() {
        System.out.println("\n=== Filter ===");
        List<String> strings = Arrays.asList("qwerty","asdfg","zx");
        List<String> filteredStrings = GenericMethods.filter(strings, s -> s.length() >= 3);
        System.out.println("Строки длиной >=3: " + filteredStrings);

        List<Integer> numbers = Arrays.asList(1,-3,7);
        List<Integer> filteredNumbers = GenericMethods.filter(numbers, n -> n <= 0);
        System.out.println("Неположительные числа: " + filteredNumbers);

        List<int[]> arrays = Arrays.asList(new int[]{-1,-2,-3}, new int[]{-5,0,5}, new int[]{-10,-20});
        List<int[]> negativeArrays = GenericMethods.filter(arrays, arr -> {
            for (int n : arr) if (n > 0) return false;
            return true;
        });
        System.out.println("Массивы без положительных элементов:");
        for (int[] arr : negativeArrays) System.out.println(Arrays.toString(arr));
    }

    private static void demoReduce() {
        System.out.println("\n=== Reduce ===");
        List<String> strings = Arrays.asList("qwerty","asdfg","zx");
        String concatenated = GenericMethods.reduce(strings, "", String::concat);
        System.out.println("Конкатенация строк: " + concatenated);

        List<Integer> numbers = Arrays.asList(1,-3,7);
        Integer sum = GenericMethods.reduce(numbers, 0, Integer::sum);
        System.out.println("Сумма чисел: " + sum);

        List<List<Integer>> listOfLists = Arrays.asList(Arrays.asList(1,2,3), Arrays.asList(4,5), Arrays.asList(6,7,8,9));
        Integer total = GenericMethods.reduce(GenericMethods.map(listOfLists, List::size), 0, Integer::sum);
        System.out.println("Общее количество элементов: " + total);
    }

    private static void demoCollect() {
        System.out.println("\n=== Collect ===");
        List<Integer> numbers = Arrays.asList(1,-3,7,-2,0,5);
        Map<Boolean,List<Integer>> partitioned = GenericMethods.collect(numbers, ()->{
            Map<Boolean,List<Integer>> map = new HashMap<>();
            map.put(true,new ArrayList<>());
            map.put(false,new ArrayList<>());
            return map;
        }, (map,n)-> {
            if (n>=0) map.get(true).add(n);
            else map.get(false).add(n);
        });
        System.out.println("Положительные: " + partitioned.get(true));
        System.out.println("Отрицательные: " + partitioned.get(false));

        List<String> strings = Arrays.asList("qwerty","asdfg","zx","qw","abc","test");
        Map<Integer,List<String>> grouped = GenericMethods.collect(strings, HashMap::new, (map,s)->{
            int len = s.length();
            map.computeIfAbsent(len, k->new ArrayList<>()).add(s);
        });
        System.out.println("Группировка по длине:");
        for (var e : grouped.entrySet()) System.out.println("Длина "+e.getKey()+": "+e.getValue());

        List<String> stringsDup = Arrays.asList("qwerty","asdfg","qwerty","qw");
        Set<String> unique = GenericMethods.collect(stringsDup, HashSet::new, Set::add);
        System.out.println("Уникальные строки: " + unique);
    }

    // =================== Вспомогательные методы ===================
    private static int readInt() {
        while (true) {
            try { return Integer.parseInt(scanner.nextLine()); }
            catch (NumberFormatException e) { System.out.print("Введите число: "); }
        }
    }

    private static int readInt(int min, int max) {
        while (true) {
            int val = readInt();
            if (val >= min && val <= max) return val;
            System.out.printf("Введите число от %d до %d: ", min, max);
        }
    }

    private static double readDouble() {
        while (true) {
            try { return Double.parseDouble(scanner.nextLine()); }
            catch (NumberFormatException e) { System.out.print("Введите число: "); }
        }
    }
}
