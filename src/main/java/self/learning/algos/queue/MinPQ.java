package self.learning.algos.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code MinPQ} class represents a priority queue of generic keys.
 * <br>
 *
 * It supports the usual <em>insert</em> and <em>delete-the-minimum</em>
 * operations, along with methods for peeking at the minimum key, testing if the
 * priority queue is empty, and iterating through the keys.
 * <p>
 * This implementation uses a binary heap. The <em>insert</em> and
 * <em>delete-the-minimum</em> operations take logarithmic amortized time. The
 * <em>min</em>, <em>size</em>, and <em>is-empty</em> operations take constant
 * time.
 * </p>
 *
 * @author Harshad Shrishrimal
 * @param <Key> the generic type of key on this priority queue
 */
public class MinPQ<Key extends Comparable<Key>> implements Iterable<Key> {

    private Key[] pq; // binary heap representation
    private int numberOfElements; // indicates the number of keys in queue

    /**
     * Create a min priority queue to hold 1 key
     */
    public MinPQ() {
        this(1);
    }

    /**
     * Create a min priority queue
     *
     * @param initialCapacity initial capacity for the array
     */
    public MinPQ(int initialCapacity) {
        // 1 based array indexing.
        pq = (Key[]) new Comparable[initialCapacity + 1];
        numberOfElements = 0;
    }

    /**
     * Is the queue really empty ?
     *
     * @return {@code true} if empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    /**
     * Returns the number of Items in queue
     *
     * @return total number of items
     */
    public int size() {
        return numberOfElements;
    }

    /**
     * Return the minimum key
     *
     * @return return the minimum key
     */
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return pq[1];
    }

    /**
     * Return and remove the minimum key
     *
     * @return return and remove the minimum key
     */
    public Key deleteMin() {
        Key min = min();
        exch(pq, 1, numberOfElements--);
        sink(pq, 1);
        pq[numberOfElements + 1] = null; // to avoid loiterig and help with garbage collection
        if (numberOfElements > 0 && (numberOfElements == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }
        return min;
    }

    /**
     * Insert the key in Priority Queue
     *
     * @param key key to be inserted
     */
    public void insert(Key key) {

        //check if there is space in array, resize otherwise
        if (pq.length - 1 == numberOfElements) {
            resize(2 * pq.length);
        }
        pq[++numberOfElements] = key;
        swim(pq, numberOfElements);
    }

    /**
     * Increase/Decrease the size of our priority queue array
     *
     * @param newCapacity the new length of priority queue.
     */
    private void resize(int newCapacity) {
        Key[] temp = (Key[]) new Comparable[newCapacity];
        for (int i = 0; i <= numberOfElements; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    /**
     * <p>
     * Bottom-up reheapify (swim). Heap order is violated because a node's key
     * becomes smaller than that node's parents key, then we can make progress
     * toward fixing the violation by exchanging the node with its parent. After
     * the exchange, the node is smaller than both its children (one is the old
     * parent, and the other is smaller than the old parent because it was a
     * child of that node) but the node may still be smaller than its parent. We
     * can fix that violation in the same way, and so forth, moving up the heap
     * until we reach a node with a smaller key, or the root.
     * </p>
     *
     * @param pq binary heap. Collection of all keys in priority queue
     * @param idx index from where to start swim operation
     */
    private void swim(Comparable[] pq, int idx) {
        while (idx > 1 && less(pq, idx, idx / 2)) {
            exch(pq, idx, idx / 2);
            idx = idx / 2;
        }
    }

    /**
     * Compare 2 keys/elements and return {@code true} if first key is less than
     * second key; {@code false} otherwise
     *
     * @param c array which has all the elements/keys
     * @param i 1st index of the element which will be used in compare
     * @param j 2nd index of the element which will be used in compare
     * @return {@code true} if key in 'i' is less than key in 'j'
     */
    private boolean less(Comparable[] c, int i, int j) {
        return c[i].compareTo(c[j]) < 0;
    }

    /**
     * Swap the position of 2 objects.
     *
     * @param c array which has all the elements/keys
     * @param i 1st index
     * @param j 2nd index
     */
    private void exch(Comparable[] c, int i, int j) {
        Comparable temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }

    /**
     * <p>
     * Heap order is violated because a node's key becomes larger than one or
     * both of that node's children's keys, then we can make progress toward
     * fixing the violation by exchanging the node with the smaller of its two
     * children. This switch may cause a violation at the child; we fix that
     * violation in the same way, and so forth, moving down the heap until we
     * reach a node with both children larger, or the bottom.
     * </p>
     *
     * @param pq binary heap. Collection of all keys in priority queue
     * @param idx index from where to start sink operation, usually it's root
     */
    private void sink(Comparable[] pq, int idx) {

        while (2 * idx <= numberOfElements) {

            // index of 1st child
            int j = 2 * idx;

            // of all the childern, find the idx with minimum key
            if (j < numberOfElements && !less(pq, j, j + 1)) {
                j++;
            }

            // compare root with minimum child, exchange if necessary
            if (less(pq, idx, j)) {
                break;
            }
            exch(pq, idx, j);
            idx = j;
        }

    }

    /**
     * Returns an iterator that iterates over the keys on this priority queue in
     * ascending order.
     *
     * @return an iterator that iterates over the keys in ascending order
     */
    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    /**
     * Iterator
     */
    private class HeapIterator implements Iterator<Key> {

        private final MinPQ<Key> copy;

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy.deleteMin();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public HeapIterator() {
            copy = new MinPQ<>(size());
            // copy takes linear time since the keys are already in heap order so no keys move.
            for (int i = 1; i <= numberOfElements; i++) {
                copy.insert(pq[i]);
            }
        }

    }

}
