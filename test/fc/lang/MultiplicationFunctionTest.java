package fc.lang;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fc.lang.Function;
import fc.lang.FunctionApplicationException;
import fc.lang.MultiplicationFunction;

public class MultiplicationFunctionTest {
    @Test
    public void testMultiplicationFunction() throws FunctionApplicationException {
        double[] arguments = {6.0, 8.0};

        Function function = new MultiplicationFunction();
        double result = function.apply(arguments);
        assertEquals(48.0, result, 0.0);
    }

    @Test
    public void testMultiplicationFunctionZero()
            throws FunctionApplicationException {
        double[] arguments = {0, 0};

        Function function = new MultiplicationFunction();
        double result = function.apply(arguments);
        assertEquals(0.0, result, 0.0);
    }

    @Test(expected = FunctionApplicationException.class)
    public void testMultiplicationFunctionInvalidArgument()
            throws FunctionApplicationException {
        double[] arguments = {0};

        Function function = new MultiplicationFunction();
        function.apply(arguments);
    }
}
