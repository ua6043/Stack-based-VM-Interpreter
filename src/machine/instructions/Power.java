package machine.instructions;

/**
 * Power.java
 * The POW instruction.
 *
 * @author UMAR ARIF
 */

import machine.InstructionStack;
import machine.Alaton;

public class Power implements Instruction{

    private final InstructionStack stack;

    /**
     * Create a new Power instruction.
     *
     * @param machine the machine
     */
    public Power(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    /**
     * Pops the second and then first operands off the stack, and pushes the result of the power of the first
     * raised to the second.
     */
    @Override
    public void execute() {
        int second = this.stack.pop();
        int first = this.stack.pop();
        this.stack.push((int) Math.pow(first, second));
    }

    /**
     * Show the instruction using text so that it can be understood by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString() {
        return Alaton.POWER;
    }
}
