package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;


    private class MyIterator implements Iterator<T> {
        private int index;
        private boolean startFlag = true;

        public MyIterator() {
            index = first;
        }

        @Override
        public boolean hasNext() {
            if (first == last && isFull() && startFlag) {
                startFlag = false;
                return true;
            }
            return index != last;
        }

        @Override
        public T next() {
            T item = rb[index];
            index = (index + 1) % capacity;
            return item;
        }

    }


    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];

    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity;
        fillCount += 1;

    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }

        T returnFirst = rb[first];
        rb[first] = null;
        first = (first + 1) % capacity;
        fillCount -= 1;

        return returnFirst;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }

        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new MyIterator();
    }

}




