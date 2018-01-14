package seminar1.collections;

import java.util.Iterator;

public class TwoStackQueue<Item> implements IQueue<Item> {

    private IStack<Item> stack1;
    private IStack<Item> stack2;
    private boolean switcher = false;

    public TwoStackQueue() {
        stack1 = new LinkedStack<>();
        stack2 = new LinkedStack<>();
    }

    /*
     * changes the location of elements
     */
    private void exchange(IStack<Item> getStack, IStack<Item> postStack) {
        while (!postStack.isEmpty()) {
            getStack.push(postStack.pop());
        }
    }

    @Override
    public void enqueue(Item item) {
        exchange(stack1, stack2);
        stack1.push(item);
        switcher = false;
    }

    @Override
    public Item dequeue() {
        if (isEmpty()) {
            return null;
        }
        if (!switcher) {
            exchange(stack2, stack1);
        }
        switcher = true;
        return stack2.pop();
    }

    @Override
    public boolean isEmpty() {
        return (stack1.isEmpty() && stack2.isEmpty());
    }

    @Override
    public int size() {
        return stack1.size() + stack2.size();
    }

    @Override
    public Iterator<Item> iterator() {

        IStack<Item> stack11 = stack1;
        IStack<Item> stack22 = stack2;

        return new Iterator<Item>() {
            {
                if (!switcher) {
                    exchange(stack11, stack22);
                    Iterator <Item> currentIterator = stack22.iterator();
                }
            }

            Iterator <Item> currentIterator = stack22.iterator();

            @Override
            public boolean hasNext() {
                return currentIterator.hasNext();
            }

            @Override
            public Item next() {
                return currentIterator.next();
            }
        };
    }

    @Override
    public String toString(){
        return null;
    }

}
