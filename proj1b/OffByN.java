public class OffByN implements CharacterComparator {
    private int diff;

    public OffByN(int diff) {
        this.diff = Math.abs(diff);
    }

    @Override
    public boolean equalChars(char x, char y) {
        x = Character.toLowerCase(x);
        y = Character.toLowerCase(y);
        return Math.abs(x - y) == diff;
    }

}
