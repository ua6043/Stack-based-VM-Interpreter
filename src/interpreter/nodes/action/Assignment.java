package interpreter.nodes.action;

import common.SymbolTable;
import interpreter.nodes.expression.ExpressionNode;

import java.io.PrintWriter;

public class Assignment implements ActionNode{

    public static final String ASSIGN = "=";
    private String name;
    private ExpressionNode child;

    public Assignment(String name, ExpressionNode child) {
        this.name = name;
        this.child = child;
    }

    @Override
    public void execute(SymbolTable symTbl) {
        int value = this.child.evaluate(symTbl);
        symTbl.set(this.name, value);
    }

    @Override
    public void emit() {
        System.out.print(this.name + " " + ASSIGN + " ");
        this.child.emit();
    }

    @Override
    public void compile(PrintWriter out) {
        this.child.compile(out);
        out.println("STORE " + this.name);
    }
}
