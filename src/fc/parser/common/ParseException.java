package fc.parser.common;

import fc.lexer.StringPosition;

/**
 * Thrown when an error occured during parse step
 */
public class ParseException extends Exception implements StringPosition {
    private final int column;

    /**
     * Versioning of serialized instances.
     */
    private static final long serialVersionUID = 7628132232321526935L;

    public ParseException(String reason, int column) {
        super(reason);
        this.column = column;
    }

    public int getColumn() {
        return column;
    }
}
