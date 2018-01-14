package tests;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import seminar1.ParenthesesSequence;

import static junit.framework.TestCase.assertEquals;

@RunWith(value = Parameterized.class)
public class TestParenthesesSequence {
    ParenthesesSequence testClass;

    @Parameterized.Parameters()
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"()()()", true},
                {"()(()", false},
                {"()()", true},
                {"(()", false}
        });
    }

    private String line;
    private boolean expected;

    public TestParenthesesSequence(String line, boolean expected) {
        this.line = line;
        this.expected = expected;
    }

    @Test
    public void test() {
        assertEquals(expected, testClass.isBalanced(line));
    }
}
