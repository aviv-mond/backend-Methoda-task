package task.methoda.methoda_task.exceptions;

public class TransactionException extends Exception {
    public TransactionException() {
        super("General Transaction Exception");
    }

    public TransactionException(String message) {
        super(message);
    }
}
