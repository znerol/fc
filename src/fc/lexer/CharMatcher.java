package fc.lexer;

/**
 * Simple interface for classes which implement conditions on char-values.
 */
public interface CharMatcher {
    /**
     * Return true if the given character complies with the implemented
     * condition.
     * 
     * @param c
     *            character to test
     * @return true if character matches condition false otherwise
     */
    boolean match(char c);
}
