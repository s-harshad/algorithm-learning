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
public class ResizingArrayBagTest {

    @Test
    @DisplayName("Bag should be empty upon creation")
    public void bagEmptyUponCreation() throws Exception {
        ResizingArrayBag<String> bagOfString = new ResizingArrayBag<>();
        Assert.assertTrue(bagOfString.isEmpty());
        Assert.assertEquals(0, bagOfString.size());
    }

    @Test
    @DisplayName("Should be able to create a bag with initial capacity")
    public void bagCreationWithUserSepcifiedInitialCapacity() {

        ResizingArrayBag<String> bagOfString = new ResizingArrayBag<>(3);
        bagOfString.add("Item1");
        bagOfString.add("Item2");
        bagOfString.add("Item3");

        Assert.assertEquals(3, bagOfString.size());

        List<String> actuals = new ArrayList<>();
        for (String s : bagOfString) {
            actuals.add(s);
        }

        Assert.assertThat(actuals, Matchers.containsInAnyOrder("Item1", "Item2", "Item3"));
    }

    @Test
    @DisplayName("Should be able to iterate over items in bag")
    public void retrieveElementsFromBag_1() throws Exception {

        ResizingArrayBag<String> bagOfString = new ResizingArrayBag<>();
        bagOfString.add("Item1");
        bagOfString.add("Item2");
        bagOfString.add("Item3");

        Assert.assertEquals(3, bagOfString.size());

        List<String> actuals = new ArrayList<>();
        for (String s : bagOfString) {
            actuals.add(s);
        }

        Assert.assertThat(actuals, Matchers.containsInAnyOrder("Item1", "Item2", "Item3"));
    }

}
