# NYTimes Spelling Bee Game

This is a Java implementation of a Spelling Bee Word Puzzle Game inspired by the NYTimes Spelling Bee. The game challenges players to form as many words as possible using a given set of letters, where each word must contain a specific letter.

## Table of Contents
- [Features](#features)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Usage](#usage)
- [How to Play](#how-to-play)
- [Contributing](#contributing)
- [License](#license)

## Features
- A graphical user interface (GUI) for the puzzle game.
- Validates user input against a list of solution words.
- Tracks the player's score and displays correctly guessed words.
- Handles file operations for loading and saving puzzle data.
- Custom exceptions for handling invalid words.

## Project Structure

The project is composed of the following key classes:

- **PuzzleGUI**: The main graphical user interface for the game.
- **Word**: Represents a word and ensures it only contains lowercase letters.
- **WordList**: An abstract base class that defines a linked list structure for storing words.
- **UnsortedWordList**: Extends `WordList` to provide an unsorted list of words.
- **SortedWordList**: Extends `WordList` to maintain a list of words in alphabetical order.
- **WordNode**: Represents a node in the linked list for storing words.
- **FileMenuHandler**: Handles file operations like loading a puzzle or quitting the game.
- **IllegalWordException**: Custom exception thrown when a word contains invalid characters.
- **Project3**: Contains utility methods for reading puzzle data from a file.
- **PuzzleData**: Encapsulates the puzzle letters and the list of solution words.

## Installation

### Prerequisites
- Java Development Kit (JDK) 8 or higher installed on your machine.
- An Integrated Development Environment (IDE) such as IntelliJ IDEA, Eclipse, or VS Code.

### Cloning the Repository
To get started, clone the repository to your local machine:

`bash
git clone https://github.com/momoperv123/NYTimes-Spelling-Bee.git
cd NYTimes-Spelling-Bee`
