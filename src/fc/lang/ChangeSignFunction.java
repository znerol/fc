package fc.lang;

/**
 * Return the first argument with inverted sign
 */
public class ChangeSignFunction implements Function {
    @Override
    public double apply(double... arguments)
            throws FunctionApplicationException {

        if (arguments.length != 1) {
            throw new FunctionApplicationException(
                    "ChangeSignFunction accepts only one argument");
        }

        return -arguments[0];
    }
}
