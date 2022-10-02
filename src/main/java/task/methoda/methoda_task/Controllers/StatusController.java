package task.methoda.methoda_task.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.methoda.methoda_task.exceptions.StatusException;
import task.methoda.methoda_task.services.StatusService;
import task.methoda.methoda_task.services.TransactionService;

@RestController
@RequestMapping("/status") // http://localhost:8080/status
@RequiredArgsConstructor
public class StatusController {
    private final StatusService statusService;
    private final TransactionService transactionService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllStatuses() {
        return new ResponseEntity<>(statusService.getAllStatuses(), HttpStatus.OK);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addNewStatus(String name) {
        statusService.addNewStatus(name);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public void deleteStatusById(@PathVariable int id) throws StatusException {
        transactionService.deleteTransactionsByStatus(statusService.getById(id));
        statusService.deleteStatusById(id);
    }
}
