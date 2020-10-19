public class ArrayDeque<T> implements Deque<T>{

    private T[] items;
    private int size;
    private int head;
    private int tail;

    /** Create an empty deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        head = 0;
        tail = 1;
        size = 0;
    }

    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        if (capacity >= items.length) {
            System.arraycopy(items, tail, temp, 0, items.length - tail);
            System.arraycopy(items, 0, temp, items.length - tail, tail);
        } else {
            System.arraycopy(items, head + 1, temp, 0, tail - head - 1);


        }

        head = capacity - 1;
        tail = size;
        items = temp;
    }

    private int minusOne(int index) {
        if (index - 1 < 0) {
            return items.length - 1;
        }
        return index - 1;
    }

    private int plusOne(int index) {
        if (index + 1 > items.length - 1) {
            return 0;
        }
        return index + 1;
    }
    
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(2 * size);
        }

        size += 1;
        items[head] = item;
        head = minusOne(head);

    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(2 * size);
        }

        if (size == 0) {
            size += 1;
            items[head] = item;
            head = minusOne(head);
        } else {
            size += 1;
            items[tail] = item;
            tail = plusOne(tail);
        }

    }

    @Override
    //Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    //Returns the number of items in the deque.
    public int size() {
        return size;
    }

    @Override
    //Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        int i = plusOne(head);
        while (i != tail)  {
            System.out.print(items[i] + " ");
            i = plusOne(i);
        }
        System.out.println("");
    }

    @Override
    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if (items.length >= 16 & (float) size / items.length < 0.25) {
            resize(items.length / 2);
        }
        head = plusOne(head);
        size -= 1;
        T removed = items[head];
        items[head] = null;
        return removed;

    }

    @Override
    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if (items.length >= 16 & (float) size / items.length < 0.25) {
            resize(items.length / 2);
        }
        tail = minusOne(tail);
        size -= 1;
        T removed = items[tail];
        items[tail] = null;
        return removed;
    }

    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    //If no such item exists, returns null. Must not alter the deque!
    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return items[(head + index + 1) % items.length];

    }

    public static void main(String[] args){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addLast(5);
        a.addLast(6);
        a.addLast(7);
        a.addFirst(4);

        a.addLast(8);
        a.addLast(9);
        a.addLast(10);
        a.addFirst(3);
        a.addFirst(2);
        a.addFirst(1);

        a.printDeque();
        a.removeFirst();
        a.removeLast();
        a.removeLast();
        a.removeLast();
        a.removeLast();
        a.removeLast();
        a.printDeque();

    }

}
