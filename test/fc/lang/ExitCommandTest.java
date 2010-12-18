package fc.lang;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import fc.lang.EvaluationException;
import fc.lang.Expression;
import fc.lang.Scope;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ExitCommandTest {
    @Mock
    private Scope scope;

    @Test
    public void testExitCommand() throws EvaluationException {
        ExitCommand exitCommand = new ExitCommand();
        exitCommand.evaluate(scope);

        verify(scope).bind(eq("_exit"), any(Expression.class));
        verifyNoMoreInteractions(scope);
    }
}
