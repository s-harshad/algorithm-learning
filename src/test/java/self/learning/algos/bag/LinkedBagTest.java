package self.learning.algos.bag;

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
public class LinkedBagTest {

    @Test
    @DisplayName("Bag should be empty upon creation")
    public void bagEmptyUponCreation() throws Exception {
        LinkedBag<String> bagOfString = new LinkedBag<>();
        Assert.assertTrue(bagOfString.isEmpty());
        Assert.assertEquals(0, bagOfString.size());
    }

    @Test
    @DisplayName("Should be able to add items to bag")
    public void bagAddElements() throws Exception {
        LinkedBag<String> bag = new LinkedBag<>();
        bag.add("Item1");
        bag.add("Item2");
        bag.add("Item3");
        Assert.assertTrue(!bag.isEmpty());
        Assert.assertEquals(3, bag.size());
    }

    @Test
    @DisplayName("Should be able to iterate over items in bag")
    public void iterateOverElements() throws Exception {
        LinkedBag<String> bag = new LinkedBag<>();
        bag.add("Item1");
        bag.add("Item2");
        bag.add("Item3");

        List<String> actuals = new ArrayList<>();
        for (String s : bag) {
            actuals.add(s);
        }

        Assert.assertThat(actuals, Matchers.containsInAnyOrder("Item1", "Item2", "Item3"));
    }

}
