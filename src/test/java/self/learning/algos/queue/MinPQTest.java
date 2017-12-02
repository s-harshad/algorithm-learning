package self.learning.algos.queue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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
public class MinPQTest {

    @Test
    @DisplayName("Queue should be empty upon creation")
    void emptyUponCreation() {
        MinPQ<String> queue = new MinPQ<>();
        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("Should be able to insert items to queue")
    void insert() {
        MinPQ<Integer> queue = new MinPQ<>();
        queue.insert(1);
        Assert.assertTrue(!queue.isEmpty());
        Assert.assertEquals(1, queue.size());
    }

    @Test
    @DisplayName("Should be able to retrieve the minimum from queue")
    void min() {
        MinPQ<Integer> queue = new MinPQ<>();
        queue.insert(1);
        queue.insert(2);
        Assert.assertEquals(2, queue.size());
        Assert.assertEquals(1, (int) queue.min());
    }

    @Test
    @DisplayName("Should be able to retrieve and delete the minimum from queue")
    void deleteMin() {
        MinPQ<Integer> queue = new MinPQ<>();
        queue.insert(1);
        queue.insert(2);
        Assert.assertEquals(2, queue.size());
        Assert.assertEquals(1, (int) queue.deleteMin());
        Assert.assertEquals(1, queue.size());
    }

    @Test
    @DisplayName("Should be able to count the number of items in queue")
    void size() {
        MinPQ<Integer> queue = new MinPQ<>();
        queue.insert(2);
        queue.insert(1);
        Assert.assertEquals(2, queue.size());
    }

    @Test
    @DisplayName("Should be able to iterate over the items in ascending order")
    void iterate() {

        final MinPQ<Integer> queue = new MinPQ<>();
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
        Integer[] expected = new Integer[]{0, 1, 1, 2, 4, 6, 8, 9, 10, 14};

        Assert.assertArrayEquals(expected, actual);
    }
}
