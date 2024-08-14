// Mohammad Pervaiz
// Lab Section 111D

/**
 * Represents an exception thrown when a word does not meet specified criteria.
 * <p>
 * This exception is specifically used to indicate that a word, typically expected
 * to contain only lowercase letters, includes characters outside of this constraint.
 * It extends {@link IllegalArgumentException} to indicate that the issue arises
 * from an illegal argument provided to a method, in this case, an invalid word.
 * </p>
 */
public class IllegalWordException extends IllegalArgumentException {
    /**
     * Constructs an {@code IllegalWordException} with a detailed message.
     * <p>
     * The message typically contains information about the nature of the error,
     * such as the reason why the word is considered illegal. This can aid in
     * debugging and user feedback by providing a clear explanation of the issue.
     * </p>
     * 
     * @param message A string describing the reason for the exception.
     */
    public IllegalWordException(String message) {
        super(message); // Call the superclass constructor with the provided message
    }
}
