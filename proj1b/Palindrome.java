//Palindrome.java: A class for palindrome operations.
public class Palindrome {

	public Deque<Character> wordToDeque(String word) {
        Deque<Character> deque = new LinkedListDeque<Character>();
		for (int i = 0; i < word.length(); i += 1) {
            deque.addLast(word.charAt(i));
		}
		return deque;
	}

	private boolean isPalindrome(Deque<Character> deque_list) {
        int length = deque_list.size();
        if (length <= 1) {
            return true;
        } else {
            if (deque_list.removeFirst() != deque_list.removeLast()) {
                return false;
            } else {
                return isPalindrome(deque_list);
            }
        }
    }

    public boolean isPalindrome(String word) {
        Deque<Character> deque_list = wordToDeque(word);
        return isPalindrome(deque_list);

    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque_list = wordToDeque(word);
        int length = deque_list.size();
        if (length <= 1) {
            return true;
        } else {

            for (int i = 0; i < length; i += 1) {
                if (i == length - i - 1) {
                    break;
                } else {
                    if (cc.equalChars(deque_list.get(i), deque_list.get(length - i - 1)) == false) {
                        return false;
                    }
                }
            }
            return true;

        }
    }


}
