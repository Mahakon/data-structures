package seminar1.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ArrayPriorityQueue<Key extends Comparable<Key>> implements IPriorityQueue<Key> {
    private static final int DEFAULT_CAPACITY = 10;

    private Key[] elementData;
    public int size = 0;
    private Comparator<Key> comparator;

    @SuppressWarnings("unchecked")
    public ArrayPriorityQueue() {
        elementData =  (Key[]) new Comparable[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public ArrayPriorityQueue(List<Key> collection) {
        elementData =  (Key[]) new Comparable[DEFAULT_CAPACITY];
        for (Key item : collection) {
            add(item);
        }
    }

    @SuppressWarnings("unchecked")
    public ArrayPriorityQueue(Comparator<Key> comparator) {
        elementData =  (Key[]) new Comparable[DEFAULT_CAPACITY];
        this.comparator = comparator;
    }

    @SuppressWarnings("unchecked")
    public ArrayPriorityQueue(Collection<Key> collection,
            Comparator<Key> comparator) {
        elementData =  (Key[]) new Comparable[DEFAULT_CAPACITY];
        for (Key item : collection) {
            add(item);
        }
        this.comparator = comparator;
    }

    @Override
    public void add(Key key) {
        if (size == elementData.length) {
            grow();
        }

        elementData[size++] = key;
        shiftUp();
    }

    @Override
    @SuppressWarnings("unchecked")
    public Key peek() {
        return elementData[0];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Key extractMin() {
        if (isEmpty()) {
            return null;
        }
        if (size < elementData.length/4 + 1) {
            shrink();
        }

        Key min = elementData[0];
        elementData[0] = elementData[size-1];
        elementData[size] = null;
        size--;
        shiftDown();
        return min;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @SuppressWarnings("unchecked")
    private void shiftUp() {
        int position = size-1;
        while (greater(position, (position-1) / 2) && position != 0) {
            Key a = elementData[position];
            elementData[position] = elementData[(position - 1) / 2];
            elementData[(position - 1) / 2] = a;
            position = (position - 1) / 2;
        }
    }

    @SuppressWarnings("unchecked")
    private void shiftDown() {
        int position = 0;
        int leftChild;
        int rightChild;
        int smallestChild;
        while (true) {
            leftChild = position * 2 + 1;
            rightChild = position * 2 + 2;
            smallestChild = position;

            if (leftChild < size  && greater(leftChild, smallestChild)) {
                smallestChild = leftChild;
            }

            if (rightChild < size && greater(rightChild, smallestChild)) {
                smallestChild = rightChild;
            }

            if (smallestChild == position) {
                break;
            }

            Key a = elementData[position];
            elementData[position] = elementData[smallestChild];
            elementData[smallestChild] = a;
            position = smallestChild;

        }

    }

    @SuppressWarnings("unchecked")
    private void grow() {
        changeCapacity(elementData.length*3/2 + 1);
    }

    @SuppressWarnings("unchecked")
    private void shrink() {
        changeCapacity(elementData.length/2 + 1);
    }

    @SuppressWarnings("unchecked")
    private boolean greater(int i, int j) {
        return comparator == null
                ? (elementData[i]).compareTo(elementData[j]) < 0
                : comparator.compare(elementData[i], elementData[j]) < 0;
    }

    private void changeCapacity(int newCapacity) {
        elementData = Arrays.copyOf(elementData, newCapacity);
    }


    @Override
    public Iterator<Key> iterator() {
        return new ArrayPriorityQueueIterator();
    }

    private class ArrayPriorityQueueIterator implements Iterator<Key> {

        private int currentPosition = -1;

        @Override
        public boolean hasNext() {
            return currentPosition != size-1;
        }

        @Override
        public Key next() {
            return elementData[++currentPosition];
        }

    }

    @Override
    public String toString() {
        String value = "[";

        for (int i = 0; i < size; i++) {
            value += elementData[i].toString();
            if (i != size - 1) {
                value += ", ";
            }
        }

        value += "]";

        return value;
    }

}
