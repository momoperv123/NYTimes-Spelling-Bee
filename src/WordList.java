// Mohammad Pervaiz
// Lab Section 111D

/**
 * Represents an abstract base class for a list of {@code Word} objects. This class
 * defines the basic structure and capabilities common to lists of words, including
 * methods for appending words to the list, adding words in a specific order, and
 * printing the list. It uses a linked list implementation with references to the
 * first and last nodes in the list and keeps track of the list's length.
 */
public abstract class WordList {
    /**
     * Reference to the first node of the linked list. If the list is empty, {@code first} is {@code null}.
     */
    protected WordNode first;

    /**
     * Reference to the last node of the linked list. If the list is empty, {@code last} is {@code null}.
     */
    protected WordNode last;

    /**
     * The number of elements in the list. Initialized to 0 for an empty list.
     */
    protected int length = 0;
    
    /**
     * Constructs an empty {@code WordList} with no elements.
     */
    public WordList() {
        first = null;
        last = null;
    }
    
    /**
     * Appends a {@code Word} to the end of the list. This method is intended to be used
     * by subclasses to add words to the list in a manner specific to the subclass's
     * sorting or ordering criteria.
     *
     * @param word The {@code Word} to append to the list.
     */
    protected void append(Word word) {
        WordNode newNode = new WordNode(word);
        if (first == null) { // List is empty
            first = newNode;
            last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        length++;
    }
    
    /**
     * Adds a {@code Word} to the list. Subclasses should override this method to implement
     * specific adding behaviors, such as maintaining a sorted order. This default
     * implementation behaves similarly to {@code append}, but is intended to be
     * overridden.
     *
     * @param word The {@code Word} to add to the list.
     */
    public void add(Word word) {
        // This method provides a default implementation. Subclasses should override it.
        append(word);
    }
    
    /**
     * Prints all {@code Word} objects in the list to standard output, one per line.
     * This method traverses the list from the first to the last element, printing
     * each word.
     */
    public void printList() {
        WordNode current = first;
        while (current != null) {
            System.out.println(current.data.getWord());
            current = current.next;
        }
    }
}
