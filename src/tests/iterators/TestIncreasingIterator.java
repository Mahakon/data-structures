package tests.iterators;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import seminar1.iterators.IncreasingIterator;

import static junit.framework.TestCase.assertTrue;

@RunWith(value = Parameterized.class)
public class TestIncreasingIterator {
    IncreasingIterator testClass;

    @Before
    public void setUp() {
        testClass = new IncreasingIterator(array[0], array[1], array[2]);
    }

    @Parameterized.Parameter
    public int[] array;

    @Parameterized.Parameters(name = "{index}")
    public static Collection<int[]> data() {
        return Arrays.asList(new int[][] {
                {0, 10, 5},
                {4, 100, 10},
                {23, 100, 20},
                {1, 400, 100}
        });
    }

    @Test
    public void test() {
        int prev = -1;
        int curr = -1;

        for (int i = 0; testClass.hasNext(); i++) {
            if (i == 0) {
                prev = testClass.next();
                curr = prev;
                continue;
            }

            int newVal = testClass.next();
            prev = curr;
            curr = newVal;

            assertTrue(curr >= prev);
        }
    }
}
