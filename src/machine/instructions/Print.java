package machine.instructions;

import machine.InstructionStack;
import machine.Alaton;

public class Print implements Instruction{

    private final InstructionStack stack;

    public Print(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    @Override
    public void execute() {
        int top = this.stack.pop();
        System.out.println(top);
    }

    @Override
    public String toString() {
        return Alaton.PRINT;
    }
}
