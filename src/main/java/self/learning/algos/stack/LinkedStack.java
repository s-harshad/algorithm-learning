package self.learning.algos.stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The {@code LinkedStack} class represents a last-in-first-out (LIFO) stack of
 * generic items.
 * <br>
 * It supports the usual <em>push</em> and <em>pop</em> operations, along with
 * methods for peeking at the top item, testing if the stack is empty, and
 * iterating through the items in LIFO order.
 * <p>
 * This implementation uses a singly linked list with a static nested class for
 * linked-list nodes.
 * <br>
 * The <em>push</em>, <em>pop</em>, <em>peek</em>, <em>size</em>, and
 * <em>is-empty</em>
 * operations all take constant time in the worst case.
 * <p>
 *
 * @author Harshad Shrishrimal
 * @param <Item> generic item
 */
public class LinkedStack<Item> implements Iterable<Item> {

    /**
     * Private helper inner class for link list implementation
     *
     * @param <Item> generic item
     */
    private static class Node<Item> {

        private Item item;
        private Node<Item> next;
    }

    private Node<Item> top; // top of stack
    private int numberOfElements; // number of elements on bag

    public LinkedStack() {
        top = null;
        numberOfElements = 0;
    }

    /**
     * Push item to top of stack
     *
     * @param item item to be inserted
     */
    public void push(Item item) {
        Node oldTop = top;
        top = new Node();
        top.item = item;
        top.next = oldTop;
        numberOfElements++;
    }

    /**
     * Remove and return the item on top of stack.
     *
     * @return remove and return the item on top of stack.
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public Item pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        Node newTop = top.next;
        Item item = top.item;
        top = newTop;
        numberOfElements--;
        return item;
    }

    /**
     * Is the stack empty?
     *
     * @return true if empty; false otherwise
     */
    public boolean isEmpty() {
        return numberOfElements == 0;
    }

    /**
     * Returns the number of elements on the stack
     *
     * @return the number of elements on the stack
     */
    public int size() {
        return numberOfElements;
    }

    /**
     * Returns the top item on the stack without removing it from stack
     *
     * @return the top item on the stack
     * @throws java.util.NoSuchElementException if this stack is empty
     */
    public Item peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Stack is empty");
        }
        return top.item;
    }

    /**
     *
     * Returns an iterator to this stack that iterates through the items in LIFO
     * order.
     *
     * @return an iterator to this stack that iterates through the items in LIFO
     * order.
     */
    @Override
    public Iterator<Item> iterator() {
        return new StackIterator(top);
    }

    /**
     * Stack Iterator
     *
     * @param <Item>
     */
    private class StackIterator<Item> implements Iterator<Item> {

        private Node<Item> current;

        public StackIterator(Node top) {
            current = top;
        }

        @Override
        public boolean hasNext() {
            return current == null;
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
