package machine.instructions;

/**
 * Load.java
 * The LOAD instruction.
 *
 * @author UMAR ARIF
 */

import common.Errors;
import common.SymbolTable;
import machine.InstructionStack;
import machine.Alaton;

public class Load implements Instruction{

    private final InstructionStack stack;
    private final SymbolTable symTbl;
    private String name;

    /**
     * Create a new Load instruction.
     *
     * @param name the variable name
     * @param machine the machine
     */
    public Load(String name, Alaton machine) {
        this.name = name;
        this.stack = machine.getInstructionStack();
        this.symTbl = machine.getSymbolTable();
    }

    /**
     * Load the variables value from the symbol table and push it onto the stack.
     */
    @Override
    public void execute() {
        if (!this.symTbl.has(this.name)) {
            Errors.report(Errors.Type.UNINITIALIZED, this.name);
            System.exit(1);
        }
        int value = this.symTbl.get(this.name);
        this.stack.push(value);
    }

    /**
     * Show the instruction using text so that it can be understood by a person.
     *
     * @return a short string describing what this instruction will do
     */
    @Override
    public String toString() {
        return Alaton.LOAD + " " + this.name;
    }
}
