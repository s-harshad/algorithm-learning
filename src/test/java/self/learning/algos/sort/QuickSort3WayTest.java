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
class QuickSort3WayTest {
    @Test
    @DisplayName("Should be able to sort in ascending order")
    void shuffleTest() {
        Integer[] array = new Integer[]{1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3,1,2,3};
        Integer[] expected = new Integer[]{1,1,1,1,1,1,1,2,2,2,2,2,2,2,3,3,3,3,3,3,3};
        QuickSort3Way.sort(array);
        Assert.assertArrayEquals(expected, array);
    }
}
