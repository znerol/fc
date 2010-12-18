package fc.parser;

import static org.junit.Assert.*;

import org.junit.Test;

import fc.lang.ConstantValueExpression;
import fc.lang.EvaluationException;
import fc.lang.Expression;
import fc.lang.Scope;
import fc.lexer.LexerException;
import fc.parser.common.ParseException;

public class AcceptanceTest {
    fc.parser.common.Parser[] parsers = {
            new fc.parser.rdp.Parser(),
            new fc.parser.javacc.Parser(),
    };

    @Test
    public void testBeo1Example1() throws ParseException,
            EvaluationException, LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            Expression expression = parser.parse("(4+5)*3/4");
            double result = expression.evaluate(scope);
            assertEquals(6.75, result, 0.0);
        }
    }

    @Test
    public void testBeo1Example2() throws ParseException,
            EvaluationException, LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            Expression expression = parser.parse("(10*3.141592)");
            double result = expression.evaluate(scope);
            assertEquals(31.41592, result, 0.0);
        }
    }

    @Test
    public void testBeo1Example3() throws ParseException,
            EvaluationException, LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            Expression expression = parser.parse("-4");
            double result = expression.evaluate(scope);
            assertEquals(-4.0, result, 0.0);
        }
    }

    @Test
    public void testBeo1Example4() throws LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            try {
                parser.parse("lex x=5*50");
                fail("Parser " + parser + " must throw ParseException if an unexpected token is encountered");
            }
            catch (ParseException e) {
                // expected
            }
        }
    }

    @Test
    public void testBeo1Example5() throws ParseException,
            EvaluationException, LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            Expression expression = parser.parse("let x=5*50");
            double result = expression.evaluate(scope);
            assertEquals(250.0, result, 0.0);
        }
    }

    @Test
    public void testBeo1Example6() throws ParseException,
            EvaluationException, LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            Expression expression = parser.parse("let y=-555.3");
            double result = expression.evaluate(scope);
            assertEquals(-555.3, result, 0.0);
        }
    }

    @Test
    public void testBeo1Example7() throws ParseException,
            EvaluationException, LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            scope.bind("x", new ConstantValueExpression(250.0));
            scope.bind("y", new ConstantValueExpression(-555.3));

            Expression expression = parser.parse("x+z");

            try {
                expression.evaluate(scope);
                fail("Evaluation of expression read by " + parser + " must fail when unbound variable z is used");
            }
            catch (EvaluationException e) {
                // expected
            }
        }
    }

    @Test
    public void testBeo1Example8() throws ParseException,
            EvaluationException, LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            scope.bind("x", new ConstantValueExpression(250.0));
            scope.bind("y", new ConstantValueExpression(-555.3));

            Expression expression = parser.parse("x+y/3");
            double result = expression.evaluate(scope);
            assertEquals(64.9, result, 0.0);
        }
    }

    @Test
    public void testBeo1Example9() throws ParseException,
            EvaluationException, LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            scope.bind("x", new ConstantValueExpression(250.0));
            scope.bind("y", new ConstantValueExpression(-555.3));

            Expression expression = parser.parse("let z=x+y/3");
            double result = expression.evaluate(scope);
            assertEquals(64.9, result, 0.0);
        }
    }

    @Test
    public void testBeo1Example10()  {
        for (fc.parser.common.Parser parser : parsers) {
            try {
                parser.parse("5+$6");
                fail("Parser " + parser + " must throw LexerException or ParseException if an unexpected token is encountered");
            }
            catch (ParseException e) {
                // expected
            }
            catch (LexerException e) {
                // expected
            }
        }
    }

    @Test
    public void testBeo1Example11() throws LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            try {
                parser.parse("let x+5");
                fail("Parser " + parser + " must throw ParseException if equal sign is missing in assignment");
            }
            catch (ParseException e) {
                // expected
            }
        }
    }

    @Test
    public void testBeo1Example12() throws ParseException {
        for (fc.parser.common.Parser parser : parsers) {
            try {
                parser.parse("let k=)");
                fail("Parser " + parser + " must throw LexerException or ParseException if expression cannot be parsed after equal sign");
            }
            catch (ParseException e) {
                // expected
            }
            catch (LexerException e) {
                // expected
            }
        }
    }

    @Test
    public void testBeo1Example13() throws LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            try {
                parser.parse("let k=-=");
                fail("Parser " + parser + " must throw ParseException if factor cannot be parsed after equal sign");
            }
            catch (ParseException e) {
                // expected
            }
        }
    }

    @Test
    public void testBeo1Example14() throws ParseException,
            EvaluationException, LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            Expression expression = parser.parse("let x=-5");
            double result = expression.evaluate(scope);
            assertEquals(-5.0, result, 0.0);
        }
    }

    @Test
    public void testBeo1Example15() throws ParseException,
            EvaluationException, LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            scope.bind("k", new ConstantValueExpression(-5));
            Expression expression = parser.parse("let k=-k");
            double result = expression.evaluate(scope);
            assertEquals(5.0, result, 0.0);
        }
    }

    @Test
    public void testBeo1Example16() throws ParseException,
            EvaluationException, LexerException {
        for (fc.parser.common.Parser parser : parsers) {
            Scope scope = new Scope();
            Expression expression = parser.parse("4+-4");
            double result = expression.evaluate(scope);
            assertEquals(0.0, result, 0.0);
        }
    }
}
