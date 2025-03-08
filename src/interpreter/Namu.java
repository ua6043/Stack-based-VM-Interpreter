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
 * @author UMAR ARIF
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

    private ArrayList<String> tokenList;
    private LinkedList<ActionNode> program;

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
        this.tokenList = new ArrayList<>();
        this.program = new LinkedList<>();
        if (stdin) {
            System.out.print("🌳 ");
        }
        System.out.println("(NMU) prefix...");
        while (stdin || in.hasNextLine()) {
            String line = in.nextLine().strip();
            System.out.println(line);

            if (stdin && line.equals(EOF)) {
                break;
            }
            String[] tokens = line.split("\\s+");
            Collections.addAll(this.tokenList, tokens);
            if (stdin) {
                System.out.print("🌳 ");
            }
        }
    }

    /**
     * Build the parse trees into the program which is a list of ActionNode's -
     * one per line of NMU input.
     */
    public void buildProgram() {
        int i = 0;
        while (i < this.tokenList.size()) {
            String token = this.tokenList.get(i);

            if (token.equals(ASSIGN)) {
                if (i + 1 >= this.tokenList.size() || !this.tokenList.get(i + 1).matches("^[a-zA-Z].*")) {
                    Errors.report(Errors.Type.PREMATURE_END);
                }
                String variable = this.tokenList.get(i + 1);
                i += 2;

                ArrayList<String> tokens = new ArrayList<>();
                while (i < this.tokenList.size() && !this.tokenList.get(i).equals(ASSIGN) && !this.tokenList.get(i).equals(PRINT)) {
                    tokens.add(this.tokenList.get(i));
                    i++;
                }
                if (tokens.isEmpty()) {
                    Errors.report(Errors.Type.PREMATURE_END);
                }
                Collections.reverse(tokens);

                LinkedList<ExpressionNode> stack = new LinkedList<>();
                for (String t : tokens) {
                    if (t.matches("^[a-zA-Z].*")) {
                        stack.push(new Variable(t));
                    } else if (t.matches("^-?\\d+$")) {
                        stack.push(new Constant(Integer.parseInt(t)));
                    } else if (UnaryOperation.OPERATORS.contains(t)) {
                        if (stack.isEmpty()) {
                            Errors.report(Errors.Type.PREMATURE_END);
                        }
                        ExpressionNode operand = stack.pop();
                        stack.push(new UnaryOperation(t, operand));
                    } else if (BinaryOperation.OPERATORS.contains(t)) {
                        if (stack.size() < 2) {
                            Errors.report(Errors.Type.PREMATURE_END);
                        }
                        ExpressionNode left = stack.pop();
                        ExpressionNode right = stack.pop();
                        stack.push(new BinaryOperation(t, left, right));
                    } else {
                        Errors.report(Errors.Type.ILLEGAL_OPERATOR, t);
                    }
                }
                if (stack.size() != 1) {
                    Errors.report(Errors.Type.PREMATURE_END);
                }
                ExpressionNode expression = stack.pop();
                this.program.add(new Assignment(variable, expression));

            } else if (token.equals(PRINT)) {
                i++;

                ArrayList<String> tokens = new ArrayList<>();
                while (i < this.tokenList.size() && !this.tokenList.get(i).equals(ASSIGN) && !this.tokenList.get(i).equals(PRINT)) {
                    tokens.add(this.tokenList.get(i));
                    i++;
                }
                if (tokens.isEmpty()) {
                    Errors.report(Errors.Type.PREMATURE_END);
                }
                Collections.reverse(tokens);

                LinkedList<ExpressionNode> stack = new LinkedList<>();
                for (String t : tokens) {
                    if (t.matches("^[a-zA-Z].*")) {
                        stack.push(new Variable(t));
                    } else if (t.matches("^-?\\d+$")) {
                        stack.push(new Constant(Integer.parseInt(t)));
                    } else if (UnaryOperation.OPERATORS.contains(t)) {
                        if (stack.isEmpty()) {
                            Errors.report(Errors.Type.PREMATURE_END);
                        }
                        ExpressionNode operand = stack.pop();
                        stack.push(new UnaryOperation(t, operand));
                    } else if (BinaryOperation.OPERATORS.contains(t)) {
                        if (stack.size() < 2) {
                            Errors.report(Errors.Type.PREMATURE_END);
                        }
                        ExpressionNode left = stack.pop();
                        ExpressionNode right = stack.pop();
                        stack.push(new BinaryOperation(t, left, right));
                    } else {
                        Errors.report(Errors.Type.ILLEGAL_OPERATOR, t);
                    }
                }
                if (stack.size() != 1) {
                    Errors.report(Errors.Type.PREMATURE_END);
                }
                ExpressionNode expression = stack.pop();
                this.program.add(new Print(expression));

            } else {
                Errors.report(Errors.Type.ILLEGAL_ACTION, token);
            }
        }
    }

    /**
     * Displays the entire NMU program of ActionNode's to standard
     * output using emit().
     */
    public void displayProgram() {
        System.out.println("(NMU) infix...");
        for (ActionNode action : this.program) {
            action.emit();
            System.out.println();
        }
    }

    /**
     * Execute the NMU program of ActionNode's to standard output using execute().
     * In order to execute the ActionNodes, a local SymbolTable must be created here
     * for use.
     */
    public void interpretProgram() {
        System.out.println("(NMU) interpreting program...");
        SymbolTable symTbl = new SymbolTable();

        for (ActionNode action : this.program) {
            action.execute(symTbl);
        }
        System.out.println("(NMU) Symbol table:");
        System.out.print(symTbl.toString());
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

        for (ActionNode action : this.program) {
            action.compile(out);
        }
        out.close();
    }

    /**
     * Takes the generated ALT instruction file and assembles/executes
     * it using the Alaton machine.
     *
     * @throws FileNotFoundException if the ALT file cannot be found.
     */
    public void executeProgram() throws FileNotFoundException {
        Alaton machine = new Alaton();
        machine.assemble(new Scanner(new File(TMP_NMU_FILE)), false);
        machine.execute();
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
