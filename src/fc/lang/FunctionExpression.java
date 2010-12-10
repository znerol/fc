package fc.lang;

/**
 * Apply given function to arguments on evaluation.
 */
public class FunctionExpression implements Expression {
    private final Function function;
    private final Expression[] arguments;

    public FunctionExpression(Function function, Expression... arguments){
        this.function = function;
        this.arguments = arguments;
    }

    @Override
    public double evaluate(Scope scope) throws EvaluationException {
        double[] argumentValues = new double[arguments.length];

        for (int i = 0; i<arguments.length; i++) {
            argumentValues[i] = arguments[i].evaluate(scope);
        }

        return function.apply(argumentValues);
    }
}
