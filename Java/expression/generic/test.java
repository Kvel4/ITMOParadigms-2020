package expression.generic;

import expression.operations.IntegerOperator;

public class test {
    private static <T> T eval(T a, T b) {
        return a;
    }

    public static void main(String[] args) {
        IntegerOperator integerOperator = new IntegerOperator();
        System.out.println(integerOperator.add(1, 5));
    }
}
