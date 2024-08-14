// Mohammad Pervaiz
// Lab Section 111D

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

/**
 * A graphical user interface for the Spelling Behive Word Puzzle Game.
 * Allows users to input words and checks them against a list of solutions.
 * Tracks the user's score and displays the guessed words.
 */
public class PuzzleGUI {
	private String puzzleLetters; // Puzzle letters for the game
    private UnsortedWordList solutionWords; // Solutions as an unsorted list
    private SortedWordList guessedWordsList; // Correctly guessed words in sorted order
    private HashSet<String> guessedWords; // Tracks words the user has already guessed
    private JLabel scoreLabel; // Displays the user's score
    private JTextArea guessedWordsTextArea; // Displays the guessed words
	private int score = 0; // Initializes the score so we can keep track of the user's valid solutionWords
	private int wordsGuessed = 0; // Initializes an integer variable to keep track of the number of words the user has guessed to check when they guessed all the words
	private JFrame frame; // Make frame an instance variable
	private JTextArea puzzleLettersTextArea; // Make text area for word puzzle letters
	/**
     * Constructs a PuzzleGUI object with the given puzzle letters and solution words.
     * @param puzzleLetters The puzzle letters.
     * @param solutionWords The solution words.
     */
    
    public PuzzleGUI(String puzzleLetters, UnsortedWordList solutionWords) {
        this.puzzleLetters = puzzleLetters;
        this.solutionWords = solutionWords;
        this.guessedWordsList = new SortedWordList();
        this.guessedWords = new HashSet<>(); // Initialize for tracking unique guesses
    }

    /**
     * Creates and shows the graphical user interface.
     */
    public void createAndShowGUI() {
    	// Creates the GUI JFrame to contain our content and have the user interact with the program
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Project 3 - NY Times Word Puzzle Game");

        // Gets the content pane in the JFrame and assigns it to a container so we can manipulate it
        Container myContentPane = frame.getContentPane();
        // Sets the layout of the content pane to a grid layout with 1 row and 2 columns
        myContentPane.setLayout(new GridLayout(1, 2));

        // Create a panel for the puzzle puzzleLetters label and text area to contain the puzzle puzzleLetters
        JPanel puzzleLettersPanel = new JPanel();
        puzzleLettersPanel.setLayout(new BorderLayout());

        // Sets the label up
        JLabel puzzleLettersLabel = new JLabel("Puzzle Letters");
        puzzleLettersLabel.setHorizontalAlignment(JLabel.CENTER);
        puzzleLettersPanel.add(puzzleLettersLabel, BorderLayout.NORTH);
        
        puzzleLettersTextArea = new JTextArea();

        // Centers the content in the text area containing the puzzle puzzleLetters
        puzzleLettersTextArea.setText(centerTextWithSpaces(puzzleLetters) + puzzleLetters);
        puzzleLettersTextArea.setEditable(false);
        puzzleLettersTextArea.setFont(puzzleLettersTextArea.getFont().deriveFont(Font.PLAIN, 30));

        puzzleLettersPanel.add(new JScrollPane(puzzleLettersTextArea), BorderLayout.CENTER);


        // Create the menu bar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        // Create the file menu
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        
        // Add "Open" menu item
        JMenuItem openItem = new JMenuItem("Open");
        fileMenu.add(openItem);
        
        // Add "Quit" menu item
        JMenuItem quitItem = new JMenuItem("Quit");
        fileMenu.add(quitItem);
        
        // Set action listeners
        openItem.addActionListener(new FileMenuHandler(this, frame));
        quitItem.addActionListener(e -> System.exit(0));
        
        // Create a panel for the button that lets the user enter input through a JOptionsPane
        JPanel guessButtonPanel = new JPanel();
        guessButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton guessButton = new JButton("Make a guess");
        guessButtonPanel.add(guessButton);

        guessButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userInput = JOptionPane.showInputDialog(frame, "Enter your word:");
                if (userInput != null && !userInput.trim().isEmpty()) {
                    String lowerCaseUserInput = userInput.toLowerCase().trim();
                    
                    if(lowerCaseUserInput.equalsIgnoreCase("Stop")) {
                    	JOptionPane.showMessageDialog(null, "Bye!");
                    	System.exit(0);
                    }
                    // Improvement 1: Check first letter
                    if (!lowerCaseUserInput.contains(String.valueOf(puzzleLetters.toLowerCase().charAt(0)))) {
                        JOptionPane.showMessageDialog(null, "The word must contain the first letter of the puzzle letters.");
                        return;
                    } else if (lowerCaseUserInput.length() < 5) {
                        JOptionPane.showMessageDialog(null, "The word must be at least 5 letters long.");
                        return;
                    }

                    // Check for valid letters
                    String lowerCasePuzzleLetters = puzzleLetters.toLowerCase();
                    boolean allLettersValid = lowerCaseUserInput.chars()
                        .mapToObj(c -> (char) c)
                        .allMatch(c -> lowerCasePuzzleLetters.contains(String.valueOf(c)));

                    if (!allLettersValid) {
                        JOptionPane.showMessageDialog(null, "Invalid letters used.");
                        return;
                    }

                    // Check if the word is in the solution list
                    if (!solutionWords.contains(new Word(lowerCaseUserInput))) {
                        JOptionPane.showMessageDialog(null, "Word not in solutions list.");
                        return;
                    }

                    if (!guessedWords.contains(lowerCaseUserInput) && solutionWords.contains(new Word(lowerCaseUserInput))) {
                        guessedWords.add(lowerCaseUserInput); // Track unique guesses

                        // Improvement 2: Calculate points with bonus
                        boolean usesAllLetters = puzzleLetters.toLowerCase().chars().allMatch(c -> lowerCaseUserInput.indexOf(c) >= 0);
                        score += usesAllLetters ? 3 : 1;
                        // Increases words guessed by 1 when the user guessed a word correctly
                        wordsGuessed++;

                        // Add to sorted list for display
                        guessedWordsList.add(new Word(lowerCaseUserInput));
                        updateGuessedWordsTextArea(); // Improvement 3: Display in alphabetical order
                        
                        scoreLabel.setText("Score: " + score); // Update score display
                    } else {
                    	JOptionPane.showMessageDialog(null, "Word already guessed."); // Handle invalid guess or duplicate guess
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a word.");
                }
                
                // Lets the user know when they guessed all the solutions in the solutions list
                if (wordsGuessed == solutionWords.size()) {
                	JPanel message = new JPanel(new BorderLayout());
                	JLabel mainText = new JLabel("You found all the words on the solutions list!");
                	mainText.setHorizontalAlignment(JLabel.CENTER); // Centers the main text
                	JLabel playAgain = new JLabel("Play again?");
                	playAgain.setHorizontalAlignment(JLabel.CENTER);

                	message.add(mainText, BorderLayout.NORTH);
                	message.add(playAgain, BorderLayout.CENTER);

                	int response = JOptionPane.showConfirmDialog(null, message, "Game Over", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (response == JOptionPane.YES_OPTION) {
                    	resetGame(); // Restart the game
                    } else {
                    	JOptionPane.showMessageDialog(null, "Bye!");
                        System.exit(0); // Exit the program
                    }
                }
            }
        });
        
        // Add the button panel to the bottom of panel1
        puzzleLettersPanel.add(guessButtonPanel, BorderLayout.SOUTH);

        // Create a panel for the second pair of title, text area, and score
        JPanel wordsGuessedPanel = new JPanel();
        wordsGuessedPanel.setLayout(new BorderLayout());
        JLabel wordsGuessedLabel = new JLabel("Words Guessed");
        wordsGuessedLabel.setHorizontalAlignment(JLabel.CENTER);
        wordsGuessedPanel.add(wordsGuessedLabel, BorderLayout.NORTH);

        // Initialize guessedWordsTextArea
        guessedWordsTextArea = new JTextArea();
        guessedWordsTextArea.setFont(guessedWordsTextArea.getFont().deriveFont(Font.PLAIN, 20));
        guessedWordsTextArea.setEditable(false);
        wordsGuessedPanel.add(new JScrollPane(guessedWordsTextArea), BorderLayout.CENTER);

        // Create a panel for displaying the score
        JPanel scorePanel = new JPanel();
        scorePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        // Keeps track of the user's score as they input valid solutionWords
        scoreLabel = new JLabel("Score: " + score);
        scoreLabel.setFont(scoreLabel.getFont().deriveFont(Font.PLAIN, 20));
        scorePanel.add(scoreLabel);
        wordsGuessedPanel.add(scorePanel, BorderLayout.SOUTH);

        // Add both panels to the content pane
        myContentPane.add(puzzleLettersPanel);
        myContentPane.add(wordsGuessedPanel);

        // Set JFrame visible to user
        frame.setVisible(true);
    }

    /**
     * Updates the text area that displays guessed words.
     * This method iterates through a linked list of guessed words and appends each word,
     * followed by a newline, to a StringBuilder. The content of the StringBuilder is then
     * set as the text of the guessedWordsTextArea to display it to the user.
     */
    private void updateGuessedWordsTextArea() {
        StringBuilder displayText = new StringBuilder();
        WordNode current = guessedWordsList.first; // Start from the first node of the list
        while (current != null) {
            // Append the word and a newline character to the StringBuilder
            displayText.append(current.data.getWord()).append("\n");
            current = current.next; // Move to the next node
        }
        // Set the constructed text to the text area
        guessedWordsTextArea.setText(displayText.toString());
    }

    /**
     * Resets the game to its initial state. This includes resetting the score and wordsGuessed 
     * counters, clearing any lists of guessed or solution words, and resetting UI components
     * to reflect these changes. It also attempts to reload puzzle data from a previously read
     * file, if available.
     */
    public void resetGame() {
        score = 0; // Reset the score
        wordsGuessed = 0; // Reset the number of words guessed
        guessedWords.clear(); // Clear guessed words list
        guessedWordsList.clear(); // Clear the list using its clear method
        solutionWords.clear(); // Clear the list of solution words

        // Reset UI components
        scoreLabel.setText("Score: " + score);
        guessedWordsTextArea.setText("");

        // Load new puzzle data if available
        PuzzleData data = Project3.readFile(); 
        if (data != null) {
            puzzleLetters = data.getPuzzleLetters();
            solutionWords = data.getSolutions();
        }
    }

    /**
     * Loads puzzle data from a specified file path, ensuring the file is entirely valid before updating the puzzle state.
     * <p>
     * This method first validates the entire file content, including puzzle letters and all solution words. 
     * It checks for the existence and readability of the file, reads through each word to ensure they conform 
     * to specified criteria (e.g., containing only lowercase letters), and only updates the puzzle state if 
     * the entire file is deemed valid. This approach prevents partial updates that could occur from reading 
     * an invalid file.
     * </p>
     * 
     * @param filePath The path to the file containing the puzzle data.
     */
    public void loadPuzzleData(String filePath) {
        File file = new File(filePath);

        // Check for file existence and readability.
        if (!file.exists() || !file.canRead()) {
            JOptionPane.showMessageDialog(frame, "File is invalid or unreadable: " + filePath);
            return;
        }

        String tempPuzzleLetters = ""; // Temporary storage for puzzle letters.
        UnsortedWordList tempSolutionWords = new UnsortedWordList(); // Temporary storage for solution words.
        boolean isValidFile = true; // Flag to determine file validity.

        try (Scanner scanner = new Scanner(file)) {
            // Attempt to read puzzle letters from the first line of the file.
            if (scanner.hasNextLine()) {
                tempPuzzleLetters = scanner.nextLine().trim();
            }

            // Read and validate each subsequent line as a solution word.
            while (scanner.hasNextLine() && isValidFile) {
                String wordStr = scanner.nextLine().trim();
                try {
                    Word word = new Word(wordStr); // May throw IllegalWordException for invalid words.
                    tempSolutionWords.add(word); // Add valid word to temporary list.
                } catch (IllegalWordException e) {
                    // Inform the user of the invalid word and set validity flag to false.
                    JOptionPane.showMessageDialog(frame, "Invalid word found in file: " + wordStr);
                    isValidFile = false;
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(frame, "File not found: " + e.getMessage());
            return;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error reading puzzle file: " + e.getMessage());
            return;
        }

        // If the file is valid, proceed to update the puzzle state with the new data.
        if (isValidFile) {
            resetGame(); // Reset the game now that a valid file is confirmed.
            // Update puzzle state with validated data.
            puzzleLetters = tempPuzzleLetters;
            puzzleLettersTextArea.setText(centerTextWithSpaces(puzzleLetters) + puzzleLetters);
            solutionWords = tempSolutionWords;

            // Inform the user of successful loading.
            JOptionPane.showMessageDialog(frame, "New puzzle loaded successfully!");
        } else {
            // Inform the user that the puzzle was not loaded due to invalid content.
            JOptionPane.showMessageDialog(frame, "The puzzle was not loaded due to invalid content.");
        }
    }

    /**
     * Centers a given string with spaces. This method calculates the number of spaces
     * needed to center the text and prepends them to the original string.
     * 
     * @param text The text to be centered.
     * @return A string representing the centered text.
     */
    private static String centerTextWithSpaces(String text) {
        int spacesToAdd = text.length() + text.length() / 2 - 1; // Calculate spaces needed for centering
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < spacesToAdd; i++) {
            sb.append(" "); // Add spaces to the beginning
        }
        
        return sb.toString();
    }

	/**
     * The main method to start the application.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        // Read the input file using Project1 class and get the PuzzleData
        PuzzleData data = Project3.readFile();
        if (data != null) {
            String puzzleLetters = data.getPuzzleLetters();
            UnsortedWordList solutionWords = data.getSolutions();

            // Create GUI and pass puzzleLetters and solutionWords directly
            PuzzleGUI gui = new PuzzleGUI(puzzleLetters, solutionWords);
            gui.createAndShowGUI();
        } else {
            System.out.println("Failed to load puzzle data.");
        }
    }
}
