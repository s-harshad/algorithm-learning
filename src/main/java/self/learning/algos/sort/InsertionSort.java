package self.learning.algos.sort;

/**
 * Implemention of Insertion Sort.
 * To Sort a randomly-ordered array with distinct keys,
 * Inserttion sort uses ~ (1/4)N*N compares and ~ (1/4)N*N exchanges on average
 *
 * @author Harshad Shrishrimal
 */
public class InsertionSort {

    /**
     * Private Constructor. Object creation not allowed.
     */
    private InsertionSort() {
    }

    ;

    /**
     * Sort the Given array using Insertion Sort
     *
     * @param arr array to be sorted.
     * @return sorted array
     */
    public static void sort(Comparable[] arr) {

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (less(arr, j, j - 1)) {
                    exch(arr, j, j - 1);
                } else {
                    break;
                }
            }
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
