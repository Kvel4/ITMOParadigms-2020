package queue;

import java.util.Arrays;

public class test {
    public  static void main(String[] args) {
        LinkedQueue queue = new LinkedQueue();
        System.out.println(Arrays.toString(queue.toArray()));
        queue.enqueue(1);
        System.out.println(Arrays.toString(queue.toArray()));
        queue.enqueue(2);
        System.out.println(Arrays.toString(queue.toArray()));
        queue.toArray();
        queue.enqueue(3);
        System.out.println(Arrays.toString(queue.toArray()));
        queue.toArray();
        queue.enqueue(4);
        System.out.println(Arrays.toString(queue.toArray()));
        queue.toArray();
    }
}
