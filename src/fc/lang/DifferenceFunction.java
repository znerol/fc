package fc.lang;

/**
 * Return the difference of the values from the argument list
 */
public class DifferenceFunction implements Function {
    @Override
    public double apply(double... arguments)
            throws FunctionApplicationException {

        if (arguments.length < 2) {
            throw new FunctionApplicationException(
                    "DifferenceFunction takes two or more arguments");
        }

        double result = arguments[0];

        for (int i = 1; i < arguments.length; i++) {
            result -= arguments[i];
        }

        return result;
    }
}
