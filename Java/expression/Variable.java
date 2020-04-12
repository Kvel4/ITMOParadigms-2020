package expression;


import expression.operations.Operator;

public class Variable<T extends Number> implements TripleExpression<T> {
    private String name;
    private Operator<T> operator;

    public Variable(String name, Operator<T> operator) {
        this.name = name;
        this.operator = operator;
    }

    @Override
    public T evaluate(int x, int y, int z) {
        switch (name) {
            case "x":
                return operator.valueOf(x);
            case "y":
                return operator.valueOf(y);
            case "z":
                return operator.valueOf(z);
            default:
                return null;
        }
    }

    @Override
    public String toString() {
        return name;
    }

}
