# EVIL HANGMAN
A twist on the classic Hangman game. 

## HOW IT WORKS
Instead of starting with a random word picked by the user, the computer maintains a list of possible words in the English dictionary at each step. As the user enters a new letter guess, the computer will continuously pare down the word list and dodge the player's guesses as much as possible. The idea is to keep the word list as large as possible and prevent the user to win.


## DESCRIPTION
This project has two java files, one EvilHangman.java file and one HangmanGame.java file. EvilHangman.java creates an EvilHangman game by implementing methods in order to read in all words in the dictionary file and eliminates words based on each letter guessed by the player. HangmanGame.java runs an EvilHangman game by first displaying a welcome message with StdDraw and then displaying different prompts in the terminal to take the player through the game. 

## HOW TO COMPILE/RUN
To play the game, you first need to download project.zip. Run the project folder in an IDE, and compile EvilHangman.java then HangmanGame.java in terminal. Both files must be compiled with javac-introcs commands.
```
$javac-introcs EvilHangman.java 
$javac-introcs HangmanGame.java
```
After compiling, simply run HangmanGame.
```
$java-introcs HangmanGame
```

## HOW TO PLAY
When the user runs HangmanGame, a StdDraw window with text will pop up saying "WELCOME TO EVIL HANGMAN! CLICK TO READ MORE ABOUT THE GAME AND
TO START". The user must click on the window to view necessary information about the game. The user must keep this window up as long as they want the game to run. After reading the important information about the game, the terminal prompts the user for inputs. The user must answer the 3 prompts before they start playing.
1. ENTER A NUMBER FOR WORD LENGTH:
2. ENTER A NUMBER OF GUESSES:
3. DO YOU WANT A RUNNING TOTAL OF THE NUMBER OF WORDS REMAINING IN THE WORD LIST TYPE? (Y OR N) :
<br />
After answering the 3 prompts, the user can now enter in different guesses. Every time a guess is inputted, it will display the game display, guesses remaining, letters already guessed, and total words remaining if the user answered Y to prompt 3. At the end of the game, it will display whether the user lost or won the game. If the user lost the game, the word chosen by the computer will also be displayed. 

## KEY NOTE
Player must keep StdDraw window while playing the game.

## AUTHORS
1. Tong Dai - td2471@princeton.edu
2. Chau Truong - ct8871@princeton.edu

## Resources Used
1. http://nifty.stanford.edu/2011/schwarz-evil-hangman/
2. https://github.com/dwyl/english-words
3. https://introcs.cs.princeton.edu/java/11cheatsheet/#ST
4. https://www.cs.princeton.edu/courses/archive/fall21/cos126/static/lectures/CS.13.SymbolTables.pdf
5. https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html
6. https://introcs.cs.princeton.edu/java/stdlib/javadoc/In.html
7. https://introcs.cs.princeton.edu/java/code/javadoc/ST.html
