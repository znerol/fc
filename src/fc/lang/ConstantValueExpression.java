package fc.lang;

/**
 * Simple expression encapsulating a constant double value
 */
public class ConstantValueExpression implements Expression {
    private final double value;

    public ConstantValueExpression(double value) {
        this.value = value;
    }

    @Override
    public double evaluate(Scope scope) throws EvaluationException {
        return value;
    }
}
