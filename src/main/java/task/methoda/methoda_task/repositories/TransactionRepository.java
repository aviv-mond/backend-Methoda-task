package task.methoda.methoda_task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import task.methoda.methoda_task.beans.Status;
import task.methoda.methoda_task.beans.Transaction;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> getTransactionsByToWho(Status status);

    List<Transaction> getTransactionsByFromWhere(Status fromWhere);
}
