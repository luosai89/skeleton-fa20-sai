import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offbyOne = new OffByOne();
    static CharacterComparator cc1 = new OffByN(1);

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        // Any word of length 1 or 0 is a palindrome
        String test1 = "";
        String test2 = "a";
        // If the argument word is null, the method should return false.
        String test3 = null;
        // A palindrome is defined as a word that is the same whether it is read forward or backward
        String test4 = "racecar";
        String test5 = "cat";
        String test6 = "%%";
        String test7 = "%&";
        String test8 = "racecAr";
        String test9 = "m!o!o!m";

        assertTrue(palindrome.isPalindrome(test1));
        assertTrue(palindrome.isPalindrome(test2));
        assertFalse(palindrome.isPalindrome(test3));
        assertTrue(palindrome.isPalindrome(test4));
        assertFalse(palindrome.isPalindrome(test5));
        assertTrue(palindrome.isPalindrome(test6));
        assertFalse(palindrome.isPalindrome(test7));
        assertTrue(palindrome.isPalindrome(test8));
        assertTrue(palindrome.isPalindrome(test9));
    }

    @Test
    public void testIsPalindromeOffByOne() {
        // Any word of length 1 or 0 is a palindrome
        String test1 = "";
        String test2 = "a";
        // If the argument word is null, the method should return false.
        String test3 = null;

        String test4 = "flake";
        String test5 = "cat";
        String test6 = "moon";
        String test7 = "Flake";

        String test8 = "%&";
        String test9 = "%%";

        String test10 = "%flakE&";
        String test11 = "%flakE?";

        assertTrue(palindrome.isPalindrome(test1, offbyOne));
        assertTrue(palindrome.isPalindrome(test2, offbyOne));
        assertFalse(palindrome.isPalindrome(test3, offbyOne));
        assertTrue(palindrome.isPalindrome(test4, offbyOne));
        assertFalse(palindrome.isPalindrome(test5, offbyOne));
        assertFalse(palindrome.isPalindrome(test6, offbyOne));
        assertTrue(palindrome.isPalindrome(test7, offbyOne));
        assertTrue(palindrome.isPalindrome(test8, offbyOne));
        assertFalse(palindrome.isPalindrome(test9, offbyOne));
        assertTrue(palindrome.isPalindrome(test10, offbyOne));
        assertFalse(palindrome.isPalindrome(test11, offbyOne));
    }

    @Test
    public void testIsPalindromeOffByN() {
        // Any word of length 1 or 0 is a palindrome
        String test1 = "";
        String test2 = "a";
        // If the argument word is null, the method should return false.
        String test3 = null;

        String test4 = "flake";
        String test5 = "cat";
        String test6 = "moon";
        String test7 = "Flake";

        String test8 = "%&";
        String test9 = "%%";

        String test10 = "%flakE&";
        String test11 = "%flakE?";

        assertTrue(palindrome.isPalindrome(test1, cc1));
        assertTrue(palindrome.isPalindrome(test2, cc1));
        assertFalse(palindrome.isPalindrome(test3, cc1));
        assertTrue(palindrome.isPalindrome(test4, cc1));
        assertFalse(palindrome.isPalindrome(test5, cc1));
        assertFalse(palindrome.isPalindrome(test6, cc1));
        assertTrue(palindrome.isPalindrome(test7, cc1));
        assertTrue(palindrome.isPalindrome(test8, cc1));
        assertFalse(palindrome.isPalindrome(test9, cc1));
        assertTrue(palindrome.isPalindrome(test10, cc1));
        assertFalse(palindrome.isPalindrome(test11, cc1));
    }
}
