package fc.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import fc.lang.EvaluationException;
import fc.lang.Expression;
import fc.lang.Scope;
import fc.parser.common.ParseException;

public class ParserTest {
    fc.parser.common.Parser[] parsers = {
            new fc.parser.rdp.Parser(),
            new fc.parser.javacc.Parser(),
    };

    @Test
    public void testConstantValue() throws ParseException, EvaluationException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            Expression expression = parser.parse("42");
            double result = expression.evaluate(scope);
            assertEquals(42.0, result, 0.0);
        }
    }

    @Test
    public void testUnaryMinus() throws ParseException, EvaluationException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            Expression expression = parser.parse("-88");
            double result = expression.evaluate(scope);
            assertEquals(-88, result, 0.0);
        }
    }

    @Test
    public void testSimpleOperations() throws ParseException,
            EvaluationException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            Expression expression = parser.parse("\r42/2+\t6 -4.0* 1");
            double result = expression.evaluate(scope);
            assertEquals(23.0, result, 0.0);
        }
    }

    @Test
    public void testCalculationWithParanthesis() throws ParseException,
            EvaluationException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            Expression expression = parser.parse("16 / (2 +6)");
            double result = expression.evaluate(scope);
            assertEquals(2, result, 0.0);
        }
    }

    @Test
    public void testVariableBinding() throws ParseException,
            EvaluationException {
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
     */
    @Test
    public void testVariableBindingNegativeFactors() throws ParseException,
            EvaluationException {
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
     */
    @Test
    public void testVariableBindingNegativeFactorAtFirstPosition()
            throws ParseException, EvaluationException {
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

    @Test(expected = ParseException.class)
    public void testUnbalancedParanthesis() throws ParseException,
            EvaluationException {
        for (fc.parser.common.Parser parser : parsers) {
            parser.parse(" 8 + 7 ((5*6/4)");
        }
    }

    @Test(expected = ParseException.class)
    public void testMissingOperator() throws ParseException,
            EvaluationException {
        for (fc.parser.common.Parser parser : parsers) {
            parser.parse("8 x + 4");
        }
    }

    @Test(expected = ParseException.class)
    public void testMultipleOperators() throws ParseException,
            EvaluationException {
        for (fc.parser.common.Parser parser : parsers) {
            parser.parse("8 * + 4");
        }
    }
}
