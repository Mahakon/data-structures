import java.lang.reflect.InvocationTargetException;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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

    @Parameterized.Parameter()
    public Class<?> testClass;

    private IDeque<Integer> deque;
    public Deque<Integer> expectedDeque;

    @Parameterized.Parameters(name = "{0}")
    public static Collection<Class<?>> data() {
        return Arrays.asList(
                CyclicArrayDeque.class,
                LinkedDeque.class
        );
    }

    @Before
    @SuppressWarnings("unchecked")
    public void init() {
        try {
            deque = (IDeque<Integer>) testClass.getConstructor().newInstance();
            expectedDeque = new ArrayDeque<>();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isEmpty() {
        Assert.assertTrue(deque.isEmpty());
        Assert.assertEquals(deque.size(), 0);
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
        generateBack(1);
        Assert.assertEquals(deque.popFront(), expectedDeque.removeFirst());
    }

    @Test
    public void popBack() {
        generateBack(1);
        Assert.assertEquals(deque.popBack(), expectedDeque.removeFirst());
    }

    @Test
    public void isSize() {
        Assert.assertEquals(deque.size(), 0);
        deque.pushFront(10);
        Assert.assertEquals(deque.size(), 1);
    }

    @Test
    public void isHasNext() {
        deque.pushFront(10);
        Iterator<Integer> it = deque.iterator();
        Assert.assertTrue(it.hasNext());
    }

    @Test
    public void isNext() {
        deque.pushFront(10);
        Iterator<Integer> it = deque.iterator();
        Assert.assertEquals(10, (int)it.next());
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
