package seminar1.collections;

import java.util.Arrays;
import java.util.Iterator;

public class CyclicArrayDeque<Item> implements IDeque<Item> {
    private static final int DEFAULT_CAPACITY = 10;

    private Item[] elementData;
    private int size = 0;
    private int head = 0;
    private int tail = -1;


    public CyclicArrayDeque() {
        elementData = (Item[]) new Object[DEFAULT_CAPACITY];
    }

    @Override
    public void pushBack(Item item) {
        size++;
        if (size > elementData.length) {
            grow();
        }
        if (head > 0 && tail == elementData.length-1) {
            Item[] newDate = (Item[]) new Integer[elementData.length];
            System.arraycopy(elementData, head, newDate, 0, size-1);
            elementData = newDate;
            head = 0;
            tail = size - 2;
        }

        if (tail == -1) {
            head = 0;
        }
        elementData[++tail] = item;
    }

    @Override
    public void pushFront(Item item) {
        size++;
        if (size > elementData.length) {
            grow();
        }
        if (head <= 0) {
            if (head == 0) {
                Item[] newDate = (Item[]) new Integer[elementData.length];
                System.arraycopy(elementData, 0, newDate, 1, size-1);
                elementData = newDate;
            }
            head = 1;
            tail++;
        }

        elementData[--head] = item;
    }

    @Override
    public Item popFront() {
        if (isEmpty()) {
            return null;
        }
        size--;
        if (size < elementData.length/4 + 1) {
            shrink();
            head = 0;
            tail = size;
        }
        Item h = elementData[head];
        elementData[head] = null;
        head++;
        return h;
    }

    @Override
    public Item popBack() {
        if (isEmpty()) {
            return null;
        }
        size--;
        if (size < elementData.length/4 + 1) {
            shrink();
            head = 0;
            tail = size;
        }
        Item t = elementData[tail];
        elementData[tail] = null;
        tail--;
        return t;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int size() {
        return size;
    }

    private void grow() {
        changeCapacity(elementData.length*3/2 + 1);
    }

    private void shrink() {
        changeCapacity(elementData.length/2 + 1);
    }

    private void changeCapacity(int newCapacity) {
        elementData = Arrays.copyOf(elementData, newCapacity);
    }


    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private int cur = head;
            @Override
            public boolean hasNext() {

                if (cur > tail) {
                    return false;
                }
                return true;
            }

            @Override
            public Item next() {
                Item value = elementData[cur];
                cur++;
                return value;
            }
        };


    }

    @Override
    public String toString() {
        return null;
    }
}
