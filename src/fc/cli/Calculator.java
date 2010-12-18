package fc.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fc.lang.EvaluationException;
import fc.lang.Scope;
import fc.lang.UnboundVariableException;
import fc.lexer.LexerException;
import fc.lexer.StringPosition;
import fc.parser.common.ParseException;
import fc.parser.common.Parser;

/**
 * Command line interface for the floating point calculator. By means of the
 * 'parser' property it is possible to choose which parser implementation is
 * used:
 * 
 * <pre>
 * java fc.cli.Calculator -Dparser=fc.parser.rdp.Parser
 * java fc.cli.Calculator -Dparser=fc.parser.javacc.Parser
 * </pre>
 */
public class Calculator {
    private static String prompt = "> ";
    private static String outprefix = "< ";
    private static String markprefix = "--";
    private static char markfill = '-';
    private static char marksign = '^';

    /**
     * Print a visual mark indicating the column in the input string where a
     * problem occurred.
     * 
     * @param object
     *            Some object implementing the StringPosition interface.
     *            Typically a ParseException or LexerException.
     */
    public static void printColumnMarker(StringPosition object) {
        System.out.print(markprefix);

        for (int i = 1; i < object.getColumn(); i++) {
            System.out.print(markfill);
        }

        System.out.println(marksign);
    }

    /**
     * Command line interface for the floating point calculator main function.
     * 
     * @param args
     *            this program does not take any arguments
     */
    public static void main(String[] args) {
        String parserName = System.getProperty("parser",
                "fc.parser.rdp.Parser");
        Parser parser;

        // Load parser class and create new instance
        try {
            parser = (Parser) Class.forName(parserName).newInstance();
        }
        catch (InstantiationException e) {
            System.out.println(e);
            System.out.println(
                    "ERROR: Failed to instantiate the specified parser class");
            return;
        }
        catch (IllegalAccessException e) {
            System.out.println(e);
            System.out.println(
                    "ERROR: Failed to access the specified parser class");
            return;
        }
        catch (ClassNotFoundException e) {
            System.out.println(e);
            System.out.println(
                    "ERROR: The specified parser class was not found");
            return;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Scope scope = new Scope();

        // loop over input lines entered by the user interactively
        while (true) {
            System.out.print(prompt);
            String input;

            try {
                input = in.readLine();
            }
            catch (IOException e) {
                System.out.println(e);
                break;
            }

            // readLine returns null if the stream was closed (e.g. the user hit
            // ctl-c).
            if (input == null) {
                break;
            }

            try {
                // parse and evaluate the line
                double result = parser.parse(input).evaluate(scope);

                try {
                    // ExitCommand simply puts a magic value in the scope for
                    // us. In reality we would have some sort of system function
                    // table in the scope (or in another place accessible from
                    // fc.lang.Expression.evaluate) where we could place some
                    // callback mechanism which would instruct the run loop to
                    // exit.
                    scope.resolve("_exit");
                    break;
                }
                catch (UnboundVariableException ex) {
                    // just continue if special _exit is not defined in scope.
                    // This is normally the case.
                }

                // print result and continue
                System.out.println(outprefix + result);
            }
            catch (EvaluationException e) {
                System.out.println(e.getLocalizedMessage());
            }
            catch (ParseException e) {
                printColumnMarker(e);
                System.out.println(e.getLocalizedMessage());
            }
            catch (LexerException e) {
                printColumnMarker(e);
                System.out.println(e.getLocalizedMessage());
            }

        }

        System.out.println("bye");
    }
}
