package fc.lang;

/**
 * ExitCommand simply puts a magic value in the scope. The scope is tested in
 * the main loop and if it contains the magic value the program exits.
 */
public class ExitCommand implements Expression {

    @Override
    public double evaluate(Scope scope) throws EvaluationException {
        scope.bind("_exit", new ConstantValueExpression(1.0));
        return 0;
    }
}
