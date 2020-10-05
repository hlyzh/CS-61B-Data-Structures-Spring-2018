public class LinkedListDeque<T> {

    private class ItemNode {
        public ItemNode prev;
        public T item;
        public ItemNode next;

        public ItemNode(ItemNode prev_node, T i, ItemNode next_node) {
            this.prev = prev_node;
            this.item = i;
            this.next = next_node;
        }
    }

    private ItemNode sentinel;
    private int size;

    /** Create an empty deque. */
    public LinkedListDeque() {
        size = 0;

        sentinel = new ItemNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    //Adds an item of type T to the front of the deque.
    public void addFirst(T item) {
        size += 1;

        sentinel.next = new ItemNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
    }

    //Adds an item of type T to the back of the deque.
    public void addLast(T item) {
        size += 1;

        sentinel.prev = new ItemNode(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev;

    }

    //Returns true if deque is empty, false otherwise.
    public boolean isEmpty() {
        return this.size == 0;
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
        if (isEmpty() == true) {
            return null;
        }
        T remove_First_Value = sentinel.next.item;
        size -= 1;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return remove_First_Value;
    }

    //Removes and returns the item at the back of the deque. If no such item exists, returns null.
    public T removeLast() {
        if (isEmpty() == true) {
            return null;
        }
        T remove_Last_Value = sentinel.prev.item;
        size -= 1;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return  remove_Last_Value;
    }

    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. Must not alter the deque!
    public T get(int index) {
        ItemNode curr = sentinel.next;
        if (index > size -1 || index < 0) {
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
        }
        return curr.item;
    }

    //ame as get, but uses recursion.
    public T getRecursive(int i) {
        if (i > size -1 || i < 0) {
            return null;
        }
        return getRecursive_helper(sentinel.next, i);
    }

}