package fc.lexer;

/**
 * Represents a character or sequence of characters read from the input and
 * classified by the lexical analyzer.
 */
public class Token {
    private final int position;
    private final Symbol symbol;
    private final String stringValue;

    public Token(int position, Symbol symbol, String stringValue) {
        this.position = position;
        this.symbol = symbol;
        this.stringValue = stringValue;
    }

    public Token(int position, Symbol symbol) {
        this(position, symbol, null);
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public String getStringValue() {
        return stringValue;
    }

    public int getPosition() {
        return position;
    }

    public String toString() {
        if (stringValue != null) {
            return stringValue;
        }
        else {
            return symbol.toString();
        }
    }
}
