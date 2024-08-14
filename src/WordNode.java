// Mohammad Pervaiz
// Lab Section 111D

/**
 * Represents a node in a linked list of {@code Word} objects. Each {@code WordNode}
 * contains a reference to a {@code Word} object and a link to the next node in the list,
 * enabling the construction of singly linked lists.
 */
public class WordNode {
    /**
     * The {@code Word} object stored in this node.
     */
    protected Word data;

    /**
     * A reference to the next {@code WordNode} in the list, or {@code null} if this node
     * is the last element of the list.
     */
    protected WordNode next;
    
    /**
     * Constructs a new {@code WordNode} with the specified {@code Word} object.
     * The {@code next} reference is initially set to {@code null}, indicating
     * that this node does not yet link to another node in the list.
     *
     * @param data The {@code Word} object to be stored in this node.
     */
    public WordNode(Word data) {
        this.data = data;
        this.next = null; // This node initially does not link to any other node
    }
}
