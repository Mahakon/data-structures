import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import seminar1.collections.CyclicArrayDeque;
import seminar1.collections.IDeque;
import seminar1.collections.LinkedDeque;

/**
 * Класс тестирующий интерфейс {@link IDeque<Integer>} в двух реализациях:
 * 1) на массиве {@link CyclicArrayDeque<Integer>}
 * 2) на списке {@link LinkedDeque<Integer>}
 */
@RunWith(value = Parameterized.class)
public class TestDeque {

    @Parameterized.Parameter(0)
    public Class<?> testClass;
    @Parameterized.Parameter(1)
    public int[] array;

    private IDeque<Integer> deque;
    public Deque<Integer> expectedDeque;

    @Parameterized.Parameters(name = "{0}{1}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {CyclicArrayDeque.class, new int[]{}},
                {CyclicArrayDeque.class, new int[]{1}},
                {CyclicArrayDeque.class, new int[]{1, 2, 3}},
                {CyclicArrayDeque.class, new int[]{1, 2, 3, 6}},
                {CyclicArrayDeque.class, new int[]{1, 2, 3, 6, 8}},

                {LinkedDeque.class, new int[]{}},
                {LinkedDeque.class, new int[]{1}},
                {LinkedDeque.class, new int[]{1, 2, 3}},
                {LinkedDeque.class, new int[]{1, 2, 3, 6}},
                {LinkedDeque.class, new int[]{1, 2, 3, 6, 8}}
        });
    }

    @Before
    @SuppressWarnings("unchecked")
    public void init()
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Integer[] newArray = new Integer[array.length];
        int i = 0;
        for (int value : array) {
            newArray[i++] = Integer.valueOf(value);
        }

        Constructor<?> cons = testClass.getConstructor(Collection.class);
        deque = (IDeque<Integer>) cons.newInstance(Arrays.asList(newArray));
        expectedDeque = new ArrayDeque<>(Arrays.asList(newArray));
    }


    @Test
    public void isEmpty() {
        Assert.assertEquals(expectedDeque.isEmpty(), deque.isEmpty());
    }

    @Test
    public void pushBack() {
        generateBack(1000);
        Assert.assertEquals(expectedDeque.toString(), deque.toString());
    }

    @Test
    public void pushFront() {
        generateFront(1000);
        Assert.assertEquals(expectedDeque.toString(), deque.toString());
    }

    @Test
    public void popFront() {
        if (!deque.isEmpty()) {
            Assert.assertEquals(deque.popFront(), expectedDeque.removeFirst());
        } else {
            Assert.assertTrue(deque.popFront() == null);
        }
    }

    @Test
    public void popBack() {
        if (!deque.isEmpty()) {
            Assert.assertEquals(deque.popBack(), expectedDeque.removeLast());
        } else {
            Assert.assertTrue(deque.popBack() == null);
        }
    }

    @Test
    public void isSize() {
        Assert.assertEquals(deque.size(), expectedDeque.size());
    }

    @Test
    public void isHasNext() {
        deque.pushBack(10);
        Iterator<Integer> it = deque.iterator();
        Assert.assertTrue(it.hasNext());
    }

    @Test
    public void isNext() {
        Iterator<Integer> it = deque.iterator();
        Iterator<Integer> itExpected = expectedDeque.iterator();

        if (expectedDeque.isEmpty()) {
            Assert.assertTrue(itExpected.hasNext() == it.hasNext());
        } else {
            Assert.assertEquals(itExpected.next(), it.next());
        }
    }

    private void generateBack(int num) {
        for (int i = 0; i < num; i++) {
            int element = (int) Math.floor(Math.random() * num);
            deque.pushBack(element);
            expectedDeque.add(element);
        }
    }

    private void generateFront(int num) {
        for (int i = 0; i < num; i++) {
            int element = (int) Math.floor(Math.random() * num);
            deque.pushFront(element);
            expectedDeque.addFirst(element);
        }
    }

}
