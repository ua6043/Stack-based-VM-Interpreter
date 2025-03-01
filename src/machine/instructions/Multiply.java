package machine.instructions;

/**
 * Multiply.java
 * The MUL instruction.
 *
 * @author UMAR ARIF
 */

import machine.InstructionStack;
import machine.Alaton;

public class Multiply implements Instruction{

    private final InstructionStack stack;

    /**
     * Create a new Multiply instruction.
     *
     * @param machine the machine
     */
    public Multiply(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    /**
     * Pops the second and then first operands off the stack, and pushes the result of the first multiplied
     * by the second.
     */
    @Override
    public void execute() {
        int second = this.stack.pop();
        int first = this.stack.pop();
        this.stack.push(first*second);
    }

    /**
     * Show the instruction using text so that it can be understood by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString() {
        return Alaton.MULTIPLY;
    }
}
