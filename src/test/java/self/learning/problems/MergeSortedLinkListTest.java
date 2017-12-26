package self.learning.problems;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Harshad Shrishrimal
 */
@RunWith(JUnitPlatform.class)
class MergeSortedLinkListTest {

    @Test
    @DisplayName("Should merge 2 sorted link list")
    void mergeTest() {

        //create the first link list
        MergeSortedLinkList<Integer> first = new MergeSortedLinkList<>();
        first.insert(1);
        first.insert(3);
        first.insert(5);
        first.insert(7);
        first.insert(9);

        //create the second link list
        MergeSortedLinkList<Integer> second = new MergeSortedLinkList<>();
        second.insert(0);
        second.insert(2);
        second.insert(4);
        second.insert(6);
        second.insert(8);
        second.insert(10);
        second.insert(11);

        //method under test
        MergeSortedLinkList<Integer> actualMerged = first.merge(first, second);

        List<Integer> actual = new ArrayList<>();
        for(Integer i : actualMerged) {
            actual.add(i);
        }
        Integer[] expected = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        Assert.assertArrayEquals(expected, actual.toArray());

    }

    @Test
    @DisplayName("Should merge 2 sorted link list. One is Empty")
    void mergeTest1Empty() {

        //create the first link list
        MergeSortedLinkList<Integer> first = new MergeSortedLinkList<>();
        first.insert(1);
        first.insert(3);
        first.insert(5);
        first.insert(7);
        first.insert(9);

        //create the second link list
        MergeSortedLinkList<Integer> second = new MergeSortedLinkList<>();

        //method under test
        MergeSortedLinkList<Integer> actualMerged = first.merge(first, second);

        List<Integer> actual = new ArrayList<>();
        for(Integer i : actualMerged) {
            actual.add(i);
        }
        Integer[] expected = new Integer[]{ 1, 3,  5,  7, 9};

        Assert.assertArrayEquals(expected, actual.toArray());

    }


    @Test
    @DisplayName("Should merge 2 sorted link list. One is null")
    void mergeTest1Null() {

        //create the first link list
        MergeSortedLinkList<Integer> first = new MergeSortedLinkList<>();
        first.insert(1);
        first.insert(3);
        first.insert(5);
        first.insert(7);
        first.insert(9);

        //create the second link list
        MergeSortedLinkList<Integer> second = null;

        //method under test
        MergeSortedLinkList<Integer> actualMerged = first.merge(first, second);

        List<Integer> actual = new ArrayList<>();
        for(Integer i : actualMerged) {
            actual.add(i);
        }
        Integer[] expected = new Integer[]{ 1, 3,  5,  7, 9};

        Assert.assertArrayEquals(expected, actual.toArray());

    }

}
