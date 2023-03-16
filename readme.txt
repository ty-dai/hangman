COS126 Final Project: Implementation

Please complete the following questions and upload this readme.txt to the
TigerFile assignment for the "Final Project Implementation".


/**********************************************************************
 * Basic Information                                                  *
 **********************************************************************/

Name 1: Tong Dai

NetID 1: td2471

Name 2: Chau Truong

NetID 2: ct8871

Project preceptor name: Esin Tureci

Project title: Evil Hangman

Approximate number of hours to complete the final project
Number of hours: 25

/**********************************************************************
 * Required Questions                                                 *
 **********************************************************************/

Describe your project in a few sentences.
Our project have two .java files, one EvilHangman.java file and one
HangmanGame.java file. EvilHangman.java creates an EvilHangman game by
implementing methods in order to read in all words in the dictionary file and
eliminates words based on each letter guessed by the player. HangmanGame.java
runs an EvilHangman game by first displaying a welcome message with StdDraw
and then displaying different prompts in the terminal to take
the player through the game.


Describe in detail your three features and where in your program
they are implemented (e.g. starting and ending line numbers,
function names, etc.).
1. Our first feature is to read in the dictionary file. we read in the dictionary
file in EvilHangman.java. Reading the words from the dictionary file happened in
the constructor for EvilHangman (line 50-55). First feature also includes splitting
the words from the dictionary in word families using a symbol table. This
happened in the lines of 84-133 in EvilHangman.java.


2. Our second feature is to prompt users for input (word length, number of guesses
wanted, yes or no as to provide a running word count, etc.) We prompt the
users for input in HangmanGame.java. Prompting for inputs happened in
the main method for HangmanGame (line 77, 97, 121, 137, and 170).

3. Our third feature is to have a loop that keeps prompting the user to input guesses
until there are no more guesses left. This is implemented within the main method of
HangmanGame.java, specifically in lines 134-149. The third features also includes
printing out the game output, with letters guessed, number of guesses left, and a word
display. This is implemented in the toString() method of EvilHangman.java in lines
233-248.



Describe in detail how to compile and run your program. Include a few example
run commands and the expected results of running your program. For non-textual
outputs (e.g. graphical or auditory), feel free to describe in words what the
output should be or reference output files (e.g. images, audio files) of the
expected output.
to compile:
javac-introcs EvilHangman.java first and then
javac-introcs HangmanGame.java

to run: java-introcs EvilHangman (will display testing output,
expected results can be found by looking at test_output.txt)
java-introcs HangmanGame (
- will first display a StDDraw window with text
saying "WELCOME TO EVIL HANGMAN! CLICK TO READ MORE ABOUT THE GAME AND
TO START"
- after clicking, it changes the window to display info about
Evil Hangman
- then the terminal will prompt user for inputs
) an expected result with inputs can be found by looking at game_output.txt






Describe how your program accepts user input and mention the line number(s) at
which your program accepts user input.
The program accepts user input with In objects. The program accepts user input at
lines 77, 97, 121, 137, and 170 in the file HangmanGame.java.




Describe how your program produces output based on user input (mention line
numbers).
The program produces output based on user input by printing a text-version of the
EvilHangman constructor (includes the game display, guesses remaining,
letters already guessed, and possibly the total words remaining if user asks for).




Describe the data structure your program uses and how it supports your program's
functionality (include the variable name and the line number(s) at which it is
declared and initialized).
Our program uses a variety of data structures including ArrayLists, Symbol Tables,
and BST. It supports our program's functionality by storing the words from the dictionary
and making word families from it.
    private ArrayList<String> wordList;
    DECLARED @ LINE 19, INITIALIZED @ LINE 43
    private ArrayList<Character> guessList;
    DECLARED @ LINE 20, INITIALIZED @ LINE 44
    private ST<String, ArrayList<String>> wordFamilies;
    DECLARED @ LINE 21, INITIALIZED @ LINE 45
    private BST<String, Integer> bst;
    DECLARED @ LINE 22, INITIALIZED @ LINE 46


List the two custom functions written by your project group, including function
signatures and line numbers; if your project group wrote more than two custom
functions, choose the two functions that were most extensively tested.
1. public String toString() LINE 233-248

2. public int updateGuessCount(char a) LINE 193-197

List the line numbers where you test each of your two custom functions twice.
For each of the four tests (two for each function), explain what was being
tested and the expected result. For non-textual results (e.g. graphical or
auditory), you may describe in your own words what the expected result
should be or reference output files (e.g. images, audio files).
1. updateGuessCount(char a) LINE 276
Given a letter as the "guessed input", the function should return a guess count
that is lower than the original count by one. Since this is tested with the
EvilHangman constructor with 9 original guesses. This test should return a guess
number of 8 now after being given the letter.

2. updateGuessCount(char a) LINE 297
Given a letter as the "guessed input", the function should return a guess count
that is lower than the original count by one. Since this is tested with the
EvilHangman constructor with 2 original guesses. This test should return a guess
number of 1 now after being given the letter.

3. toString() LINE 271
It should return the game display of the new EvilHangman constructor.
Expected Output:
GAME DISPLAY: ___
YOU HAVE 9 GUESSES REMAINING.
THERE ARE 2130 POSSIBLE WORDS REMAINING.
LETTERS YOU HAVE ALREADY GUESSED: []


4. toString() LINE 272
It should return the game display of the new EvilHangman constructor.
Expected Output:
GAME DISPLAY: ____
YOU HAVE 2 GUESSES REMAINING.
LETTERS YOU HAVE ALREADY GUESSED: []


/**********************************************************************
 * Citing Resources                                                   *
 **********************************************************************/

List below *EVERY* resource your project group looked at (bullet lists and
links suffice).
1. http://nifty.stanford.edu/2011/schwarz-evil-hangman/
2. https://github.com/dwyl/english-words
3. https://introcs.cs.princeton.edu/java/11cheatsheet/#ST
4. https://www.cs.princeton.edu/courses/archive/fall21/
cos126/static/lectures/CS.13.SymbolTables.pdf
5. https://introcs.cs.princeton.edu/java/stdlib/javadoc/StdDraw.html
6. https://introcs.cs.princeton.edu/java/stdlib/javadoc/In.html
7. https://introcs.cs.princeton.edu/java/code/javadoc/ST.html



Remember that you should *ALSO* be citing every resource that informed your
code at/near the line(s) of code that it informed.

Did you receive help from classmates, past COS 126 students, or anyone else?
If so, please list their names.  ("A Sunday lab TA" or "Office hours on
Thursday" is ok if you don't know their name.)
Yes or no? NO



Did you encounter any serious problems? If so, please describe.
Yes or no? NO




List any other comments here. NONE




/**********************************************************************
 * Submission Checklist                                               *
 **********************************************************************/

Please mark that you’ve done all of the following steps:
[x] Created a project.zip file, unzipped its contents, and checked that our
    compile and run commands work on the unzipped contents. Ensure that the .zip
    file is under 50MB in size.
[x] Created and uploaded a Loom or YouTube video, set its thumbnail/starting
    frame to be an image of your program or a title slide, and checked that
    the video is viewable in an incognito browser.
[x] Uploaded all .java files to TigerFile.
[x] Uploaded project.zip file to TigerFile.

After you’ve submitted the above on TigerFile, remember to do the following:
[x] Complete and upload readme.txt to TigerFile.
[x] Complete and submit Google Form
    (https://forms.cs50.io/de2ccd26-d643-4b8a-8eaa-417487ba29ab).

