package io.github.yeffycodegit.Neo;

import java.util.List;

interface NeoCallable {
    int arity();
    Object call(Interpreter interpreter, List<Object> arguments);
}
