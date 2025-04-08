package application;



public class MyList<T extends Comparable<T>> implements Listable<T> {
    private Object[] list;
    private int count = 0;

    public MyList(int size) {
        list = new Object[size];
    }

    @SuppressWarnings("unchecked")
    private T getElement(int index) {
        return (T) list[index];
    }

    @Override
    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    @Override
    public void add(T data) {
        if (count >= list.length) {
            resize();
        }
        list[count++] = data;
    }

    @Override
    public boolean delete(T data) {
        for (int i = 0; i < count; i++) {
            if (getElement(i).equals(data)) {
                System.arraycopy(list, i + 1, list, i, count - i - 1);
                count--;
                return true;
            }
        }
        return false;
    }

    @Override
    public int find(T data) {
        for (int i = 0; i < count; i++) {
            if (data.compareTo(getElement(i)) == 0) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public T get(int index) {
        if (index >= 0 && index < count) {
            return getElement(index);
        }
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + count);
    }

    @Override
    public void clear() {
        count = 0;
    }


    @Override
    public int countOf(T data) {
        int num = 0;
        for (int i = 0; i < count; i++) {
            if (getElement(i).equals(data)) {
                num++;
            }
        }
        return num;
    }

    @Override
    public void resize() {
        Object[] newList = new Object[list.length * 2];
        System.arraycopy(list, 0, newList, 0, list.length);
        list = newList;
    }
}

