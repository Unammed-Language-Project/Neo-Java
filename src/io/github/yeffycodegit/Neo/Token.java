package io.github.yeffycodegit.Neo;

public class Token {
    final TokenTypes type;
    final String lexeme;
    final Object literal;
    final int line;

    Token(TokenTypes type, String lexeme, Object literal, int line) {
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }

    public String toString() {
        return type + " " + lexeme + " " + literal;
    }
}
