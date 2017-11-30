package self.learning.algos.bag;

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
class LinkedBagTest {

    @Test
    @DisplayName("Bag should be empty upon creation")
    void bagEmptyUponCreation() throws Exception {
        LinkedBag<String> bagOfString = new LinkedBag<>();
        Assert.assertTrue(bagOfString.isEmpty());
        Assert.assertEquals(0, bagOfString.size());
    }

    @Test
    @DisplayName("Should be able to add items to bag")
    void bagAddElements() throws Exception {
        LinkedBag<String> bag = new LinkedBag<>();
        bag.add("Item1");
        bag.add("Item2");
        bag.add("Item3");
        Assert.assertTrue(!bag.isEmpty());
        Assert.assertEquals(3, bag.size());
    }

    @Test
    @DisplayName("Should be able to iterate over items in bag")
    void iterateOverElements() throws Exception {
        String[] expected = new String[]{"Item3", "Item2", "Item1"};

        LinkedBag<String> bag = new LinkedBag<>();
        bag.add("Item1");
        bag.add("Item2");
        bag.add("Item3");

        String[] actual = new String[3];
        int i = 0;
        for (String s : bag) {
            actual[i++] = s;
        }

        Assert.assertArrayEquals(expected, actual);
    }

}
