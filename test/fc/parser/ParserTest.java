package fc.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import fc.lang.EvaluationException;
import fc.lang.Expression;
import fc.lang.Scope;
import fc.lexer.LexerException;
import fc.parser.common.ParseException;

public class ParserTest {
    fc.parser.common.Parser[] parsers = {
            new fc.parser.rdp.Parser(),
            new fc.parser.javacc.Parser(),
    };

    @Test
    public void testConstantValue() throws ParseException, EvaluationException, LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            Expression expression = parser.parse("42");
            double result = expression.evaluate(scope);
            assertEquals(42.0, result, 0.0);
        }
    }

    @Test
    public void testUnaryMinus() throws ParseException, EvaluationException, LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            Expression expression = parser.parse("-88");
            double result = expression.evaluate(scope);
            assertEquals(-88, result, 0.0);
        }
    }

    @Test
    public void testSimpleOperations() throws ParseException,
            EvaluationException, LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            Expression expression = parser.parse("\r42/2+\t6 -4.0* 1");
            double result = expression.evaluate(scope);
            assertEquals(23.0, result, 0.0);
        }
    }

    @Test
    public void testCalculationWithParanthesis() throws ParseException,
            EvaluationException, LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            Expression expression = parser.parse("16 / (2 +6)");
            double result = expression.evaluate(scope);
            assertEquals(2, result, 0.0);
        }
    }

    @Test
    public void testVariableBinding() throws ParseException,
            EvaluationException, LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            double result;

            result = parser.parse("let x = 7").evaluate(scope);
            assertEquals(7, result, 0.0);

            result = parser.parse("let y = 8").evaluate(scope);
            assertEquals(8, result, 0.0);

            result = parser.parse("x * y").evaluate(scope);
            assertEquals(56, result, 0.0);
        }
    }

    /**
     * Bugfix test for rdp.Parser not respecting unary minus before an
     * identifier.
     * @throws LexerException 
     */
    @Test
    public void testVariableBindingNegativeFactors() throws ParseException,
            EvaluationException, LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            double result;

            result = parser.parse("let x = 7").evaluate(scope);
            assertEquals(7, result, 0.0);

            result = parser.parse("let y = 8").evaluate(scope);
            assertEquals(8, result, 0.0);

            result = parser.parse("x * -y").evaluate(scope);
            assertEquals(-56, result, 0.0);
        }
    }

    /**
     * Bugfix test for rdp.Parser not respecting unary minus before an
     * identifier which is not the last one in an expression.
     * @throws LexerException 
     */
    @Test
    public void testVariableBindingNegativeFactorAtFirstPosition()
            throws ParseException, EvaluationException, LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            double result;

            result = parser.parse("let x = 7").evaluate(scope);
            assertEquals(7, result, 0.0);

            result = parser.parse("let y = 8").evaluate(scope);
            assertEquals(8, result, 0.0);

            result = parser.parse("-x * y").evaluate(scope);
            assertEquals(-56, result, 0.0);
        }
    }

    @Test
    public void testUnbalancedParanthesis() throws LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            try {
                parser.parse(" 8 + 7 ((5*6/4)");
                fail("Parser " + parser + " must throw ParseException if the number of left and right paranthesis do not match");
            }
            catch (ParseException e) {
                // expected
            }
        }
    }

    @Test
    public void testMissingOperator() throws LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            try {
                parser.parse("8 x + 4");
                fail("Parser " + parser + " must throw ParseException if operators are missing in expressions");
            }
            catch (ParseException e) {
                // expected
            }
        }
    }

    @Test
    public void testMultipleOperators() throws LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            try {
                parser.parse("8 * + 4");
                fail("Parser " + parser + " must throw ParseException if multiple operators follow each other");
            }
            catch (ParseException e) {
                // expected
            }
        }
    }
}
