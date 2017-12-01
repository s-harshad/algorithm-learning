package self.learning.algos.queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code LinkedQueue} class represents a first-in-first-out (FIFO) queue of
 * generic items.
 * <br>
 * It supports the usual <em>enqueue</em> and <em>dequeue</em>
 * operations, along with methods for peeking at the first item, testing if the
 * queue is empty, and iterating through the items in FIFO order.
 * <p>
 * This implementation uses a singly linked list with a non-static nested class
 * for linked-list nodes.
 * <br>
 * The <em>enqueue</em>, <em>dequeue</em>, <em>peek</em>, <em>size</em>, and
 * <em>is-empty</em>
 * operations all take constant time in the worst case.
 * </p>
 *
 * @author Harshad Shrishrimal
 * @param <Item> generic item
 */
public class LinkedQueue<Item> implements Iterable<Item> {

    private int numberOfElements; // number of elements in queue
    private Node<Item> first; // points to start of queue
    private Node<Item> last; // points to last node in queue

    public LinkedQueue() {
        first = null;
        last = null;
        numberOfElements = 0;
    }

    /**
     * Private helper inner class for link list implementation
     *
     * @param <Item>
     */
    private static class Node<Item> {

        Item item;
        Node<Item> next;
    }

    /**
     * Is the queue really empty ?
     *
     * @return true if empty; false otherwise
     */
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    /**
     * Adds the item to queue
     *
     * @param item the item to add
     */
    public void enqueue(Item item) {

        Node oldLast = last;

        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty()) {
            first = last;
        } else {
            oldLast.next = last;
        }

        numberOfElements++;
    }

    /**
     * Removes the item from queue
     *
     * @return the removed item
     */
    public Item dequeue() {
        Item item = peek();
        first = first.next;
        numberOfElements--;
        if (isEmpty()) {
            last = null; // to avoid loitering
        }
        return item;

    }

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws java.util.NoSuchElementException if this queue is empty
     */
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        return first.item;
    }

    /**
     * Returns the number of items on queue
     *
     * @return the number of elements on stack
     */
    public int size() {
        return numberOfElements;
    }

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO
     * order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO
     * order
     */
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator(first);
    }

    private class ListIterator<Item> implements Iterator<Item> {

        Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
