package task.methoda.methoda_task.Advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import task.methoda.methoda_task.exceptions.TransactionException;

@RestController
@ControllerAdvice
public class TransactionAdvice {
    @ExceptionHandler(value = {TransactionException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail handleTransactionException(Exception exception) {
        return new ErrorDetail("Transaction error", exception.getMessage());
    }
}
