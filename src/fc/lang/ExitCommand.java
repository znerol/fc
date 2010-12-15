package fc.lang;

public class ExitCommand implements Expression {

    @Override
    public double evaluate(Scope scope) throws EvaluationException {
        scope.bind("_exit", new ConstantValueExpression(1.0));
        return 0;
    }
}
