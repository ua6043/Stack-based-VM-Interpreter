package machine.instructions;

import common.Errors;
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
        if (top < 0) {
            Errors.report(Errors.Type.NEGATIVE_SQUARE_ROOT);
            System.exit(1);
        }
        this.stack.push((int) Math.sqrt(top));
    }

    @Override
    public String toString() {
        return Alaton.SQUARE_ROOT;
    }
}
