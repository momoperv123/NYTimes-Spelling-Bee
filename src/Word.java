// Mohammad Pervaiz
// Lab Section 111D

/**
 * Represents a word with constraints that it must only contain lowercase letters.
 * <p>
 * This class encapsulates a word for use in applications requiring validation
 * of word content. It ensures that each instance of {@code Word} contains only
 * lowercase letters by throwing an {@code IllegalWordException} if the
 * validation fails upon instantiation.
 * </p>
 */
public class Word {
    /**
     * The word represented as a {@code String}.
     */
    protected String word;

    /**
     * Constructs a {@code Word} object with the specified string.
     * <p>
     * This constructor validates that the given string contains only lowercase
     * letters. If the string includes any characters outside of the lowercase
     * alphabet, an {@code IllegalWordException} is thrown.
     * </p>
     * 
     * @param word The string representation of the word.
     * @throws IllegalWordException if the word contains characters other than lowercase letters.
     */
    public Word(String word) throws IllegalWordException {
        // Validate that the word contains only lowercase letters.
        if (!word.matches("^[a-z]+$")) {
            // If the validation fails, throw an exception with a message indicating the illegal word.
            throw new IllegalWordException("Illegal word detected: " + word);
        }
        this.word = word;
    }

    /**
     * Retrieves the word.
     * <p>
     * This method allows access to the word encapsulated by the {@code Word} object.
     * </p>
     * 
     * @return The word as a {@code String}.
     */
    public String getWord() {
        return word;
    }
}
