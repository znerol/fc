package fc.lexer;

public class Token {
    private final int position;
    private final Symbol symbol;
    private final String stringValue;
    private final Number numberValue;

    public Token(int position, Symbol symbol, String stringValue, Number numberValue) {
        this.position = position;
        this.symbol = symbol;
        this.stringValue = stringValue;
        this.numberValue = numberValue;
    }

    public Token(int position, Symbol symbol) {
        this(position, symbol, null, null);
    }

    public Token(int position, Symbol symbol, String stringValue) {
        this(position, symbol, stringValue, null);
    }

    public Token(int position, Symbol symbol, Number numberValue) {
        this(position, symbol, null, numberValue);
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
