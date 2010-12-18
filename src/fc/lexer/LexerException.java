package fc.lexer;

/**
 * Thrown when an error occurs during the lexical analysis step
 */
public class LexerException extends Exception implements StringPosition {
    private final int column;

    /**
     * Versioning of serialized instances.
     */
    private static final long serialVersionUID = 7268307009672490166L;

    public LexerException(String message, int column) {
        super(message);
        this.column = column;
    }

    public int getColumn() {
        return column;
    }
}
