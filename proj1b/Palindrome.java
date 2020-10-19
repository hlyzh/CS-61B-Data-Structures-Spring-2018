//Palindrome.java: A class for palindrome operations.
public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i += 1) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    private boolean isPalindrome(Deque<Character> dequeList) {
        int length = dequeList.size();
        if (length <= 1) {
            return true;
        } else {
            if (dequeList.removeFirst() != dequeList.removeLast()) {
                return false;
            } else {
                return isPalindrome(dequeList);
            }
        }
    }

    public boolean isPalindrome(String word) {
        Deque<Character> dequeList = wordToDeque(word);
        return isPalindrome(dequeList);

    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> dequeList = wordToDeque(word);
        int length = dequeList.size();
        if (length <= 1) {
            return true;
        } else {
            for (int i = 0; i < length; i += 1) {
                if (i == length - i - 1) {
                    break;
                } else {
                    System.out.println(dequeList.get(i) + "    " + dequeList.get(length - i - 1));
                    if (! cc.equalChars(dequeList.get(i), dequeList.get(length - i - 1))) {
                        return false;
                    }
                }
            }
            return true;

        }
    }


}
