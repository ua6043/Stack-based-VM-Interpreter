package interpreter.nodes.expression;

import common.Errors;
import common.SymbolTable;

import java.io.PrintWriter;
import java.util.List;

/**
 * A calculation represented by a unary operator and its operand.
 *
 * @author UMAR ARIF
 */
public class UnaryOperation implements ExpressionNode{

    /** NMU negation operator */
    public static final String NEG = "!";
    /** NMU square root operator */
    public static final String SQRT = "$";
    /** the legal unary operators, for use when parsing */
    public static final List<String> OPERATORS = List.of(NEG, SQRT);

    private String operator;
    private ExpressionNode child;

    /**
     * Create a new UnaryOperation node.
     *
     * @param operator the operator
     * @param child the child expression
     */
    public UnaryOperation(String operator, ExpressionNode child) {
        this.operator = operator;
        this.child = child;
    }

    /**
     * Compute the result of evaluating the expression and applying the operator to it.
     *
     * @param symTbl the symbol table, if needed, to fetch the variable values.
     * @return the result of the computation
     */
    @Override
    public int evaluate(SymbolTable symTbl) {
        int value = this.child.evaluate(symTbl);
        if (this.operator.equals(NEG)) {
            value = -value;
        } else if (this.operator.equals(SQRT)) {
            if (value < 0) {
                Errors.report(Errors.Type.NEGATIVE_SQUARE_ROOT, value);
            } else {
                value = (int) Math.sqrt(value);
            }
        }
        return value;
    }

    /**
     * Print to standard output the infix display of the child nodes preceded by the operator
     * and without an intervening blank.
     */
    @Override
    public void emit() {
        System.out.print(this.operator);
        this.child.emit();
    }

    /**
     * Generates the ALT instructions for this operation.
     *
     * @param out the stream to write output to using out.println()
     */
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
