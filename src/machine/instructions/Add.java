package machine.instructions;

/**
 * Add.java
 * The ADD instruction.
 *
 * @author UMAR ARIF
 */

import machine.InstructionStack;
import machine.Alaton;

public class Add implements Instruction{

    private InstructionStack stack;

    /**
     * Create a new Add instruction.
     *
     * @param machine the machine
     */
    public Add(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    /**
     * Pops the second and then first operands off the stack, and pushes the result of the first added by the second.
     */
    @Override
    public void execute() {
        int second = this.stack.pop();
        int first = this.stack.pop();
        this.stack.push(first+second);
    }

    /**
     * Show the instruction using text so that it can be understood by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString() {
        return Alaton.ADD;
    }
}
