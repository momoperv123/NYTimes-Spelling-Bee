// Mohammad Pervaiz
// Lab Section 111D

/**
 * Represents a sorted list of words, extending the {@code WordList} class.
 * Words added to this list are automatically placed in their correct position
 * based on alphabetical order, ensuring the list is always sorted.
 */
public class SortedWordList extends WordList {

    /**
     * Constructs an empty {@code SortedWordList}.
     */
    public SortedWordList() {
        super(); // Call the constructor of the superclass (WordList)
    }

    /**
     * Adds a word to the list in sorted order. The method inserts the word
     * such that the list maintains its sorted state, ordering words
     * alphabetically without regard to case.
     *
     * @param word The {@code Word} object to be added to the list.
     */
    public void add(Word word) {
        WordNode newNode = new WordNode(word); // Create a new node for the word
        // If the list is empty or the new word comes before the first word, insert at the beginning
        if (first == null || word.getWord().compareToIgnoreCase(first.data.getWord()) <= 0) {
            newNode.next = first;
            first = newNode;
        } else {
            // Find the insertion point where the new word comes after the current word
            WordNode current = first;
            while (current.next != null && current.next.data.getWord().compareToIgnoreCase(word.getWord()) < 0) {
                current = current.next;
            }
            // Insert the new word in the found location
            newNode.next = current.next;
            current.next = newNode;
        }
        // Update the last node reference if the new word is the last alphabetically
        if (last == null || word.getWord().compareToIgnoreCase(last.data.getWord()) >= 0) {
            last = newNode;
        }
        length++; // Increment the length of the list
    }

    /**
     * Prints all words in the list to the standard output, one word per line.
     * Words are printed in the order they appear in the list, which is sorted alphabetically.
     */
    public void printList() {
        WordNode current = first; // Start from the first node
        while(current != null) {
            System.out.println(current.data.getWord()); // Print the word contained in the current node
            current = current.next; // Move to the next node
        }
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
