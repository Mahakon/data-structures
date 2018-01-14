package seminar1.collections;

import java.util.Iterator;

public class LinkedDeque<Item> implements IDeque<Item> {
    // null<- [tail <-> .. <-> .. <-> head] ->null
    private Node<Item> head;
    private Node<Item> tail;
    private int size = 0;

    /*
     * adds element to the head of deque
     * head=new_item
     */
    @Override
    public void pushFront(Item item) {
        if (isEmpty()) {
            head = new Node<>(item, null, null);
            tail = head;
        } else {
            Node<Item> element = new Node<>(item, null, head);
            head.next = element;
            head = element;
        }
        size++;
    }

    /*
     * adds element to the end of deque
     * tail=new_item
    */
    @Override
    public void pushBack(Item item) {
        if (isEmpty()) {
            tail = new Node<Item>(item, null, null);
            head = tail;
        } else {
            Node<Item> element = new Node<Item>(item, tail, null);
            tail.prev = element;
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
    public Item popFront() {
        if (isEmpty()) {
            return null;
        }

        Item h = head.item;
        if (head != tail) {
            head.prev.next = null;
            head = head.prev;
        }
        size--;
        return h;
    }

    /*
     * returns tail
     * tail becomes next element
     * deletes last element
     */
    @Override
    public Item popBack() {
        if (isEmpty()) {
            return null;
        }
        Item t= tail.item;
        if (head != tail) {
            tail.next.prev = null;
            tail = tail.next;
        }
        size--;
        return t;
    }

    /*
     * returns ? deque is empty true : false
    */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /*
     * returns size of deque
    */
    @Override
    public int size() {
        return size;
    }

    /*
     * tail->...->head
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
    *exta class for one element of deque
    */
    private static class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;

        public Node(Item item, Node<Item> next, Node<Item> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public String toString() {
        String value = "[";

        Node<Item> el = head;
        int i = 0;

        while (el != null) {
            value += el.item;
            if (el != tail) {
                value += ", ";
            }

            el = el.next;
        }

        value += "]";

        return value;
    }
}
