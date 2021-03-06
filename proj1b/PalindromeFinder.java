//PalindromeFinder.java: Class that helps identify generalized Palindromes in English.

/** This class outputs all palindromes in the words file in the current directory. */
public class PalindromeFinder {

    public static void main(String[] args) {
        int minLength = 4;
        //In in = new In("src/data/words.txt");
        In in = new In("../library-sp18/data/words.txt");
        Palindrome palindrome = new Palindrome();
        CharacterComparator offByOne = new OffByOne();
        CharacterComparator offByFive = new OffByN(5);


        while (!in.isEmpty()) {
            String word = in.readString();
            if (word.length() >= minLength && palindrome.isPalindrome(word, offByFive)) {
                System.out.println(word);
            }
        }
    }
}
