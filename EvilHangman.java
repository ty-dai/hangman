/* *************************************************************************
 * Description: Creates an EvilHangman game by implementing methods in
 *              order to read in all words in the dictionary file and
 *              eliminates words based on each letter guessed by the player.
 *              Tests each instance method in the main method of this class
 *              by instantiating multiple EvilHangman objects.
 ************************************************************************ */

import java.util.ArrayList;
import java.util.Comparator;

public class EvilHangman {
    private int wordLength;             // word length
    private int numGuesses;             // number of guesses remaining
    private boolean provideWordCount;   // if player wants a count of remaining words
    private int mostCommonSize;         // size of most common word family
    private String mostCommonFamily;    // string of most common word family
    private String wordDisplay;         // word with space-holders for letters
    private ArrayList<String> wordList; // list of words with inputted length
    private ArrayList<Character> guessList; // list of letters player guessed
    private ST<String, ArrayList<String>> wordFamilies; // ST for word families
    private BST<String, Integer> bst; // BST to search ST & find most common family

    // construct an Evil Hangman model of word length, number of guesses,
    // and string of whether if player wants a count of remaining words
    public EvilHangman(int wordLength, int numGuesses, String wordCountYesNo) {
        this.wordLength = wordLength;
        this.numGuesses = numGuesses;

        // initializes word display to underscore place-holders
        this.wordDisplay = "";
        for (int i = 0; i < this.wordLength; i++)
            this.wordDisplay += "_";

        // initializes wordCountYesNo to true or false depending on user's input
        if (wordCountYesNo.equalsIgnoreCase("Y"))
            this.provideWordCount = true;
        else
            this.provideWordCount = false;

        this.mostCommonSize = 0;
        this.mostCommonFamily = "";
        this.wordList = new ArrayList<String>();
        this.guessList = new ArrayList<Character>();
        this.wordFamilies = new ST<String, ArrayList<String>>();
        this.bst = new BST<String, Integer>();

        // reads all words from dictionary
        // constructs an ArrayList of words whose length matches the input length
        In dictionary = new In("words_alpha.txt");
        String[] allWords = dictionary.readAllLines();
        for (String word : allWords) {
            if (word.length() == this.wordLength)
                this.wordList.add(word);
        }
    }

    // returns the word length user inputted
    public int getWordLength() {
        return wordLength;
    }

    // returns the number of guesses remaining
    public int getNumGuesses() {
        return numGuesses;
    }

    // returns whether if player wants a count of remaining words
    public boolean getYesNo() {
        return provideWordCount;
    }

    // returns the word display
    public String getWordDisplay() {
        return wordDisplay;
    }

    // returns the list of guessed letters
    public ArrayList<Character> getGuessList() {
        return guessList;
    }

    // creates ST based on the letter guessed
    public void makeWordFamilies(char letter) {
        String family;
        // if letter is the first guess from player, create word family String
        if (wordFamilies.size() == 0) {
            for (String word : wordList) {
                family = "";
                for (int i = 0; i < wordLength; i++) {
                    if (word.charAt(i) == letter)
                        family += letter;
                    else
                        family += "_";
                }
                // if word family does not exist, instantiates ArrayList and adds word
                if (!wordFamilies.contains(family)) {
                    ArrayList<String> wordsInFamily = new ArrayList<String>();
                    wordsInFamily.add(word);
                    wordFamilies.put(family, wordsInFamily);
                }
                else
                    wordFamilies.get(family).add(word);
            }
        }

        // if letter is not the first guess from the player
        // wordList, correctGuesses, mostCommonFamily must be updated first
        else {
            // resets the symbol table to make new word families
            wordFamilies = new ST<String, ArrayList<String>>();

            // goes through each word and makes word family
            for (String word : wordList) {
                family = "";
                for (int i = 0; i < wordLength; i++) {
                    if ((word.charAt(i) == mostCommonFamily.charAt(i)) ||
                            word.charAt(i) == letter)
                        family += word.charAt(i);
                    else
                        family += "_";
                }
                // if word family does not exist, instantiates ArrayList and adds word
                if (!wordFamilies.contains(family)) {
                    ArrayList<String> wordsInFamily = new ArrayList<String>();
                    wordsInFamily.add(word);
                    wordFamilies.put(family, wordsInFamily);
                }
                else
                    wordFamilies.get(family).add(word);
            }
        }
    }

    /* @citation Adapted from:https://www.cs.princeton.edu/courses/archive/fall21/
     * cos126/static/lectures/CS.13.SymbolTables.pdf Accessed 12/12/2021*/
    // makes a BST to count the length of each word family
    // returns the most common word family
    public void searchWordFamilies() {

        // resets BST if necessary
        if (guessList.size() > 1) {
            bst = new BST<String, Integer>();
            mostCommonFamily = "";
            mostCommonSize = 0;
        }

        // creates a BST with word families and their sizes
        for (String key : wordFamilies.keys()) {
            ArrayList<String> value = wordFamilies.get(key);
            int sizeOfArrayList = value.size();
            if (!bst.contains(key))
                bst.put(key, sizeOfArrayList);
        }

        // finds the word family with the most number of words
        for (String family : bst.keys()) {
            if (bst.get(family) > mostCommonSize) {
                mostCommonSize = bst.get(family);
                mostCommonFamily = family;
            }
        }

        // updates wordDisplay
        wordDisplay = mostCommonFamily;
    }

    // get the size of the most common word family
    public int getMostCommonSize() {
        return mostCommonSize;
    }

    // loops through possible words & removes words not in most common word family
    public void updateWordList() {
        int i = 0;
        while (i < wordList.size()) {
            if (!wordFamilies.get(mostCommonFamily).contains(wordList.get(i))) {
                wordList.remove(i);
                i--;
            }
            i++;
        }
    }

    // creates an ArrayList of letters the player has guessed (in abc order)
    public void setGuessList(char a) {
        if (!guessList.contains(a))
            guessList.add(a);
        guessList.sort(Comparator.naturalOrder());
    }

    // updates and returns number of guesses player has remaining
    public int updateGuessCount(char a) {
        if (!guessList.contains(a))
            numGuesses--;
        return numGuesses;
    }

    // prints out word families with their words from ST
    public String getST() {
        String result = "";
        for (String key : wordFamilies.keys()) {
            result += (key + ": ");
            // Get ArrayList of words of the key
            ArrayList<String> listWords = wordFamilies.get(key);
            for (int i = 0; i < listWords.size(); i++)
                result += (listWords.get(i) + "\t");
            result += "\n";
        }
        return result;
    }

    // prints out word families and their size from BST
    public String getBST() {
        String result = "";
        for (String family : bst.keys())
            result += (family + ": " + bst.get(family) + "\n");
        return result;
    }

    // returns a random word in the most common word family when player loses game
    public String returnWord() {
        String word = "";
        if (numGuesses == 0 && wordDisplay.contains("_")) {
            int random = StdRandom.uniform(wordList.size());
            word = wordList.get(random);
        }
        return word;
    }

    // returns output of the word display, guesses remaining, and
    // letters guessed (also returns total possible words left if user wants)
    public String toString() {
        String output = "";
        output += ("GAME DISPLAY: " + wordDisplay + "\n");
        if (wordDisplay.contains("_")) {
            output += ("YOU HAVE " + numGuesses + " GUESSES REMAINING.\n");
            if (provideWordCount) {
                if (wordList.size() == 1)
                    output += ("THERE IS " + wordList.size() + " POSSIBLE WORD ");
                else
                    output += ("THERE ARE " + wordList.size() + " POSSIBLE WORDS ");
                output += "REMAINING.\n";
            }
            output += ("LETTERS YOU HAVE ALREADY GUESSED: " + guessList + "\n");
        }
        return output;
    }

    // tests this class by directly calling all instance methods.
    public static void main(String[] args) {
        // tests EvilHangman constructor by building it
        EvilHangman game1 = new EvilHangman(3, 9, "Y");
        EvilHangman game2 = new EvilHangman(4, 2, "N");

        // tests method of getWordLength() and gets word length
        StdOut.println("WORD LENGTH: " + game1.getWordLength()); // expected: 3
        StdOut.println("WORD LENGTH: " + game2.getWordLength()); // expected: 4

        // tests method of getNumGuesses() and gets number of guesses
        StdOut.println("NUM OF GUESSES: " + game1.getNumGuesses()); // expected: 9
        StdOut.println("NUM OF GUESSES: " + game2.getNumGuesses()); // expected: 2

        // tests method of getYesNo()
        // gets boolean of whether total word count is provided
        StdOut.println("TOTAL WORD COUNT? " + game1.getYesNo()); // expected: true
        StdOut.println("TOTAL WORD COUNT? " + game2.getYesNo()); // expected: false
        StdOut.println();

        // tests toString() method and gets output of game
        StdOut.println(game1.toString());
        StdOut.println(game2.toString());

        char test1 = 'u';       // given letter test1, tests methods on game1
        // tests updateGuessCount() method and gets updated guess count, expected: 8
        StdOut.println("UPDATED GUESS COUNT: " + game1.updateGuessCount(test1));
        // tests methods by calling them
        game1.setGuessList(test1);
        game1.makeWordFamilies(test1);
        StdOut.println(game1.getST());
        game1.searchWordFamilies();
        game1.getBST();
        game1.updateWordList();
        // prints game output to see if game is updated
        StdOut.println(game1.toString());
        // tests getGuessList() and gets list of letters guessed, expected: ['u']
        StdOut.println(game1.getGuessList());
        // tests getWordDisplay() and get word with space-holders, expected: "___"
        StdOut.println(game1.getWordDisplay());
        // tests getMostCommonSize() and get size of the most common word family
        StdOut.println("LONGEST WORD FAMILY: " + game1.getMostCommonSize());
        StdOut.println();


        char test2 = 'a';       // given letter test2, tests methods on game2
        // tests updateGuessCount() method and gets updated guess count, expected: 1
        StdOut.println("UPDATED GUESS COUNT: " + game2.updateGuessCount(test2));
        // tests methods by calling them
        game2.setGuessList(test2);
        game2.makeWordFamilies(test2);
        game2.getST();
        game2.searchWordFamilies();
        game2.getBST();
        game2.updateWordList();
        // prints game output to see if game is updated
        StdOut.println(game2.toString());
        // tests getGuessList() and gets list of letters guessed, expected: ['a']
        StdOut.println(game2.getGuessList());
        // tests getWordDisplay() and get word with space-holders, expected: "____"
        StdOut.println(game2.getWordDisplay());
        // tests getMostCommonSize() and get size of the most common word family
        StdOut.println("LONGEST WORD FAMILY: " + game2.getMostCommonSize());
        StdOut.println();

        // runs game2 again with another letter guessed in order to test returnWord()
        char test4 = 'e';
        StdOut.println(game2.updateGuessCount(test4));
        game2.setGuessList(test4);
        game2.makeWordFamilies(test4);
        StdOut.println(game2.getST());
        game2.searchWordFamilies();
        game2.getBST();
        game2.updateWordList();
        StdOut.println(game2.toString());

        // tests returnWord() when there are no more guesses
        StdOut.println("WORD: " + game2.returnWord());

        // tests returnWord() again with new Constructor and runs methods again
        EvilHangman game3 = new EvilHangman(2, 1, "Y");
        char test5 = 'l';
        game3.updateGuessCount(test5);
        game3.setGuessList(test5);
        game3.makeWordFamilies(test5);
        game3.getST();
        game3.searchWordFamilies();
        game3.getBST();
        game3.updateWordList();
        StdOut.println(game3.toString());
        StdOut.println("WORD: " + game3.returnWord());
    }
}
