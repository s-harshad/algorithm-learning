package self.learning.algos.stack;

import java.util.ArrayList;
import java.util.List;
import org.hamcrest.Matchers;
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
class LinkedStackTest {

    @Test
    @DisplayName("Stack should be empty upon creation")
    void emptyUponCreation() {
        LinkedStack<String> stack = new LinkedStack<>();
        Assert.assertTrue(stack.isEmpty());
        Assert.assertEquals(0, stack.size());
    }

    @Test
    @DisplayName("Should be able to push items to stack")
    void pushItems() {
        LinkedStack<String> stack = new LinkedStack<>();
        stack.push("Item1");
        stack.push("Item2");
        stack.push("Item3");
        Assert.assertTrue(!stack.isEmpty());
        Assert.assertEquals(3, stack.size());
    }

    @Test
    @DisplayName("Should be able to peek items present in stack")
    void peekItems() {
        LinkedStack<String> stack = new LinkedStack<>();
        stack.push("Item1");
        stack.push("Item2");
        stack.push("Item3");

        Assert.assertEquals("Item3", stack.peek());
    }

    @Test
    @DisplayName("Peeking should not remove items from stack")
    void peekShouldNotRemoveItems() {
        LinkedStack<String> stack = new LinkedStack<>();
        stack.push("Item1");
        stack.push("Item2");
        stack.push("Item3");

        Assert.assertTrue(!stack.isEmpty());
        Assert.assertEquals(3, stack.size());
        Assert.assertEquals("Item3", stack.peek());
        Assert.assertEquals("Item3", stack.peek());
        Assert.assertTrue(!stack.isEmpty());
        Assert.assertEquals(3, stack.size());
    }

    @Test
    @DisplayName("Pop should remove and retrieve item from top of stack")
    void pop() {
        LinkedStack<String> stack = new LinkedStack<>();
        stack.push("Item1");
        stack.push("Item2");
        stack.push("Item3");

        Assert.assertTrue(!stack.isEmpty());
        Assert.assertEquals(3, stack.size());

        Assert.assertEquals("Item3", stack.pop());

        Assert.assertTrue(!stack.isEmpty());
        Assert.assertEquals(2, stack.size());

        Assert.assertEquals("Item2", stack.pop());

        Assert.assertTrue(!stack.isEmpty());
        Assert.assertEquals(1, stack.size());

        Assert.assertEquals("Item1", stack.pop());

        Assert.assertTrue(stack.isEmpty());
        Assert.assertEquals(0, stack.size());

        try {
            stack.pop();
            Assert.fail();
        } catch (Exception ex) {
            // Stack is empty
        }
    }

    @Test
    @DisplayName("Interleaved Push Pop operations")
    void pushPopInterleaved() {

        LinkedStack<String> stack = new LinkedStack<>();

        stack.push("Item1");

        Assert.assertTrue(!stack.isEmpty());
        Assert.assertEquals(1, stack.size());

        Assert.assertEquals("Item1", stack.pop());

        Assert.assertTrue(stack.isEmpty());
        Assert.assertEquals(0, stack.size());

        stack.push("Item1");

        Assert.assertTrue(!stack.isEmpty());
        Assert.assertEquals(1, stack.size());

        Assert.assertEquals("Item1", stack.pop());

        Assert.assertTrue(stack.isEmpty());
        Assert.assertEquals(0, stack.size());
    }

    @Test
    @DisplayName("Should be able to iterate over the stack in LIFO order")
    void iterateOverElements() {
        LinkedStack<String> stack = new LinkedStack<>();
        stack.push("Item1");
        stack.push("Item2");
        stack.push("Item3");

        List<String> actuals = new ArrayList<>();
        for (String s : stack) {
            actuals.add(s);
        }

        Assert.assertThat(actuals, Matchers.containsInAnyOrder("Item1", "Item2", "Item3"));

    }
}
