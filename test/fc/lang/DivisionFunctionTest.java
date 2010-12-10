package fc.lang;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fc.lang.DivisionFunction;
import fc.lang.Function;
import fc.lang.FunctionApplicationException;

public class DivisionFunctionTest {
    @Test
    public void testDivisionFunction() throws FunctionApplicationException {
        double[] arguments = {48.0, 8.0};

        Function function = new DivisionFunction();
        double result = function.apply(arguments);
        assertEquals(6.0, result, 0.0);
    }

    @Test
    public void testDivisionFunctionDevideZero()
            throws FunctionApplicationException {
        double[] arguments = {0, 8.0};

        Function function = new DivisionFunction();
        double result = function.apply(arguments);
        assertEquals(0.0, result, 0.0);
    }

    @Test(expected = FunctionApplicationException.class)
    public void testDivisionFunctionDivisionByZero()
            throws FunctionApplicationException {
        double[] arguments = {8.0, 0};

        Function function = new DivisionFunction();
        function.apply(arguments);
    }

    @Test(expected = FunctionApplicationException.class)
    public void testDivisionFunctionInvalidArgument()
            throws FunctionApplicationException {
        double[] arguments = {};

        Function function = new DivisionFunction();
        function.apply(arguments);
    }
}
