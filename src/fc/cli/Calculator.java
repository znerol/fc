package fc.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import fc.lang.EvaluationException;
import fc.lang.Scope;
import fc.parser.common.ParseException;
import fc.parser.common.Parser;

public class Calculator {
    private static boolean done = false;
    private static String prompt = "> ";
    
    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        Parser parser = new fc.parser.rdp.Parser();
        Scope scope = new Scope();

        while(!done) {
            System.out.print(prompt);
            String line = "";

            try {
                line = in.readLine();
            }
            catch (IOException e) {
                System.out.println(e);
                return;
            }

            try {
                System.out.println(parser.parse(line).evaluate(scope));
            }
            catch (EvaluationException e) {
                System.out.println(e);
            }
            catch (ParseException e) {
                System.out.println(e);
            }
        }
    }
}
