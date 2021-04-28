## Neo
_A simple, slow, interpreted language, written in Java._

Neo is an interpreted language with a simple syntax, good for beginners to programming. It is written in the Java programming language, and is my ~~first~~ second crack at making a compiler/interpreter.

An example of its syntax is shown below:

```
func add(var x, var y) {
   ret x + y;
}

print add(2, 2); // This will output 4
```

The language is in its very early stages, so any contributions are very welcome! To know more on how to contribute, check out **CONTRIBUTING.md**.

## Using Neo
I plan to have a proper installation system for Neo in the future, but for now, you need to set it up manually. 
To set up Neo, you need some prerequisites. They are:
- Java 11 or higher
- Intellij Idea (a different editor like Netbeans or Eclipse will do fine, but I like Intellij the best)
- Some basic knowledge of the command line
- Git

First, git clone this repository. Then, open up the folder with your IDE, and run `Neo.java`. This will first run a test program file, `hehe.neo`, then pop you into a REPL.

## Repl commands
Currently, there arent many REPL commands. In fact, there's only one command. That little command is `#exit`. This does exactly what you think it does, it exits the REPL. 