package io.github.yeffycodegit.Neo;

import java.util.List;

public class NeoFunc implements NeoCallable {
    private final Stmt.Function declaration;
    private final Enviorment closure;
    private final boolean isInitializer;

    NeoFunc(Stmt.Function declaration, Enviorment closure, boolean isInitializer) {
        this.declaration = declaration;
        this.isInitializer = isInitializer;
        this.closure = closure;
    }

    @Override
    public int arity() {
        return declaration.params.size();
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        Enviorment environment = new Enviorment(closure);
        for (int i = 0; i < declaration.params.size(); i++) {
            environment.define(declaration.params.get(i).lexeme, arguments.get(i));
        }

        try {
            interpreter.executeBlock(declaration.body, environment);
        } catch (Return returnValue) {
            if (isInitializer) return closure.getAt(0, "this");

            return returnValue.value;
        }

        if (isInitializer) return closure.getAt(0, "this");

        return null;
    }

    NeoFunc bind(NeoInstance instance) {
        Enviorment environment = new Enviorment(closure);
        environment.define("this", instance);
        return new NeoFunc(declaration, environment, isInitializer);
    }

    @Override
    public String toString() {
        return "<fn> " + declaration.name.lexeme + "</fn>";
    }
}
