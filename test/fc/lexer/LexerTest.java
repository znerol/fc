package fc.lexer;

import static org.junit.Assert.*;

import org.junit.Test;

import fc.lexer.Lexer;
import fc.lexer.Symbol;
import fc.lexer.Token;

public class LexerTest {
    @Test
    public void testEmptyString() {
        final Lexer lexer = new Lexer("");
        Token t = lexer.nextToken();
        assertEquals(Symbol.END, t.getSymbol());
    }
    
    @Test
    public void testWhitespaceOnly() {
        final Lexer lexer = new Lexer(" \t\n \r");
        Token t = lexer.nextToken();
        assertEquals(Symbol.END, t.getSymbol());
    }

    @Test
    public void testSimpleOperators() {
        final Lexer lexer = new Lexer("=()+-*/");
        Token token;

        token = lexer.nextToken();
        assertEquals(Symbol.EQUAL, token.getSymbol());
        token = lexer.nextToken();
        assertEquals(Symbol.LPAREN, token.getSymbol());
        token = lexer.nextToken();
        assertEquals(Symbol.RPAREN, token.getSymbol());
        token = lexer.nextToken();
        assertEquals(Symbol.PLUS, token.getSymbol());
        token = lexer.nextToken();
        assertEquals(Symbol.MINUS, token.getSymbol());
        token = lexer.nextToken();
        assertEquals(Symbol.MULTIPLY, token.getSymbol());
        token = lexer.nextToken();
        assertEquals(Symbol.DIVIDE, token.getSymbol());
        token = lexer.nextToken();
        assertEquals(Symbol.END, token.getSymbol());
    }

    @Test
    public void testScanLet() {
        final Lexer lexer = new Lexer("let");
        Token token;
        
        token = lexer.nextToken();
        assertEquals(Symbol.LET, token.getSymbol());
        token = lexer.nextToken();
        assertEquals(Symbol.END, token.getSymbol());
    }

    @Test
    public void testScanName() {
        final Lexer lexer = new Lexer("x");
        Token token;
        
        token = lexer.nextToken();
        assertEquals(Symbol.IDENTIFIER, token.getSymbol());
        assertEquals("x", token.getStringValue());
        token = lexer.nextToken();
        assertEquals(Symbol.END, token.getSymbol());
    }

    @Test
    public void testScanConstantNumber() {
        Lexer lexer;
        Token token;

        lexer = new Lexer("42");
        token = lexer.nextToken();
        assertEquals(Symbol.NUMBER, token.getSymbol());
        assertEquals(42, token.getNumberValue().doubleValue(), 0.0);
        token = lexer.nextToken();
        assertEquals(Symbol.END, token.getSymbol());

        lexer = new Lexer("2.89");
        token = lexer.nextToken();
        assertEquals(Symbol.NUMBER, token.getSymbol());
        assertEquals(2.89, token.getNumberValue().doubleValue(), 0.0);
        token = lexer.nextToken();
        assertEquals(Symbol.END, token.getSymbol());

        lexer = new Lexer("2.0E8");
        token = lexer.nextToken();
        assertEquals(Symbol.NUMBER, token.getSymbol());
        assertEquals(2.0E8, token.getNumberValue().doubleValue(), 0.0);
        token = lexer.nextToken();
        assertEquals(Symbol.END, token.getSymbol());
    }

    @Test
    public void testScanWhitoutWhitespace() {
        Lexer lexer;
        Token token;

        lexer = new Lexer("42/2+6-4*1");
        token = lexer.nextToken();
        assertEquals(Symbol.NUMBER, token.getSymbol());
        assertEquals(42, token.getNumberValue().doubleValue(), 0.0);

        token = lexer.nextToken();
        assertEquals(Symbol.DIVIDE, token.getSymbol());

        token = lexer.nextToken();
        assertEquals(Symbol.NUMBER, token.getSymbol());
        assertEquals(2, token.getNumberValue().doubleValue(), 0.0);
       
        token = lexer.nextToken();
        assertEquals(Symbol.PLUS, token.getSymbol());

        token = lexer.nextToken();
        assertEquals(Symbol.NUMBER, token.getSymbol());
        assertEquals(6, token.getNumberValue().doubleValue(), 0.0);

        token = lexer.nextToken();
        assertEquals(Symbol.MINUS, token.getSymbol());

        token = lexer.nextToken();
        assertEquals(Symbol.NUMBER, token.getSymbol());
        assertEquals(4, token.getNumberValue().doubleValue(), 0.0);

        token = lexer.nextToken();
        assertEquals(Symbol.MULTIPLY, token.getSymbol());

        token = lexer.nextToken();
        assertEquals(Symbol.NUMBER, token.getSymbol());
        assertEquals(1, token.getNumberValue().doubleValue(), 0.0);

        token = lexer.nextToken();
        assertEquals(Symbol.END, token.getSymbol());
    }
}
