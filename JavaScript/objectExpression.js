"use strict";

function parse(string) {
    let i = string.length - 1;

    function skipWs() {
        while (string[i] === " ") {
            i--;
        }
    }

    function readToken() {
        let s = [];
        while (i > -1 && string[i] !== " ") {
            s.push(string[i--]);
        }
        return s.reverse().join("");
    }

    function processToken() {
        skipWs();
        let token = readToken();

        // number aka const
        if (!isNaN(token)) {
            return new Const(parseInt(token));
        }

        if (token in variables) {
            return variables[token]
        }

        if (token in binaryOperations) {
            let second = processToken();
            let first = processToken();
            return new binaryOperations[token](first, second);

        }

        if (token in unaryOperations) {
            return new unaryOperations[token](processToken());
        }
    }

    return processToken();
}

function parsePrefix(string) {
    let Token = Object.freeze({
        LPAREN: 1,
        RPAREN: 2,
        ADD: 3,
        SUB: 4,
        MUL: 5,
        DIV: 6,
        NEGATE: 7,
        VARIABLE: 8,
        CONST: 9,
        AVG: 10,
        SUM: 11,
        ATAN: 12,
        EXP: 13,
        END: 14
    });

    let lexer = function (string) {
        let i = 0;
        let tokenText = [];

        function getToken() {
            skipWs();
            tokenText = [];

            if (isNumber(string[i])) {
                return readNumber();
            }

            tokenText.push(string[i]);
            switch (string[i++]) {
                case "(":
                    return Token.LPAREN;
                case ")":
                    return Token.RPAREN;
                case "+":
                    return Token.ADD;
                case "-":
                    if (isNumber(string[i])) {
                        return readNumber();
                    }
                    return Token.SUB;
                case "*":
                    return Token.MUL;
                case "/":
                    return Token.DIV;
                case "n":
                    if (expected("egate")) {
                        return Token.NEGATE;
                    }
                    throw new error.IllegalCharacterError(`${string} | Illegal character at position ${i}`);
                case "a":
                    if (expected("tan")) {
                        return Token.ATAN;
                    }
                    throw new error.IllegalCharacterError(`${string} | Illegal character at position ${i}`);
                case "e":
                    if (expected("xp")) {
                        return Token.EXP;
                    }
                    throw new error.IllegalCharacterError(`${string} | Illegal character at position ${i}`);
                case "x":
                case "y":
                case "z":
                    return Token.VARIABLE;
                case "\0":
                    return Token.END;
                default:
                    throw new error.IllegalCharacterError(`${string} | Illegal character at position ${i}`);
            }
        }

        function readNumber() {
            while (i < string.length && isNumber(string[i])) {
                tokenText.push(string[i++]);
            }
            return Token.CONST;
        }

        function expected(expectedString) {
            for (let j = 0; j < expectedString.length; j++) {
                if (string[i] !== expectedString[j]) {
                    return false
                }
                tokenText.push(string[i++]);
            }
            return true
        }

        function isNumber(c){
            return c >= '0' && c <= '9';
        }

        function skipWs() {
            while (i < string.length && string[i] === " ") {
                i++;
            }
        }

        function getTokenText() {
            return tokenText.join("");
        }

        function getTokenPosition() {
            return i - tokenText.length;
        }

        function getIndex() {
            return i;
        }

        return {
            getToken: getToken,
            getTokenText: getTokenText,
            getIndex: getIndex,
            getTokenPosition: getTokenPosition,
            skipWs: skipWs
        }
    }(string + '\0');

    let error = function () {
        function ParseError(message) {
            this.message = message;
        }

        setProperties(ParseError, Error);

        function IllegalCharacterError(message) {
            this.message = message;
        }

        setProperties(IllegalCharacterError, ParseError);

        function OperandMismatchError(message) {
            this.message = message;
        }

        setProperties(OperandMismatchError, ParseError);

        function OperatorMismatchError(message) {
            this.message = message;
        }

        setProperties(OperatorMismatchError, ParseError);

        function BracketMismatchError(message) {
            this.message = message;
        }

        setProperties(BracketMismatchError, ParseError);

        function UnexpectedSymbolError(message) {
            this.message = message;
        }

        setProperties(UnexpectedSymbolError, ParseError);

        function setProperties(ErrorName, ParentName) {
            ErrorName.prototype = Object.create(ParentName.prototype);
            ErrorName.prototype.constructor = ErrorName;
            ErrorName.prototype.name = ErrorName.name;
        }

        return {
            IllegalCharacterError: IllegalCharacterError,
            OperandMismatchError: OperandMismatchError,
            OperatorMismatchError: OperatorMismatchError,
            BracketMismatchError: BracketMismatchError,
            UnexpectedSymbolError: UnexpectedSymbolError
        }
    }();

    function expression() {
        let token = lexer.getToken();

        switch (token) {
            case Token.LPAREN:
                let op = operation();
                if (lexer.getToken() === Token.RPAREN) {
                    return op;
                }
                throw new error.BracketMismatchError(`"${string}" | Expected closing bracket at position ${lexer.getTokenPosition()}`);
            case Token.CONST:
                return new Const(parseInt(lexer.getTokenText()));
            case Token.VARIABLE:
                return new Variable(lexer.getTokenText());
            default:
                throw new error.OperandMismatchError(`"${string}" | Expected operand at position ${lexer.getTokenPosition()}, but got "${lexer.getTokenText()}"`)
        }
    }

    function operation() {
        let token = lexer.getToken();

        switch (token) {
            case Token.NEGATE:
                return new Negate(expression());
            case Token.ATAN:
                return new ArcTan(expression());
            case Token.EXP:
                return new Exp(expression());
            case Token.ADD:
                return new Add(expression(), expression());
            case Token.SUB:
                return new Subtract(expression(), expression());
            case Token.MUL:
                return new Multiply(expression(), expression());
            case Token.DIV:
                return new Divide(expression(), expression());
            default:
                throw new error.OperatorMismatchError(`"${string}" | Expected operator at position ${lexer.getTokenPosition()}, but got "${lexer.getTokenText()}"`)
        }
    }

    let exp = expression();
    lexer.skipWs();
    if (lexer.getIndex() < string.length) {
        lexer.getToken();
        throw new error.UnexpectedSymbolError(`"${string}" | Expected nothing at position ${lexer.getTokenPosition()}, but got "${lexer.getTokenText()}"`)
    }
    return exp;
}


function AbstractOperation(operation, operator, ...args) {
    this.evaluate = (x, y, z) => operation(...args.map(item => item.evaluate(x, y, z)));
    this.toString = () => args.map(item => item.toString()).join(" ") + " " + operator;
    this.prefix = () => "(" + operator + " " + args.map(item => item.prefix()).join(" ") + ")";
}

function Add(a, b) {
    AbstractOperation.call(this, (a, b) => a + b, "+", a, b);
    this.diff = variable => new Add(a.diff(variable), b.diff(variable))
}

function Subtract(a, b) {
    AbstractOperation.call(this, (a, b) => a - b, "-", a, b);
    this.diff = variable => new Subtract(a.diff(variable), b.diff(variable));
}

function Multiply(a, b) {
    AbstractOperation.call(this, (a, b) => a * b, "*", a, b);
    this.diff = variable => new Add(
        new Multiply(a.diff(variable), b),
        new Multiply(a, b.diff(variable))
    );
}

function Divide(a, b) {
    AbstractOperation.call(this, (a, b) => a / b, "/", a, b);
    this.diff = variable => new Divide(
        new Subtract(
            new Multiply(a.diff(variable), b),
            new Multiply(a, b.diff(variable))
        ),
        new Multiply(b, b)
    );
}

function Negate(a) {
    AbstractOperation.call(this, a => -a, "negate", a);
    this.diff = variable => new Negate(a.diff(variable))
}

function ArcTan(a) {
    AbstractOperation.call(this, a => Math.atan(a), "atan", a);
}

function Exp(a) {
    AbstractOperation.call(this, a => Math.exp(a), "exp", a);
}

function Sinh(a) {
    AbstractOperation.call(this, a => Math.sinh(a), "sinh", a);
    this.diff = variable => new Multiply(a.diff(variable), new Cosh(a));
}

function Cosh(a) {
    AbstractOperation.call(this, a => Math.cosh(a), "cosh", a);
    this.diff = variable => new Multiply(a.diff(variable), new Sinh(a));
}

function Const(a) {
    this.evaluate = () => a;
    this.toString = () => a.toString();
    this.prefix = () => a.toString();
    this.diff = () => new Const(0);
}

function Variable(name) {
    this.evaluate = (x, y, z) => {
        switch (name) {
            case "x":
                return x;
            case "y":
                return y;
            default:
                return z;
        }
    };
    this.toString = () => name;
    this.prefix = () => name;
    this.diff = variable => {
        if (variable === name) {
            return new Const(1);
        }
        return new Const(0);
    }
}

let binaryOperations = {
    "+": Add,
    "-": Subtract,
    "*": Multiply,
    "/": Divide,
};

let unaryOperations = {
    "negate": Negate,
    "sinh": Sinh,
    "cosh": Cosh
};

let variables = {
    "x": new Variable("x"),
    "y": new Variable("y"),
    "z": new Variable("z"),
};