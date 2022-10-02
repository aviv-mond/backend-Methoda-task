package task.methoda.methoda_task.Advices;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import task.methoda.methoda_task.exceptions.StatusException;

@RestController
@ControllerAdvice
public class StatusAdvice {
    @ExceptionHandler(value = {StatusException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ErrorDetail handleStatusException(Exception exception) {
        return new ErrorDetail("Status error", exception.getMessage());
    }
}
