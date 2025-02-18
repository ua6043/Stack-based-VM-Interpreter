package machine.instructions;

import machine.InstructionStack;
import machine.Alaton;

public class Add implements Instruction{

    private InstructionStack stack;

    public Add(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    @Override
    public void execute() {
        int second = this.stack.pop();
        int first = this.stack.pop();
        this.stack.push(first+second);
    }

    @Override
    public String toString() {
        return Alaton.ADD;
    }
}
