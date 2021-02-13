public class ArrayDeque<T> {
    private int nextLast;
    private int nextFirst;
    private T[] array;
    private int size;
    private final double MIN_USAGE = 0.25;
    private final int SMALL_ARRAY_MAX_LEN = 8;

    public ArrayDeque() {
        array = (T[]) new Object[SMALL_ARRAY_MAX_LEN];
        size = 0;
        nextFirst = 0;
        nextLast = nextFirst + 1;
    }

    public void addFirst(T item) {
        // if the array is already full, double capacity which resets nextLast and nextFirst
        if (size == array.length) {
            adjustCapacity(size * 2);
        }
        // add item to index nextFirst and update size and nextFirst
        array[nextFirst] = item;
        nextFirst = getPrevIndex(nextFirst);
        size++;
    }

    public void addLast(T item) {
        // if the array is already full, double capacity which resets nextLast and nextFirst
        if (size == array.length) {
            adjustCapacity(size * 2);
        }
        // add item to index nextLast and update size and nextLast
        array[nextLast] = item;
        nextLast = getNextIndex(nextLast);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (!isEmpty()) {
            // find the first index of the deque;
            int first = getNextIndex(nextFirst);
            // print from first and wrap around when reaches end of array
            for (int i = first; i < first + size; i++) {
                System.out.print(array[i % array.length] + " ");
            }
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int first = getNextIndex(nextFirst);
        T removed = remove(first);
        nextFirst = first;
        if (getUsage() < MIN_USAGE && !isSmallArray()) {
            adjustCapacity(array.length / 2);
        }
        return removed;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int last = getPrevIndex(nextLast);
        T removed = remove(last);
        nextLast = last;
        if (getUsage() < MIN_USAGE && !isSmallArray())  {
            adjustCapacity(array.length / 2);
        }
        return removed;
    }

    public T get(int index) {
        int first = getFirstIndex();
        return array[(first + index) % array.length];
    }

    private void adjustCapacity(int capacity) {
        if (isEmpty()) {
            return;
        }
        T[] temp = (T[]) new Object[capacity];
        int first = getNextIndex(nextFirst);
        int last = getPrevIndex(nextLast);
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
        return removed;
    }

    private int getPrevIndex(int index) {
        if (index == 0) {
            return array.length - 1;
        }
        return index - 1;
    }

    private int getNextIndex(int index) {
        if (index == array.length - 1) {
            return 0;
        }
        return index + 1;
    }

    private double getUsage() {
        return size * 1.0 / array.length;
    }

    private boolean isSmallArray() {
        return array.length <= SMALL_ARRAY_MAX_LEN;
    }

    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        deque.addFirst(9);
        deque.addFirst(8);
        deque.addFirst(7);
        deque.addFirst(6);
        deque.addFirst(5);
        deque.addFirst(4);
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addFirst(9);
        deque.addFirst(8);
        deque.addFirst(7);
        deque.addFirst(6);
        deque.addFirst(5);
        deque.addFirst(4);
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        System.out.print("removed: " + deque.removeFirst());
        System.out.print(", " + deque.removeFirst());
        System.out.print(", " + deque.removeFirst());
        System.out.print(", " + deque.removeFirst());
        System.out.print(", " + deque.removeFirst());
        System.out.print(", " + deque.removeFirst());
        System.out.println();

        deque.printDeque();

        ArrayDeque<Integer> deque1 = new ArrayDeque<>();
        deque1.addFirst(9);
        deque1.addFirst(8);
        deque1.addFirst(7);
        deque1.addFirst(6);
        deque1.addFirst(5);
        deque1.addFirst(4);
        deque1.addFirst(3);
        deque1.addFirst(2);
        deque1.addFirst(1);
        System.out.print("removed: " + deque1.removeFirst());
        System.out.print(", " + deque1.removeFirst());
        System.out.print(", " + deque1.removeFirst());
        System.out.print(", " + deque1.removeFirst());
        System.out.print(", " + deque1.removeFirst());
        System.out.print(", " + deque1.removeFirst());
        System.out.println();

        deque1.printDeque();
    }
}
