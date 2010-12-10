package fc.lang;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fc.lang.ConstantValueExpression;
import fc.lang.EvaluationException;
import fc.lang.Scope;

@RunWith(MockitoJUnitRunner.class)
public class ConstantValueExpressionTest {
    @Mock
    private Scope scope;

    @Test
    public void testConstantValueExpression() throws EvaluationException {
        ConstantValueExpression expression = new ConstantValueExpression(42.0);
        double result = expression.evaluate(scope);
        assertEquals(42.0, result, 0.0);
    }
}