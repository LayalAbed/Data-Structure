package application;

public interface Listable <T extends Comparable<T>> {

	boolean isEmpty();
	void Add(T data);
	boolean delete(T data);
	int find(T data);
	T gettheobj(int i);
	void clear();
	int NumOf(T data);
	void resize();
}
