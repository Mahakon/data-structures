import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import seminar1.collections.ArrayPriorityQueue;
import seminar1.collections.IPriorityQueue;
import seminar1.iterators.IncreasingIterator;

/**
 * Класс тестирующий интерфейс {@link IPriorityQueue<Integer>} на основе {@link ArrayPriorityQueue<>}
 */
@RunWith(value = Parameterized.class)
public class TestPriorityQueue {
    IPriorityQueue<Integer> queue;
    Queue<Integer> expectedQueue;

    @Parameterized.Parameter
    public int[] array;

    @Parameterized.Parameters(name = "{index}")
    public static Collection<int[]> data() {
        return Arrays.asList(new int[][]{
                {},
                {1},
                {0, 0, 0, 0},
                {1, 2, 3, 5},
                generateArray(1000),
                generateArray(100)
        });
    }

    @Before
    public void init() {
        Integer[] newArray = new Integer[array.length];
        int i = 0;
        for (int value : array) {
            newArray[i++] = Integer.valueOf(value);
        }

        queue = new ArrayPriorityQueue<>(Arrays.asList(newArray));
        expectedQueue = new PriorityQueue<>(Arrays.asList(newArray));
    }


    @Test
    public void isAdd() {
        for (int i = 0; i < 1000; i++) {
            Assert.assertEquals(queue.extractMin(), expectedQueue.poll());
        }
    }

    @Test
    public void isPeek() {
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(expectedQueue.peek(), queue.peek());
        }
    }

    @Test
    public void isExtractMin() {
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(queue.extractMin(), expectedQueue.poll());
        }
    }

    @Test
    public void isEmpty() {
        Assert.assertEquals(queue.isEmpty(), expectedQueue.isEmpty());
    }

    @Test
    public void isSize() {
        Assert.assertEquals(queue.size(), expectedQueue.size());
        queue.add(1);
        expectedQueue.add(1);
        Assert.assertEquals(queue.size(), expectedQueue.size());
    }

    @Test
    public void isIteratorHasNext() {
        queue.add(10);
        Iterator<Integer> it = queue.iterator();
        Assert.assertTrue(it.hasNext());
    }

    @Test
    public void isIteratorNext() {
        Iterator<Integer> it = queue.iterator();
        Iterator<Integer> itExpected = expectedQueue.iterator();

        if (expectedQueue.isEmpty()) {
            Assert.assertTrue(itExpected.hasNext() == it.hasNext());
        } else {
            Assert.assertTrue(itExpected.next() == it.next());
        }
    }

    public static int[] generateArray(int num) {
        int[] array = new int[num];
        for (int i = 0; i < num; i++) {
            array[i] = (int) Math.floor(Math.random() * num);
        }
        return array;
    }

}
