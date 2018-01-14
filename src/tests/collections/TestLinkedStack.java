package tests.collections;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import seminar1.collections.IStack;
import seminar1.collections.LinkedStack;

import static junit.framework.TestCase.assertEquals;

public class TestLinkedStack {
    public IStack<Integer> testClass;
    public Stack<Integer> expectedClass;

    @Before
    public void setUp() {
        testClass = new LinkedStack<>();;
        expectedClass = new Stack<>();
    }

    @Test
    public void testPushMethod() {
        generate();
        List<Integer> list = new LinkedList<>();
        while(!testClass.isEmpty()) {
            list.add(0, testClass.pop());
        }

        assertEquals(expectedClass.toString(), list.toString());
    }

    @Test
    public void testPopMethod() {
        generate();

        for (int i = 0; i < 5; i++) {
            testClass.pop();
            expectedClass.pop();
        }

        assertEquals(testClass.size(), expectedClass.size());

        while (!expectedClass.isEmpty()) {
            Assert.assertEquals(expectedClass.pop(), testClass.pop());
        }

        assertEquals(testClass.size(), expectedClass.size());
    }

    @Test
    public void testIsEmptyMethod() {
        assertEquals(testClass.isEmpty(), expectedClass.isEmpty());
        testClass.push(13);
        expectedClass.push(13);
        assertEquals(testClass.isEmpty(), expectedClass.isEmpty());
    }

    public void generate() {
        for (int i = 0; i < 100; i++) {
            int element = (int)Math.floor(Math.random() * 100);
            testClass.push(element);
            expectedClass.push(element);
        }
    }
}
