package machine.instructions;

import machine.InstructionStack;
import machine.Alaton;

public class Subtract implements Instruction{

    private final InstructionStack stack;

    public Subtract(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    @Override
    public void execute() {
        int second = this.stack.pop();
        int first = this.stack.pop();
        this.stack.push(first-second);
    }

    @Override
    public String toString() {
        return Alaton.SUBTRACT;
    }
}
