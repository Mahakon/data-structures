package tests.collections;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import seminar1.collections.IQueue;
import seminar1.collections.TwoStackQueue;

import static junit.framework.TestCase.assertEquals;

public class TestTwoStackQueue {
    public IQueue<Integer> testClass;
    public Queue<Integer> expectedClass;

    @Before
    public void setUp() {
        testClass = new TwoStackQueue<>();;
        expectedClass = new LinkedList<>();
    }

    @Test
    public void testDequeueMethod() {
        generate();

        for (int i = 0; i < expectedClass.size(); i++) {
            assertEquals(expectedClass.remove(), testClass.dequeue());
        }
    }


    @Test
    public void testEnqueueMethod() {
        assertEquals(expectedClass.isEmpty(), testClass.isEmpty());
        final int s = 10;
        generate();
        for (int i = 0; i < s; i++) {
            testClass.enqueue(i);
            expectedClass.add(i);
        }
        assertEquals(expectedClass.size(), testClass.size());
    }


    public void generate() {
        for (int i = 0; i < 100; i++) {
            int element = (int)Math.floor(Math.random() * 100);
            testClass.enqueue(element);
            expectedClass.add(element);
        }
    }
}
