import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByN(1);

    @Test
    public void testEqualChars() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertFalse(offByOne.equalChars('a', 'z'));
        assertTrue(offByOne.equalChars('f', 'e'));
        assertTrue(offByOne.equalChars('%', '&'));
        assertFalse(offByOne.equalChars('%', '%'));
        assertTrue(offByOne.equalChars('a', 'B'));
        assertFalse(offByOne.equalChars('C', 'c'));
    }

}