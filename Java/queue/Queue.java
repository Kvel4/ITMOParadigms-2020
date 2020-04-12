package queue;

public interface Queue {
    // Pre: i: start <= i <= end, queue[i] != null && element != null
    // Post: queue.end += 1, queue[end] == element
    void enqueue(Object element);

    // Pre: start <= i <= end, queue[i] != null
    // Post: return queue[start]
    Object element();

    // Pre: start <= i <= end, queue[i] != null
    // Post: return queue[start] && queue.start += 1
    Object dequeue();

    // Pre: start <= i <= end, queue[i] != null
    // Post: return queue.end - queue.start
    int size();

    // Pre: start <= i <= end, queue[i] != null
    // Post: return queue.start == queue.end
    boolean isEmpty();

    // Pre: start <= i <= end, queue[i] != null
    // Post: queue.start = queue.end
    void clear();

    // Pre: start <= i <= end, queue[i] != null
    // Post: return new Object[queue.size] = [queue[start], queue[start+1], ... , queue[end]]
    Object[] toArray();
}
