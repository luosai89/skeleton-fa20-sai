import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testEqualChars() {
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('a', 'B'));
        assertFalse(offByOne.equalChars('a', 'z'));
        assertTrue(offByOne.equalChars('f', 'e'));
        assertTrue(offByOne.equalChars('F', 'e'));
        assertTrue(offByOne.equalChars('%', '&'));
    }

}
