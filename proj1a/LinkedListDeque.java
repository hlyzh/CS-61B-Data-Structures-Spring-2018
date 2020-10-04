public class LinkedListDeque<T> {

    private class ItemNode {
        public ItemNode prev;
        public T item;
        public ItemNode next;

        public ItemNode(T i) {
            this.prev = null;
            this.item = i;
            this.next = null;
        }
    }

    private ItemNode sentinel;
    private int size;

    /** Create an empty deque. */
    public LinkedListDeque() {
        size = 0;

        sentinel = new ItemNode(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    //Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        size += 1;

        ItemNode curr = new ItemNode(item);
        curr.prev = sentinel;
        curr.next = sentinel.next;
        sentinel.next.prev = curr;
        sentinel.next = curr;

    }

    //Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        size += 1;

        ItemNode curr = new ItemNode(item);
        curr.prev = sentinel.prev;
        curr.next = sentinel;
        sentinel.prev.next = curr;
        sentinel.prev = curr;

    }

    //Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        if (sentinel.next == sentinel ) {
            return true;
        } else {
            return false;
        }
    }

    //Returns the number of items in the deque.
    public int size() {
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space.
    public void printDeque() {
        ItemNode curr = sentinel.next;
        while (curr != sentinel) {
            System.out.println(curr.item);
            curr = curr.next;
        }
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    public T removeFirst() {
        ItemNode curr = sentinel;
        T remove_First_Value = sentinel.next.item;

        if (isEmpty() == true) {
            return null;
        } else {
            size -= 1;

            curr.next = sentinel.next.next;
            curr.next.prev = sentinel;
            sentinel = curr;

            return remove_First_Value;
        }
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast() {
        ItemNode curr = sentinel;
        T remove_Last_Value = sentinel.prev.item;

        if (isEmpty() == true) {
            return null;
        } else {
            size -= 1;

            curr.prev = sentinel.prev.prev;
            curr.prev.next = sentinel;

            sentinel = curr;

            return remove_Last_Value;
        }
    }

    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
    public T get(int index) {
        ItemNode curr = sentinel.next;
        if (index > size -1 ) {
            return null;
        } else {
            int curr_index = 0;
            while (curr_index != index) {
                curr_index += 1;

                curr = curr.next;
            }
            return curr.item;
        }
    }

    private T getRecursive_helper(ItemNode curr, int index) {
        if (index != 0) {
            curr = curr.next;
            index -= 1;
            return getRecursive_helper(curr, index);
        } else {
            return curr.item;
        }
    }

    //ame as get, but uses recursion.
    public T getRecursive(int i) {
        if (i > size -1 ) {
            return null;
        } else {
            return getRecursive_helper(sentinel.next, i);
        }
    }


}