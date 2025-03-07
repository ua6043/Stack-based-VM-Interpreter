package interpreter.nodes.expression;

import common.SymbolTable;

import java.io.PrintWriter;
import java.util.List;

public class BinaryOperation implements ExpressionNode{

    public static final String ADD = "+";
    public static final String SUB = "-";
    public static final String MUL = "*";
    public static final String DIV = "/";
    public static final String MOD = "%";
    public static final String POW = "^";
    public static final List<String> OPERATORS = List.of(ADD, SUB, MUL, DIV, MOD, POW);
    private String operator;
    private ExpressionNode leftChild;
    public ExpressionNode rightChild;

    public BinaryOperation(String operator, ExpressionNode leftChild, ExpressionNode rightChild) {
        this.operator = operator;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

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
            result = leftValue / rightValue;
        } else if (this.operator.equals(MOD)) {
            result = leftValue % rightValue;
        } else if (this.operator.equals(POW)) {
            result = (int) Math.pow(leftValue, rightValue);
        }
        return result;
    }

    @Override
    public void emit() {
        System.out.print("( ");
        this.leftChild.emit();
        System.out.print(" " + this.operator + " ");
        this.rightChild.emit();
        System.out.print(" )");
    }

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
