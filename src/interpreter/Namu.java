package interpreter;

import common.Errors;
import common.SymbolTable;
import interpreter.nodes.action.*;
import interpreter.nodes.expression.*;
import machine.Alaton;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * The main program for the high level NMU language.  It takes a program in NMU,
 * converts it into a token list, builds the parse trees for each statement,
 * displays the program in infix, interprets/evaluates the program, then
 * compiles into ALT instructions so that the machine, Alaton, can execute it.
 *
 * @author RIT CS
 */
public class Namu {
    /** the terminating character when reading machine instructions from user (not file) */
    private final static String EOF = ".";

    /** the NMU print token */
    private final static String PRINT = "@";
    /** the NMU assignment token */
    private final static String ASSIGN = "=";

    /** the location to generate the compiled NMU program of NMU instructions */
    private final static String TMP_NMU_FILE = "tmp/TEMP.nmu";

    // TODO

    /**
     * Create a new Namu instance.  The result of this method is the tokenization
     * of the entire NMU input into a list of strings.  Each line that
     * represents a statement in prefix form should be printed to standard output
     * here.
     *
     * @param in where to read the NMU input from
     * @param stdin if true, the user should be prompted to enter NMU statements until
     *              a terminating ".".
     */
    public Namu(Scanner in, boolean stdin) {
        if (stdin) {
            System.out.print("🌳 ");
        }
        System.out.println("(NMU) prefix...");

        // TODO
    }

    /**
     * Build the parse trees into the program which is a list of ActioNode's -
     * one per line of NMU input.
     */
    public void buildProgram() {
        // TODO
    }

    /**
     * Displays the entire NMU program of ActionNode's to standard
     * output using emit().
     */
    public void displayProgram() {
        System.out.println("(NMU) infix...");
        // TODO
    }

    /**
     * Execute the NMU program of ActionNode's to standard output using execute().
     * In order to execute the ActioNodes, a local SymbolTable must be created here
     * for use.
     */
    public void interpretProgram() {
        System.out.println("(NMU) interpreting program...");
        // TODO
        System.out.println("(NMU) Symbol table:");
        // TODO
    }

    /**
     * Compile the NMU program using ActionNode's compile() into the
     * temporary ALT instruction file.
     *
     * @throws IOException if there are issues working with the temp file
     */
    public void compileProgram() throws IOException {
        System.out.println("(NMU) compiling program to " + TMP_NMU_FILE + "...");
        PrintWriter out = new PrintWriter(TMP_NMU_FILE);

        //TODO

        out.close();
    }

    /**
     * Takes the generated ALT instruction file and assembles/executes
     * it using the Alaton machine.
     *
     * @throws FileNotFoundException if the ALT file cannot be found.
     */
    public void executeProgram() throws FileNotFoundException {
        // TODO
    }

    /**
     * The main program runs either with no input (NMU program entered through standard
     * input), or with a file name that represents the NMU program.
     *
     * @param args command line arguments
     * @throws IOException if there are issues working with the NMU/ALT files.
     */
    public static void main(String[] args) throws IOException {
        // determine NMU input source
        Scanner nmuIn = null;
        boolean stdin = false;
        if (args.length == 0) {
            nmuIn = new Scanner(System.in);
            stdin = true;
        } else if (args.length == 1) {
            nmuIn = new Scanner(new File(args[0]));
        } else {
            System.out.println("Usage: java Namu filename.nmu");
            System.exit(1);
        }

        // step 1: read NMU program into token list
        Namu interpreter = new Namu(nmuIn, stdin);

        // step 2: parse and build the program from the token list
        interpreter.buildProgram();

        // step 3: display the program in infix
        interpreter.displayProgram();

        // step 4: interpret program
        interpreter.interpretProgram();

        // step 5: compile the program
        interpreter.compileProgram();

        // step 6: have machine execute compiled program
        interpreter.executeProgram();
    }
}
