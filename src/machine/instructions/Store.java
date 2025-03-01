package machine.instructions;

import common.SymbolTable;
import machine.InstructionStack;
import machine.Alaton;

/**
 * Store.java
 * The STORE instruction.
 *
 * @author UMAR ARIF
 */
public class Store implements Instruction{

    private final InstructionStack stack;
    private final SymbolTable symTbl;
    private String name;

    /**
     * Create a new Store instruction.
     *
     * @param name the variable name
     * @param machine the machine
     */
    public Store(String name, Alaton machine) {
        this.stack = machine.getInstructionStack();
        this.name = name;
        this.symTbl = machine.getSymbolTable();
    }

    /**
     * Pops the value off the top of stack and sets the variable's value in the symbol table to the value.
     */
    @Override
    public void execute() {
        int top = this.stack.pop();
        this.symTbl.set(this.name, top);
    }

    /**
     * Show the instruction using text so that it can be understood by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString() {
        return Alaton.STORE + " " + this.name;
    }
}