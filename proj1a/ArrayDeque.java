public class ArrayDeque<T> {

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    /** Create an empty deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }

    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        System.arraycopy(items, 0, temp, 1, size);
        items = temp;
    }

    public void addFirst(T item) {
        if (size >= items.length) {
            resize(2 * size);
            nextFirst = 0;
            nextLast = size + 1;
        }
        size += 1;
        items[nextFirst] = item;
        nextFirst -= 1;
        if (nextFirst < 0) {
            nextFirst = items.length - 1;
        }
    }

    public void addLast(T item) {
        if (size >= items.length) {
            resize(2 * size);
            nextFirst = items.length - 1;
            nextLast = size;
        }

        size += 1;
        items[nextLast] = item;
        nextLast += 1;
        if (nextLast > items.length -1) {
            nextLast = 0;
        }

    }

    //Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        for (int i = 0; i < items.length; i += 1) {
            if (items[i] != null) {
                return false;
            }
        }
        return true;
    }

    //Returns the number of items in the deque.
    public int size() {
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        for (int i = nextFirst - 1; i < nextLast - 1; i += 1) {
            System.out.println(items[i]);
        }
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst() {
        if (isEmpty() == true) {
            return null;
        } else {
            size -= 1;
            int lastFirst = nextFirst + 1;
            if (lastFirst > items.length - 1) {
                lastFirst = 0;
            }
            T removeFirstValue = items[lastFirst];
            items[lastFirst] = null;
            return removeFirstValue;
        }
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast() {
        if (isEmpty() == true) {
            return null;
        } else {
            size -= 1;
            int lastLast = nextLast - 1;
            if (lastLast < 0) {
                lastLast = items.length - 1;
            }
            T removeLastValue = items[lastLast];
            items[lastLast] = null;
            return removeLastValue;
        }
    }

    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
    public T get(int index) {
        if (index >= nextFirst + 1 & index <= nextLast - 1) {
            return items[index];
        } else {
            return null;
        }

    }

}

