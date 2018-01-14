package seminar1.collections;

import java.util.Iterator;

public class LinkedQueue<Item> implements IQueue<Item> {

    // -> [tail -> .. -> .. -> head] ->
    private Node<Item> head;
    private Node<Item> tail;
    private int size = 0;

    /*
     *adds element to the end of queue
     *new_item = tail
     */
    @Override
    public void enqueue(Item item) {
        if (isEmpty()) {
            head = new Node<>(item, null);
            tail = head;
        } else {
            Node<Item> element = new Node<>(item, tail);
            tail = element;
        }
        size++;
    }

    /*
     * returns head
     * head becomes previous element
     * deletes first element
     */
    @Override
    public Item dequeue() {
        if (isEmpty()) {
            return null;
        }

        Node<Item> cur = tail;

        while (cur != head && cur.next != head) {
            cur = cur.next;
        }
        Item h = head.item;
        cur.next = null;
        head = cur;
        size--;
        return h;
    }

    /*
     *returns ? queue is empty true : false
    */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * returns size of queue
    */
    @Override
    public int size() {
        return size;
    }

    /*
     *tail->...->head
     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node<Item> cur = tail;

            @Override
            public boolean hasNext() {
                if (cur == null) {
                    return false;
                }
                return true;
            }

            @Override
            public Item next() {
                Item value = cur.item;
                cur = cur.next;
                return value;
            }
        };
    }

    /*
     *exta class for one element of queue
     */
    private static class Node<Item> {
        Item item;
        Node<Item> next;

        public Node(Item item) {
            this.item = item;
        }

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        String value = "[";

        Node<Item> el = tail;
        int i = 0;

        while (el != null) {
            value += el.item;
            if (el != head) {
                value += ", ";
            }

            el = el.next;
        }

        value += "]";

        return value;
    }
}
