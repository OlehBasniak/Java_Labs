package L4;

public class Pair<T> {
    private T first;
    private T second;

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T getSecond() {
        return second;
    }

    public boolean equals(Pair<?> otherPair) {
        return this.first.equals(otherPair.getFirst()) && this.second.equals(otherPair.getSecond());
    }

    public static void main(String[] args) {
        // Приклад використання класу Pair з різними типами
        Pair<String> stringPair = new Pair<>("Hello", "World");
        Pair<Integer> intPair = new Pair<>(1, 2);

        // Порівняння пар
        System.out.println("String Pair equals: " + stringPair.equals(new Pair<>("Hello", "World")));
        System.out.println("Int Pair equals: " + intPair.equals(new Pair<>(1, 2)));
        System.out.println("Mismatched Pair equals: " + stringPair.equals(intPair));
    }
}