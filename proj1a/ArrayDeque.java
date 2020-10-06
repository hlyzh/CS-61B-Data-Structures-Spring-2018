public class ArrayDeque<T> {

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
            System.arraycopy(items, head + 1, temp, 0, items.length - head - 1);
            System.arraycopy(items, 0, temp, items.length - head - 1, tail);
        }

        head = capacity - 1;
        tail = size;
        items = temp;
    }

    private int minusOne(int index) {
        if (index - 1 < 0)
            return items.length - 1;
        return index - 1;
    }

    private int plusOne(int index) {
        if (index + 1 > items.length - 1)
            return 0;
        return index + 1;
    }

    public void addFirst(T item) {
        items[head] = item;
        head = minusOne(head);
        size += 1;

        if (size == items.length) {
            resize(2 * size);
        }
    }

    public void addLast(T item) {
        if (size == 0) {
            items[head] = item;
            head = minusOne(head);
        } else {
            items[tail] = item;
            tail = plusOne(tail);
        }
        size += 1;

        if (size == items.length) {
            resize(2 * size);
        }
    }

    //Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        return size == 0;
    }

    //Returns the number of items in the deque.
    public int size() {
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space.
    //?
    public void printDeque() {
        if (size == 0) {
            return;
        }
        int i = plusOne(head);
        while (i != tail)  {
            System.out.print(items[i] + " ");
            i = plusOne(i);
        }
        System.out.println("");
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst() {
        if (isEmpty() == true) {
            return null;
        }
        size -= 1;
        head = plusOne(head);
        T removed = items[head];
        //items[head] = null;
        System.out.println("ratio:" + (float)size/items.length);
        if ((float)size/items.length <= 0.25 ) {
            resize(items.length / 2);
        }
        return removed;

    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast() {
        if (isEmpty() == true) {
            return null;
        }
        size -= 1;
        tail = minusOne(tail);
        T removed = items[tail];
        //items[tail] = null;
        System.out.println("ratio:" + (float)size/items.length);
        if ((float)size/items.length <= 0.25 ) {
            resize(items.length / 2);
        }
        return removed;
    }

    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        return items[(head + index + 1) % items.length];

    }

    public static void main(String[] args){
        ArrayDeque<Integer> a = new ArrayDeque<>();
        /*
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

        System.out.println(a.get(0));
         */
        a.addFirst(0);
        a.addFirst(1);
        System.out.println(a.removeLast());

        a.addFirst(3);

        System.out.println(a.get(1));


    }



}

