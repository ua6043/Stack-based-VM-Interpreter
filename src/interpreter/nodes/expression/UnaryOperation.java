package interpreter.nodes.expression;

import common.SymbolTable;

import java.io.PrintWriter;
import java.util.List;

public class UnaryOperation implements ExpressionNode{

    public static final String NEG = "!";
    public static final String SQRT = "$";
    public static final List<String> OPERATORS = List.of(NEG, SQRT);
    private String operator;
    private ExpressionNode child;

    public UnaryOperation(String operator, ExpressionNode child) {
        this.operator = operator;
        this.child = child;
    }

    @Override
    public int evaluate(SymbolTable symTbl) {
        int value = this.child.evaluate(symTbl);
        if (this.operator.equals(NEG)) {
            value = -value;
        } else if (this.operator.equals(SQRT)) {
            value = (int) Math.sqrt(value);
        }
        return value;
    }

    @Override
    public void emit() {
        System.out.print(this.operator);
        this.child.emit();
    }

    @Override
    public void compile(PrintWriter out) {
        this.child.compile(out);
        if (this.operator.equals(NEG)) {
            out.println("NEG");
        } else if (this.operator.equals(SQRT)) {
            out.println("SQRT");
        }
    }
}
