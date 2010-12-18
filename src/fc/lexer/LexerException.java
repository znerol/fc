package fc.lexer;

public class LexerException extends Exception {
    private final int column;

    /**
     * Versioning of serialized instances.
     */
    private static final long serialVersionUID = 7268307009672490166L;

    public LexerException(String message, int column) {
        super(message);
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
