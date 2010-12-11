package fc.lexer;

import java.text.CharacterIterator;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.StringCharacterIterator;

public class Lexer {
    private final CharacterIterator charIterator;

    public Lexer(String input) {
        charIterator = new StringCharacterIterator(input);
    }

    public Token nextToken() {
        Token result;

        // consume leading whitespace
        scanWhitespace();

        switch (charIterator.current()) {
        case CharacterIterator.DONE:
            result = new Token(Symbol.END);
            charIterator.next();
            break;

        case '=':
            result = new Token(Symbol.EQUAL);
            charIterator.next();
            break;

        case '(':
            result = new Token(Symbol.LPAREN);
            charIterator.next();
            break;

        case ')':
            result = new Token(Symbol.RPAREN);
            charIterator.next();
            break;

        case '+':
            result = new Token(Symbol.PLUS);
            charIterator.next();
            break;

        case '-':
            result = new Token(Symbol.MINUS);
            charIterator.next();
            break;

        case '*':
            result = new Token(Symbol.MULTIPLY);
            charIterator.next();
            break;

        case '/':
            result = new Token(Symbol.DIVIDE);
            charIterator.next();
            break;

        default:
            String identifierOrNumber = scanIdentifierOrNumber();

            try {
                Number number;
                number = NumberFormat.getInstance().parse(identifierOrNumber);
                result = new Token(Symbol.NUMBER, number);
            }
            catch (ParseException e) {
                if (identifierOrNumber.equals("let")) {
                    result = new Token(Symbol.LET);
                }
                else {
                    result = new Token(Symbol.IDENTIFIER, identifierOrNumber);
                }
            }
        }

        return result;
    }

    private void scanWhitespace() {
        for (char c = charIterator.current(); c != CharacterIterator.DONE
                && Character.isWhitespace(c); c = charIterator.next())
            ;
    }

    private String scanIdentifierOrNumber() {
        String result = "";

        for (char c = charIterator.current(); c != CharacterIterator.DONE
                && (Character.isLetterOrDigit(c) || c == '.'); c = charIterator
                .next()) {
            result = result + c;
        }

        return result;
    }
}
