package fc.lexer;

/**
 * Interface for classes which provide information about character position in
 * the input string.
 */
public interface StringPosition {
    /**
     * @return the 1-based position in the input line this object is referring
     *         to.
     */
    public int getColumn();
}
