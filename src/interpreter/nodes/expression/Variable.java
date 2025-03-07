package interpreter.nodes.expression;

import common.SymbolTable;

import java.io.PrintWriter;

public class Variable implements ExpressionNode{

    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public int evaluate(SymbolTable symTbl) {
        return symTbl.get(this.name);
    }

    @Override
    public void emit() {
        System.out.print(this.name);
    }

    @Override
    public void compile(PrintWriter out) {
        out.println("LOAD " + this.name);
    }
}
