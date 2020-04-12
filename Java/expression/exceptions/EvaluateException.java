package expression.exceptions;

public class EvaluateException extends RuntimeException {
    EvaluateException(String message) {
        super(message);
    }

    EvaluateException(String message, Throwable cause) {
        super(message, cause);
    }
}
