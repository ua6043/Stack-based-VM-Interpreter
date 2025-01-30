package machine.instructions;

import machine.InstructionStack;
import machine.Alaton;

/**
 * The PUSH instruction.
 *
 * @author RIT CS
 */
public class Push implements Instruction {
    /** the value to push */
    private final int value;
    /** the instruction stack */
    private final InstructionStack stack;

    /**
     * Create a new instruction.
     * @param value value to push
     * @param machine the machine
     */
    public Push(int value, Alaton machine) {
        this.value = value;
        this.stack = machine.getInstructionStack();
    }

    /**
     * Pushes the saved value onto the instruction stack.
     */
    @Override
    public void execute() {
        this.stack.push(this.value);
    }

    @Override
    public String toString() {
        return Alaton.PUSH + " " + this.value;
    }
}
