package fc.parser.common;


import fc.lang.Expression;
import fc.lexer.LexerException;

/**
 * Common interface for parser classes converting the given input line into an
 * expression ready to evaluate.
 */
public interface Parser {
    /**
     * Consume characters from input stream and construct expression
     * 
     * @param line
     *            input stream containing an expression
     * @return parsed expression object
     * @throws ParseException
     *             thrown when an error occurred during the parsing process
     */
    public Expression parse(String line) throws ParseException, LexerException;
}
