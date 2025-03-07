package interpreter.nodes.expression;

import common.SymbolTable;

import java.io.PrintWriter;

public class Constant implements ExpressionNode{

    private int value;

    public Constant(int value) {
        this.value = value;
    }

    @Override
    public int evaluate(SymbolTable symTbl) {
        return this.value;
    }

    @Override
    public void emit() {
        System.out.print(this.value);
    }

    @Override
    public void compile(PrintWriter out) {
        out.println("PUSH " + this.value);
    }
}
