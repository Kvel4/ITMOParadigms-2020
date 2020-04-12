package queue;

public abstract class AbstractQueue implements Queue {
    @Override
    abstract public void enqueue(Object element);

    @Override
    abstract public Object element();

    @Override
    abstract public Object dequeue();

    @Override
    abstract public int size();

    @Override
    abstract public boolean isEmpty();

    @Override
    abstract public void clear();

    @Override
    abstract public Object[] toArray();
}
