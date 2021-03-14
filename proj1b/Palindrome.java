public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> charDeque = new ArrayDeque<>();
        for (Character ch : word.toCharArray()) {
            charDeque.addLast(ch);
        }
        return charDeque;
    }

    public boolean isPalindrome(String word) {
        if (word == null) {
            return false;
        }
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque<Character> deque = wordToDeque(word);
        while (deque.size() > 1) {
            char first = Character.toLowerCase(deque.removeFirst());
            char last = Character.toLowerCase(deque.removeLast());
            if (first != last) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word == null) {
            return false;
        }
        if (word.length() == 0 || word.length() == 1) {
            return true;
        }
        Deque<Character> deque = wordToDeque(word);
        while (deque.size() > 1) {
            if (!cc.equalChars(deque.removeFirst(), deque.removeLast())) {
                return false;
            }
        }
        return true;
    }
}
