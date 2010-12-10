package fc.lang;

/**
 * Exception thrown when a variable is not defined in the current scope during
 * evaluation of an expression.
 */
public class UnboundVariableException extends EvaluationException {
    /**
     * Versioning of serialized instances.
     */
    private static final long serialVersionUID = -3020026864968242402L;

    public UnboundVariableException(String reason) {
        super(reason);
    }
}
