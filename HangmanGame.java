/* *************************************************************************
 * Description: Runs an EvilHangman game by first displaying a welcome message
 *              with StdDraw and then displaying different prompts in the
 *              terminal to take the player through the game. Continuously
 *              runs the game until the player decides to quit.
 ************************************************************************ */

import java.awt.Color;
import java.awt.Font;
import java.util.InputMismatchException;

public class HangmanGame {
    // if mouse is clicked, bring user to the abouts page
    public static void mouseClicked() {
        if (StdDraw.isMousePressed()) {
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.clear(StdDraw.RED);
            final double POSITION = 480.0 / 2.0;
            int SPACING = 20;
            StdDraw.text(POSITION, POSITION + SPACING, "IN EVIL HANGMAN");
            StdDraw.text(POSITION, POSITION,
                         "THE COMPUTER MAINTAINS A LIST OF EVERY WORD");
            StdDraw.text(POSITION, POSITION - SPACING,
                         "IN THE ENGLISH DICTIONARY AND THEN PARES DOWN");
            StdDraw.text(POSITION, POSITION - SPACING * 2,
                         "THE WORD LIST TO TRY TO DODGE THE PLAYER's GUESS");
            StdDraw.text(POSITION, POSITION - SPACING * 3, "AS MUCH AS POSSIBLE");
        }
    }

    // draws a main page where it welcomes the user
    public static void mainPage() {
        final int CANVAS_WIDTH = 680;
        final int CANVAS_HEIGHT = 480;
        Color BACKGROUND_COLOR = Color.BLACK;
        StdDraw.setCanvasSize(CANVAS_WIDTH, CANVAS_HEIGHT);
        StdDraw.setScale(0, CANVAS_HEIGHT);
        StdDraw.clear(BACKGROUND_COLOR);
        StdDraw.enableDoubleBuffering();
        Font fontText = new Font("Arial", Font.ITALIC, 15);
        StdDraw.setFont(fontText);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.text((CANVAS_HEIGHT / 2.0), (CANVAS_HEIGHT / 2.0),
                     "WELCOME TO EVIL HANGMAN. CLICK TO READ "
                             + "MORE ABOUT THE GAME AND TO START");
        boolean run = true;
        while (run) {
            mouseClicked();
            StdDraw.show();
            if (StdDraw.isMousePressed()) {
                run = false;
            }
            StdDraw.pause(30);
        }
    }

    // runs EvilHangman game
    public static void main(String[] args) {
        // opens up with a welcoming page for the game
        mainPage();

        // In objects for user to enter inputs
        In lengthIn = new In();     // user enters word length
        In guessIn = new In();      // user enters number of guesses wanted
        In totalIn = new In();  // user enters whether they want total possible words
        In nextLetter = new In();   // user enters their letter of guess
        In playAgain = new In();    // user enters whether they want to play again

        // creates game loop
        boolean continuePlay = true;
        while (continuePlay) {
            // prompts user for word length
            int lengthInput;
            do {
                try {
                    StdOut.print("ENTER A NUMBER FOR WORD LENGTH: ");
                    lengthInput = lengthIn.readInt();
                    if (lengthInput == 26 || lengthInput == 30) {
                        StdOut.println("----there are no words with that "
                                               + "word length. try again.\n");
                        lengthInput = 0; // guarantees we go around the loop again
                    }
                }
                catch (InputMismatchException e) {
                    StdOut.println("----word length was not "
                                           + "a number. try again.\n");
                    lengthInput = 0; // guarantees we go around the loop again
                }
            } while (lengthInput <= 0);
            StdOut.println();

            // prompts user for number of guesses
            int guessInput;
            do {
                try {
                    StdOut.print("ENTER A NUMBER OF GUESSES: ");
                    guessInput = guessIn.readInt();
                    if (guessInput > 26) {
                        StdOut.println("----there are only 26 letters in "
                                               + "the alphabet. try again.\n");
                        guessInput = 0; // guarantees we go around the loop again
                    }
                }
                catch (InputMismatchException e) {
                    StdOut.println("----number of guess was not a "
                                           + "number. try again.\n");
                    guessInput = 0; // guarantee we go around the loop again
                }
            } while (guessInput <= 0);
            StdOut.println();

            // prompts user whether they want a running
            // total of the number of words remaining in the word list
            String yes = "y";
            String no = "n";
            String wordCountYesNo;
            do {
                StdOut.print("DO YOU WANT A RUNNING TOTAL OF THE NUMBER "
                                     + "OF WORDS REMAINING IN THE WORD LIST "
                                     + "TYPE? (Y OR N) : ");
                wordCountYesNo = totalIn.readLine();
            } while (!(wordCountYesNo.equalsIgnoreCase(yes) ||
                    wordCountYesNo.equalsIgnoreCase(no)));
            StdOut.println();

            // creates new EvilHangman constructor
            EvilHangman game = new EvilHangman(lengthInput, guessInput, wordCountYesNo);

            // reads in player's letter guess
            String letterInput;
            String continuePlayInput;
            char character;
            int guessRemaining = lengthInput;
            while (guessRemaining > 0 && game.getWordDisplay().contains("_")) {
                do {
                    StdOut.print("PLEASE ENTER A LETTER: ");
                    letterInput = nextLetter.readLine();
                    if (letterInput.length() > 0)
                        character = letterInput.toLowerCase().charAt(0);
                    else {
                        character = '1';
                        StdOut.println("----invalid letter entered. \n");
                    }
                    if (game.getGuessList().contains(character)) {
                        character = '1';
                        StdOut.println("----you have already guessed this letter. "
                                               + "try again.\n");
                    }
                } while (letterInput.length() != 1 || !Character.isLetter(character));
                StdOut.println();
                guessRemaining = game.updateGuessCount(character);
                game.setGuessList(character);
                game.makeWordFamilies(character);
                game.searchWordFamilies();
                game.updateWordList();
                StdOut.println(game);
            }

            // prints winning or losing message
            if (game.getWordDisplay().contains("_")) {
                StdOut.println("YOU LOST :(");
                StdOut.println("THE WORD WAS: " + game.returnWord() + "\n");
            }
            else
                StdOut.println("YOU WON! CONGRATULATIONS! BE SURE TO PLAY AGAIN!\n");

            // prompts the player whether he would like to play again
            do {
                StdOut.print("WOULD YOU LIKE TO PLAY AGAIN? (Y OR N) : ");
                continuePlayInput = playAgain.readLine().toLowerCase();
            } while (!(continuePlayInput.equalsIgnoreCase(yes) ||
                    continuePlayInput.equalsIgnoreCase(no)));

            if (continuePlayInput.equalsIgnoreCase(no))
                continuePlay = false;
            StdOut.println();
        }

        // prints out good-bye message
        StdOut.println("THANK YOU FOR PLAYING!");
    }
}
