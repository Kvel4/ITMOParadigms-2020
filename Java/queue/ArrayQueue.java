package queue;

public class ArrayQueue extends AbstractQueue {
    private Object[] elements = new Object[2];
    private int start = 0, end = 0;

    // Pre: i: start <= i <= end, queue[i] != null && element != null
    // Post: queue.end += 1, queue[end] == element
    public void enqueue(Object element) {
        if ((end + 1) % elements.length == start) {
            Object[] tmp = new Object[elements.length * 2];
            System.arraycopy(toArray(), 0, tmp, 0, size());
            elements = tmp;
            start = 0;
            end = elements.length / 2 - 1;
        }
        elements[end] = element;
        end = (end + 1) % elements.length;
    }

    // Pre: start <= i <= end, queue[i] != null
    // Post: return queue[start]
    public Object element() {
        return elements[start];
    }

    // Pre: start <= i <= end, queue[i] != null
    // Post: return queue[start] && queue.start += 1
    public Object dequeue() {
        Object tmp = elements[start];
        elements[start] = null;
        start = (start + 1) % elements.length;
        return tmp;
    }

    // Pre: start <= i <= end, queue[i] != null
    // Post: return queue.end - queue.start
    public int size() {
        if (end >= start) {
            return end - start;
        }
        return elements.length - start + end;
    }

    // Pre: start <= i <= end, queue[i] != null
    // Post: return queue.start == queue.end
    public boolean isEmpty() {
        return end == start;
    }

    // Pre: start <= i <= end, queue[i] != null
    // Post: queue.start = queue.end
    public void clear() {
        elements = new Object[elements.length];
        start = 0;
        end = 0;
    }

    // Pre: start <= i <= end, queue[i] != null
    // Post: return new Object[queue.size] = [queue[start], queue[start+1], ... , queue[end]]
    public Object[] toArray() {
        Object[] tmp = new Object[size()];
        if (start <= end) {
            System.arraycopy(elements, start, tmp, 0, end - start);
        } else {
            System.arraycopy(elements, start, tmp, 0, elements.length - start);
            System.arraycopy(elements, 0, tmp, elements.length - start, end);
        }
        return tmp;
    }
}
