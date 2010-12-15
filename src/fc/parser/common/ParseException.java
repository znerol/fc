package fc.parser.common;

public class ParseException extends Exception {
    private final int column;

    /**
     * Versioning of serialized instances.
     */
    private static final long serialVersionUID = 7628132232321526935L;

    public ParseException(String reason, int column) {
        super(reason);
        this.column = column;
    }

    public String getColumnMarker(String prefix, char fill, char marker) {
        String result = prefix;

        for (int i = 1; i < column; i++) {
            result += fill;
        }

        result += marker;
        return result;
    }
}
