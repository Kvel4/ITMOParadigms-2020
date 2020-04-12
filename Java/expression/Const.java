package expression;


public class Const<T extends Number> implements TripleExpression<T> {
    private T val;

    public Const(T val) {
        this.val = val;
    }

    @Override
    public T evaluate(int x, int y, int z) {
        return val;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}
