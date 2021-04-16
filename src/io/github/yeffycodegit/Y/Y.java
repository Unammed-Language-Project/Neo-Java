package io.github.yeffycodegit.Y;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Y {
    private static final Interpreter interpreter = new Interpreter();

    private static boolean hadError;
    static boolean hadRuntimeError;

    public static void main(String[] args) throws IOException {
       runFile("C:\\Users\\aditc\\Desktop\\Y#\\src\\hehe.ys");
    }

    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path)); // Convert the file provided into a list of bytes
        run(new String(bytes, Charset.defaultCharset())); // Create a string with the bytes from the bytes array and pass it to the run method to run the program


        // Indicate an error in the exit code.
        if (hadError) System.exit(65);
    }

    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;) {
            System.out.print("> ");
            String line = reader.readLine();
            if (line == "exit") {
                System.out.println("Exiting repl..");
                return;
            }

            if (line == null) break;
            run(line);
        }
    }

    static void error(int line, String message) {
        report(line, "", message);
    }

    /**
     * Function to report errors during runtime
     *
     * @param: error
     */
    static void runtimeError(RuntimeError error) {
        System.err.println(error.getMessage() + "\n[line " + error.token.line + "]");
        hadRuntimeError = true;
    }

    /**
     * Function to report errors
     * */
    private static void report(int line, String where, String message) {
        System.err.println("[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }

    /**
     * Function to compile Y code
     *
     * @param source The source code to compile
     */
    private static void run(String source) {
        Lexer lexer = new Lexer(source);
        List<Token> tokens = lexer.scanTokens();

        Parser parser = new Parser(tokens);
        List<Stmt> statements = parser.parse();

        if (hadError) return;
        interpreter.interpret(statements);
    }

    /**
     * Function to print all the tokens given in a nice manner. Will be used as a debugging tool if I need to check if the tokenization is broken.
     *
     * @param tokens The list of tokens, returned from the Lexer
     */
    private static void prettyPrint(List<Token> tokens) {
        for(Token token : tokens) {
            System.out.println("[" + token.type + " : " + token.lexeme + "]");
        }
    }
}
