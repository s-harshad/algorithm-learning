package self.learning.algos.sort;

/**
 * The {@code Heapsort} class provides a static methods for heap sorting an
 * array.
 *
 * @author Harshad Shrishrimal
 */
public class Heapsort {

    /**
     * Rearranges the array in ascending order, using the natural order.
     *
     * @param pq the array to be sorted
     */
    public static void sort(Comparable[] pq) {
        int n = pq.length; // number of elements in array

        //build the heap
        for (int i = n / 2; i >= 1; i--) {
            sink(pq, i, n);
        }

        // exchange first element with the last.
        while (n > 1) {
            exch(pq, 1, n--);
            sink(pq, 1, n);
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
     * @param numberOfElements number of keys to consider when sinking
     */
    private static void sink(Comparable[] pq, int idx, int numberOfElements) {
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
     * second key; {@code false} otherwise. For the given values of {@code  i}
     * and {@code  j} we subtract 1, to support 1-based indexing. Indices are
     * "off-by-one" to support 1-based indexing.
     *
     * @param c array which has all the elements/keys
     * @param i 1st index of the element which will be used in compare
     * @param j 2nd index of the element which will be used in compare
     * @return {@code true} if key in 'i' is less than key in 'j'
     */
    private static boolean less(Comparable[] c, int i, int j) {
        return c[i - 1].compareTo(c[j - 1]) < 0;
    }

    /**
     * Swap the position of 2 objects. For the given values of {@code  i} and
     * {@code  j} we subtract 1, to support 1-based indexing. Indices are
     * "off-by-one" to support 1-based indexing.
     *
     * @param c array which has all the elements/keys
     * @param i 1st index
     * @param j 2nd index
     */
    private static void exch(Comparable[] c, int i, int j) {
        Comparable temp = c[i - 1];
        c[i - 1] = c[j - 1];
        c[j - 1] = temp;
    }

}
