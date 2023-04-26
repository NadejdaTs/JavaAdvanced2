import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChainblockImpl implements Chainblock{

    private Map<Integer, Transaction> transactions;

    public ChainblockImpl(){
        this.transactions = new HashMap<>();
    }

    public int getCount() {
        return this.transactions.size();
    }

    public void add(Transaction transaction) {
        int id = transaction.getId();
        if(!this.transactions.containsKey(id)){
            this.transactions.putIfAbsent(id, transaction);
        }

    }

    public boolean contains(Transaction transaction) {
        return contains(transaction.getId());
    }

    public boolean contains(int id) {
        if(this.transactions.containsKey(id)){
            return true;
        }
        return false;
    }

    public void changeTransactionStatus(int id, TransactionStatus newStatus) {
        if(!this.transactions.containsKey(id)){
            throw new IllegalArgumentException();
        }
        Transaction transaction = this.transactions.get(id);
        transaction.setStatus(newStatus);
        this.transactions.put(id, transaction);
    }

    public void removeTransactionById(int id) {
        if(!this.transactions.containsKey(id)){
            throw new IllegalArgumentException();
        }
        this.transactions.remove(id);
    }

    public Transaction getById(int id) {
        if(!transactions.containsKey(id)){
            throw new IllegalArgumentException();
        }
        return this.transactions.get(id);
    }

    public Iterable<Transaction> getByTransactionStatus(TransactionStatus status) {
        List<Transaction> filteredTransaction = new ArrayList<>();
        for (Transaction t : transactions.values()) {
            if(t.getStatus().equals(status)){
                filteredTransaction.add(t);
            }
        };

        if(filteredTransaction.isEmpty()){
            throw new IllegalArgumentException();
        }

        filteredTransaction.sort(Comparator.comparingDouble(Transaction::getAmount).reversed());
        return filteredTransaction;
    }

    public Iterable<String> getAllSendersWithTransactionStatus(TransactionStatus status) {
        List<Transaction> filteredTransaction = new ArrayList<>();
        getByTransactionStatus(status).forEach(t -> filteredTransaction.add(t));

        List<String> senders = filteredTransaction.stream()
                .map(Transaction::getFrom)
                .collect(Collectors.toList());

        if(senders.isEmpty()){
            throw new IllegalArgumentException();
        }

        return senders;
    }

    public Iterable<String> getAllReceiversWithTransactionStatus(TransactionStatus status) {
        List<Transaction> filteredTransaction = new ArrayList<>();
        getByTransactionStatus(status).forEach(t -> filteredTransaction.add(t));

        List<String> receivers = filteredTransaction.stream()
                .map(Transaction::getTo)
                .collect(Collectors.toList());

        if(receivers.isEmpty()){
            throw new IllegalArgumentException();
        }

        return receivers;
    }

    public Iterable<Transaction> getAllOrderedByAmountDescendingThenById() {
        return this.transactions.values().stream()
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderOrderedByAmountDescending(String sender) {
        List<Transaction> result = transactions.values().stream()
                .filter(t -> t.getFrom().equals(sender))
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        if(result.isEmpty()){
            throw new IllegalArgumentException();
        }

        return result;
    }

    public Iterable<Transaction> getByReceiverOrderedByAmountThenById(String receiver) {
        List<Transaction> result = transactions.values().stream()
                .filter(t -> t.getTo().equals(receiver))
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());

        if(result.isEmpty()){
            throw new IllegalArgumentException();
        }

        return result;
    }

    public Iterable<Transaction> getByTransactionStatusAndMaximumAmount(TransactionStatus status, double amount) {
        return transactions.values().stream()
                .filter(t -> t.getStatus().equals(status) && t.getAmount() <= amount)
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .collect(Collectors.toList());
    }

    public Iterable<Transaction> getBySenderAndMinimumAmountDescending(String sender, double amount) {
        List<Transaction> result = transactions.values().stream()
                .filter(t -> t.getFrom().equals(sender) && t.getAmount() > amount)
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .collect(Collectors.toList());

        if(result.isEmpty()){
            throw new IllegalArgumentException();
        }

        return result;
    }

    public Iterable<Transaction> getByReceiverAndAmountRange(String receiver, double lo, double hi) {
        List<Transaction> result = transactions.values().stream()
                .filter(t -> t.getTo().equals(receiver) && t.getAmount() >= lo && t.getAmount() < hi)
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed().thenComparing(Transaction::getId))
                .collect(Collectors.toList());

        if(result.isEmpty()){
            throw new IllegalArgumentException();
        }

        return result;
    }

    public Iterable<Transaction> getAllInAmountRange(double lo, double hi) {
        return transactions.values().stream()
                .filter(t -> t.getAmount() >= lo && t.getAmount() <= hi)
                .collect(Collectors.toList());
    }

    public Iterator<Transaction> iterator() {
        return null;
    }
}
