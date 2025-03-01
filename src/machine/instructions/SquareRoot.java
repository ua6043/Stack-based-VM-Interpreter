package machine.instructions;

/**
 * SquareRoot.java
 * The SQRT instruction.
 *
 * @author UMAR ARIF
 */

import common.Errors;
import machine.InstructionStack;
import machine.Alaton;

public class SquareRoot implements Instruction{

    private final InstructionStack stack;

    /**
     * Create a new SquareRoot instruction.
     * @param machine the machine
     */
    public SquareRoot(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    /**
     * Pops the operand off the stack, and pushes the integer result of taking the square root of it.
     */
    @Override
    public void execute() {
        int top = this.stack.pop();
        if (top < 0) {
            Errors.report(Errors.Type.NEGATIVE_SQUARE_ROOT);
            System.exit(1);
        }
        this.stack.push((int) Math.sqrt(top));
    }

    /**
     * Show the instruction using text so that it can be understood by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString() {
        return Alaton.SQUARE_ROOT;
    }
}
