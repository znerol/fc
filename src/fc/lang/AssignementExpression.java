package fc.lang;

/**
 * Evaluates an expression and assigns the result to a given named variable.
 */
public class AssignementExpression implements Expression {
    private final String name;
    private final Expression expression;

    public AssignementExpression(String name, Expression expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public double evaluate(Scope scope) throws EvaluationException {
        double result = expression.evaluate(scope);
        scope.bind(name, new ConstantValueExpression(result));
        return result;
    }
}
