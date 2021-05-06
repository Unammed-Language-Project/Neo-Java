package io.github.yeffycodegit.Neo.tools;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

public class GenerateAst {

    public static void main(String[] args) throws IOException {
        String outputDir = "C:\\Users\\aditc\\Desktop\\Y#\\src\\io\\github\\yeffycodegit\\Neo\\";


         defineAst(outputDir, "Expr", Arrays.asList(
                 "Binary   : Expr left, Token operator, Expr right",
                 "Call     : Expr callee, Token paren, List<Expr> arguments",
                 "Get      : Expr object, Token name",
                 "Assign   : Token name, Expr value",
                 "Grouping : Expr expression",
                 "Literal  : Object value",
                 "Logical  : Expr left, Token operator, Expr right",
                 "Set      : Expr object, Token name, Expr value",
                 "Super    : Token keyword, Token method",
                 "This     : Token keyword",
                 "Unary    : Token operator, Expr right",
                 "Variable : Token name"
         ));

        defineAst(outputDir, "Stmt", Arrays.asList(
                "Block      : List<Stmt> statements",
                "Class      : Token name, Expr.Variable superclass," + " List<Stmt.Function> methods",
                "Expression : Expr expression",
                "If         : Expr condition, Stmt thenBranch," + " Stmt elseBranch",
                "Print      : Expr expression",
                "While      : Expr condition, Stmt body",
                "Return     : Token keyword, Expr value",
                "Var        : Token name, Expr initializer",
                "Function   : Token name, List<Token> params," + " List<Stmt> body"
        ));
    }

    private static void defineAst(String outputDir, String baseName, List<String> types) throws IOException {
        String path = outputDir + "/" + baseName + ".java";
        PrintWriter writer = new PrintWriter(path, "UTF-8");

        writer.println("package io.github.yeffycodegit.Neo;");
        writer.println();
        writer.println("import java.util.List;");
        writer.println();
        writer.println("abstract class " + baseName + " {");

        defineVisitor(writer, baseName, types);

        for (String type : types) {
            String className = type.split(":")[0].trim();
            String fields = type.split(":")[1].trim();
            defineType(writer, baseName, className, fields);
        }

        // The base accept() method.
        writer.println();
        writer.println(" public abstract <R> R accept(Visitor<R> visitor);");

        writer.println("}");
        writer.close();
    }

    private static void defineType(PrintWriter writer, String baseName, String className, String fieldList) {
        writer.println(" public static class " + className + " extends " + baseName + " {");

        // Constructor.
        writer.println("    " + className + "(" + fieldList + ") {");

        // Store parameters in fields.
        String[] fields = fieldList.split(", ");

        for (String field : fields) {
            String name = field.split(" ")[1];
            writer.println("      this." + name + " = " + name + ";");
        }

        writer.println("    }");

        // Visitor pattern.
        writer.println();
        writer.println("    @Override");
        writer.println("    public <R> R accept(Visitor<R> visitor) {");
        writer.println("      return visitor.visit" + className + baseName + "(this);");
        writer.println("    }");

        // Fields.
        writer.println();
        for (String field : fields) {
            writer.println("   public final " + field + ";");
        }

        writer.println("  }");
    }
    private static void defineVisitor(PrintWriter writer, String baseName, List<String> types) {
        writer.println("  interface Visitor<R> {");

        for (String type : types) {
            String typeName = type.split(":")[0].trim();
            writer.println("    R visit" + typeName + baseName + "(" +
                    typeName + " " + baseName.toLowerCase() + ");");
        }

        writer.println("  }");
    }
}
