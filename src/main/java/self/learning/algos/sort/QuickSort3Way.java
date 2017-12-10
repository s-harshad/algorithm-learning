package self.learning.algos.sort;

import java.util.Random;

/**
 * 3-Way Quick Sort implementation.
 *
 * @author Harshad Shrishrimal
 */
public class QuickSort3Way {

    // private constructor. Object cannot be instantiated
    private QuickSort3Way() {
    }

    /**
     * Sort the array with 3-way quick sort algo
     *
     * @param arr array to be sorted
     */
    public static void sort(Comparable[] arr) {
        // shuffle the array
        shuffle(arr);
        sort(arr, 0, arr.length - 1);
    }

    // quicksort the subarray arr[lo .. hi] using 3-way partitioning
    private static void sort(Comparable[] arr, int lo, int hi) {

        // exit condition for recursive calls.
        if (lo >= hi) {
            return;
        }

        // partition using 3 way
        // arr[lo..lt-1] < v = arr[lt..gt] < arr[gt+1..hi]
        int lt = lo;
        int gt = hi;
        Comparable v = arr[lo];
        for (int i = lo; i <= gt; ) {
            int cmp = arr[i].compareTo(v);
            if (cmp < 0) {
                exch(arr, lt++, i++);
            } else if (cmp > 0) {
                exch(arr, i, gt--);
            } else {
                i++;
            }
        }
        // sort the sub arrays.
        sort(arr, lo, lt - 1);
        sort(arr, gt + 1, hi);

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

}
