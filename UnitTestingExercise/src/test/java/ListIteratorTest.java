import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p03_IteratorTest.ListIterator;

import javax.naming.OperationNotSupportedException;
import java.util.Arrays;


public class ListIteratorTest {

    private ListIterator listIterator = null;
    private static final String[] DATA = {"Desi", "Ivo", "Pesho"};

    @Before
    public void setUp() throws OperationNotSupportedException {
        listIterator = new ListIterator(DATA);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorWhenElementIsNull() throws OperationNotSupportedException {
        new ListIterator(null);
    }

    @Test
    public void testHasNextAndMove(){
        Assert.assertTrue(listIterator.hasNext());
        Assert.assertTrue(listIterator.move());

        Assert.assertTrue(listIterator.hasNext());
        Assert.assertTrue(listIterator.move());

        Assert.assertFalse(listIterator.hasNext());
        Assert.assertFalse(listIterator.move());
    }

    @Test(expected = IllegalStateException.class)
    public void testPrintEmptyList() throws OperationNotSupportedException {
        ListIterator listIteratorNew = new ListIterator();
        listIteratorNew.print();
    }

    @Test
    public void testPrintCorrectly(){
        int index = 0;
        while(listIterator.hasNext()){
            Assert.assertEquals(listIterator.print(), DATA[index]);
            index++;
            listIterator.move();
        }
    }
}
