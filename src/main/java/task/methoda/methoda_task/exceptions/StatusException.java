package task.methoda.methoda_task.exceptions;

public class StatusException extends Exception {
    public StatusException() {
        super("General Status Exception");
    }

    public StatusException(String message) {
        super(message);
    }
}
