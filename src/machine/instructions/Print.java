package machine.instructions;

/**
 * Print.java
 * The PRINT instruction.
 *
 * @author UMAR ARIF
 */

import machine.InstructionStack;
import machine.Alaton;

public class Print implements Instruction{

    private final InstructionStack stack;

    /**
     * Create a new Print instruction.
     *
     * @param machine the machine
     */
    public Print(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    /**
     * Pops the top operand off the stack and prints the resulting value.
     */
    @Override
    public void execute() {
        int top = this.stack.pop();
        System.out.println(top);
    }

    /**
     * Show the instruction using text so that it can be understood by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString() {
        return Alaton.PRINT;
    }
}
