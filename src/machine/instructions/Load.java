package machine.instructions;

import common.SymbolTable;
import machine.InstructionStack;
import machine.Alaton;

public class Load implements Instruction{

    private final InstructionStack stack;
    private final SymbolTable symTbl;
    private String name;

    public Load(String name, Alaton machine) {
        this.name = name;
        this.stack = machine.getInstructionStack();
        this.symTbl = machine.getSymbolTable();
    }

    @Override
    public void execute() {
        int value = this.symTbl.get(this.name);
        this.stack.push(value);
    }

    @Override
    public String toString() {
        return Alaton.LOAD + " " + this.name;
    }
}
