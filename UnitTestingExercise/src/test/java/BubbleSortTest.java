import org.junit.Assert;
import org.junit.Test;
import p04_BubbleSortTest.Bubble;

public class BubbleSortTest {

    @Test
    public void testSort(){
        int[] numbers = {3, 44, 38, 5, 47, 15};
        Bubble.sort(numbers);
        int[] expectedArray = {3, 5, 15, 38, 44, 47};

        Assert.assertEquals(expectedArray.length, numbers.length);
        Assert.assertArrayEquals(expectedArray, numbers);
    }
}
