package tests.collections;

import java.util.PriorityQueue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import seminar1.collections.ArrayPriorityQueue;
import seminar1.collections.IPriorityQueue;

public class TestArrayPriorityQueue extends Assert {
    public IPriorityQueue<Integer> testClass;
    public PriorityQueue<Integer> expectedClass;

    @Before
    public void setUp() {
        testClass = new ArrayPriorityQueue<>((Integer x, Integer y) -> x <= y ? x.equals(y) ? 0 : 1 : -1);;
        expectedClass = new PriorityQueue<Integer>();
    }

    @Test
    public void testAddMethod() {
        generate();
        assertEquals(expectedClass.toString(), testClass.toString());
    }

    @Test
    public  void testPeekMethod() {
        generate();
        for (int i = 0; i < expectedClass.size(); i++) {
            assertEquals(expectedClass.peek(), testClass.peek());
        }
    }

    public void generate() {
        for (int i = 0; i < 100; i++) {
            int element = (int)Math.floor(Math.random() * 100);
            testClass.add(element);
            expectedClass.add(element);
        }
    }

}
