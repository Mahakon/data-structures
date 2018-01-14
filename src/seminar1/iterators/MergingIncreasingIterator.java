package seminar1.iterators;

import java.lang.reflect.Array;
import java.rmi.MarshalException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

import javax.swing.ImageIcon;

/**
 * Итератор возвращающий последовательность из двух возрастающих итераторов в порядке возрастания
 * first = 1,3,4,5,7
 * second = 0,2,4,6,8
 * result = 0,1,2,3,4,4,5,6,7,8
 *
 * Time = O(k),
 *  k — суммарное количество элементов
 */
public class MergingIncreasingIterator implements Iterator<Integer> {

    private IncreasingIterator first;
    private IncreasingIterator second;
    private ArrayList<Integer> curElements = new ArrayList<>();


    public MergingIncreasingIterator(IncreasingIterator first, IncreasingIterator second) {
        this.first = first;
        this.second = second;
        curElements.add(Integer.MAX_VALUE);
    }

    @Override
    public boolean hasNext() {
        return !curElements.isEmpty();
    }

    @Override
    public Integer next() {
        if (first.hasNext() && second.hasNext()) {
            curElements.add(first.next());
            curElements.add(second.next());
            Collections.sort(curElements);
        }

        if (first.hasNext() && !second.hasNext()) {
            curElements.add(first.next());
            Collections.sort(curElements);
        }

        if (second.hasNext() && !first.hasNext()) {
            curElements.add(second.next());
            Collections.sort(curElements);
        }

        int min = curElements.get(0);
        curElements.remove(0);
        if (curElements.size() == 1 &&
                curElements.get(0) == Integer.MAX_VALUE) {
            curElements.remove(0);
        }
        return min;
    }
}
