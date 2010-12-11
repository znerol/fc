package fc.lang;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of flat static scope
 */
public class Scope {
    private final Map<String, Expression> bindings = new HashMap<String, Expression>();

    /**
     * Bind a variable with the given name to an expression. If the variable
     * already exists in the scope, the old value will be replaced with the new
     * one.
     * 
     * @param name
     *            of the variable
     * @param expression
     *            which should be bound to the given name
     */
    public void bind(String name, Expression expression) {
        bindings.put(name, expression);
    }

    /**
     * Delete the bound variable with the given name in this scope.
     * 
     * @param name
     *            of a variable bound in this scope
     */
    public void unbind(String name) {
        bindings.remove(name);
    }

    /**
     * Resolve the value of the bound variable with the given name
     * 
     * @param name
     *            of a variable bound in this scope
     * @return Expression representing the value of the variable
     * @throws UnboundVariableException
     *             given variable is not bound in the current scope.
     */
    public Expression resolve(String name) throws UnboundVariableException {
        Expression result = bindings.get(name);

        if (result == null) {
            throw new UnboundVariableException("Variable '" + name
                    + "' is not bound");
        }

        return result;
    }
}
