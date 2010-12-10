package fc.lang;

public interface Function {
    public double apply(double... arguments) throws FunctionApplicationException;
}
