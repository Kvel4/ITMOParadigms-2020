package expression.generic;

import expression.TripleExpression;
import expression.exceptions.EvaluateException;
import expression.operations.BigIntegerOperator;
import expression.operations.DoubleOperator;
import expression.operations.IntegerOperator;
import expression.operations.Operator;
import expression.parser.ExpressionParser;

import java.util.HashMap;

public class GenericTabulator implements Tabulator {
    private static HashMap<String, Operator<? extends Number>> MODE = new HashMap<>();
    static {
        MODE.put("i", new IntegerOperator());
        MODE.put("d", new DoubleOperator());
        MODE.put("bi", new BigIntegerOperator());
    }

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) throws Exception {
        int deltaX = x2 - x1 + 1, deltaY = y2 - y1 + 1, deltaZ = z2 - z1 + 1;
        Object[][][] table = new Object[deltaX][deltaY][deltaZ];
        ExpressionParser<? extends Number> parser = new ExpressionParser<>(MODE.get(mode));
        TripleExpression<? extends Number> exp = parser.parse(expression);

        for (int i = 0; i < deltaX; i++) {
            for (int j = 0; j < deltaY; j++) {
                for (int k = 0; k < deltaZ; k++) {
                    try{
                        table[i][j][k] = exp.evaluate(x1 + i, y1 + j, z1 + k);
                    } catch (EvaluateException e) {
                        table[i][j][k] = null;
                    }
                }
            }
        }
        return table;
    }
}
