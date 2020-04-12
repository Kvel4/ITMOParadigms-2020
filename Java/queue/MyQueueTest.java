package queue;

import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class MyQueueTest {
    private Queue<Object> queue;
    private ArrayQueue arrayQueue;
//    private ArrayQueueADT arrayQueueADT = new ArrayQueueADT();

    private void add(Object element) {
        queue.add(element);
        arrayQueue.enqueue(element);
//        ArrayQueueADT.enqueue(arrayQueueADT, element);
//        ArrayQueueModule.enqueue(element);
    }

    private void remove() {
        Object expected = queue.poll();
        Assert.assertEquals(expected, arrayQueue.dequeue());
//        Assert.assertEquals(expected, ArrayQueueADT.dequeue(arrayQueueADT));
//        Assert.assertEquals(expected, ArrayQueueModule.dequeue());
    }

    private void checkSize() {
        Assert.assertEquals(queue.size(), arrayQueue.size());
//        Assert.assertEquals(queue.size(), ArrayQueueADT.size(arrayQueueADT));
//        Assert.assertEquals(queue.size(), ArrayQueueModule.size());
    }

    private void checkElement() {
        Assert.assertEquals(queue.peek(), arrayQueue.element());
//        Assert.assertEquals(queue.peek(), ArrayQueueADT.element(arrayQueueADT));
//        Assert.assertEquals(queue.peek(), ArrayQueueModule.element());
    }

    private void checkEmpty() {
        Assert.assertEquals(queue.isEmpty(), arrayQueue.isEmpty());
//        Assert.assertEquals(queue.isEmpty(), ArrayQueueADT.isEmpty(arrayQueueADT));
//        Assert.assertEquals(queue.isEmpty(), ArrayQueueModule.isEmpty());
    }

    private void checkClear() {
        queue.clear();
        arrayQueue.clear();
//        ArrayQueueADT.clear(arrayQueueADT);
//        ArrayQueueModule.clear();
        checkEmpty();
    }

    @Test
    public void addAndRemove() {
        queue = new LinkedList<>();
        arrayQueue = new ArrayQueue();
        add("test");
        remove();
    }

    @Test
    public void size() {
        queue = new LinkedList<>();
        arrayQueue = new ArrayQueue();
        checkSize();
        add(123);
        checkSize();
        remove();
        checkSize();
    }

    @Test
    public void empty() {
        queue = new LinkedList<>();
        arrayQueue = new ArrayQueue();
        checkEmpty();
        add('\n');
        checkEmpty();
        remove();
        checkEmpty();
    }

    @Test
    public void clear() {
        queue = new LinkedList<>();
        arrayQueue = new ArrayQueue();
        add("For the horde");
        checkClear();
    }

    @Test
    public void element() {
        queue = new LinkedList<>();
        arrayQueue = new ArrayQueue();
        checkElement();
        add(8841);
        checkElement();
        add(233);
        checkElement();
        remove();
        checkElement();
    }

    @Test
    public void emptyQueue() {
        queue = new LinkedList<>();
        arrayQueue = new ArrayQueue();
        checkSize();
        checkEmpty();
        checkElement();
        remove();
        checkClear();
    }

    @Test
    public void singleElementQueue() {
        queue = new LinkedList<>();
        arrayQueue = new ArrayQueue();
        add("hello");
        checkSize();
        checkElement();
        checkEmpty();
        checkClear();
    }

    @Test
    public void severalElementQueue() {
        queue = new LinkedList<>();
        arrayQueue = new ArrayQueue();
        add("hello");
        add("there");
        checkSize();
        remove();
        add("not");
        add("there");
        checkElement();
        checkSize();
        remove();
        checkEmpty();
        checkElement();
        checkClear();
    }
}


