package machine.instructions;

import machine.InstructionStack;
import machine.Alaton;

public class SquareRoot implements Instruction{

    private final InstructionStack stack;

    public SquareRoot(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    @Override
    public void execute() {
        int top = this.stack.pop();
        this.stack.push((int) Math.sqrt(top));
    }

    @Override
    public String toString() {
        return Alaton.SQUARE_ROOT;
    }
}
