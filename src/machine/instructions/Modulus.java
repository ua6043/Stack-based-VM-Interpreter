package machine.instructions;

/**
 * Modulus.java
 * The MOD instruction.
 *
 * @author UMAR ARIF
 */

import machine.InstructionStack;
import machine.Alaton;

public class Modulus implements Instruction{

    private final InstructionStack stack;

    /**
     * Create a new Modulus instruction.
     *
     * @param machine the machine
     */
    public Modulus(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    /**
     * Pops the second and then first operands off the stack, and pushes the result of the first modulus by the second.
     */
    @Override
    public void execute() {
        int second = this.stack.pop();
        int first = this.stack.pop();
        this.stack.push(first%second);
    }

    /**
     * Show the instruction using text so that it can be understood by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString() {
        return Alaton.MODULUS;
    }
}
