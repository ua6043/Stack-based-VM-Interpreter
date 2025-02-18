package machine.instructions;

import common.SymbolTable;
import machine.InstructionStack;
import machine.Alaton;

public class Store implements Instruction{

    private final InstructionStack stack;
    private final SymbolTable symTbl;
    private String name;

    public Store(String name, Alaton machine) {
        this.stack = machine.getInstructionStack();
        this.name = name;
        this.symTbl = machine.getSymbolTable();
    }

    @Override
    public void execute() {
        int top = this.stack.pop();
        this.symTbl.set(this.name, top);
    }

    @Override
    public String toString() {
        return Alaton.STORE + " " + this.name;
    }
}