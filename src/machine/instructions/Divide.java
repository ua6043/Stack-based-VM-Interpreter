package machine.instructions;

/**
 * Divide.java
 * The DIV instruction.
 *
 * @author UMAR ARIF
 */

import common.Errors;
import machine.InstructionStack;
import machine.Alaton;

public class Divide implements Instruction{

    private final InstructionStack stack;

    /**
     * Create a new Divide instruction.
     *
     * @param machine the machine
     */
    public Divide(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    /**
     * Pops the second and then first operands off the stack, and pushes the result of the first divided by the second.
     */
    @Override
    public void execute() {
        int second = this.stack.pop();
        if (second == 0) {
            Errors.report(Errors.Type.DIVIDE_BY_ZERO);
            System.exit(1);
        }
        int first = this.stack.pop();
        this.stack.push(first/second);
    }

    /**
     * Show the instruction using text so that it can be understood by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString() {
        return Alaton.DIVIDE;
    }
}
