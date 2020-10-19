//OffByN.java: A class for off-by-N comparators.
public class OffByN<T> implements CharacterComparator {
    private int gap;

    public OffByN(int N) {
        this.gap = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        int difference = x  - y;
        if (difference == this.gap || difference == -this.gap) {
            return true;
        } else {
            return false;
        }
    }

}