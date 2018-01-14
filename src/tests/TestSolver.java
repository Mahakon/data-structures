package tests;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import seminar1.Solver;

import static junit.framework.TestCase.assertEquals;

@RunWith(value = Parameterized.class)
public class TestSolver {
    Solver testClass;

    @Parameterized.Parameters()
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )", 101},
                {"( 1 + ( 5 * ( 4 * 5 ) ) ) ( 1 + ( 5 * 20 ) )", 101},
                {"( 1 + 100 )", 101}
        });
    }

    private String line;
    private double expected;

    public TestSolver(String line, double expected) {
        this.line = line;
        this.expected = expected;
    }

    @Test
    public void test() {
        assertEquals(expected, testClass.evaluate(line.split(" ")));
    }
}
