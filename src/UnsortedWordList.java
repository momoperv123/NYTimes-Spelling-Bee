// Mohammad Pervaiz
// Lab Section 111D

/**
 * Represents an unsorted list of words. This class extends {@code WordList},
 * providing functionalities to add words in an unsorted manner and to print
 * and check the presence of words in the list.
 */
public class UnsortedWordList extends WordList {

    /**
     * Constructs an empty {@code UnsortedWordList}.
     */
    public UnsortedWordList() {
        super(); // Call the constructor of the superclass (WordList)
    }

    /**
     * Adds a word to the end of the list. The list remains unsorted regardless of
     * the order in which words are added.
     *
     * @param word The {@code Word} to be added to the list.
     */
    public void add(Word word) {
        super.append(word); // Utilizes the append method from the superclass to add the word
    }

    /**
     * Prints all words in the list to the standard output, one word per line.
     */
    public void printList() {
        WordNode current = first; // Start from the first node in the list
        while (current != null) {
            System.out.println(current.data.getWord()); // Print the word contained in the current node
            current = current.next; // Move to the next node
        }
    }

    /**
     * Checks if a specific word is present in the list. The search is case-insensitive.
     *
     * @param word The {@code Word} to be searched in the list.
     * @return {@code true} if the word is found in the list, {@code false} otherwise.
     */
    public boolean contains(Word word) {
        WordNode current = first; // Start from the first node in the list
        while (current != null) {
            // Compare the current node's word with the target word, ignoring case differences
            if (current.data.getWord().equalsIgnoreCase(word.getWord())) {
                return true; // Word found
            }
            current = current.next; // Move to the next node
        }
        return false; // Word not found in the list
    }
    
    /**
     * Calculates and returns the size of the list.
     * <p>
     * This method iterates through each node in the linked list, starting from the first node,
     * and increments a counter until it reaches the end of the list (where {@code current} becomes null).
     * The count reflects the total number of nodes, which is the size of the list.
     * 
     * @return The number of elements in the list as an integer.
     */
    public int size() {
        // Initial count of elements set to 0
        int count = 0;
        // Start iterating from the first node
        WordNode current = first;
        while(current != null) { // Continue until the end of the list is reached
            count++; // Increment the count for each node encountered
            current = current.next; // Move to the next node
        }
        return count; // Return the total count as the size of the list
    }
    
    /**
     * Clears the list, removing all elements and resetting it to its initial state.
     */
    public void clear() {
        first = null; // Remove reference to the first node, effectively clearing the list
        last = null;  // Remove reference to the last node, if you're using a 'last' pointer
        length = 0;   // Reset the length of the list to 0
    }
}
