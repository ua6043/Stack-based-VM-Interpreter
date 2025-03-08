package interpreter.nodes.action;

import common.SymbolTable;
import interpreter.nodes.expression.ExpressionNode;

import java.io.PrintWriter;

/**
 * An ActionNode that represents the assignment of the value of an expression to a variable.
 *
 * @author UMAR ARIF
 */
public class Assignment implements ActionNode{

    public static final String ASSIGN = "=";
    private String name;
    private ExpressionNode child;

    /**
     * Create a new Assignment node with an identifier name and child expression.
     *
     * @param name the name of the variable that is getting a new value
     * @param child the expression on the right-hand-side (RHS) of the assignment statement
     */
    public Assignment(String name, ExpressionNode child) {
        this.name = name;
        this.child = child;
    }

    /**
     * Evaluate the child expression and assign the result to the variable.
     *
     * @param symTbl the table where variable values are stored
     */
    @Override
    public void execute(SymbolTable symTbl) {
        int value = this.child.evaluate(symTbl);
        symTbl.set(this.name, value);
    }

    /**
     * Print to standard output the assignment with the variable name, followed by the assignment token,
     * and followed by the infix form of the child expression.
     */
    @Override
    public void emit() {
        System.out.print(this.name + " " + ASSIGN + " ");
        this.child.emit();
    }

    /**
     * Generates the ALT instructions that when instructed will perform the assignment.
     *
     * @param out the stream to write output to using out.println()
     */
    @Override
    public void compile(PrintWriter out) {
        this.child.compile(out);
        out.println("STORE " + this.name);
    }
}
