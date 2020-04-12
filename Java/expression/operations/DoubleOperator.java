package expression.operations;

public class DoubleOperator implements Operator<Double> {
    @Override
    public Double add(Double a, Double b) {
        return a + b;
    }

    @Override
    public Double sub(Double a, Double b) {
        return a - b;
    }

    @Override
    public Double mul(Double a, Double b) {
        return a * b;
    }

    @Override
    public Double divide(Double a, Double b) {
        if (b == 0) {
            if (a < 0) {
                return Double.NEGATIVE_INFINITY;
            } else if (a == 0) {
                return Double.NaN;
            } else if (a > 0) {
                return Double.POSITIVE_INFINITY;
            }
        }
        return a / b;
    }

    @Override
    public Double negate(Double a) {
        return -a;
    }

    @Override
    public Double parse(String s) {
        return Double.parseDouble(s);
    }

    @Override
    public Double valueOf(int a) {
        return (double) a;
    }

    @Override
    public Double min(Double a, Double b) {
        return Math.min(a, b);
    }

    @Override
    public Double max(Double a, Double b) {
        return Math.max(a, b);
    }

    @Override
    public Double count(Double arg) {
        return (double) Long.bitCount(Double.doubleToLongBits(arg));
    }
}
