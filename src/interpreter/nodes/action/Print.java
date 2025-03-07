package interpreter.nodes.action;

import common.SymbolTable;
import interpreter.nodes.expression.ExpressionNode;

import java.io.PrintWriter;

public class Print implements ActionNode{

    public static final String PRINT = "@";
    private ExpressionNode child;

    public Print(ExpressionNode child) {
        this.child = child;
    }

    @Override
    public void execute(SymbolTable symTbl) {
        int value = this.child.evaluate(symTbl);
        System.out.println(value);
    }

    @Override
    public void emit() {
        System.out.print("Print ");
        this.child.emit();
    }

    @Override
    public void compile(PrintWriter out) {
        this.child.compile(out);
        out.println("PRINT");
    }
}
