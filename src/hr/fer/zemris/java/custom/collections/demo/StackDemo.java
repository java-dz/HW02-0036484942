package hr.fer.zemris.java.custom.collections.demo;

import hr.fer.zemris.java.custom.collections.EmptyStackException;
import hr.fer.zemris.java.custom.collections.ObjectStack;

/**
 * This program demonstrates the usage of {@linkplain ObjectStack}. It accepts
 * only one argument via command line enclosed in quotation marks that contains
 * numbers and operations. The number-operation ratio must be
 * <code>numbers-1 = operations</code> in order for this program to end
 * normally.
 *
 * @author Mario Bobic
 */
public class StackDemo {

    /** An instance of a stack to be tested. */
    private static ObjectStack stack;

    /**
     * Program entry point.
     *
     * @param args arguments from the command line
     */
    public static void main(String[] args) {
        if (args.length != 1 || args[0].isEmpty()) {
            System.err.println("Expected only 1 argument enclosed in quotation marks.");
            System.exit(1);
        }

        String[] all = args[0].trim().split(" +");

        stack = new ObjectStack();
        for (String s : all) {
            processElement(s);
        }

        if (stack.size() != 1) {
            System.err.println("Invalid arguments.");
            System.exit(4);
        } else {
            System.out.println("Result: " + stack.pop());
        }
    }

    /**
     * Processes an element by testing if it is a number or an operation. In
     * case this element is a number, it is only pushed onto the stack. Else, in
     * case this is an operation, two numbers are popped from the stack and the
     * operation is performed upon them. If the operation was successful, the
     * result is pushed back onto the stack and more operations are performed,
     * if any. The operation may not be successful if there was nothing on the
     * stack while calling the <code>pop</code> method, a division by zero
     * occurred, or an unsupported operation was given. In these cases, the
     * program prints out an error message, stops executing and a status code is
     * set.
     * <p>Status code <code>2</code> means the stack was empty while calling pop().
     * <p>Status code <code>3</code> means that division by zero occurred.
     * <p>Status code <code>4</code> means that an unsupported operation was given.
     *
     * @param s the element to be processed
     */
    private static void processElement(String s) {
        try {
            Integer num = Integer.parseInt(s);
            stack.push(num);
        } catch (NumberFormatException e1) {

            Integer num1 = null, num2 = null;
            try {
                num2 = (Integer) stack.pop();
                num1 = (Integer) stack.pop();
            } catch (EmptyStackException e2) {
                System.out.println(e2.getMessage());
                System.exit(2);
            }

            try {
                Integer result = operate(num1, num2, s);
                stack.push(result);
            } catch (ArithmeticException e3) {
                System.err.println("Cannot divide by zero: " + num1 + "/" + num2);
                System.exit(3);
            } catch (UnsupportedOperationException e4) {
                System.err.println("Unsupported operation: " + s);
                System.exit(4);
            }

        }
    }

    /**
     * Performs the given mathematical operation on two numbers, <code>a</code>
     * and <code>b</code>. The currently supported operations on two integers
     * are:
     * <ul>
     * <li>addition (+),
     * <li>subtraction (-),
     * <li>division (/),
     * <li>multiplication (*) and
     * <li>modulo (%)
     * </ul>
     * <p>Note that if this method tries to perform a division by zero, an
     * {@linkplain ArithmeticException} is thrown.
     * <p>Also note that if this method receives an unsupported operation, it
     * will naturally throw an {@linkplain UnsupportedOperationException}.
     *
     * @param a an argument
     * @param b another argument
     * @param op operation to be performed
     * @return a result of the given operation on two given integers a and b
     * @throws ArithmeticException if division by zero occurs
     * @throws UnsupportedOperationException if the operation is unsupported
     */
    private static int operate(int a, int b, String op) {
        switch (op) {
        case "+": return a+b;
        case "-": return a-b;
        case "/": return a/b;
        case "*": return a*b;
        case "%": return a%b;
        default: throw new UnsupportedOperationException(op);
        }
    }

}
