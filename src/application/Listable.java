package application;

public interface Listable<T extends Comparable<T>> {
    boolean isEmpty();
    void add(T data);
    boolean delete(T data);
    int find(T data);
    T get(int index);
    void clear();
    int countOf(T data);  // Renamed from NumOf for clarity
    void resize();
}

