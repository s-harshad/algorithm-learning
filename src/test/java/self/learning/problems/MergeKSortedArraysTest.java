package self.learning.problems;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

/**
 *
 * @author Harshad Shrishrimal
 */
@RunWith(JUnitPlatform.class)
class MergeKSortedArraysTest {

    @Test
    @DisplayName("Should merge 2 sorted arrays of Integers of same length ")
    void mergeIntegerTest() {
        Integer[][] data = new Integer[][]{{1, 3}, {2, 4}};

        Object[] actual = MergeKSortedArrays.merge(data);
        Integer[] expected = new Integer[]{1, 2, 3, 4};

        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    @DisplayName("Should merge 2 sorted arrays of Integers of different length ")
    void mergeIntegerDiffLengthTest() {
        Integer[][] data = new Integer[][]{{1, 3}, {2, 4, 7}};

        Object[] actual = MergeKSortedArrays.merge(data);
        Integer[] expected = new Integer[]{1, 2, 3, 4, 7};

        Assert.assertArrayEquals(expected, actual);
    }
}
