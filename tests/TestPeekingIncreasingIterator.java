import java.util.Arrays;
import java.util.Collection;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import seminar1.iterators.IncreasingIterator;
import seminar1.iterators.PeekingIncreasingIterator;

import static junit.framework.TestCase.assertTrue;

/**
 * Класс тестирующий {@link seminar1.iterators.PeekingIncreasingIterator}
 */

@RunWith(value = Parameterized.class)
public class TestPeekingIncreasingIterator {

    IncreasingIterator testClass;

    @Before
    public void setUp() {
        testClass = new PeekingIncreasingIterator(
                array[0], array[1], array[2], array[3]);
    }

    @Parameterized.Parameter
    public int[] array;

    @Parameterized.Parameters(name = "{index}")
    public static Collection<int[]> data() {
        return Arrays.asList(new int[][] {
                {0, 10, 5, 3},
                {4, 100, 10, 3},
                {23, 100, 20, 4},
                {1, 400, 100, 5}
        });
    }

    @Test
    public void testFunctionality() {
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
