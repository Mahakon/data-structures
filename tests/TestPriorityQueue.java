import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import seminar1.collections.ArrayPriorityQueue;
import seminar1.collections.IPriorityQueue;

/**
 * Класс тестирующий интерфейс {@link IPriorityQueue<Integer>} на основе {@link ArrayPriorityQueue<>}
 */
public class TestPriorityQueue {

    private IPriorityQueue<Integer> queue;
    private Queue<Integer> expectedQueue;

    @Before
    public void init() {
        queue = new ArrayPriorityQueue<>();
        expectedQueue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(o1, o2);
            }
        });
    }

    @Test
    public void isAdd() {
        final int maxNum = 1000;
        for (int i = 0; i < 1000; i++) {
            int res = (int) Math.floor(Math.random() * maxNum);
            expectedQueue.add(res);
            queue.add(res);
        }

        for (int i = 0; i < 1000; i++) {
            Assert.assertEquals(queue.extractMin(), expectedQueue.poll());
        }
    }

    @Test
    public void isPeek() {
        final int maxNum = 1000;

        for (int i = 0; i < 5; i++) {
            int res = (int) Math.floor(Math.random() * maxNum);
            expectedQueue.add(res);
            queue.add(res);
            Assert.assertEquals(expectedQueue.peek(), expectedQueue.peek());
        }

        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(expectedQueue.peek(), expectedQueue.peek());
        }
    }

    @Test
    public void isExtractMin() {
        final int maxNum = 1000;

        for (int i = 0; i < 5; i++) {
            int res = (int) Math.floor(Math.random() * maxNum);
            queue.add(res);
            expectedQueue.add(res);
        }

        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(queue.extractMin(), expectedQueue.poll());
        }
    }

    @Test
    public void isEmpty() {
        Assert.assertTrue(queue.isEmpty());
        queue.add(15);
        queue.add(25);
        Assert.assertFalse(queue.isEmpty());
    }

    @Test
    public void isSize() {
        Assert.assertEquals(queue.size(), 0);
        queue.add(1);
        Assert.assertEquals(queue.size(), 1);
        queue.add(2);
        Assert.assertEquals(queue.size(), 2);
        queue.add(3);
        Assert.assertEquals(queue.size(), 3);
    }

    @Test
    public void isIteratorHasNext() {
        queue.add(10);
        Iterator<Integer> it = queue.iterator();
        Assert.assertTrue(it.hasNext());
    }

    @Test
    public void isIteratorNext() {
        queue.add(10);
        Iterator<Integer> it = queue.iterator();
        Assert.assertTrue(10 == it.next());
    }

}
