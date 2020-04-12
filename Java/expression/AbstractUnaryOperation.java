package expression;

import expression.operations.Operator;

abstract class AbstractUnaryOperation<T extends Number> implements TripleExpression<T> {
    protected TripleExpression<T> arg;
    protected Operator<T> operator;

    protected AbstractUnaryOperation(TripleExpression<T> arg, Operator<T> operator) {
        this.arg = arg;
        this.operator = operator;
    }

    protected String toString(String operand) {
        return operand + "(" + arg.toString() + ")";
    }
}
