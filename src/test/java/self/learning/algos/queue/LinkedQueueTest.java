package self.learning.algos.queue;

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
class LinkedQueueTest {

    @Test
    @DisplayName("Queue should be empty upon creation")
    void emptyUponCreation() {
        LinkedQueue<String> queue = new LinkedQueue<>();
        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("Should be able to enqueue items to queue")
    void pushItems() {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("Item1");
        queue.enqueue("Item2");
        queue.enqueue("Item3");
        Assert.assertTrue(!queue.isEmpty());
        Assert.assertEquals(3, queue.size());
    }

    @Test
    @DisplayName("Should be able to peek items present in queue")
    void peekItems() {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("Item1");
        queue.enqueue("Item2");
        queue.enqueue("Item3");
        Assert.assertEquals("Item1", queue.peek());
    }

    @Test
    @DisplayName("Peeking should not remove items from queue")
    void peekShouldNotRemoveItems() {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("Item1");
        queue.enqueue("Item2");
        queue.enqueue("Item3");
        Assert.assertTrue(!queue.isEmpty());
        Assert.assertEquals(3, queue.size());
        Assert.assertEquals("Item1", queue.peek());
        Assert.assertEquals("Item1", queue.peek());
        Assert.assertTrue(!queue.isEmpty());
        Assert.assertEquals(3, queue.size());
    }

    @Test
    @DisplayName("Dequeue should remove and retrieve item from top of stack")
    void deque() {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("Item1");
        queue.enqueue("Item2");
        queue.enqueue("Item3");

        Assert.assertTrue(!queue.isEmpty());
        Assert.assertEquals(3, queue.size());

        Assert.assertEquals("Item1", queue.dequeue());

        Assert.assertTrue(!queue.isEmpty());
        Assert.assertEquals(2, queue.size());

        Assert.assertEquals("Item2", queue.dequeue());

        Assert.assertTrue(!queue.isEmpty());
        Assert.assertEquals(1, queue.size());

        Assert.assertEquals("Item3", queue.dequeue());

        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(0, queue.size());

        try {
            queue.dequeue();
            Assert.fail();
        } catch (Exception ex) {
            // Queue is empty
        }
    }

    @Test
    @DisplayName("Interleaved Enqueue Dequeue operations")
    void interleaved() {

        LinkedQueue<String> queue = new LinkedQueue<>();

        queue.enqueue("Item1");

        Assert.assertTrue(!queue.isEmpty());
        Assert.assertEquals(1, queue.size());

        Assert.assertEquals("Item1", queue.dequeue());

        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(0, queue.size());

        queue.enqueue("Item1");

        Assert.assertTrue(!queue.isEmpty());
        Assert.assertEquals(1, queue.size());

        Assert.assertEquals("Item1", queue.dequeue());

        Assert.assertTrue(queue.isEmpty());
        Assert.assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("Should be able to iterate over queue in FIFO order")
    void iterateOverElements() {
        LinkedQueue<String> queue = new LinkedQueue<>();
        queue.enqueue("Item1");
        queue.enqueue("Item2");
        queue.enqueue("Item3");

        String[] expected = new String[]{"Item1", "Item2", "Item3"};

        String[] actual = new String[3];
        int i = 0;

        for (String s : queue) {
            actual[i++] = s;
        }

        Assert.assertArrayEquals(expected, actual);
    }
}
