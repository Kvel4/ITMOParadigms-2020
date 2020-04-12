package expression.operations;

import expression.exceptions.DivisionByZeroException;
import expression.exceptions.EvaluateException;

import java.math.BigInteger;

public class BigIntegerOperator implements Operator<BigInteger> {
    @Override
    public BigInteger add(BigInteger a, BigInteger b) {
        return a.add(b);
    }

    @Override
    public BigInteger sub(BigInteger a, BigInteger b) {
        return a.subtract(b);
    }

    @Override
    public BigInteger mul(BigInteger a, BigInteger b) {
        return a.multiply(b);
    }

    @Override
    public BigInteger divide(BigInteger a, BigInteger b) throws EvaluateException {
        if (b.equals(BigInteger.valueOf(0))) {
            throw new DivisionByZeroException("Division by zero while evaluating");
        }
        return a.divide(b);
    }

    @Override
    public BigInteger negate(BigInteger a) {
        return a.negate();
    }

    @Override
    public BigInteger parse(String s) {
        return new BigInteger(s);
    }

    @Override
    public BigInteger valueOf(int a) {
        return BigInteger.valueOf(a);
    }

    @Override
    public BigInteger min(BigInteger a, BigInteger b) {
        return a.min(b);
    }

    @Override
    public BigInteger max(BigInteger a, BigInteger b) {
        return a.max(b);
    }

    @Override
    public BigInteger count(BigInteger arg) {
        return BigInteger.valueOf(arg.bitCount());
    }
}
