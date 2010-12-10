package fc.lang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fc.lang.AssignementExpression;
import fc.lang.EvaluationException;
import fc.lang.Expression;
import fc.lang.Scope;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AssignementExpressionTest {
    @Mock
    private Scope scope;
    @Mock
    private Expression expression;

    @Test
    public void testAssignementExpression() throws EvaluationException {
        when(expression.evaluate(scope)).thenReturn(42.0);

        AssignementExpression assignementExpression =
            new AssignementExpression("x", expression);
        double result = assignementExpression.evaluate(scope);
        assertEquals(42.0, result, 0.0);

        verify(expression).evaluate(scope);
        verify(scope).bind(eq("x"), any(Expression.class));
        verifyNoMoreInteractions(expression);
        verifyNoMoreInteractions(scope);
    }
}
