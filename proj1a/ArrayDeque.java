public class ArrayDeque<T> {
    private int nextLast;
    private int nextFirst;
    private T[] array;
    private int size;
    private final double MIN_USAGE = 0.25;
    private final int MAX_ARRAY_LENGTH_TO_AVOID_USAGE_REQUIREMENT = 100;

    public ArrayDeque() {
        array = (T[]) new Object[8];
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
        // find the actual start and end of the deque
        int first = getNextIndex(nextFirst);
        int last = getPrevIndex(nextLast);
        // print from first till earlier of last or end of array
        for (int i = first; i < Math.min(last, array.length - 1); i++) {
            System.out.print(array[i] + " ");
        }
        // if last is before first, array is split and print second half from start of array
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
        int first = getNextIndex(nextFirst);
        T removed = remove(first);
        if (size * 1.0 / array.length >= MIN_USAGE || array.length <= MAX_ARRAY_LENGTH_TO_AVOID_USAGE_REQUIREMENT) {
            nextFirst = first;
        } else {
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
        if (size * 1.0 / array.length >= MIN_USAGE || array.length <= MAX_ARRAY_LENGTH_TO_AVOID_USAGE_REQUIREMENT) {
            nextLast = last;
        } else {
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

    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        Integer removed;
        removed = deque.removeFirst();
        deque.addFirst(23);
        deque.addFirst(15);
        removed = deque.removeLast();
        removed = deque.removeLast();
        System.out.println(removed);
        System.out.println(deque.isEmpty());

    }
}
