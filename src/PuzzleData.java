// Mohammad Pervaiz
// Lab Section 111D

import java.io.*;
import java.util.Scanner;

/**
 * Encapsulates the data for a puzzle, including the letters that make up the
 * puzzle and the list of solutions as an {@code UnsortedWordList}. This class
 * provides a structured way to handle puzzle data, allowing easy access to both
 * the puzzle letters and the potential solutions.
 */
public class PuzzleData {
    /**
     * The letters that constitute the puzzle, represented as a {@code String}.
     */
    private String puzzleLetters;

    /**
     * The list of solutions for the puzzle, stored in an {@code UnsortedWordList}.
     * This allows for storing multiple words as potential solutions to the puzzle.
     */
    private UnsortedWordList solutions;

    /**
     * Constructs a new {@code PuzzleData} instance with specified puzzle letters
     * and solutions.
     *
     * @param puzzleLetters The letters that make up the puzzle.
     * @param solutions The list of solutions to the puzzle, as an {@code UnsortedWordList}.
     */
    public PuzzleData(String puzzleLetters, UnsortedWordList solutions) {
        this.puzzleLetters = puzzleLetters;
        this.solutions = solutions;
    }

    /**
     * Returns the letters that constitute the puzzle.
     *
     * @return The puzzle letters as a {@code String}.
     */
    public String getPuzzleLetters() {
        return puzzleLetters;
    }

    /**
     * Returns the list of solutions for the puzzle.
     *
     * @return The solutions as an {@code UnsortedWordList}.
     */
    public UnsortedWordList getSolutions() {
        return solutions;
    }
    
    /**
     * Reads puzzle data from a specified file path.
     * <p>
     * This method attempts to read puzzle data, including puzzle letters and solution words,
     * from a file located at the given file path. The first line of the file is expected to contain
     * the puzzle letters, while subsequent lines contain potential solution words. Words that do
     * not consist exclusively of lowercase letters are considered illegal and are ignored,
     * with an error message printed to the console.
     * </p>
     * 
     * @param filePath The path to the file containing the puzzle data.
     * @return A {@code PuzzleData} object containing the puzzle letters and a list of valid solution words,
     *         or {@code null} if the file could not be found or another error occurs during reading.
     */
    public static PuzzleData readFile(String filePath) {
        String puzzleLetters = "";
        UnsortedWordList solutionsList = new UnsortedWordList();

        try {
            // Attempt to open and read from the file specified by filePath
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            // The first line is read as the puzzle letters (assumed to be valid)
            puzzleLetters = scanner.nextLine().trim();

            // Process each subsequent line as a potential solution word
            while (scanner.hasNextLine()) {
                String wordStr = scanner.nextLine().trim();
                try {
                    // Attempt to create a Word object, which will throw IllegalWordException
                    // for words containing characters other than lowercase letters
                    Word word = new Word(wordStr);
                    // If successful, add the word to the solutions list
                    solutionsList.add(word);
                } catch (IllegalWordException e) {
                    // If an IllegalWordException is caught, print the error message to the console
                    System.err.println(e.getMessage()); // Indicate illegal words found in the file
                }
            }
            // Ensure the scanner is closed after use to free resources
            scanner.close();
        } catch (FileNotFoundException e) {
            // Handle the case where the file cannot be found at the specified filePath
            System.err.println("File not found: " + e.getMessage());
            return null; // Return null to indicate failure to load puzzle data
        }

        // Return a new PuzzleData object containing the read puzzle letters and solution words list
        return new PuzzleData(puzzleLetters, solutionsList);
    }
}