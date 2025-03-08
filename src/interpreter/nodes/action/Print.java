package interpreter.nodes.action;

import common.SymbolTable;
import interpreter.nodes.expression.ExpressionNode;

import java.io.PrintWriter;

/**
 * A node that represents the displaying of the value of an expression.
 *
 * @author UMAR ARIF
 */
public class Print implements ActionNode{

    public static final String PRINT = "@";
    private ExpressionNode child;

    /**
     * Create a new Print node.
     *
     * @param child the child expression
     */
    public Print(ExpressionNode child) {
        this.child = child;
    }

    /**
     * Evaluate the child expression and print the result to standard output.
     *
     * @param symTbl the table where variable values are stored
     */
    @Override
    public void execute(SymbolTable symTbl) {
        int value = this.child.evaluate(symTbl);
        System.out.println(value);
    }

    /**
     * Print the statement to standard output in the format "Print" followed by the infix form of the expression.
     */
    @Override
    public void emit() {
        System.out.print("Print ");
        this.child.emit();
    }

    /**
     * Generates the ALT instructions that when instructed will perform the print action.
     *
     * @param out the stream to write output to using out.println()
     */
    @Override
    public void compile(PrintWriter out) {
        this.child.compile(out);
        out.println("PRINT");
    }
}
