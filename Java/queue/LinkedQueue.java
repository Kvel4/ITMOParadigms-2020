package queue;

public class LinkedQueue extends AbstractQueue {
    private int size = 0;
    private Node start, end;

    @Override
    public void enqueue(Object element) {
        Node tmp = new Node(element);
        if (size == 0) {
            start = tmp;
            end = tmp;
        } else {
            end.next = tmp;
            end = tmp;
        }
        size++;
    }

    @Override
    public Object element() {
        return start.value;
    }

    @Override
    public Object dequeue() {
        Object tmp = start.value;
        start = start.next;
        size--;
        return tmp;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public Object[] toArray() {
        Object[] elements = new Object[size];
        Node head = start;
        int i = 0;

        while (head != end) {
            elements[i] = head.value;
            head = head.next;
            i++;
        }
        if (size != 0) {
            elements[i] = end.value;
        }

        return elements;
    }

    private static class Node {
        Object value;
        Node next;

        Node(Object element){
            this.value = element;
            this.next = this;
        }
    }
}
