// Mohammad Pervaiz
// Lab Section 111D

import java.io.*;
import java.util.ArrayList;

/**
 * A class to read data from a file for the Spelling Beehive Word Puzzle Game.
 */
public class Project3 {

    /**
     * Reads data from a file and returns puzzle data.
     *
     * @return A PuzzleData object containing the puzzle letters and solution words in an UnsortedWordList.
     */
    public static PuzzleData readFile() {
        String puzzleLetters = ""; // Initialize variable to store puzzle letters
        UnsortedWordList solutionsList = new UnsortedWordList(); // Initialize UnsortedWordList to store solutions

        try {
            // Use Project3.class to obtain the InputStream for reading the solutions file
            InputStream is = Project3.class.getResourceAsStream("/solutions.txt");
            if (is == null) {
                // Handle the case where the file doesn't exist within the JAR
                throw new FileNotFoundException("Cannot find resource file: solutions.txt");
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));

            // Read the first line which contains the letters for the word puzzle
            puzzleLetters = reader.readLine().trim();

            // Read subsequent lines containing solutions
            String line;
            while ((line = reader.readLine()) != null) {
                // Trim each line and add it as a new Word to the solutions list
                solutionsList.add(new Word(line.trim()));
            }

            reader.close(); // Close the reader to free resources
        } catch (IOException e) {
            // Print an error message to standard error if an exception occurs
            System.err.println("Error reading input file: " + e.getMessage());
            // Return null to indicate that reading the file failed
            return null;
        }

        // Return the PuzzleData containing puzzle letters and the list of solutions
        return new PuzzleData(puzzleLetters, solutionsList);
    }
}
