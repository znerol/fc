package fc.lexer;

public class Token {
    private final Symbol symbol;
    private final String stringValue;
    private final Number numberValue;

    public Token(Symbol symbol, String stringValue, Number numberValue) {
        this.symbol = symbol;
        this.stringValue = stringValue;
        this.numberValue = numberValue;
    }

    public Token(Symbol symbol) {
        this(symbol, null, null);
    }

    public Token(Symbol symbol, String stringValue) {
        this(symbol, stringValue, null);
    }

    public Token(Symbol symbol, Number numberValue) {
        this(symbol, null, numberValue);
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public String getStringValue() {
        return stringValue;
    }

    public Number getNumberValue() {
        return numberValue;
    }
}
