// Mohammad Pervaiz
// Lab Section 111D

import javax.swing.*;
import java.awt.event.*;
import java.io.File;

/**
 * Handles actions performed on the file menu of a puzzle game's GUI.
 * <p>
 * This class is designed to handle actions such as opening a puzzle file and quitting the application,
 * which are typically found in a file menu. It interacts with a PuzzleGUI instance to load and display
 * the puzzle data from a selected file.
 * </p>
 */
public class FileMenuHandler implements ActionListener {
    // Instance of PuzzleGUI that this handler will interact with
    protected PuzzleGUI puzzleGUI;
    // The main application window that the file menu belongs to
    protected JFrame frame;

    /**
     * Constructs a FileMenuHandler with a reference to the main PuzzleGUI and the JFrame.
     *
     * @param puzzleGUI the PuzzleGUI instance this handler will work with to load and display puzzles
     * @param frame     the main application window, used for positioning file chooser dialogs
     */
    public FileMenuHandler(PuzzleGUI puzzleGUI, JFrame frame) {
        this.puzzleGUI = puzzleGUI;
        this.frame = frame;
    }

    /**
     * Responds to action events triggered from the file menu.
     * <p>
     * This method handles opening files and quitting the application based on the action command received from the
     * event. If the "Open" command is received, it will open a file chooser to select a puzzle file. If the "Quit"
     * command is received, it will terminate the application.
     * </p>
     *
     * @param e the event that triggered this action
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if ("Open".equals(command)) {
            // Create and display a file chooser dialog for selecting puzzle files
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                // If a file was selected, get its path and use the PuzzleGUI to load the puzzle data
                File selectedFile = fileChooser.getSelectedFile();
                String filePath = selectedFile.getAbsolutePath();
                puzzleGUI.loadPuzzleData(filePath);
            }
        } else if ("Quit".equals(command)) {
            // Exit the application
            System.exit(0);
        }
    }
}
