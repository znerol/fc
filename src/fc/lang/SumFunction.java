package fc.lang;


/**
 * Return the sum of the values from the argument list
 */
public class SumFunction implements Function {
    @Override
    public double apply(double... arguments) throws FunctionApplicationException {
        if (arguments.length < 2) {
            throw new FunctionApplicationException(
                "MultiplicationFunction takes two or more arguments");
        }

        double result = 0.0;
        for (int i = 0; i < arguments.length; i++) {
            result += arguments[i];
        }

        return result;
    }
}
