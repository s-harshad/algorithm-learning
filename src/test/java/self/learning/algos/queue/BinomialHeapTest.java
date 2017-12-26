package self.learning.algos.queue;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Harshad Shrishrimal
 */
class BinomialHeapTest {

    @Test
    @DisplayName("Heap should be empty upon creation")
    void emptyUponCreation() {
        BinomialHeap<String> heap = new BinomialHeap<>();
        Assert.assertTrue(heap.isEmpty());
        Assert.assertEquals(0, heap.size());
    }

    @Test
    @DisplayName("Should be able to insert in heap")
    void insert() {
        BinomialHeap<String> heap = new BinomialHeap<>();
        heap.insert("A");
        heap.insert("B");
        heap.insert("C");
        heap.insert("D");
        heap.insert("E");
        Assert.assertEquals(5, heap.size());
    }


    @Test
    @DisplayName("Should be able to retrieve the min (without deleting) from the heap")
    void minKey_1() {
        BinomialHeap<String> heap = new BinomialHeap<>();
        heap.insert("A");
        heap.insert("B");
        heap.insert("C");
        heap.insert("D");
        heap.insert("E");
        Assert.assertEquals("A", heap.minKey());
    }

    @Test
    @DisplayName("Should be able to retrieve the min (without deleting) from the heap")
    void minKey_2() {
        BinomialHeap<String> heap = new BinomialHeap<>();
        heap.insert("B");
        heap.insert("C");
        heap.insert("A");
        heap.insert("Z");
        heap.insert("Y");
        Assert.assertEquals("A", heap.minKey());
        Assert.assertEquals("A", heap.minKey());
    }

    @Test
    @DisplayName("Should be able to retrieve and remove the min from the heap")
    void deleteKey_2() {
        BinomialHeap<String> heap = new BinomialHeap<>();
        heap.insert("B");
        heap.insert("C");
        heap.insert("A");
        heap.insert("Z");
        heap.insert("Y");
        Assert.assertEquals("A", heap.deleteMin());
        Assert.assertEquals("B", heap.deleteMin());
        Assert.assertEquals("C", heap.deleteMin());
        Assert.assertEquals("Y", heap.deleteMin());
        Assert.assertEquals("Z", heap.deleteMin());
        Assert.assertTrue(heap.isEmpty());
        Assert.assertEquals(0, heap.size());
    }


    @Test
    @DisplayName("Should be able to iterate the heap, in heap order")
    void IteratorTest() {
        BinomialHeap<String> heap = new BinomialHeap<>();
        heap.insert("B");
        heap.insert("C");
        heap.insert("A");
        heap.insert("Z");
        heap.insert("Y");

        String[] expected = new String[]{"A", "B", "C", "Y", "Z"};

        String[] actual = new String[5];
        int i = 0;

        for (String s : heap) {
            actual[i++] = s;
        }

        Assert.assertArrayEquals(expected, actual);
    }

}
