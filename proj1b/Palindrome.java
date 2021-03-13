public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> charDeque = new ArrayDeque<>();
        for (Character ch : word.toCharArray()) {
            charDeque.addLast(ch);
        }
        return charDeque;
    }
}
