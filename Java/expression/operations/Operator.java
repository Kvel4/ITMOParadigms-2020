package expression.operations;

public interface Operator<T extends Number> {
    T add(T a, T b);
    T sub(T a, T b);
    T mul(T a, T b);
    T divide(T a, T b);
    T min(T a, T b);
    T max(T a, T b);
    T count(T arg);
    T negate(T arg);
    T valueOf(int a);
    T parse(String s);
}
