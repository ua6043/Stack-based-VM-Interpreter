package machine.instructions;

/**
 * Negate.java
 * The NEG instruction.
 *
 * @author UMAR ARIF
 */

import machine.InstructionStack;
import machine.Alaton;

public class Negate implements Instruction{

    private final InstructionStack stack;

    /**
     * Create a new Negate instruction.
     *
     * @param machine the machine
     */
    public Negate(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    /**
     * Pops the operand off the stack, and pushes the result of negating it.
     */
    @Override
    public void execute() {
        int top = this.stack.pop();
        this.stack.push(-top);
    }

    /**
     * Show the instruction using text so that it can be understood by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString() {
        return Alaton.NEGATE;
    }
}
