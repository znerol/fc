package fc.lang;

import static org.junit.Assert.*;

import org.junit.Test;

import fc.lang.ChangeSignFunction;
import fc.lang.Function;
import fc.lang.FunctionApplicationException;

public class ChangeSignFunctionTest {

    @Test
    public void testChangeSignFunction() throws FunctionApplicationException {
        double[] arguments = {7.3};

        Function function = new ChangeSignFunction();
        double result = function.apply(arguments);
        assertEquals(-7.3, result, 0.0);
    }

    @Test
    public void testChangeSignFunctionZero()
            throws FunctionApplicationException {
        double[] arguments = {0};

        Function function = new ChangeSignFunction();
        double result = function.apply(arguments);
        assertEquals(0.0, result, 0.0);
    }

    @Test(expected = FunctionApplicationException.class)
    public void testChangeSignFunctionInvalidArgument()
            throws FunctionApplicationException {
        double[] arguments = {};

        Function function = new ChangeSignFunction();
        function.apply(arguments);
    }

    @Test(expected = FunctionApplicationException.class)
    public void testChangeSignFunctionTooManyArguments()
            throws FunctionApplicationException {
        double[] arguments = {7.3, -3};

        Function function = new ChangeSignFunction();
        function.apply(arguments);
    }
}
