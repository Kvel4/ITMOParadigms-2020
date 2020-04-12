"use strict";

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
    if (!isNaN(token)){
        return cnst(parseInt(token));
    }

    if (token in variables) {
        return variables[token]
    }

    if (token in const_values) {
        return const_values[token]
    }

    if (token in binaryOperations) {
        let second = processToken();
        let first = processToken();
        return binaryOperations[token](first, second);

    }

    if (token in unaryOperations) {
        return unaryOperations[token](processToken());

    }
}

function parse(str) {
    string = str;
    i = str.length - 1;
    return processToken();
}


function abstractOperation(operation) {
    return (...args) => (x, y, z) => operation(...args.map(item => item(x, y, z)));
}

let add = abstractOperation((a, b) => a + b);
let subtract = abstractOperation((a, b) => a - b);
let multiply = abstractOperation((a, b) => a * b);
let divide = abstractOperation((a, b) => a / b);
let cos = abstractOperation(a => Math.cos(a));
let sin = abstractOperation(a => Math.sin(a));
let negate = abstractOperation(a => -a);
let cnst = a => (x, y, z) => a;
let variable = name => (x, y, z) => {
    switch (name) {
        case "x":
            return x;
        case "y":
            return y;
        default:
            return z;
    }
};
let pi = () => Math.PI;
let e = () => Math.E;

let binaryOperations = {
    "+" : add,
    "-" : subtract,
    "*" : multiply,
    "/" : divide,
};
let unaryOperations = {
    "negate": negate,
    "sin": sin,
    "cos": cos,
};
let variables = {
    "x" : variable("x"),
    "y" : variable("y"),
    "z" : variable("z"),
};
let const_values = {
    "e": e,
    "pi": pi,
};

let i = 0;
let string = "";
