package machine;

import common.Errors;
import common.SymbolTable;
import machine.instructions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


/**
 * The machine can process/execute a series of low level ALT instructions using
 * an instruction stack and symbol table.
 *
 * @author RIT CS
 */
public class Alaton {
    /** the push instruction */
    public final static String PUSH = "PUSH";
    /** the print instruction */
    public final static String PRINT = "PRINT";
    /** the store instruction */
    public final static String STORE = "STORE";
    /** the load instruction */
    public final static String LOAD = "LOAD";
    /** the negate instruction */
    public final static String NEGATE = "NEG";
    /** the square root instruction */
    public final static String SQUARE_ROOT = "SQRT";
    /** the add instruction */
    public final static String ADD = "ADD";
    /** the subtract instruction */
    public final static String SUBTRACT = "SUB";
    /** the multiply instruction */
    public final static String MULTIPLY = "MUL";
    /** the divide instruction */
    public final static String DIVIDE = "DIV";
    /** the modulus instruction */
    public final static String MODULUS = "MOD";
    /** the power instruction */
    public final static String POWER = "POW";

    /** the list of valid machine instructions */
    public static final List< String > OPERATIONS =
            List.of(
                    ADD,
                    DIVIDE,
                    LOAD,
                    MODULUS,
                    MULTIPLY,
                    NEGATE,
                    POWER,
                    PUSH,
                    PRINT,
                    SQUARE_ROOT,
                    STORE,
                    SUBTRACT
            );

    /** the terminating character when reading machine instructions from user (not file) */
    private final static String EOF = ".";

    private InstructionStack instStack;
    private SymbolTable symTbl;
    private final ArrayList<Instruction> instructions = new ArrayList<Instruction>();

    /**
     * Create a new machine, with an empty symbol table, instruction stack, and
     * list of instructions.
     */
    public Alaton() {
        InstructionStack instrStack = new InstructionStack();
        SymbolTable symTbl = new SymbolTable();
        this.instStack = instrStack;
        this.symTbl = symTbl;
    }

    /**
     * Return the instruction stack.
     *
     * @return the stack
     */
    public InstructionStack getInstructionStack() {
        return this.instStack;
    }

    /**
     * Return the symbol table.
     *
     * @return the symbol table
     */
    public SymbolTable getSymbolTable() {
        return this.symTbl;
    }


    /**
     * Assemble the machine instructions.
     *
     * @param altIn the input source
     * @param stdin true if input is coming from standard input (for prompting)
     */
    public void assemble(Scanner altIn, boolean stdin) {
        while (true) {
            if (stdin) {
                System.out.print("🤖 ");
            }

            if (!altIn.hasNextLine()) {
                break;
            }

            String line = altIn.nextLine().strip();

            if (stdin && line.equals(EOF)) {
                break;
            }
            if (line.isEmpty()) {
                continue;
            }

            String[] fields = line.split("\\s+");

            if (!OPERATIONS.contains(fields[0])) {
                Errors.report(Errors.Type.ILLEGAL_INSTRUCTION, fields[0]);
                System.exit(1);
            }

            if (fields[0].equals(PUSH) && fields.length == 2) {
                instructions.add(new Push(Integer.parseInt(fields[1]), this));
            } else if (fields[0].equals(PRINT)) {
                instructions.add(new Print(this));
            } else if (fields[0].equals(STORE) && fields.length == 2) {
                instructions.add(new Store(fields[1], this));
            } else if (fields[0].equals(LOAD) && fields.length == 2) {
                instructions.add(new Load(fields[1], this));
            } else if (fields[0].equals(NEGATE)) {
                instructions.add(new Negate(this));
            } else if (fields[0].equals(SQUARE_ROOT)) {
                instructions.add(new SquareRoot(this));
            } else if (fields[0].equals(ADD)) {
                instructions.add(new Add(this));
            } else if (fields[0].equals(SUBTRACT)) {
                instructions.add(new Subtract(this));
            } else if (fields[0].equals(MULTIPLY)) {
                instructions.add(new Multiply(this));
            } else if (fields[0].equals(DIVIDE)) {
                instructions.add(new Divide(this));
            } else if (fields[0].equals(MODULUS)) {
                instructions.add(new Modulus(this));
            } else if (fields[0].equals(POWER)) {
                instructions.add(new Power(this));
            }
        }

        System.out.println("(ALT) Machine instructions:");
        for (Instruction instruction : instructions) {
            System.out.println(instruction);
        }
    }

    /**
     * Executes each assembled machine instruction in order.  When completed it
     * displays the symbol table and the instruction stack.
     */
    public void execute() {
        System.out.println("(ALT) Executing...");

        for (Instruction instruction : instructions) {
            instruction.execute();
        }

        System.out.println("(ALT) Completed execution!");
        System.out.println("(ALT) Symbol table:");
        System.out.print(this.symTbl.toString());

        System.out.println("(ALT) Instruction stack:");
        if (this.instStack.size() == 0) {
            System.out.println("\tEMPTY");
        } else {
            System.out.println(this.instStack);
        }
    }

    /**
     * The main method.  Machine instructions can either be specified from standard input
     * (no command line), or from a file (only argument on command line).  From
     * here the machine assembles the instructions and then executes them.
     *
     * @param args command line argument (optional)
     * @throws FileNotFoundException if the machine file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        // determine input source
        Scanner altIn = null;
        boolean stdin = false;
        if (args.length == 0) {
            altIn = new Scanner(System.in);
            stdin = true;
        } else if (args.length == 1){
            altIn = new Scanner(new File(args[0]));
        } else {
            System.out.println("Usage: java Alaton [filename.alt]");
            System.exit(1);
        }

        Alaton machine = new Alaton();
        machine.assemble(altIn, stdin);            // assemble the machine instructions
        machine.execute();                         // execute the program
        altIn.close();
    }
}
