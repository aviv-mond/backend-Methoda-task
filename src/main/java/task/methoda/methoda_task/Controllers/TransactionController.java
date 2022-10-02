package task.methoda.methoda_task.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.methoda.methoda_task.beans.Transaction;
import task.methoda.methoda_task.pojo.TransactionPojo;
import task.methoda.methoda_task.services.TransactionService;

@RestController
@RequestMapping("/transaction") // http://localhost:8080/transaction
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;


    @GetMapping("/all")
    public ResponseEntity<?> getAllTransaction() {
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewTransaction(@RequestBody TransactionPojo transactionPojo) {
        transactionService.addNewTransaction(transactionPojo);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteTransaction(@PathVariable int id) {
        transactionService.deleteTransaction(id);
    }

    @DeleteMapping("/delete/all")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteAllTransaction() {
        transactionService.deleteAllTransactionsAndStatuses();
    }
}
