package fc.parser.common;

public class ParseException extends Exception {
    /**
     * Versioning of serialized instances.
     */
    private static final long serialVersionUID = 7628132232321526935L;

    public ParseException(String reason) {
        super(reason);
    }
}
