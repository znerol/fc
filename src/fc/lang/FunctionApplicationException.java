package fc.lang;

public class FunctionApplicationException extends EvaluationException {
    /**
     * Versioning of serialized instances.
     */
    private static final long serialVersionUID = 5530751964827385284L;

    public FunctionApplicationException(String reason) {
        super(reason);
    }
}
