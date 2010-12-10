package fc.lang;

import static org.junit.Assert.*;

import org.junit.Test;

import fc.lang.ConstantValueExpression;
import fc.lang.Expression;
import fc.lang.Scope;
import fc.lang.UnboundVariableException;

public class ScopeTest {
    private final Expression expression = new ConstantValueExpression(42.);
    private final Scope scope = new Scope();

    @Test
    public void bindUnbindResolveTest() {
        scope.bind("x", expression);

        Expression result = null;
        try {
            result = scope.resolve("x");
        }
        catch (UnboundVariableException ex) {
            fail("Variable should exist in scope by now");
        }
        assertSame(expression, result);

        scope.unbind("x");

        try {
            result = scope.resolve("x");
            fail("Must throw exception if variable does not exist in scope");
        }
        catch (UnboundVariableException ex) {
            // ok
        }
    }
}
