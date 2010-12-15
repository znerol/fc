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
            result = new Token(charIterator.getIndex() + 1, Symbol.END);
            charIterator.next();
            break;

        case '=':
            result = new Token(charIterator.getIndex() + 1, Symbol.EQUAL);
            charIterator.next();
            break;

        case '(':
            result = new Token(charIterator.getIndex() + 1, Symbol.LPAREN);
            charIterator.next();
            break;

        case ')':
            result = new Token(charIterator.getIndex() + 1, Symbol.RPAREN);
            charIterator.next();
            break;

        case '+':
            result = new Token(charIterator.getIndex() + 1, Symbol.PLUS);
            charIterator.next();
            break;

        case '-':
            result = new Token(charIterator.getIndex() + 1, Symbol.MINUS);
            charIterator.next();
            break;

        case '*':
            result = new Token(charIterator.getIndex() + 1, Symbol.MULTIPLY);
            charIterator.next();
            break;

        case '/':
            result = new Token(charIterator.getIndex() + 1, Symbol.DIVIDE);
            charIterator.next();
            break;

        default:
            String identifierOrNumber = scanIdentifierOrNumber();

            try {
                Number number;
                number = NumberFormat.getInstance().parse(identifierOrNumber);
                result = new Token(charIterator.getIndex() + 1, Symbol.NUMBER, identifierOrNumber, number);
            }
            catch (ParseException e) {
                if (identifierOrNumber.equals("let")) {
                    result = new Token(charIterator.getIndex() + 1, Symbol.LET);
                }
                else if (identifierOrNumber.equals("exit")) {
                    result = new Token(charIterator.getIndex() + 1, Symbol.EXIT);
                }
                else {
                    result = new Token(charIterator.getIndex() + 1, Symbol.IDENTIFIER, identifierOrNumber);
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
