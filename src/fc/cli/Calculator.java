package fc.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fc.lang.EvaluationException;
import fc.lang.Scope;
import fc.lang.UnboundVariableException;
import fc.parser.common.ParseException;
import fc.parser.common.Parser;

public class Calculator {
    private static String prompt = "> ";
    
    public static void main(String[] args) {
        String parserName = System.getProperty("parser", "fc.parser.rdp.Parser");
        Parser parser;

        try {
            parser = (Parser) Class.forName(parserName).newInstance();
        }
        catch (InstantiationException e) {
            System.out.println(e);
            System.out.println("ERROR: Failed to instantiate the specified parser class");
            return;
        }
        catch (IllegalAccessException e) {
            System.out.println(e);
            System.out.println("ERROR: Failed to access the specified parser class");
            return;
        }
        catch (ClassNotFoundException e) {
            System.out.println(e);
            System.out.println("ERROR: The specified parser class was not found");
            return;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Scope scope = new Scope();

        while(true) {
            System.out.print(prompt);
            String line = "";

            try {
                line = in.readLine();
            }
            catch (IOException e) {
                System.out.println(e);
                break;
            }

            if (line == null) {
                break;
            }

            try {
                // parse and evaluate the line
                double result = parser.parse(line).evaluate(scope);

                try {
                    scope.resolve("_exit");
                    break;
                }
                catch(UnboundVariableException ex) {
                    // just continue if special _exit is not defined in scope
                }

                // print result and continue
                System.out.println(result);
            }
            catch (EvaluationException e) {
                System.out.println(e);
            }
            catch (ParseException e) {
                System.out.println(e);
            }

        }

        System.out.println("bye");
    }
}
