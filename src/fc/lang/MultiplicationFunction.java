package fc.lang;

/**
 * Build the product from the values in the argument list
 */
public class MultiplicationFunction implements Function {
    @Override
    public double apply(double... arguments) throws FunctionApplicationException {
        if (arguments.length < 2) {
            throw new FunctionApplicationException(
                "MultiplicationFunction takes two or more arguments");
        }

        double result = arguments[0];
        for (int i = 1; i < arguments.length; i++) {
            result *= arguments[i];
        }

        return result;
    }
}
