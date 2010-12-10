package fc.lang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fc.lang.EvaluationException;
import fc.lang.Expression;
import fc.lang.Function;
import fc.lang.FunctionApplicationException;
import fc.lang.FunctionExpression;
import fc.lang.Scope;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FunctionExpressionTest {
    @Mock
    private Scope scope;
    @Mock
    private Function function;
    @Mock
    private Expression arg1;
    @Mock
    private Expression arg2;

    @Test
    public void testFunctionExpression() throws EvaluationException {
        final Expression[] arguments = {arg1, arg2};
        FunctionExpression expression = new FunctionExpression(function,
                arguments);

        when(function.apply((double[]) anyVararg())).thenReturn(42.0);

        double result = expression.evaluate(scope);
        assertEquals(42.0, result, 0.0);

        verify(function).apply((double[]) anyVararg());
        verify(arg1).evaluate(scope);
        verify(arg2).evaluate(scope);

        verifyNoMoreInteractions(scope);
        verifyNoMoreInteractions(function);
        verifyNoMoreInteractions(arg1);
        verifyNoMoreInteractions(arg2);
    }
    
    @Test(expected=FunctionApplicationException.class)
    public void testFunctionThrowingAnException() throws EvaluationException {
        final Expression[] arguments = {arg1, arg2};
        FunctionExpression expression = new FunctionExpression(function,
                arguments);

        when(function.apply((double[]) anyVararg()))
            .thenThrow(new FunctionApplicationException("Error"));
        expression.evaluate(scope);
    }
}
