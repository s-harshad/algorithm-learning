package self.learning.algos.bag;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The {@code LinkedBag} class represents a bag (or multiset) of generic items 
 *  It supports insertion and iterating over the items in arbitrary order
 *  <p>
 *  This implementation uses a singly linked list with a non-static nested class Node 
 *  The <em>add</em>, <em>isEmpty</em>, and <em>size</em> operations take constant time
 *  Iteration takes time proportional to the number of items
 *
 *  @author Harshad Shrishrimal
 *  @param <Item> 
 */
public class LinkedBag<Item> implements Iterable<Item> {


    /**
     * Helper class for link list implementation
     * @param <Item> 
     */
    private class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    private Node<Item> first; // beginning of bag
    private int numberOfElements; // number of elements on bag

    /**
     * Initializes an empty bag
     */
    public LinkedBag() {
        first = null;
        numberOfElements = 0;
    }

    /**
     * Is this bag empty ?
     * @return true if empty; false otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Return the number of items in this bag
     * @return the number of items in this bag
     */
    public int size() {
        return numberOfElements;
    }

    /**
     * Adds the item to this bag.
     * @param item the item to be added to this bag.
     */
    public void add(Item item) {
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        numberOfElements++;
    }
    
    /**
     * Returns an iterator that iterates over the items in the bag
     * @return an iterator that iterates over the items in the bag
     */
    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }
    
    // an iterator over a linked list
    private class ListIterator implements Iterator<Item> {

        private Node<Item> current;
        
        public ListIterator() {
            current = first;
        }
        
        // is there a next item in the iterator?
        @Override
        public boolean hasNext() {
            return current != null;
        }

        // returns the next item in the iterator (and advances the iterator)
        @Override
        public Item next() {
            if(!hasNext()) { throw new NoSuchElementException(); }
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
