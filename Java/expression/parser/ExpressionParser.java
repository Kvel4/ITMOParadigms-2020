package expression.parser;

import expression.*;
import expression.exceptions.*;
import expression.operations.Operator;


public class ExpressionParser<T extends Number> implements Parser<T> {
    private Operator<T> operator;
    private Lexer lex;

    public ExpressionParser(Operator<T> operator){
        this.operator = operator;
    }

    private TripleExpression<T> cmm() throws ParseException {
        TripleExpression<T> prev = expression();
        return cmmPrime(prev);
    }

    private TripleExpression<T> cmmPrime(TripleExpression<T> prev) throws ParseException {
        TripleExpression<T> exp;
        checkCorrectness();

        switch (lex.getCurrentToken()) {
            case MIN:
                lex.nextToken();
                exp = expression();
                return cmmPrime(new Min<>(prev, exp, operator));
            case MAX:
                lex.nextToken();
                exp = expression();
                return cmmPrime(new Max<>(prev, exp, operator));
            case NUMBER:
                throw new IllegalStatementException("Unexpected number at position " + lex.getPosition());
            case LPAREN:
                throw new IllegalStatementException("Unexpected '(' at position " + lex.getPosition());
            case VARIABLE:
                throw new IllegalStatementException("Unexpected variable at position " + lex.getPosition());
            default:
                return prev;
        }
    }

    private TripleExpression<T> expression() throws ParseException {
        TripleExpression<T> prev = term();
        return expressionPrime(prev);
    }

    private TripleExpression<T> expressionPrime(TripleExpression<T> prev) throws ParseException {
        TripleExpression<T> term;
        checkCorrectness();

        switch (lex.getCurrentToken()) {
            case PLUS:
                lex.nextToken();
                term = term();
                return expressionPrime(new Add<>(prev, term, operator));
            case MINUS:
                lex.nextToken();
                term = term();
                return expressionPrime(new Subtract<>(prev, term, operator));
            default:
                return prev;
        }
    }

    private TripleExpression<T> term() throws ParseException {
        TripleExpression<T> prev = factor();
        return termPrime(prev);
    }

    private TripleExpression<T> termPrime(TripleExpression<T> prev) throws ParseException {
        TripleExpression<T> term;
        checkCorrectness();

        switch (lex.getCurrentToken()) {
            case MUL:
                lex.nextToken();
                term = factor();
                return termPrime(new Multiply<>(prev, term, operator));
            case DIV:
                lex.nextToken();
                term = factor();
                return termPrime(new Divide<>(prev, term, operator));
            default:
                return prev;
        }
    }

    private TripleExpression<T> factor() throws ParseException {
        TripleExpression<T> tmp;
        switch (lex.getCurrentToken()) {
            case MINUS:
                lex.nextToken();
                if (lex.getCurrentToken() == Token.NUMBER) {
                    return readNumber(true);
                }
                tmp = factor();
                return new Negate<>(tmp, operator);
            case NUMBER:
                return readNumber(false);
            case LPAREN:
                lex.nextToken();
                TripleExpression<T> cm = cmm();
                if (lex.getCurrentToken() == Token.RPAREN) {
                    lex.nextToken();
                    return cm;
                }
                throw new BracketMismatchException(lex.getData() + " | " + "Expected ')' at position " + lex.getPosition());
            case VARIABLE:
                tmp = new Variable<>(lex.getTokenText(), operator);
                lex.nextToken();
                return tmp;
            case COUNT:
                lex.nextToken();
                return new Count<>(cmm(), operator);
            default:
                throw new OperandMismatchException(lex.getData() + " | " +
                        "Expected number or variable or expression in brackets at position " + lex.getPosition());
        }
    }

    private TripleExpression<T> readNumber(boolean negative) throws ParseException {
        TripleExpression<T> tmp;
        if (negative) {
            tmp = new Const<>(operator.parse("-" + lex.getTokenText()));
        } else {
            tmp = new Const<>(operator.parse(lex.getTokenText()));
        }
        lex.nextToken();
        return tmp;

    }

    private void checkCorrectness() throws ParseException {
        switch (lex.getCurrentToken()) {
            case NUMBER:
                throw new OperatorMismatchException(lex.getData() + " | " + "Unexpected number at position " + lex.getPosition());
            case LPAREN:
                throw new OperatorMismatchException(lex.getData() + " | " + "Unexpected '(' at position " + lex.getPosition());
            case VARIABLE:
                throw new OperatorMismatchException(lex.getData() + " | " + "Unexpected variable at position " + lex.getPosition());
        }
    }

    @Override
    public TripleExpression<T> parse(String expression) throws ParseException {
        lex = new Lexer(expression);
        lex.nextToken();
        return cmm();
    }
}
