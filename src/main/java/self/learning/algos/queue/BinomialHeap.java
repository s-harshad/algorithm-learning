package self.learning.algos.queue;


import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Implemenation of Binomial Heap
 * <p>
 * The insert, delete-the-minimum, union, min-key
 * and size operations take logarithmic time.
 * The is-empty and constructor operations take constant time.
 * </p>
 *
 * @param <Key> Generic key.
 * @author Harshad Shrishrimal
 */
public class BinomialHeap<Key extends Comparable<Key>> implements Iterable<Key> {

    private Node<Key> head; // Point to the root of the binomial tree

    public BinomialHeap() {
    }

    /**
     * Returns the number of elements in the Heap
     *
     * @return the number of elements in the Heap
     */
    public int size() {
        int result = 0;
        int tmp;
        for (Node node = this.head; node != null; node = node.sibiling) {
            if (node.order > 30) {
                throw new ArithmeticException("The number of elements cannot be evaluated, but the priority queue is still valid.");
            }
            tmp = 1 << node.order; // equivalent to Math.pow(2, node.order)
            result += tmp;
        }
        return result;
    }

    /**
     * Is the Binomial heap really empty
     *
     * @return {@code true} if no elements are present, {@code false} otherwise
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Inserts the given key in the Heap.
     * Worst case is O(log(n))
     *
     * @param key a Key
     */
    public void insert(Key key) {

        // Create the Node object
        Node<Key> n = new Node();
        n.key = key;
        n.order = 0;
        n.sibiling = null;
        n.child = null;

        // Create the temp heap with the node.
        BinomialHeap<Key> H = new BinomialHeap();
        H.head = n;

        this.head = this.union(H).head;
    }

    /**
     * Union of 2 binomial heaps.
     *
     * @param h the heap to be merged with this heap
     * @return merged binomial heap
     */
    private BinomialHeap<Key> union(BinomialHeap<Key> h) {

        if (h == null) throw new IllegalArgumentException("Cannot Merge a Binomial Heap with null");

        this.head = merge(this, h);

        // mergedHeap may contain 2 nodes with same order.
        // need to combine them make one node the child of the other.
        Node<Key> prevx = null;
        Node<Key> x = head;
        Node<Key> nextx = x.sibiling;

        /*
            Rule 1 : if order of x and nextx is not the same; march pointers 1 position down

            Rule 2 : if order of x, nextx and nextx.sibiling are the same; march pointers 1 position down

            Rule 3 : if order of x & nextx is equal and next.sibiling is not equal then
                     Compare keys of x & nextx and make the node with greater key the child of the other.
         */

        while (nextx != null) {
            if (x.order < nextx.order || (nextx.sibiling != null && x.order == nextx.sibiling.order)) {
                prevx = x;
                x = nextx;
            } else if (greater(nextx.key, x.key)) {
                x.sibiling = nextx.sibiling;
                link(nextx, x); // x becomes the parent and nextx becomes the leftmost child of x
            } else {
                if (prevx == null) {
                    this.head = nextx;
                } else {
                    prevx.sibiling = nextx;
                }
                link(x, nextx);
                x = nextx;
            }
            nextx = x.sibiling;
        }


        return this;
    }

    /**
     * Make one node the root of the other.
     * In this case 'root2' becomes the parent of 'root1'
     * 'root1' becomes the left most child of 'root2'
     *
     * @param root1
     * @param root2
     */
    private void link(Node<Key> root1, Node<Key> root2) {
        root1.sibiling = root2.child;
        root2.child = root1;
        root2.order++;
    }

    /**
     * Return true, is key1 is greater then key2
     *
     * @param key1 key to compare
     * @param key2 key to compare
     * @return {@code true} if key1 is greater then key2; {@code false} otherwise
     */
    private boolean greater(Key key1, Key key2) {
        if (key1 == null) return false;
        if (key2 == null) return true;
        return key1.compareTo(key2) > 0;
    }

    /**
     * Merges root lists of two Binomial heaps together into a single link list, in increasing order of degree.
     * In the merged list, we will have at-most 2 nodes with the same degree
     *
     * @param heap1 first Binomial Heap to merge
     * @param heap2 second Binomial Heap to merge
     * @return merged root lists of the two Binomial heaps
     */
    private Node<Key> merge(BinomialHeap<Key> heap1, BinomialHeap<Key> heap2) {

        //if the 2nd heap doesn't exists return the first heap
        if (heap2.head == null) {
            return heap1.head;
        }

        //if the 1st heap doesn't exists return the second heap
        if (heap1.head == null) {
            return heap2.head;
        }

        // indicates the beginning of the merged list.
        Node<Key> head = null;

        Node<Key> heap1ptr = heap1.head;
        Node<Key> heap2prt = heap2.head;

        // let's initialize the head of our new merged list
        if (heap1ptr.order < heap2prt.order) {
            head = heap1ptr;
            heap1ptr = heap1ptr.sibiling; // move the pointer to next sibling
        } else {
            head = heap2prt;
            heap2prt = heap2prt.sibiling; // move the pointer to next sibling
        }

        // indicates the tail, tail.sibiling with point to the next node in the merged list.
        Node<Key> tail = head;

        // while both the root lists have data, compare the orders and merge in increasing order.
        while (heap1ptr != null && heap2prt != null) {
            if (heap1ptr.order < heap2prt.order) {
                tail.sibiling = heap1ptr;
                heap1ptr = heap1ptr.sibiling;
            } else {
                tail.sibiling = heap2prt;
                heap2prt = heap2prt.sibiling;
            }
            tail = tail.sibiling; // move tail to the end of the list.
        }

        // Add the remaining entries from one of the 2 lists our new merged lists.
        if (heap1ptr != null) {
            tail.sibiling = heap1ptr;
        } else {
            tail.sibiling = heap2prt;
        }

        return head;
    }

    /**
     * Get the minimum key currently in the queue
     *
     * @return
     */
    public Key minKey() {

        if (this.head == null) {
            return null;
        }

        Node<Key> min = this.head;
        Node<Key> next = min.sibiling;
        while (next != null) {
            if (greater(min.key, next.key)) {
                min = next;
            }
            next = next.sibiling;
        }
        return min.key;
    }

    /**
     * Deletes the minimum key
     * Worst case is O(log(n))
     *
     * @return the minimum key
     * @throws NoSuchElementException if the priority queue is empty
     */
    public Key deleteMin() throws NoSuchElementException {

        if (isEmpty()) {
            throw new NoSuchElementException("Cannot delete from empty heap");
        }

        //find the minimum Node and remove it from the heap
        Node<Key> min = eraseMin();

        // if the minimun node has childrens, the child nodes are then reversed to form another heap.
        Node<Key> x = (min.child == null) ? min : min.child;

        //reverse the link list
        if (min.child != null) {
            Node<Key> prev = null;
            Node<Key> next = x.sibiling;

            while (next != null) {
                x.sibiling = prev;
                prev = x;
                x = next;
                next = next.sibiling;
            }
            x.sibiling = prev;

            // Remove child pointer from min. it's now a single node with no pointers.
            min.child = null;

            BinomialHeap<Key> h = new BinomialHeap();
            h.head = x;

            this.head = this.union(h).head;
        }

        min.sibiling = null;
        return min.key;
    }

    /**
     * Deletes and return the node containing the minimum key
     *
     * @return the node containing the minimum key
     */
    private Node<Key> eraseMin() {

        // Find the Node with the Minimum key in the root list.
        // min points to the Node with minimum key
        // also remove the node from the root list.
        // this is done by keep a track of the previous node and updating it's sibling.

        Node<Key> prev = null;
        Node<Key> min = this.head;
        Node<Key> current = head.sibiling;
        while (current != null) {
            if (greater(min.key, current.key)) {
                prev = min;
                min = current;
            }
            current = current.sibiling;
        }

        // remove node from the root list

        if (prev != null) {
            prev.sibiling = min.sibiling;
        }

        // update head, if head is the minimum node.
        if (min == head) {
            head = min.sibiling;
        }

        return min;
    }


    @Override
    public Iterator<Key> iterator() {
        return new BinomialHeapIterator();
    }

    /**
     * Represents a Node of a Binomial Tree
     *
     * @param <Key> generic key
     */
    private static class Node<Key> {
        Key key; // the item the node contains.
        int order; // the order of the Binomial Tree rooted by this Node
        Node<Key> sibiling; //points to other nodes on the same level
        Node<Key> child; // points to the children of this node.
    }

    /**
     * Iterator
     */
    private class BinomialHeapIterator implements Iterator<Key> {

        BinomialHeap<Key> data;

        public BinomialHeapIterator() {
            data = new BinomialHeap<>();
            data.head = clone(head, null);
        }

        private Node<Key> clone(Node<Key> x, Node<Key> parent) {

            //exit condition
            if (x == null) {
                return null;
            }

            Node n = new Node();
            n.key = x.key;
            n.order = x.order;
            n.sibiling = clone(x.sibiling, parent);
            n.child = clone(x.child, n);
            return n;
        }

        @Override
        public boolean hasNext() {
            return !data.isEmpty();
        }

        @Override
        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return data.deleteMin();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
