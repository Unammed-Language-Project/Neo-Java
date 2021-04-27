package io.github.yeffycodegit.Y;

import java.util.List;

interface YCallable {
    int arity();
    Object call(Interpreter interpreter, List<Object> arguments);
}
