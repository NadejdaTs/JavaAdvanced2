package p01_Database;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.management.openmbean.OpenMBeanOperationInfoSupport;
import javax.naming.OperationNotSupportedException;

public class DatabaseTest {

    private Database database;
    private static final Integer[] NUMBERS = {5, 8, 20, 67, 98};

    @Before
    public void prepareDatabase() throws OperationNotSupportedException {
        database = new Database(NUMBERS);
    }

    @Test
    public void testConstrHasToCreateValidObject(){
        Integer[] databaseElements = database.getElements();
        Assert.assertArrayEquals(NUMBERS, databaseElements);
        for (int i = 0; i < databaseElements.length; i++) {
            Assert.assertEquals(NUMBERS[i], databaseElements[i]);
        }
    }

    @Test (expected = OperationNotSupportedException.class)
    public void testConstThrowsErrWithMoreElements() throws OperationNotSupportedException {
        Integer[] arrOfNumbers = new Integer[17];
        new Database(arrOfNumbers);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void testConstThrowsErrWithZeroElements() throws OperationNotSupportedException {
        Integer[] arrOfNumbers = new Integer[0];
        new Database(arrOfNumbers);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void testAddThrowExceptionIfParamNull() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void testAddElementCorrectly() throws OperationNotSupportedException {
        database.add(15);

        Assert.assertEquals(NUMBERS.length + 1, database.getElements().length);
        int lastElement  = database.getElements().length - 1;
        Assert.assertEquals(Integer.valueOf(15), database.getElements()[lastElement]);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void testRemoveThrowsWithEmptyData() throws OperationNotSupportedException {
        for (int i = 0; i < NUMBERS.length; i++) {
            database.remove();
        }
        database.remove();
    }

    @Test
    public void testRemoveLastArray() throws OperationNotSupportedException {
        database.remove();
        Integer[] elements = database.getElements();
        int length = elements.length - 1;
        Assert.assertEquals( NUMBERS.length - 1, elements.length);
        Assert.assertEquals(Integer.valueOf(67) , elements[length]);
    }
}