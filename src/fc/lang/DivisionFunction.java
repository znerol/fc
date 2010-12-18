package fc.lang;

/**
 * Divide the first argument by the remaining ones
 */
public class DivisionFunction implements Function {
    @Override
    public double apply(double... arguments)
            throws FunctionApplicationException {

        if (arguments.length < 2) {
            throw new FunctionApplicationException(
                "DivisionFunction takes two or more arguments");
        }

        double result = arguments[0];
        for (int i = 1; i < arguments.length; i++) {
            if (arguments[i] == 0) {
                throw new FunctionApplicationException("Division by 0");
            }
            result /= arguments[i];
        }

        return result;
    }
}
