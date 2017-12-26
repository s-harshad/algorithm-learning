package self.learning.problems;

import java.util.Iterator;

/**
 * @author Harshad Shrishrimal
 */
public class MergeSortedLinkList<Item extends Comparable<Item>> implements Iterable<Item> {


    /**
     * Inner class to create a Node
     *
     * @param <Item> generic Item
     */
    private static class Node<Item> {
        Item item;
        Node<Item> next;
    }

    // indicates the head of the list
    private Node<Item> head;

    // indicates the end of the list. useful in inserting in O(1)
    private Node<Item> tail;

    /**
     * Instantiate the object.
     */
    public MergeSortedLinkList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Private constructor, used to Instantiate the object from an already existing Link list
     *
     * @param head
     */
    private MergeSortedLinkList(Node<Item> head) {
        this.head = head;
    }

    /**
     * Get the firstNode of the list
     *
     * @return the firstNode of the list
     */
    public Node<Item> getHead() {
        return head;
    }

    /**
     * Add a node to the list
     *
     * @param item
     */
    public void insert(Item item) {

        // Instantiate the node object
        Node n = new Node();
        n.item = item;
        n.next = null;

        // if first node, initialize head, otherwise initialize head->next
        if (head == null) {
            head = n;
            tail = head;
        } else {
            tail.next = n;
            tail = n;
        }

    }

    /**
     * Merge 2 sorted link list.
     * Destructive, Modifies the first and second lists.
     * It's in place merge
     *
     * @param first  first linklist to merge
     * @param second second linklist to merge
     */
    public MergeSortedLinkList merge(MergeSortedLinkList<Item> first, MergeSortedLinkList<Item> second) {

        if (first == null) {
            return second;
        }

        if (second == null) {
            return first;
        }

        Node<Item> m = null; // indicates the start of the merged linked list
        Node<Item> mt = null; // indicates the tail of the merged linked list

        Node<Item> firstListNode = first.getHead();
        Node<Item> secondListNode = second.getHead();


        while (firstListNode != null || secondListNode != null) {

            // first list empty and second list has data.
            // append second list data to the merged list.
            if (firstListNode == null && secondListNode != null) {
                if (m == null) {
                    m = secondListNode;
                    mt = m;
                } else {
                    mt.next = secondListNode;
                }
                secondListNode = null;
            }

            // second list empty and first list has data.
            // append first list data to the merged list.
            if (firstListNode != null && secondListNode == null) {
                if (m == null) {
                    m = firstListNode;
                    mt = m;
                } else {
                    mt.next = firstListNode;
                }
                firstListNode = null;
            }

            // if there is data in both the list
            if (firstListNode != null && secondListNode != null) {


                if (firstListNode.item.compareTo(secondListNode.item) < 0) {
                    // of the 2 list, first node has the smaller Item

                    // initialize head if not yet done. if block only executed once
                    if (m == null) {
                        m = firstListNode;
                        mt = m;
                        firstListNode = firstListNode.next;
                    } else {
                        mt.next = firstListNode;
                        mt = mt.next;
                        firstListNode = firstListNode.next;
                    }
                } else {
                    // of the 2 list, second node has the smaller Item

                    // initialize head if not yet done. if block only executed once
                    if (m == null) {
                        m = secondListNode;
                        mt = m;
                        secondListNode = secondListNode.next;
                    } else {
                        mt.next = secondListNode;
                        mt = mt.next;
                        secondListNode = secondListNode.next;
                    }
                }

            }

        }

        return new MergeSortedLinkList(m);

    }


    /**
     * Returns an iterator for this instance.
     * Used to iterate over all the data in the order in which it was inserted.
     *
     * @return iterator for this instance
     */
    @Override
    public Iterator<Item> iterator() {
        return new MergedLinkListIterator(head);
    }

    /**
     * Iterator for this instanace.
     *
     * @param <Item> generic Item.
     */
    private class MergedLinkListIterator<Item> implements Iterator<Item> {

        private Node<Item> current;

        public MergedLinkListIterator(Node<Item> current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {

            if (!hasNext()) {
                throw new UnsupportedOperationException("Reached the End of list");
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
