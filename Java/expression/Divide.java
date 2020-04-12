package expression;

import expression.operations.Operator;

public class Divide<T extends Number> extends AbstractBinaryOperation<T> {
    public Divide(TripleExpression<T> arg1, TripleExpression<T> arg2, Operator<T> operator) {
        super(arg1, arg2, operator);
    }

    @Override
    public T evaluate(int x, int y, int z) {
        return operator.divide(arg1.evaluate(x, y, z), arg2.evaluate(x, y, z));
    }

    @Override
    public String toString(){
        return super.toString("/");
    }
}
