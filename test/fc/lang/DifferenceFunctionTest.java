package fc.lang;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fc.lang.DifferenceFunction;
import fc.lang.Function;
import fc.lang.FunctionApplicationException;

public class DifferenceFunctionTest {
    @Test
    public void testDifferenceFunction() throws FunctionApplicationException {
        double[] arguments = {6.0, 8.0};

        Function function = new DifferenceFunction();
        double result = function.apply(arguments);
        assertEquals(-2.0, result, 0.0);
    }

    @Test
    public void testDifferenceFunctionZero()
            throws FunctionApplicationException {
        double[] arguments = {0, 0};

        Function function = new DifferenceFunction();
        double result = function.apply(arguments);
        assertEquals(0.0, result, 0.0);
    }

    @Test(expected = FunctionApplicationException.class)
    public void testDifferenceFunctionInvalidArgument()
            throws FunctionApplicationException {
        double[] arguments = {};

        Function function = new DifferenceFunction();
        function.apply(arguments);
    }
}
