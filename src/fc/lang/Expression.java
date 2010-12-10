package fc.lang;

/**
 * An expression which may be evaluated to a double value.
 */
public interface Expression {
    double evaluate(Scope scope) throws EvaluationException;
}
