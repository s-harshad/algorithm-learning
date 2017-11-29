package self.learning.algos.bag;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  The {@code ResizingArrayBag} class represents a bag (or multiset) of generic items 
 *  <br>
 *  It supports insertion and iterating over the items in arbitrary order
 *  <p>
 *  This implementation uses a resizing array
 *  <br>
 *  The <em>add</em> operation takes constant amortized time
 *  <br>
 *  The <em>isEmpty</em>, and <em>size</em> operations take constant time
 *  <br>
 *  Iteration takes time proportional to the number of items
 *
 *  @author Harshad Shrishrimal
 *  @param <Item> generic
 */
public class ResizingArrayBag<Item> implements Iterable<Item> {
    
    private Item[] a; // Array to hold a collection of items
    private int numberOfElements; // number of elements on bag
    
    /**
     * Create a bag with initial capacity of 2
     * <br>
     * When the capacity is full, it will be resized to double it's capacity
     */
    public ResizingArrayBag() {
        a = (Item[]) new Object[2]; // at first we'll make space for 2 items
        numberOfElements = 0; // explictly set size to 0
    }
    
    /**
     * Create a bag with initial capacity specified by user
     * <br>
     * When the capacity is full, it will be resized to double its initial capacity
     * @param initialCapacity initialCapacity for the number of items
     */
    public ResizingArrayBag(int initialCapacity) {
        a = (Item[]) new Object[initialCapacity]; // initialize array
        numberOfElements = 0; // explictly set size to 0
    }
    
    /**
     * Is this bag empty ?
     * @return true if empty; false otherwise
     */
    public boolean isEmpty() {
        return numberOfElements == 0;
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
        
        // double the size of the array if necessary
        if(a.length == numberOfElements) {
            resize(2*numberOfElements);
        }
        
        // add the item in the array
        a[numberOfElements++] = item;
    }
    
    /**
     * Returns an iterator that iterates over the items in the bag, arbitrary order
     * @return an iterator that iterates over the items in the bag, arbitrary order
     */
    @Override
    public Iterator<Item> iterator() {
        return new ArrayIterator();
    }

    private void resize(int newCapacity) {
        // new array with increased capacity
        Item[] b = (Item[]) new Object[newCapacity];
        
        // copy from existing array to new array
        for(int i=0; i<numberOfElements; i++) {
            b[i] = a[i];
        }
        
        // point existing to new array
        a = b;
    }

    private class ArrayIterator implements Iterator<Item> {

        private int currentElementIndex = 0;
        
        @Override
        public boolean hasNext() {
            return currentElementIndex < numberOfElements;
        }

        @Override
        public Item next() {
            if(!hasNext()) { throw new NoSuchElementException(); }
            return a[currentElementIndex++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
}
