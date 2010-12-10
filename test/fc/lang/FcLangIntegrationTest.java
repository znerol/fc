package fc.lang;

import org.junit.Test;

import static org.junit.Assert.*;

public class FcLangIntegrationTest {
    @Test
    public void testSimpleExpressions() throws EvaluationException {
        Expression minusSix = new ConstantValueExpression(-6.0);
        Expression two = new ConstantValueExpression(2.0);

        double result;

        Expression sum = new FunctionExpression(new SumFunction(), minusSix,
                two);
        result = sum.evaluate(new Scope());
        assertEquals(-4.0, result, 0.0);

        Expression dif = new FunctionExpression(new DifferenceFunction(),
                minusSix, two);
        result = dif.evaluate(new Scope());
        assertEquals(-8.0, result, 0.0);

        Expression mul = new FunctionExpression(new MultiplicationFunction(),
                minusSix, two);
        result = mul.evaluate(new Scope());
        assertEquals(-12.0, result, 0.0);

        Expression div = new FunctionExpression(new DivisionFunction(),
                minusSix, two);
        result = div.evaluate(new Scope());
        assertEquals(-3.0, result, 0.0);
    }

    @Test
    public void testExpressionCombination() throws EvaluationException {
        Expression one = new ConstantValueExpression(1.0);
        Expression two = new ConstantValueExpression(2.0);

        // Expression: 2.0 * 2.0
        Expression twoTimesTwo = new FunctionExpression(
                new MultiplicationFunction(), two, two);

        // Expression: 1 + 2.0 * 2.0
        Expression oneAndTwoTimesTwo = new FunctionExpression(
                new SumFunction(), one, twoTimesTwo);

        double result = oneAndTwoTimesTwo.evaluate(new Scope());
        assertEquals(5.0, result, 0.0);
    }

    @Test
    public void testExpressionWithBoundVariable() throws EvaluationException {
        // Expression: let x = 42.0
        Expression fortytwo = new ConstantValueExpression(42.0);
        Expression assign = new AssignementExpression("x", fortytwo);

        Scope scope = new Scope();
        double result;

        result = assign.evaluate(scope);
        assertEquals(42.0, result, 0.0);

        // Expression: x / 2.0 + 2.0
        Expression var = new VariableExpression("x");
        Expression two = new ConstantValueExpression(2.0);
        Expression divByTwo = new FunctionExpression(new DivisionFunction(), var, two);
        Expression plusTwo = new FunctionExpression(new SumFunction(), divByTwo, two);

        result = plusTwo.evaluate(scope);
        assertEquals(23.0, result, 0.0);
    }
}
