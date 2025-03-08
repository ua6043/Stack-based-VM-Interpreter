package interpreter.nodes.expression;

import common.SymbolTable;

import java.io.PrintWriter;

/**
 * An ExpressionNode representing a constant, i.e., a literal integer value.
 *
 * @author UMAR ARIF
 */
public class Constant implements ExpressionNode{

    private int value;

    /**
     * Create a new constant.
     *
     * @param value the value
     */
    public Constant(int value) {
        this.value = value;
    }

    /**
     * Return the stored value when evaluated.
     *
     * @param symTbl the symbol table, if needed, to fetch the variable values.
     * @return the value
     */
    @Override
    public int evaluate(SymbolTable symTbl) {
        return this.value;
    }

    /**
     * Print the stored value to standard output.
     */
    @Override
    public void emit() {
        System.out.print(this.value);
    }

    /**
     * Generates the ALT instruction for pushing the value.
     *
     * @param out the stream to write output to using out.println()
     */
    @Override
    public void compile(PrintWriter out) {
        out.println("PUSH " + this.value);
    }
}
