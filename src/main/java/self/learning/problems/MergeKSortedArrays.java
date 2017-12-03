package self.learning.problems;

import java.util.PriorityQueue;

/**
 * Given K sorted arrays, class {@code MergeKSortedArrays} has a static method
 * that will merge them in a single array using priority queue.
 *
 * @author Harshad Shrishrimal
 */
public class MergeKSortedArrays {

    /**
     * Helper class for merging k sorted arrays.
     *
     * @param <Key>
     */
    private static class QNode<Key extends Comparable<Key>>
            implements Comparable<QNode<Key>> {

        int indexOfArray; // track from which array value is taken
        int indexOfValueInArray; // index of value in the array
        Key value; // value itself

        public QNode(int indexOfArray, int indexOfValueInArray, Key value) {
            this.indexOfArray = indexOfArray;
            this.indexOfValueInArray = indexOfValueInArray;
            this.value = value;
        }

        @Override
        public int compareTo(QNode<Key> o) {
            return value.compareTo(o.value);
        }

    }

    /**
     * Merge K sorted arrays using Priority Queue
     *
     * @param <Key> generic key
     * @param kSortedArrays the arrays to be merged
     * @return the merged sorted array
     */
    public static <Key extends Comparable<Key>> Object[] merge(Key[][] kSortedArrays) {

        PriorityQueue<QNode<Key>> pq = new PriorityQueue<>();

        //find the size of our result
        //place the 1st element from each array in a Priority Queue
        int size = 0;
        for (int i = 0; i < kSortedArrays.length; i++) {
            size += kSortedArrays[i].length;
            pq.add(new QNode(i, 0, kSortedArrays[i][0]));
        }

        //array that will hold our merged content
        Object[] result = new Object[size];

        for (int i = 0; !pq.isEmpty(); i++) {

            //retrieve the min from PQ.
            QNode node = pq.poll();

            // find to which array our node belongs too.
            // and insert the next element from that array in queue
            if (kSortedArrays[node.indexOfArray].length > node.indexOfValueInArray + 1) {
                pq.add(new QNode(node.indexOfArray, node.indexOfValueInArray + 1,
                        kSortedArrays[node.indexOfArray][node.indexOfValueInArray + 1]));
            }

            result[i] = (Key) node.value;
        }

        return result;
    }

}
