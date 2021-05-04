package io.github.yeffycodegit.Neo;

import java.util.List;
import java.util.Map;

public class NeoClass implements NeoCallable {
    final String name;
    private final Map<String, NeoFunc> methods;

    NeoClass(String name, Map<String, NeoFunc> methods) {
        this.name = name;
        this.methods = methods;
    }

    @Override
    public String toString() {
        return "<class>" + name + "</class>";
    }

    @Override
    public int arity() {
        NeoFunc initializer = findMethod("constructor");
        if (initializer == null) return 0;
        return initializer.arity();
    }

    NeoFunc findMethod(String name) {
        if (methods.containsKey(name)) {
            return methods.get(name);
        }

        return null;
    }

    @Override
    public Object call(Interpreter interpreter, List<Object> arguments) {
        NeoInstance instance = new NeoInstance(this);

        NeoFunc initializer = findMethod("constructor");

        if (initializer != null) {
            initializer.bind(instance).call(interpreter, arguments);
        }

        return instance;
    }
}
