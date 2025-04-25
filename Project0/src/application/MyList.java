package application;

public class MyList<T extends Comparable<T>> implements Listable<T> {

	T[] list;
	int count = 0;

	public MyList(int size) {
		list = (T[]) new Comparable[size];
	}

	@Override
	public boolean isEmpty() {

		return count == 0;
	}

	// add new object
	@Override
	public void Add(T data) {
		if (count < list.length) {
			list[count++] = data;
			System.out.println("done");
		} else {
			resize();
			list[count++] = data;
		}

	}

	@Override
	// deleat the  object
	public boolean delete(T data) {
		int index = find(data);
		if (index == -1) {
			System.out.println("Does not exist");
			return false;
		} else {

			for (int i = index + 1; i < count; i++) {
				list[i - 1] = list[i];
			}
			count--;
			return true;
		}

	}



	// get the index by using the object
	@Override
	public int find(T data) {
		for (int i = 0; i < count; i++) {
			if (data.compareTo(list[i]) == 0) {
				return i;
			}
		}

		return -1;
	}

	// get the object by using the index
	@Override
	public T gettheobj(int i) {
		if (i >= 0 && i < count) {
			return list[i];
		} else {
			System.out.println("Does not exist");
			return null;
		}
	}



	// to add more index to the array
	 public void resize() {
	        T[] newList = (T[]) new Comparable[list.length * 2];
	        
	        System.arraycopy(list, 0, newList, 0, list.length);
	        list = newList;
	    }


	// number object on the array
	public int NumOf(T data) {
		int sum = 0;
		for (int i = 0; i < count; i++) {
			if ((data.compareTo(list[i])) == 0) {
				sum++;

			}
		}
		return sum;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub

	}
}
