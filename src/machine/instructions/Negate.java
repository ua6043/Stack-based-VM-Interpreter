package machine.instructions;

import machine.InstructionStack;
import machine.Alaton;

public class Negate implements Instruction{

    private final InstructionStack stack;

    public Negate(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    @Override
    public void execute() {
        int top = this.stack.pop();
        this.stack.push(-top);
    }

    @Override
    public String toString() {
        return Alaton.NEGATE;
    }
}
