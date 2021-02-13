public class LinkedListDeque<T> {
    private IntNode<T> sentinel;
    private int size;

    private class IntNode<T> {
        private IntNode prev;
        private T item;
        private IntNode next;
        private IntNode(IntNode prev, T item, IntNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    public LinkedListDeque() {
        sentinel = new IntNode<>(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

//    public LinkedListDeque(T first) {
//        this();
//        sentinel.next = new IntNode(sentinel, first, sentinel);
//        sentinel.prev = sentinel.next;
//        size++;
//    }

    public void addFirst(T item) {
        sentinel.next = new IntNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    public void addLast(T item) {
        sentinel.prev = new IntNode(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        IntNode<T> p = sentinel.next;
        while (p != sentinel) {
            System.out.print(String.format("%s ", p.item.toString()));
            p = p.next;
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T p = (T) sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
        return p;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T p = (T) sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size--;
        return p;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        IntNode<T> p;
        if (index + 1 <= size / 2) {
            p = sentinel.next;
            while (index > 0) {
                p = p.next;
                index--;
            }
            return p.item;
        } else {
            p = sentinel.prev;
            index = size - 1 - index;
            while (index > 0) {
                p = p.prev;
                index--;
            }
            return p.item;
        }
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        if (index == 0) {
            return (T) sentinel.next.item;
        }
        LinkedListDeque<T> newDeque = new LinkedListDeque<>();
        newDeque.sentinel.next = sentinel.next.next;
        newDeque.sentinel.prev = sentinel.prev;
        newDeque.size = size;
        index--;
        return newDeque.getRecursive(index);
    }

    public static void main(String[] args) {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addLast(0);
        deque.addLast(1);
        deque.addLast(2);
        Integer result = deque.getRecursive(3);
        System.out.print(result);
    }
}
