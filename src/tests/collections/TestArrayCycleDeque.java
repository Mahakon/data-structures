package tests.collections;

import java.util.ArrayDeque;
import java.util.Deque;

import org.junit.Before;
import org.junit.Test;

import seminar1.collections.CyclicArrayDeque;
import seminar1.collections.IDeque;

import static junit.framework.TestCase.assertEquals;

public class TestArrayCycleDeque {
    public IDeque<Integer> testClass;
    public Deque<Integer> expectedClass;

    @Before
    public void setUp() {
        testClass = new CyclicArrayDeque<>();;
        expectedClass = new ArrayDeque<>();
    }

    @Test
    public void testPopFront() {
        generate();

        for (int i = 0; i < expectedClass.size(); i++) {
            assertEquals(expectedClass.pollFirst(), testClass.popFront());
        }
    }

    @Test
    public void testPopBack() {
        generate();

        for (int i = 0; i < expectedClass.size(); i++) {
            assertEquals(expectedClass.pollLast(), testClass.popBack());
        }
    }

    @Test
    public void testPushBack() {
        final int s = 20;

        for (int i = 0; i < s; i++) {
            expectedClass.push(i);
            testClass.pushBack(i);
        }

        for (int i = 0; i < s; i++) {
            assertEquals(testClass.popBack(), expectedClass.pop());
        }

    }

    @Test
    public void testPushFront() {
        final int s = 20;

        for (int i = 0; i < s; i++) {
            expectedClass.offer(i);
            testClass.pushFront(i);
        }

        for (int i = 0; i < s; i++) {
            assertEquals(testClass.popBack(), expectedClass.pop());
        }

    }




    public void generate() {
        for (int i = 0; i < 100; i++) {
            int element = (int)Math.floor(Math.random() * 100);
            testClass.pushBack(element);
            expectedClass.add(element);
        }
    }
}
