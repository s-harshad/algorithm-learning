package self.learning.algos.queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.junit.Assert;

/**
 *
 * @author Harshad Shrishrimal
 */
@RunWith(JUnitPlatform.class)
class MaxPQTest {

    @Test
    @DisplayName("Queue should be empty upon creation")
    void emptyUponCreation() {
        MaxPQ<String> queue = new MaxPQ<>();
        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("Should be able to insert items to queue")
    void insert() {
        MaxPQ<Integer> queue = new MaxPQ<>();
        queue.insert(1);
        Assert.assertTrue(!queue.isEmpty());
        Assert.assertEquals(1, queue.size());
    }

    @Test
    @DisplayName("Should be able to retrieve the maximum from queue")
    void max() {
        MaxPQ<Integer> queue = new MaxPQ<>();
        queue.insert(1);
        queue.insert(2);
        Assert.assertEquals(2, queue.size());
        Assert.assertEquals(2, (int) queue.max());
    }

    @Test
    @DisplayName("Should be able to retrieve and delete the maximum from queue")
    void deleteMax() {
        MaxPQ<Integer> queue = new MaxPQ<>();
        queue.insert(1);
        queue.insert(2);
        Assert.assertEquals(2, queue.size());
        Assert.assertEquals(2, (int) queue.deleteMax());
        Assert.assertEquals(1, queue.size());
    }

    @Test
    @DisplayName("Should be able to count the number of items in queue")
    void size() {
        MaxPQ<Integer> queue = new MaxPQ<>();
        queue.insert(2);
        queue.insert(1);
        Assert.assertEquals(2, queue.size());
    }

    @Test
    @DisplayName("Should be able to iterate over the items in descending order")
    void iterate() {

        final MaxPQ<Integer> queue = new MaxPQ<>();
        queue.insert(1);
        queue.insert(10);
        queue.insert(2);
        queue.insert(9);
        queue.insert(8);
        queue.insert(6);
        queue.insert(14);
        queue.insert(4);
        queue.insert(0);
        queue.insert(1);

        List<Integer> received = new ArrayList<>(10);
        for (Iterator<Integer> iterator = queue.iterator(); iterator.hasNext();) {
            Integer next = iterator.next();
            received.add(next);
        }

        Integer[] actual = received.toArray(new Integer[received.size()]);
        Integer[] expected = new Integer[]{14, 10, 9, 8, 6, 4, 2, 1, 1, 0};

        Assert.assertArrayEquals(expected, actual);
    }
}
