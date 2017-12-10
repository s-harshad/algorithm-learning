package self.learning.algos.sort;

/**
 * Implementation of selection sort
 * Selection sort uses (N*N)/2 compares and N exchanges
 *
 * @author Harshad Shrishrimal
 */
public class SelectionSort {

    /**
     * Private Constructor. Object creation not allowed.
     */
    private SelectionSort() {
    }

    /**
     * Sort the given array using Selection Sort
     *
     * @param arr array to be sorted.
     * @return sorted array
     */
    public static void sort(Comparable[] arr) {
        int N = arr.length;
        // At each iteration of 'i' find the min in the remaining and exchange if necessary
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++) {
                if (less(arr, j, min)) {
                    min = j;
                }
            }
            exch(arr, i, min);
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
        return arr[first].compareTo(arr[second]) < 0;
    }
}
