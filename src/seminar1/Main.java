package seminar1;

import java.util.Arrays;
import java.util.Comparator;

import seminar1.collections.ArrayPriorityQueue;
import seminar1.collections.ArrayStack;
import seminar1.collections.CyclicArrayDeque;
import seminar1.collections.CyclicArrayQueue;
import seminar1.collections.IDeque;
import seminar1.collections.IPriorityQueue;
import seminar1.collections.IQueue;
import seminar1.collections.IStack;
import seminar1.collections.LinkedDeque;
import seminar1.collections.LinkedQueue;
import seminar1.collections.LinkedStack;
import seminar1.collections.TwoStackQueue;
import seminar1.iterators.IncreasingIterator;
import seminar1.iterators.MergingIncreasingIterator;

public class Main {

    public static void main(String[] args) {
        //Test ArrayPriorityQueue
        /*IPriorityQueue<Integer> apQueue = new ArrayPriorityQueue<>((Integer x, Integer y) -> x <= y ? x.equals(y) ? 0 : 1 : -1);
        for (int i = 0; i < 6; i++) {
            apQueue.add(i);
        }
        apQueue.add(-1);
        apQueue.add(-3);

        for (int i = 0; i < 3; i++) {
            System.out.println(apQueue.extractMin());

            for (int j : apQueue) {
                System.out.print(j + " ");
            }

            System.out.println();
        }

        for (int i : apQueue) {
            System.out.print(i + " ");
        }
        */

        //Test ArrayStack
        /*IStack<Integer> arrayStack = new ArrayStack<>();
        for (int i = 0; i < 12; i++) {
            arrayStack.push(i);
        }
        for (int i = 0; i < 13; i++) {
            System.out.println(arrayStack.pop());
        }
        for (int i: arrayStack) {
            System.out.print(i + " ");
        }*/

        //Test for LinkedQueue
        /*IQueue<Integer> queue = new LinkedQueue<>();
        for (int i = 0; i < 4; i++) {
            queue.enqueue(i);
        }

        for (int i : queue) {
            System.out.print(i + " ");
        }

        for (int i = 0; i < 5; i++) {
            System.out.print(queue.dequeue() + " ");
        }*/

        //Test for LinkedDeque
        /*IDeque<Integer> deque = new LinkedDeque<>();
        for (int i = 0; i < 10; i++) {
            deque.pushBack(i);
        }

        for (int i : deque) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < 5; i++) {
            System.out.print(deque.popBack() + " ");
        }*/


        //Test LinkedStack
        /*IStack<Integer> stack = new LinkedStack<>();
        for (int i = 0; i < 4; i++) {
            stack.push(i);
        }

        for (int i : stack) {
            System.out.print(i + " ");
        }

        for (int i = 0; i < 5; i++) {
            System.out.print(stack.pop() + " ");
        }*/

        //Test MergingIncreasingIterator
        /*MergingIncreasingIterator it = new MergingIncreasingIterator(
                new IncreasingIterator(1, 6, 4),
                new IncreasingIterator(1, 6, 4)
        );

        while (it.hasNext()) {
            System.out.println(it.next());
        }*/

    }
}
