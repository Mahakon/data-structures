package seminar1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1. пустая строка — правильная скобочная последовательность;
 * 2. правильная скобочная последовательность,
 *      взятая в скобки одного типа — правильная скобочная последовательность;
 * 3. правильная скобочная последовательность,
 *      к которой приписана слева или справа правильная скобочная последовательность
 *      — тоже правильная скобочная последовательность.
 */
public class ParenthesesSequence {

    private static final String QUIT = "q";

    private static final char LEFT_PAREN     = '(';
    private static final char RIGHT_PAREN    = ')';

    // sequence = "()()" | "((((" | ")()(" | ...
    public static boolean isBalanced(String sequence) {
        char[] stack;
        stack = new char[sequence.length()];
        int head = 0;
        boolean isBalanced = true;

        for (int i = 0; i < sequence.length() && isBalanced; i++) {
            char c = sequence.charAt(i);
            if (LEFT_PAREN == c) stack[head++] = c;
            if (c == RIGHT_PAREN && (head == 0 || stack[--head] != LEFT_PAREN)) isBalanced = false;
        }
        isBalanced = isBalanced && head == 0;
        return isBalanced;
    }


    public static void main(String[] args) {
        try (BufferedReader lineReader = new BufferedReader(new InputStreamReader(System.in))) {
            String sequence;
            while (!QUIT.equals(sequence = lineReader.readLine())) {
                System.out.println(isBalanced(sequence) ? "YES" : "NO");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
