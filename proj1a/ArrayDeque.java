public class ArrayDeque<T> {
    private int nextLast;
    private int nextFirst;
    private T[] array;
    private int size;
    private final double MIN_USAGE = 0.25;

    public ArrayDeque() {
        array = (T[]) new Object[8];
        size = 0;
        nextLast = 0;
        nextFirst = 0;
    }

    public void addFirst(T item) {
        if (size == array.length) {
            adjustCapacity(size * 2);
        }
        array[nextFirst] = item;
        nextFirst = nextFirst == 0 ? array.length - 1 : nextFirst - 1;
        size++;
    }

    public void addLast(T item) {
        if (size == array.length) {
            adjustCapacity(size * 2);
        }
        array[nextLast] = item;
        nextLast = nextLast == array.length - 1 ? 0 : nextLast + 1;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int first = getFirstIndex();
        int last = getLastIndex();
        for (int i = first; i < Math.min(last, array.length - 1); i++) {
            System.out.print(array[i] + " ");
        }
        if (last < first) {
            for (int i = 0; i <= last; i++) {
                System.out.print(array[i] + " ");
            }
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int first = getFirstIndex();
        T removed = remove(first);
        if (size * 1.0 / array.length >= MIN_USAGE) {
            nextFirst = first;
        }
        return removed;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int last = getLastIndex();
        T removed = remove(last);
        if (size * 1.0 / array.length >= MIN_USAGE) {
            nextLast = last;
        }
        return removed;
    }

    public T get(int index) {
        int first = getFirstIndex();
        return array[(first + index) % array.length];
    }

    private void adjustCapacity(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        int first = getFirstIndex();
        int last = getLastIndex();
        // when array is empty, do nothing
        if (first == last || size == 0) {
            return;
        }
        // copy from first to min(last, end of array)
        System.arraycopy(array, first, temp, 0,
                         Math.min(size, array.length - first));
        // when array is split, print second half from index 0
        if (last < first) {
            System.arraycopy(array, 0, temp, array.length - first,
                      last + 1);
        }
        nextFirst = temp.length - 1;
        nextLast = size;
        array = temp;
    }

    private int getFirstIndex() {
        return nextFirst == array.length - 1 ? 0 : nextFirst + 1;
    }

    private int getLastIndex() {
        return nextLast == 0 ? array.length - 1 : nextLast - 1;
    }

    private T remove(int index) {
        T removed = array[index];
        array[index] = null;
        size--;
        if (size * 1.0 / array.length < MIN_USAGE) {
            adjustCapacity(array.length / 2);
        }
        return removed;
    }
}
