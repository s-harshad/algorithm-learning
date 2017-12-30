package self.learning.tree;

import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import self.learning.algos.tree.TreeTraversal;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Harshad Shrishrimal
 */
@RunWith(JUnitPlatform.class)
class TreeTraversalTest {

    @Test
    @DisplayName("Should be able to perform pre-order traversal (recursively)")
    void recursivePreOrder() {

        String[] expected = new String[]{"B","A","C"};

        TreeTraversal<String> tree = new TreeTraversal();
        tree.put("B");
        tree.put("A");
        tree.put("C");

        List<String> actual = new ArrayList<>();

        // method under test
        tree.recursivePreOrder(s -> {
            actual.add(s);
        });

        Assert.assertArrayEquals(expected, actual.toArray());
    }


    @Test
    @DisplayName("Should be able to perform pre-order traversal (non-recursive)")
    void iterativePreOrder() {

        String[] expected = new String[]{"B","A","C"};

        TreeTraversal<String> tree = new TreeTraversal();
        tree.put("B");
        tree.put("A");
        tree.put("C");

        List<String> actual = new ArrayList<>();

        // method under test
        tree.nonRecursivePreOrder(s -> {
            actual.add(s);
        });

        Assert.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    @DisplayName("Should be able to perform in-order traversal (recursively)")
    void recursiveInOrder() {

        String[] expected = new String[]{"A","B","C"};

        TreeTraversal<String> tree = new TreeTraversal();
        tree.put("B");
        tree.put("A");
        tree.put("C");

        List<String> actual = new ArrayList<>();

        // method under test
        tree.recursiveInOrder(s -> {
            actual.add(s);
        });

        Assert.assertArrayEquals(expected, actual.toArray());
    }


    @Test
    @DisplayName("Should be able to perform in-order traversal (non-recursive)")
    void iterativeInOrder() {

        String[] expected = new String[]{"A","B","C"};

        TreeTraversal<String> tree = new TreeTraversal();
        tree.put("B");
        tree.put("A");
        tree.put("C");

        List<String> actual = new ArrayList<>();

        // method under test
        tree.nonRecursiveInOrder(s -> {
            actual.add(s);
        });

        Assert.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    @DisplayName("Should be able to perform post-order traversal (recursively)")
    void recursivePostOrder() {

        String[] expected = new String[]{"A","C","B"};

        TreeTraversal<String> tree = new TreeTraversal();
        tree.put("B");
        tree.put("A");
        tree.put("C");

        List<String> actual = new ArrayList<>();

        // method under test
        tree.recursivePostOrder(s -> {
            actual.add(s);
        });

        Assert.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    @DisplayName("Should be able to perform post-order traversal (non-recursive)")
    void iterativePostOrder() {

        String[] expected = new String[]{"A","C","B"};

        TreeTraversal<String> tree = new TreeTraversal();
        tree.put("B");
        tree.put("A");
        tree.put("C");

        List<String> actual = new ArrayList<>();

        // method under test
        tree.nonRecursivePostOrder(s -> {
            actual.add(s);
        });

        Assert.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    @DisplayName("Should be able to perform post-order traversal (non-recursive)")
    void iterativePostOrder_2() {

        String[] expected = new String[]{"C","B","A"};

        TreeTraversal<String> tree = new TreeTraversal();
        tree.put("A");
        tree.put("B");
        tree.put("C");

        List<String> actual = new ArrayList<>();

        // method under test
        tree.nonRecursivePostOrder(s -> {
            actual.add(s);
        });

        Assert.assertArrayEquals(expected, actual.toArray());
    }


    @Test
    @DisplayName("Should be able to perform post-order traversal (non-recursive)")
    void iterativePostOrder_3() {

        String[] expected = new String[]{"B","A","C"};

        TreeTraversal<String> tree = new TreeTraversal();
        tree.put("C");
        tree.put("A");
        tree.put("B");

        List<String> actual = new ArrayList<>();

        // method under test
        tree.nonRecursivePostOrder(s -> {
            actual.add(s);
        });

        Assert.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    @DisplayName("Should be able to perform post-order traversal (non-recursive)")
    void iterativePostOrder_4() {

        String[] expected = new String[]{"B"};

        TreeTraversal<String> tree = new TreeTraversal();
        tree.put("B");

        List<String> actual = new ArrayList<>();

        // method under test
        tree.nonRecursivePostOrder(s -> {
            actual.add(s);
        });

        Assert.assertArrayEquals(expected, actual.toArray());
    }

    @Test
    @DisplayName("Should be able to perform level order traversal")
    void levelOrderTraversal_1() {

        String[] expected = new String[]{"B","A","C"};

        TreeTraversal<String> tree = new TreeTraversal();
        tree.put("B");
        tree.put("A");
        tree.put("C");

        List<String> actual = new ArrayList<>();

        // method under test
        tree.levelOrderTraversal(s -> {
            actual.add(s);
        });

        Assert.assertArrayEquals(expected, actual.toArray());

    }

    @Test
    @DisplayName("Should be able to perform level order traversal")
    void levelOrderTraversal_2() {

        String[] expected = new String[]{"A","B","C"};

        TreeTraversal<String> tree = new TreeTraversal();
        tree.put("A");
        tree.put("B");
        tree.put("C");

        List<String> actual = new ArrayList<>();

        // method under test
        tree.levelOrderTraversal(s -> {
            actual.add(s);
        });

        Assert.assertArrayEquals(expected, actual.toArray());

    }
}
