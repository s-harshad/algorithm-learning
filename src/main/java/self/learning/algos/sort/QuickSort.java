package self.learning.algos.sort;

import java.util.Random;

/**
 * Implementation of Quick sort algorithm
 *
 * @author Harshad Shrishrimal
 */
public class QuickSort {

    // private constructor. Object cannot be instantiated
    private QuickSort() {
    }

    /**
     * Sort the array with quick sort algo.
     * @param arr array to be sorted.
     */
    public static void sort(Comparable[] arr) {
        // shuffle the array
        QuickSort.shuffle(arr);
        // Sort the entire array
        sort(arr, 0, arr.length - 1);
    }

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(Comparable[] arr, int lo, int hi) {
        // exit condition
        if (lo >= hi) {
            return;
        }
        int j = partition(arr, lo, hi);
        // sort left of partition
        sort(arr, lo, j - 1);
        // sort right of partition
        sort(arr, j + 1, hi);
    }

    /**
     * partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
     * and return the index j
     *
     * @param arr array to be partitioned
     * @param lo  start index of array
     * @param hi  end index of array
     * @return correct index position of the partition element
     */
    private static int partition(Comparable[] arr, int lo, int hi) {
        int i = lo;
        int j = hi + 1;

        while (true) {
            // increment i, till we find key greater than arr[lo]
            while (less(arr, ++i, lo)) {
                if (i == hi) break;
            }

            // decrement j, till we find key less than arr[lo]
            while (less(arr, lo, --j)) {
                if (j == lo) break;
            }

            // break the loop if the index cross
            if (i >= j) break;

            exch(arr, i, j);
        }
        // put the partitioning item in its place
        exch(arr, lo, j);

        return j;
    }

    /**
     * Shuffle the array
     *
     * @param arr array to be shuffled
     */
    private static void shuffle(Comparable[] arr) {
        Random random = new Random();
        int N = arr.length;
        // In each iteration 'i', generate a Random number between 0 & i, and exchange
        for (int i = 0; i < N; i++) {
            int r = random.nextInt(i + 1); // random between [0,i]
            exch(arr, r, i);
        }
    }

    /**
     * Swap 2 elements
     *
     * @param arr    array which has the elements
     * @param index1 index of first element
     * @param index2 index of second element
     */
    private static void exch(Comparable[] arr, int index1, int index2) {
        Comparable temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }

    /**
     * Compare 2 elements and return {@code true} if the element represented by {@code first} index,
     * is less than the element represented by {@code second} index; {@code false} otherwise
     *
     * @param arr    array of elements of which 2 will be compared
     * @param first  index of first element
     * @param second index of second element
     * @return {@code true} if the element represented by {@code first} index, is less than the element represented by {@code second} index; {@code false} otherwise
     */
    private static boolean less(Comparable[] arr, int first, int second) {
        if (arr[first].compareTo(arr[second]) == 0) return false;
        return arr[first].compareTo(arr[second]) < 0;
    }

}
