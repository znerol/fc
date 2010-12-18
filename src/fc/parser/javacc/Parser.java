package fc.parser.javacc;

import java.io.StringReader;

import fc.grammar.javacc.CalculatorGrammer;
import fc.grammar.javacc.TokenMgrError;
import fc.lang.Expression;
import fc.lexer.LexerException;
import fc.parser.common.ParseException;

/**
 * Adapter for the JavaCC version of the calculator parser
 */
public class Parser implements fc.parser.common.Parser {

    @Override
    public Expression parse(String line) throws ParseException, LexerException {
        StringReader reader = new StringReader(line);
        CalculatorGrammer grammar = new CalculatorGrammer(reader);
        Expression result;

        try {
            result = grammar.parse();
        }
        catch (fc.grammar.javacc.ParseException e) {
            throw new ParseException(e.getMessage(),
                    e.currentToken.next.beginColumn);
        }
        catch (TokenMgrError e) {
            // Regrettably TokenMgrError does not provide us with position
            // information. However JavaCC can be configured to throw
            // ParseException wherever possible (see comments and code at the
            // end of CalculatorGrammar.jj)
            throw new LexerException(e.getMessage(), 0);
        }

        return result;
    }
}
