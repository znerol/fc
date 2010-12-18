package fc.lang;

/**
 * Interface for classes providing mathematical functions or operations.
 */
public interface Function {
    /**
     * Apply the function to the arguments and throw an exception if it is not
     * possible.
     *
     * @param arguments a list of number values
     * @return resulting value
     * @throws FunctionApplicationException
     */
    public double apply(double... arguments) throws FunctionApplicationException;
}
