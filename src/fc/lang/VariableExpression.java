package fc.lang;

/**
 * Expression which evaluates to the value of the given named variable
 */
public class VariableExpression implements Expression {
    private final String name;
   
    public VariableExpression(String name) {
        this.name = name;
    }

    @Override
    public double evaluate(Scope scope) throws EvaluationException {
        return scope.resolve(name).evaluate(scope);
    }
}
