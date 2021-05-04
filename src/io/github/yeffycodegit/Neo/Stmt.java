package io.github.yeffycodegit.Neo;

import java.util.List;

abstract class Stmt {
  interface Visitor<R> {
    R visitBlockStmt(Block stmt);
    R visitClassStmt(Class stmt);
    R visitExpressionStmt(Expression stmt);
    R visitIfStmt(If stmt);
    R visitPrintStmt(Print stmt);
    R visitWhileStmt(While stmt);
    R visitReturnStmt(Return stmt);
    R visitVarStmt(Var stmt);
    R visitFunctionStmt(Function stmt);
  }
 public static class Block extends Stmt {
    Block(List<Stmt> statements) {
      this.statements = statements;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visitBlockStmt(this);
    }

   public final List<Stmt> statements;
  }
 public static class Class extends Stmt {
    Class(Token name, List<Stmt.Function> methods) {
      this.name = name;
      this.methods = methods;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visitClassStmt(this);
    }

   public final Token name;
   public final List<Stmt.Function> methods;
  }
 public static class Expression extends Stmt {
    Expression(Expr expression) {
      this.expression = expression;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visitExpressionStmt(this);
    }

   public final Expr expression;
  }
 public static class If extends Stmt {
    If(Expr condition, Stmt thenBranch, Stmt elseBranch) {
      this.condition = condition;
      this.thenBranch = thenBranch;
      this.elseBranch = elseBranch;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visitIfStmt(this);
    }

   public final Expr condition;
   public final Stmt thenBranch;
   public final Stmt elseBranch;
  }
 public static class Print extends Stmt {
    Print(Expr expression) {
      this.expression = expression;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visitPrintStmt(this);
    }

   public final Expr expression;
  }
 public static class While extends Stmt {
    While(Expr condition, Stmt body) {
      this.condition = condition;
      this.body = body;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visitWhileStmt(this);
    }

   public final Expr condition;
   public final Stmt body;
  }
 public static class Return extends Stmt {
    Return(Token keyword, Expr value) {
      this.keyword = keyword;
      this.value = value;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visitReturnStmt(this);
    }

   public final Token keyword;
   public final Expr value;
  }
 public static class Var extends Stmt {
    Var(Token name, Expr initializer) {
      this.name = name;
      this.initializer = initializer;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visitVarStmt(this);
    }

   public final Token name;
   public final Expr initializer;
  }
 public static class Function extends Stmt {
    Function(Token name, List<Token> params, List<Stmt> body) {
      this.name = name;
      this.params = params;
      this.body = body;
    }

    @Override
    public <R> R accept(Visitor<R> visitor) {
      return visitor.visitFunctionStmt(this);
    }

   public final Token name;
   public final List<Token> params;
   public final List<Stmt> body;
  }

 public abstract <R> R accept(Visitor<R> visitor);
}
