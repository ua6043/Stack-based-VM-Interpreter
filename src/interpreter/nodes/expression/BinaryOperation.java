package interpreter.nodes.expression;

import common.Errors;
import common.SymbolTable;

import java.io.PrintWriter;
import java.util.List;

/**
 * A calculation represented by a binary operator and its two operands.
 *
 * @author UMAR ARIF
 */
public class BinaryOperation implements ExpressionNode{

    /** NMU addition operator */
    public static final String ADD = "+";
    /** NMU subtraction operator */
    public static final String SUB = "-";
    /** NMU multiplication operator */
    public static final String MUL = "*";
    /** NMU division operator */
    public static final String DIV = "/";
    /** NMU modulus operator */
    public static final String MOD = "%";
    /** NMU power operator */
    public static final String POW = "^";
    /** the legal binary operators, for use when parsing */
    public static final List<String> OPERATORS = List.of(ADD, SUB, MUL, DIV, MOD, POW);

    private String operator;
    private ExpressionNode leftChild;
    public ExpressionNode rightChild;

    /**
     * Create a new BinaryOperation node.
     *
     * @param operator the operator
     * @param leftChild the left child expression
     * @param rightChild the right child expression
     */
    public BinaryOperation(String operator, ExpressionNode leftChild, ExpressionNode rightChild) {
        this.operator = operator;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    /**
     * Compute the result of evaluating the child expression and applying the operator to it.
     *
     * @param symTbl the symbol table, if needed, to fetch the variable values.
     * @return the result of the computation
     */
    @Override
    public int evaluate(SymbolTable symTbl) {
        int leftValue = this.leftChild.evaluate(symTbl);
        int rightValue = this.rightChild.evaluate(symTbl);
        int result = 0;
        if (this.operator.equals(ADD)) {
            result = leftValue + rightValue;
        } else if (this.operator.equals(SUB)) {
            result = leftValue - rightValue;
        } else if (this.operator.equals(MUL)) {
            result = leftValue * rightValue;
        } else if (this.operator.equals(DIV)) {
            if (rightValue == 0) {
                Errors.report(Errors.Type.DIVIDE_BY_ZERO);
            } else {
                result = leftValue / rightValue;
            }
        } else if (this.operator.equals(MOD)) {
            result = leftValue % rightValue;
        } else if (this.operator.equals(POW)) {
            result = (int) Math.pow(leftValue, rightValue);
        }
        return result;
    }

    /**
     * Print to standard output the infix display of the two child nodes separated by the operator
     * and surrounded by parentheses.
     */
    @Override
    public void emit() {
        System.out.print("( ");
        this.leftChild.emit();
        System.out.print(" " + this.operator + " ");
        this.rightChild.emit();
        System.out.print(" )");
    }

    /**
     * Generates the ALT instructions for this operation.
     *
     * @param out the stream to write output to using out.println()
     */
    @Override
    public void compile(PrintWriter out) {
        this.leftChild.compile(out);
        this.rightChild.compile(out);
        if (this.operator.equals(ADD)) {
            out.println("ADD");
        } else if (this.operator.equals(SUB)) {
            out.println("SUB");
        } else if (this.operator.equals(MUL)) {
            out.println("MUL");
        } else if (this.operator.equals(DIV)) {
            out.println("DIV");
        } else if (this.operator.equals(MOD)) {
            out.println("MOD");
        } else if (this.operator.equals(POW)) {
            out.println("POW");
        }
    }
}
