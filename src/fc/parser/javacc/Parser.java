package fc.parser.javacc;

import java.io.StringReader;

import fc.grammar.javacc.CalculatorGrammer;
import fc.grammar.javacc.TokenMgrError;
import fc.lang.Expression;
import fc.parser.common.ParseException;

public class Parser implements fc.parser.common.Parser {

    @Override
    public Expression parse(String line) throws ParseException {
        StringReader reader  = new StringReader(line);
        CalculatorGrammer grammar = new CalculatorGrammer(reader);
        Expression result;

        try {
            result = grammar.parse();
        }
        catch (fc.grammar.javacc.ParseException e) {
            throw new ParseException(e.getMessage());
        }
        catch (TokenMgrError e) {
            throw new ParseException(e.getMessage());
        }

        return result;
    }

}
