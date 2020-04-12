package expression.parser;

import expression.exceptions.BracketMismatchException;
import expression.exceptions.IllegalCharacterException;
import expression.exceptions.OperandMismatchException;
import expression.exceptions.ParseException;

class Lexer {
    private StringBuilder tokenText;
    private Token currentToken;
    
    private final StringSource source;
    private int bracerBalance = 0;
    private int index = 0;
    private int position = 0;
    private String data;
    private char ch;

    Lexer(String string) {
        this.source = new StringSource(string);
        this.data = source.getData();
        this.ch = source.hasNext() ? source.next() : '\0';
    }

    private void nextChar(){
        ch = source.hasNext() ? source.next() : '\0';
        index++;
    }

    private void skipWhiteSpaces(){
        while (ch == ' ' || ch == '\r' || ch == '\t' || ch == '\n') {
            nextChar();
        }
    }

    void nextToken() throws ParseException {
        tokenText = new StringBuilder();
        skipWhiteSpaces();
        position = index;

        if (Character.isDigit(ch)) {
            readNumber();
            return;
        }

        switch (ch) {
            case '+':
                readOperation(Token.PLUS);
                break;
            case '-':
                readOperation(Token.MINUS);
                break;
            case '*':
                nextChar();
                tokenText.append(ch);
                if (ch == '*') {
                    readOperation(Token.POW);
                    break;
                }
                currentToken = Token.MUL;
                break;
            case '/':
                nextChar();
                tokenText.append(ch);
                if (ch == '/') {
                    readOperation(Token.LOG);
                    break;
                }
                currentToken = Token.DIV;
                break;
            case '(':
                bracerBalance++;
                readOperation(Token.LPAREN);
                break;
            case ')':
                bracerBalance--;
                if (bracerBalance < 0) {
                    throw new BracketMismatchException(data + " | " + "Expected '(' before ')' at position " + position);
                }
                readOperation(Token.RPAREN);
                break;
            case '\0':
                readOperation(Token.END);
                break;
            case 'c':
                readOperation("ount", Token.COUNT);
                break;
            case 'm':
                tokenText.append(ch);
                nextChar();
                switch (ch) {
                    case 'i':
                        readOperation("n", Token.MIN);
                        break;
                    case 'a':
                        readOperation("x", Token.MAX);
                        break;
                    default: throw new IllegalCharacterException(data + " | " + "Illegal symbol '" + ch + "' at position " + position);
                }
                break;
            case 'x':
            case 'y':
            case 'z':
                readVariable();
                break;
            default:
                throw new IllegalCharacterException(data + " | " + "Illegal symbol '" + ch + "' at position " + position);
        }
    }

    private void readNumber() {
        currentToken = Token.NUMBER;

        while (Character.isDigit(ch) || ch == '.') {
            tokenText.append(ch);
            nextChar();
        }
    }

    private void readVariable() {
        currentToken = Token.VARIABLE;
        tokenText.append(ch);
        nextChar();
    }

    private void readOperation(String remainingPart, Token token) throws ParseException {
        currentToken = token;
        tokenText.append(ch);
        
        for (char c : remainingPart.toCharArray()) {
            nextChar();
            tokenText.append(ch);
            if (c != ch) {
                throw new OperandMismatchException(data + " | " + "Illegal argument '" + tokenText.toString()
                        + "' at position: " + position);
            }
        }
        nextChar();
    }

    private void readOperation(Token token) {
        nextChar();
        tokenText.append(ch);
        currentToken = token;
    }

    Token getCurrentToken() {
        return currentToken;
    }

    String getTokenText() {
        return tokenText.toString();
    }

    String getData() {
        return data;
    }

    int getPosition() {
        return position;
    }
}
