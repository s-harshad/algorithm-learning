package self.learning.algos.sort;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
/**
 * @author Harshad Shrishrimal
 */
@RunWith(JUnitPlatform.class)
class QuickSortTest {

    @Test
    @DisplayName("Should be able to sort in ascending order")
    void shuffleTest() {
        Integer[] array = new Integer[]{14, 10, 9, 8, 6, 4, 2, 1, 1, 0};
        Integer[] expected = new Integer[]{0, 1, 1, 2, 4, 6, 8, 9, 10, 14};
        QuickSort.sort(array);
        Assert.assertArrayEquals(expected, array);
    }
}
