package expression.operations;


import expression.exceptions.DivisionByZeroException;
import expression.exceptions.EvaluateException;
import expression.exceptions.OverflowException;

public class IntegerOperator implements Operator<Integer> {
    @Override
    public Integer add(Integer a, Integer b) throws EvaluateException {
        if ((b > 0) && (a > Integer.MAX_VALUE - b)) {
            throw new OverflowException("Overflow while evaluating");
        }else if ((b < 0) && (a < Integer.MIN_VALUE - b)) {
            throw new OverflowException("Overflow while evaluating");
        }
        return a + b;
    }

    @Override
    public Integer sub(Integer a, Integer b) throws EvaluateException {
        if ((b < 0) && (a > Integer.MAX_VALUE + b)){ /* `a - b` would overflow */
            throw new OverflowException("Overflow while evaluating");
        }else if ((b > 0) && (a < Integer.MIN_VALUE + b)) { /* `a - b` would underflow */
            throw new OverflowException("Overflow while evaluating");
        }
        return a - b;
    }

    @Override
    public Integer mul(Integer a, Integer b) throws EvaluateException {
        int r = a * b;
        int ax = Math.abs(a);
        int ay = Math.abs(b);
        if (((ax | ay) >>> 15 != 0)) {
            if (((b != 0) && (r / b != a)) ||
                    (a == Integer.MIN_VALUE && b == -1)) {
                throw new OverflowException("Overflow while evaluating");
            }
        }
        return r;
    }

    @Override
    public Integer divide(Integer a, Integer b) throws EvaluateException {
        if ((b == -1) && (a == Integer.MIN_VALUE)) {
            throw new OverflowException("Overflow while evaluating");
        }
        if (b == 0) {
            throw new DivisionByZeroException("Division by zero while evaluating");
        }
        return a / b;
    }

    @Override
    public Integer negate(Integer a) throws EvaluateException {
        if (a == Integer.MIN_VALUE) {
            throw new OverflowException("Overflow while evaluating");
        }
        return -a;
    }

    @Override
    public Integer parse(String s) throws EvaluateException {
        try {
            return Integer.parseInt(s);
        } catch (NumberFormatException e) {
            throw new OverflowException("Number " + s + " overflowed");
        }
    }

    @Override
    public Integer valueOf(int a) {
        return a;
    }

    @Override
    public Integer min(Integer a, Integer b) {
        return Math.min(a, b);
    }

    @Override
    public Integer max(Integer a, Integer b) {
        return Math.max(a, b);
    }

    @Override
    public Integer count(Integer arg) {
        return Integer.bitCount(arg);
    }
}
