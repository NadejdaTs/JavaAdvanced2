import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import p02_ExtendedDatabase.Database;
import p02_ExtendedDatabase.Person;

import javax.naming.OperationNotSupportedException;

public class DatabaseExtendedTest {
    private Database database;
    private Person person = new Person(123, "Pesho");

    @Before
    public void prepareDatabase() throws OperationNotSupportedException {
        database = new Database(person);
    }

    @Test
    public void testConstrHasToCreateValidObject(){
        Person[] databaseElements = database.getElements();
        Assert.assertArrayEquals(database.getElements(), databaseElements);
        for (int i = 0; i < databaseElements.length; i++) {
            Assert.assertEquals(database.getElements()[i], databaseElements[i]);
        }
    }

    @Test (expected = OperationNotSupportedException.class)
    public void testConstThrowsErrWithMoreElements() throws OperationNotSupportedException {
        Person[] arrOfNumbers = new Person[17];
        new Database(arrOfNumbers);
    }

    @Test (expected = OperationNotSupportedException.class)
    public void testConstThrowsErrWithZeroElements() throws OperationNotSupportedException {
        Person[] arrOfNumbers = new Person[0];
        new Database(arrOfNumbers);
    }
    @Test
    public void testAdd() throws OperationNotSupportedException {
        Person newPerson = new Person(222, "Pipi");
        int personInDB = database.getElements().length;
        database.add(newPerson);
        Assert.assertEquals(personInDB + 1, database.getElements().length);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddIfThereAreMultipleUsersWithThisId() throws OperationNotSupportedException {
        Person personWithTheSameId = new Person(123, "Mimi");
        database.add(personWithTheSameId);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddIfNegativeId() throws OperationNotSupportedException {
        Person personWithTheSameId = new Person(-123, "Mimi");
        database.add(personWithTheSameId);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddPersonWithNull() throws OperationNotSupportedException {
        Person notExistingPerson = null;
        database.add(notExistingPerson);
    }

    @Test
    public void testRemove() throws OperationNotSupportedException {
        database.remove();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testRemoveWithEmptyDatabase() throws OperationNotSupportedException {
        int dbSize = database.getElements().length;
        for (int i = 0; i < dbSize; i++) {
            database.remove();
        }
        database.remove();
    }

    @Test
    public void testFindByUser() throws OperationNotSupportedException {
        String notExistingName = "Pesho";
        database.findByUsername(notExistingName);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUserNameIfNoSuchUserName() throws OperationNotSupportedException {
        String notExistingName = "Hrizantema";
        database.findByUsername(notExistingName);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByUserNameIfUserNameIsNull() throws OperationNotSupportedException {
        String notExistingName = null;
        database.findByUsername(notExistingName);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testFindByNotExistingId() throws OperationNotSupportedException {
        int notExistingId = 358;
        database.findById(notExistingId);
    }

    @Test
    public void testFindById() throws OperationNotSupportedException {
        int notExistingId = 123;
        database.findById(notExistingId);
    }
}
