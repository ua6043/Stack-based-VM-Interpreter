package machine.instructions;

import machine.InstructionStack;
import machine.Alaton;

public class Power implements Instruction{

    private final InstructionStack stack;

    public Power(Alaton machine) {
        this.stack = machine.getInstructionStack();
    }

    @Override
    public void execute() {
        int second = this.stack.pop();
        int first = this.stack.pop();
        this.stack.push((int) Math.pow(first, second));
    }

    @Override
    public String toString() {
        return Alaton.POWER;
    }
}
