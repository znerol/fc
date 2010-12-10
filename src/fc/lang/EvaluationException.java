package fc.lang;

/**
 * Exception thrown when an error occurred during evaluation of an expression.
 */
public class EvaluationException extends Exception {
    /**
     * Versioning of serialized instances.
     */
    private static final long serialVersionUID = -5054865354360934828L;

    public EvaluationException(String reason) {
        super(reason);
    }
}
