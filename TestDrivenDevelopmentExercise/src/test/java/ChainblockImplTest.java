import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class ChainblockImplTest extends TestCase {

    private Chainblock chainblock;
    private List<Transaction> transactionList;
    private Transaction transaction;
    private static final int NOT_EXISTING_ID = 58;
    private static final double HI_AMOUNT = 30.0;
    private static final double LO_AMOUNT = 10.0;

    @Before
    public void setUp(){
        this.chainblock = new ChainblockImpl();
        this.transactionList = new ArrayList<>();
        prepareTransactions();
        transaction = this.transactionList.get(0);
        this.chainblock.add(transaction);
    }

    private void prepareTransactions(){
        Transaction firstTransaction = new TransactionImpl(1, TransactionStatus.SUCCESSFUL, "Desi", "Pesho", 20.25);
        Transaction secondTransaction = new TransactionImpl(2, TransactionStatus.SUCCESSFUL, "Desi", "Pesho", 10.80);
        Transaction thirdTransaction = new TransactionImpl(3, TransactionStatus.SUCCESSFUL, "Desi", "Pesho", 10.70);
        Transaction forthTransaction = new TransactionImpl(4, TransactionStatus.FAILED, "Desi", "Pesho", 10.60);
        Transaction fifthTransaction = new TransactionImpl(5, TransactionStatus.SUCCESSFUL, "Desi", "Pesho", 500.60);

        this.transactionList.add(firstTransaction);
        this.transactionList.add(secondTransaction);
        this.transactionList.add(thirdTransaction);
        this.transactionList.add(forthTransaction);
        this.transactionList.add(fifthTransaction);
    }

    private void fillChainblock(){
        this.transactionList.forEach(transaction -> chainblock.add(transaction));
    }

    @Test
    public void testContainsSuchTransaction(){
        Transaction newTransaction = this.transactionList.get(1);
        assertFalse(this.chainblock.contains(newTransaction));
        this.chainblock.add(newTransaction);
        assertTrue(this.chainblock.contains(newTransaction));
    }

    @Test
    public void testAddValid(){
        assertEquals(1, this.chainblock.getCount());
        assertTrue(this.chainblock.contains(transaction));
    }

    @Test
    public void testAddWithExistingId(){
        assertEquals(1, this.chainblock.getCount());
        this.chainblock.add(transaction);
        assertEquals(1, this.chainblock.getCount());
    }

    @Test
    public void testChangeTransactionStatusWithExistingId(){
        this.chainblock.changeTransactionStatus(transaction.getId(), TransactionStatus.UNAUTHORIZED);
        assertEquals(TransactionStatus.UNAUTHORIZED, transaction.getStatus());
    }

    /*@Test(expected = IllegalArgumentException.class)
    public void testChangeTransactionStatusWithNotExistingId(){
        this.chainblock.changeTransactionStatus(NOT_EXISTING_ID, TransactionStatus.ABORTED);
    }*/

    /*@Test(expected = IllegalArgumentException.class)
    public void testRemoveTransactionByIdWithNotExistingId(){
        fillChainblock();
        assertEquals(4, chainblock.getCount());

        this.chainblock.removeTransactionById(this.chainblock.getCount() + 1);
    }*/

    @Test
    public void testRemoveTransactionByIdSuccessfully(){
        fillChainblock();
        assertEquals(transactionList.size(), chainblock.getCount());

        this.chainblock.removeTransactionById(transaction.getId());
        assertFalse(this.chainblock.contains(transaction.getId()));
    }

    @Test
    public void testGetByIdWithExistingId(){
        fillChainblock();
        Transaction expected = transactionList.get(0);
        Transaction actual = chainblock.getById(expected.getId());
        assertEquals(expected, actual);
    }

    /*@Test(expected = IllegalArgumentException.class)
    public void testGetByIdWithNotExistingId(){
        fillChainblock();
        chainblock.getById(NOT_EXISTING_ID);
    }*/

    @Test
    public void testGetByTransactionStatusSuccessfully(){
        fillChainblock();
        List<Transaction> succesfulTransaction = this.transactionList.stream()
                .filter(t -> t.getStatus().equals(TransactionStatus.SUCCESSFUL))
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        Iterable<Transaction> result = this.chainblock.getByTransactionStatus(TransactionStatus.SUCCESSFUL);
        assertNotNull(result);
        List<Transaction> resultTransactions = new ArrayList<>(); //списък с върнатите транзакции
        //result.forEach(t -> resultTransactions.add(t)); is the same like the row below
        result.forEach(resultTransactions::add);

        assertEquals(succesfulTransaction.size(), resultTransactions.size());
        resultTransactions.forEach(t -> assertEquals(TransactionStatus.SUCCESSFUL, t.getStatus()));

        assertEquals(succesfulTransaction, resultTransactions);

    }

    /*@Test(expected = IllegalArgumentException.class)
    public void testGetByTransactionStatusWithNotExistingStatus(){
        fillChainblock();
        chainblock.getByTransactionStatus(TransactionStatus.ABORTED);
    }*/

    @Test
    public void testGetAllSendersWithTransactionStatusSuccessfully(){
        fillChainblock();
        List<String> expectedSenders = this.transactionList.stream()
                .filter(t -> t.getStatus() == TransactionStatus.SUCCESSFUL)
                .map(Transaction::getFrom)
                .collect(Collectors.toList());

        Iterable<String> result = this.chainblock.getAllSendersWithTransactionStatus(TransactionStatus.SUCCESSFUL);
        assertNotNull(result);
        List<String> resultSender = new ArrayList<>();
        result.forEach(sender -> resultSender.add(sender));
        assertEquals(expectedSenders.size(), resultSender.size());
        resultSender.forEach(s -> assertEquals("Desi", s));
    }

    /*@Test(expected = IllegalArgumentException.class)
    public void testGetAllSendersWithTransactionStatusNotSuccessfully(){
        fillChainblock();
        this.chainblock.getAllSendersWithTransactionStatus(TransactionStatus.ABORTED);
    }*/

    @Test
    public void testGetAllReceiversWithTransactionStatusSuccessfully(){
        fillChainblock();
        List<String> expectedSenders = this.transactionList.stream()
                .filter(t -> t.getStatus() == TransactionStatus.SUCCESSFUL)
                .map(Transaction::getTo)
                .collect(Collectors.toList());


        Iterable<String> result = this.chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.SUCCESSFUL);
        assertNotNull(result);
        List<String> resultReceivers = new ArrayList<>();
        //result.forEach(receiver -> resultReceivers.add(receiver));
        result.forEach(resultReceivers::add);

        assertEquals(expectedSenders.size(), resultReceivers.size());
        resultReceivers.forEach(r -> assertEquals("Pesho", r));
    }

    /*@Test(expected = IllegalArgumentException.class)
    public void testGetAllReceiversWithTransactionStatusNotSuccessfully(){
        fillChainblock();
        this.chainblock.getAllReceiversWithTransactionStatus(TransactionStatus.ABORTED);
    }*/

    @Test
    public void testGetAllOrderedByAmountDescendingThenById(){
        fillChainblock();
        List<Transaction> expected = this.transactionList.stream()
                .sorted(Comparator.comparingDouble(Transaction::getAmount)
                        .reversed()
                        .thenComparing(Transaction::getId))
                .collect(Collectors.toList());

        Iterable<Transaction> result = this.chainblock.getAllOrderedByAmountDescendingThenById();
        assertNotNull(result);

        List<Transaction> actual = new ArrayList<>();
        //result.forEach(t -> actual.add(t));
        result.forEach(actual::add);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetBySenderOrderedByAmountDescendingSuccessfully(){
        fillChainblock();
        String nameOfSender = transactionList.get(0).getFrom();
        List<Transaction> expected = this.transactionList.stream()
                .filter(t -> t.getFrom().equals(nameOfSender))
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        Iterable<Transaction> result = this.chainblock.getBySenderOrderedByAmountDescending(nameOfSender);
        assertNotNull(result);

        List<Transaction> actual = new ArrayList<>();
        result.forEach(actual::add);
        assertEquals(expected, actual);
    }

    /*@Test(expected = IllegalArgumentException.class)
    public void testGetBySenderOrderedByAmountDescendingNotSuccessful(){
        fillChainblock();
        this.chainblock.getBySenderOrderedByAmountDescending("Desislava");
    }*/

    @Test
    public void testGetByReceiverOrderedByAmountThenByIdSuccessfully(){
        fillChainblock();
        List<Transaction> expected = this.transactionList.stream()
                .filter(t -> t.getTo().equals("Pesho"))
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());

        Iterable<Transaction> result = this.chainblock.getByReceiverOrderedByAmountThenById(transaction.getTo());
        assertNotNull(result);

        List<Transaction> actual = new ArrayList<>();
        result.forEach(actual::add);
        assertEquals(expected, actual);
    }

    /*@Test(expected = IllegalArgumentException.class)
    public void testGetBySenderOrderedByAmountDescendingNotNotSuccessful(){
        fillChainblock();
        this.chainblock.getBySenderOrderedByAmountDescending("Petar");
    }*/

    @Test
    public void testGetByTransactionStatusAndMaximumAmountSuccessfully(){
        fillChainblock();
        TransactionStatus status = transaction.getStatus();
        List<Transaction> expected = this.transactionList.stream()
                .filter(t -> t.getStatus().equals(status) && t.getAmount() <= HI_AMOUNT)
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        Iterable<Transaction> result = chainblock.getByTransactionStatusAndMaximumAmount(status, HI_AMOUNT);
        assertNotNull(result);

        List<Transaction> actual = new ArrayList<>();
        result.forEach(actual::add);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetByTransactionStatusAndMaximumAmountNotSameStatus(){
        Iterable<Transaction> result = chainblock.getByTransactionStatusAndMaximumAmount(TransactionStatus.UNAUTHORIZED, HI_AMOUNT);
        assertEquals(new ArrayList<>(), result);

        List<Transaction> actual = new ArrayList<>();
        result.forEach(actual::add);
        assertEquals(new ArrayList<>(), actual);
    }

    @Test
    public void testGetByTransactionStatusAndMaximumAmountNotAllowedAmount(){
        double notAllowedAmount = 5.0;
        Iterable<Transaction> result = chainblock.getByTransactionStatusAndMaximumAmount(transaction.getStatus(), notAllowedAmount);
        assertEquals(new ArrayList<>(), result);

        List<Transaction> actual = new ArrayList<>();
        result.forEach(actual::add);
        assertEquals(new ArrayList<>(), actual);
    }

    @Test
    public void testGetBySenderAndMinimumAmountDescendingSuccessfully(){
        fillChainblock();
        List<Transaction> expected = transactionList.stream()
                .filter(t -> t.getFrom().equals(transaction.getFrom()) && t.getAmount() > HI_AMOUNT)
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        Iterable<Transaction> result = chainblock.getBySenderAndMinimumAmountDescending(transaction.getFrom(), HI_AMOUNT);
        assertNotNull(result);

        List<Transaction> actual = new ArrayList<>();
        result.forEach(actual::add);
        assertEquals(expected, actual);
    }

    /*@Test(expected = IllegalArgumentException.class)
    public void testGetBySenderAndMinimumAmountDescendingNotSuccessfully(){
        fillChainblock();
        double amount = 502.0;
        Iterable<Transaction> result = chainblock.getBySenderAndMinimumAmountDescending(transaction.getFrom(), amount);
    }*/

    @Test
    public void testGetByReceiverAndAmountRangeSuccessfully(){
        fillChainblock();
        List<Transaction> expected = transactionList.stream()
                .filter(t -> t.getTo().equals(transaction.getTo()) && t.getAmount() >= LO_AMOUNT && t.getAmount() < HI_AMOUNT)
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());

        Iterable<Transaction> result = chainblock.getByReceiverAndAmountRange(transaction.getTo(), LO_AMOUNT, HI_AMOUNT);
        assertNotNull(result);

        List<Transaction> actual = new ArrayList<>();
        result.forEach(actual::add);
        assertEquals(expected, actual);
    }

    /*@Test(expected = IllegalArgumentException.class)
    public void testGetByReceiverAndAmountRangeNotSuccessfully(){
        fillChainblock();
        Iterable<Transaction> result = chainblock.getByReceiverAndAmountRange(transaction.getTo(), 1, 5);
    }*/

    @Test
    public void testGetAllInAmountRangeSuccessfully(){
        fillChainblock();
        List<Transaction> expected = transactionList.stream()
                .filter(t -> t.getAmount() >= LO_AMOUNT && t.getAmount() <= HI_AMOUNT)
                .collect(Collectors.toList());

        Iterable<Transaction> result = chainblock.getAllInAmountRange(LO_AMOUNT, HI_AMOUNT);
        assertNotNull(result);

        List<Transaction> actual = new ArrayList<>();
        result.forEach(actual::add);
        assertEquals(expected, actual);
    }

    @Test
    public void testGetAllInAmountRangeNotSuccessfully(){
        fillChainblock();
        Iterable<Transaction> result = chainblock.getAllInAmountRange(1, 5);
        assertEquals(new ArrayList<>(), result);
    }
}