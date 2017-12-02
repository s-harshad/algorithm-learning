package self.learning.algos.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code MaxPQ} class represents a priority queue of generic keys.
 * <br>
 * It supports the usual <em>insert</em> and <em>delete-the-minimum</em>
 * operations, along with methods for peeking at the minimum key, testing if the
 * priority queue is empty, and iterating through the keys.
 * <p>
 * This implementation uses a binary heap. The <em>insert</em> and
 * <em>delete-the-minimum</em> operations take logarithmic amortized time. The
 * <em>min</em>, <em>size</em>, and <em>is-empty</em> operations take constant
 * time.
 * <br>
 * </p>
 *
 * @author Harshad Shrishrimal
 *
 * @param <Key> the generic type of key on this priority queue
 */
public class MaxPQ<Key extends Comparable<Key>> implements Iterable<Key> {

    private Key pq[];   // binary heap representation
    private int numberOfElements; // number of elements in queue

    /**
     * Create a max priority queue to hold 1 key
     */
    public MaxPQ() {
        this(1);
    }

    /**
     * Create a max priority queue
     *
     * @param initialCapacity initial capacity for the array
     */
    public MaxPQ(int initialCapacity) {
        pq = (Key[]) new Comparable[initialCapacity + 1];
        numberOfElements = 0;
    }

    /**
     * Return and remove the maximum key
     *
     * @return return and remove the maximum key
     */
    public Key deleteMax() {
        Key max = max();
        exch(pq, 1, numberOfElements--);
        sink(pq, 1);
        pq[numberOfElements + 1] = null; // avoid loiterig and help with garbage collection
        // resize the array
        if ((numberOfElements > 0) && (numberOfElements == (pq.length - 1) / 4)) {
            resize(pq.length / 2);
        }
        return max;
    }

    /**
     * Return the maximum key
     *
     * @return return the maximum key
     */
    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("Priority queue underflow");
        }
        return pq[1];
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
     * Increase/Decrease the size of our priority queue array
     *
     * @param newCapacity the new length of priority queue.
     */
    private void resize(int newCapacity) {
        // create the array with given capacity
        Key[] newPQ = (Key[]) new Comparable[newCapacity];
        newPQ[0] = null;

        //copy existing elements
        for (int i = 1; i <= numberOfElements; i++) {
            newPQ[i] = pq[i];
        }

        pq = newPQ;
    }

    /**
     * <p>
     * Bottom-up reheapify (swim). Heap order is violated because a node's key
     * becomes larger than that node's parents key, then we can make progress
     * toward fixing the violation by exchanging the node with its parent. After
     * the exchange, the node is larger than both its children (one is the old
     * parent, and the other is smaller than the old parent because it was a
     * child of that node) but the node may still be larger than its parent. We
     * can fix that violation in the same way, and so forth, moving up the heap
     * until we reach a node with a larger key, or the root.
     * </p>
     *
     * @param pq binary heap. Collection of all keys in priority queue
     * @param idx index from where to start swim operation
     */
    private void swim(Key[] pq, int idx) {
        while (idx > 1 && less(pq, idx / 2, idx)) {
            exch(pq, idx / 2, idx);
            idx = idx / 2;
        }
    }

    /**
     * <p>
     * Heap order is violated because a node's key becomes smaller than one or
     * both of that node's children's keys, then we can make progress toward
     * fixing the violation by exchanging the node with the larger of its two
     * children. This switch may cause a violation at the child; we fix that
     * violation in the same way, and so forth, moving down the heap until we
     * reach a node with both children smaller, or the bottom.
     * </p>
     *
     * @param pq binary heap. Collection of all keys in priority queue
     * @param idx index from where to start sink operation, usually it's root
     */
    private void sink(Key[] pq, int idx) {
        while (2 * idx <= numberOfElements) {
            int childIdx = 2 * idx;
            if (childIdx < numberOfElements && less(pq, childIdx, childIdx + 1)) {
                childIdx = childIdx + 1;
            }
            if (!less(pq, idx, childIdx)) {
                break;
            }
            exch(pq, idx, childIdx);
            idx = childIdx;
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
    private void exch(Key[] c, int i, int j) {
        Key temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }

    /**
     * Returns an iterator that iterates over the keys on this priority queue in
     * descending order.
     *
     * @return an iterator that iterates over the keys in descending order
     */
    @Override
    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    /**
     * Iterator
     */
    private class HeapIterator implements Iterator<Key> {

        private final MaxPQ<Key> copy;

        @Override
        public boolean hasNext() {
            return !copy.isEmpty();
        }

        @Override
        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy.deleteMax();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        public HeapIterator() {
            copy = new MaxPQ<>(size());
            // copy takes linear time since the keys are already in heap order so no keys move.
            for (int i = 1; i <= numberOfElements; i++) {
                copy.insert(pq[i]);
            }
        }

    }

}
