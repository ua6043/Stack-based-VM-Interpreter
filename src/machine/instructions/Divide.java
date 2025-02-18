package machine.instructions;

import machine.InstructionStack;
import machine.Alaton;

public class Divide implements Instruction{

    private final InstructionStack stack;

    public Divide(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    @Override
    public void execute() {
        int second = this.stack.pop();
        int first = this.stack.pop();
        this.stack.push(first/second);
    }

    @Override
    public String toString() {
        return Alaton.DIVIDE;
    }
}
