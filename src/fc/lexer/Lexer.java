package fc.lexer;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class Lexer {
    private final CharacterIterator charIterator;
    private final StringBuilder stringBuilder = new StringBuilder();

    public Lexer(String input) {
        charIterator = new StringCharacterIterator(input);
    }

    public Token nextToken() throws LexerException {
        Token result;

        // consume leading whitespace
        scanWhitespace(null);

        int tokenPosition = charIterator.getIndex() + 1;

        switch (charIterator.current()) {
        case CharacterIterator.DONE:
            result = new Token(tokenPosition, Symbol.END);
            charIterator.next();
            break;

        case '=':
            result = new Token(tokenPosition, Symbol.EQUAL);
            charIterator.next();
            break;

        case '(':
            result = new Token(tokenPosition, Symbol.LPAREN);
            charIterator.next();
            break;

        case ')':
            result = new Token(tokenPosition, Symbol.RPAREN);
            charIterator.next();
            break;

        case '+':
            result = new Token(tokenPosition, Symbol.PLUS);
            charIterator.next();
            break;

        case '-':
            result = new Token(tokenPosition, Symbol.MINUS);
            charIterator.next();
            break;

        case '*':
            result = new Token(tokenPosition, Symbol.MULTIPLY);
            charIterator.next();
            break;

        case '/':
            result = new Token(tokenPosition, Symbol.DIVIDE);
            charIterator.next();
            break;

        default:
            // clear string buffer
            stringBuilder.delete(0, stringBuilder.length());

            if (scanNumber(stringBuilder) > 0) {
                result = new Token(tokenPosition, Symbol.NUMBER,
                        stringBuilder.toString());
            }
            else if (scanIdentifier(stringBuilder) > 0) {
                String identifier = stringBuilder.toString();

                if (identifier.equals("let")) {
                    result = new Token(tokenPosition, Symbol.LET);
                }
                else if (identifier.equals("exit")) {
                    result = new Token(tokenPosition, Symbol.EXIT);
                }
                else {
                    result = new Token(tokenPosition, Symbol.IDENTIFIER,
                            identifier);
                }
            }
            else {
                throw new LexerException("Unexpected symbol '"
                        + charIterator.current() + "'", tokenPosition);
            }
        }

        return result;
    }

    private int scan(CharacterIterator charIterator, CharMatcher charMatcher,
            StringBuilder stringBuilder) {
        int count = 0;

        for (char c = charIterator.current(); c != CharacterIterator.DONE
                && charMatcher.match(c); c = charIterator.next()) {
            if (stringBuilder != null) {
                stringBuilder.append(c);
            }
            count++;
        }

        return count;
    }

    private int scanWhitespace(StringBuilder stringBuilder) {
        final CharMatcher matcher = new CharMatcher() {
            @Override
            public boolean match(char c) {
                return Character.isWhitespace(c);
            }
        };

        return scan(charIterator, matcher, null);
    }

    private int scanNumber(StringBuilder stringBuilder) {
        final CharMatcher digitOrDecimalDot = new CharMatcher() {
            @Override
            public boolean match(char c) {
                return Character.isDigit(c) || c == '.';
            }
        };

        final CharMatcher digit = new CharMatcher() {
            @Override
            public boolean match(char c) {
                return Character.isDigit(c);
            }
        };

        return scan(charIterator, digitOrDecimalDot, stringBuilder)
                + scan(charIterator, digit, stringBuilder);
    }

    private int scanIdentifier(StringBuilder stringBuilder) {
        final CharMatcher matcher = new CharMatcher() {
            @Override
            public boolean match(char c) {
                return Character.isLetter(c);
            }
        };

        return scan(charIterator, matcher, stringBuilder);
    }
}
