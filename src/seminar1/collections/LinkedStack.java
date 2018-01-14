package seminar1.collections;

import java.io.BufferedReader;
import java.util.Iterator;

public class LinkedStack<Item> implements IStack<Item> {

    // -> [head -> .. -> .. -> ] ->null
    private Node<Item> head;
    private int size = 0;

    /*
     * adds element to the head of stack
     * new_item = head
     */
    @Override
    public void push(Item item) {
        if (isEmpty()) {
            head = new Node<>(item, null);
        }

        Node<Item> element = new Node<>(item, head);
        head = element;
        size++;
    }

    public Item peek() {
        return head.item;
    }

    /*
     * deletes element from the head of stack
     * return first element
     */
    @Override
    public Item pop() {
        if (isEmpty()) {
            return null;
        }

        Item h = head.item;
        head = head.next;
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
     *head->...->null
     */
    @Override
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node<Item> cur = head;

            @Override
            public boolean hasNext() {
                if (cur.next == null) {
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
     * exta class for one element of queue
     */
    private static class Node<Item> {
        Item item;
        Node<Item> next;

        public Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        String value = "[";

        Node<Item> el = head;
        int i = 0;

        while (el.next != null) {
            value += el.item;
            if (i != size - 1) {
                value += ", ";
            }

            el = el.next;
            i++;
        }

        value += "]";

        return value;
    }
}
