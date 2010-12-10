package fc.lang;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fc.lang.Function;
import fc.lang.FunctionApplicationException;
import fc.lang.SumFunction;

public class SumFunctionTest {
    @Test
    public void testSumFunction() throws FunctionApplicationException {
        double[] arguments = {6.0, 8.0};

        Function function = new SumFunction();
        double result = function.apply(arguments);
        assertEquals(14.0, result, 0.0);
    }

    @Test
    public void testSumFunctionZero()
            throws FunctionApplicationException {
        double[] arguments = {0, 0};

        Function function = new SumFunction();
        double result = function.apply(arguments);
        assertEquals(0.0, result, 0.0);
    }

    @Test(expected = FunctionApplicationException.class)
    public void testSumFunctionInvalidArgument()
            throws FunctionApplicationException {
        double[] arguments = {};

        Function function = new SumFunction();
        function.apply(arguments);
    }
}
