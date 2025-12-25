public class Box<T> {
    private T item;

    public Box() {
        this.item = null;
    }

    public Box(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Нельзя положить null в коробку");
        }
        this.item = item;
    }

    public T getItem() {
        T temp = item;
        item = null; // обнуляем после извлечения
        return temp;
    }

    public void setItem(T item) {
        if (item == null) {
            throw new IllegalArgumentException("Нельзя положить null в коробку");
        }
        if (this.item != null) {
            throw new IllegalStateException("Коробка уже содержит объект!");
        }
        this.item = item;
    }

    public boolean isFull() {
        return item != null;
    }

    @Override
    public String toString() {
        return "Коробка{" + (item != null ? "содержит: " + item : "пуста") + "}";
    }
}
