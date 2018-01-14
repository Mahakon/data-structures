package tests;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import seminar1.ParenthesesSequenceExt;

import static junit.framework.TestCase.assertEquals;

@RunWith(value = Parameterized.class)
public class TestParenthesesSequenceExt {
ParenthesesSequenceExt testClass;

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

public TestParenthesesSequenceExt(String line, boolean expected) {
        this.line = line;
        this.expected = expected;
        }

@Test
public void test() {
        assertEquals(expected, testClass.isBalanced(line));
        }
}
