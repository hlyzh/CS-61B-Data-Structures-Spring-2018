//OffByOne.java: A class for off-by-1 comparators.
public class OffByOne implements CharacterComparator {

    @Override
    public boolean equalChars(char x, char y) {
        int difference = x  - y;
        if (difference == 1 || difference == -1) {
            return true;
        } else {
            return false;
        }
    }

}