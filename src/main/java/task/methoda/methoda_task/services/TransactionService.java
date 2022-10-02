package task.methoda.methoda_task.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import task.methoda.methoda_task.beans.Status;
import task.methoda.methoda_task.beans.StatusLabel;
import task.methoda.methoda_task.beans.Transaction;
import task.methoda.methoda_task.pojo.TransactionPojo;
import task.methoda.methoda_task.repositories.TransactionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final StatusService statusService;

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public void addNewTransaction(TransactionPojo transactionPojo) {
        Status fromWhoStatus = statusService.getByName(transactionPojo.getFromWhoStatusName());
        Status toWhoStatus = statusService.getByName(transactionPojo.getToWhoStatusName());
        Transaction transaction = Transaction.builder().name(transactionPojo.getTransactionName()).fromWhere(fromWhoStatus).toWho(toWhoStatus)
                .build();
        transactionRepository.save(transaction);
        calculateStatusLabels();
    }

    public void deleteTransaction(int id) {
        transactionRepository.deleteById(id);
    }

    public void deleteAllTransactionsAndStatuses() {
        transactionRepository.deleteAll();
        statusService.deleteAllStatuses();
    }

    public void calculateStatusLabels() {
        List<Transaction> allTransactions = getAllTransactions();
        for (Transaction transaction : allTransactions) {
            Status fromStatus = transaction.getFromWhere();
            Status toWhoStatus = transaction.getToWho();
            if (fromStatus.getLabel() == StatusLabel.FINAL) {
                fromStatus.setLabel(StatusLabel.ORPHAN);
                statusService.saveStatus(fromStatus);
                break;
            } else if (fromStatus.getLabel() == StatusLabel.INITIAL_STATUS && toWhoStatus.getLabel() == StatusLabel.ORPHAN) {
                System.out.println("Initial cannot transaction to Orphan Status.");
                break;
            } else if (fromStatus.getLabel() == StatusLabel.FINAL && toWhoStatus.getLabel() == StatusLabel.FINAL) {
                System.out.println("Final cannot transaction to Final Status.");
                break;
            } else if (fromStatus.getLabel() == StatusLabel.FINAL && toWhoStatus.getLabel() == StatusLabel.INITIAL_STATUS) {
                System.out.println("Final cannot transaction to Initial Status.");
                break;
            }
        }
    }

    public List<Transaction> getTransactionsByToWho(Status status) {
        return transactionRepository.getTransactionsByToWho(status);
    }

    public List<Transaction> getTransactionsByFromWhere(Status status) {
        return transactionRepository.getTransactionsByFromWhere(status);
    }

    public void deleteTransactionsByStatus(Status status) {
        List<Transaction> toWhoTransactions = getTransactionsByToWho(status);
        List<Transaction> fromWhereTransactions = getTransactionsByFromWhere(status);
        for (Transaction transaction : toWhoTransactions) {
            transactionRepository.delete(transaction);
        }
        for (Transaction transaction : fromWhereTransactions) {
            transactionRepository.delete(transaction);
        }
    }
}
