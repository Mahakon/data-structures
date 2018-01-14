package tests.collections;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.junit.Before;
import org.junit.Test;

import seminar1.collections.IQueue;
import seminar1.collections.LinkedQueue;


import static junit.framework.TestCase.assertEquals;

public class TestCycleArrayQueue {
    public IQueue<Integer> testClass;
    public Queue<Integer> expectedClass;

    @Before
    public void setUp() {
        testClass = new LinkedQueue<>();;
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
        generate();
        List<Integer> list = new LinkedList<>();
        while(!expectedClass.isEmpty()) {
            list.add(0, expectedClass.remove());
        }
        assertEquals(list.toString(), testClass.toString());
    }


    public void generate() {
        for (int i = 0; i < 100; i++) {
            int element = (int)Math.floor(Math.random() * 100);
            testClass.enqueue(element);
            expectedClass.add(element);
        }
    }

}
