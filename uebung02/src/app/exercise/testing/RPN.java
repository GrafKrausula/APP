package app.exercise.testing;

import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Function;

import app.exercise.algebra.Fractional;
import app.exercise.algebra.Rational;

/**
 * Reverse-Polnish-Notation-Class (RPN) to calculate by RPN.
 * Reads inputs from the command line. Supported Operations:
 * <uL>
 * <li>+</li>
 * <li>-</li>
 * <li>*</li>
 * <li>/</li>
 * </ul>
 * @author Martin Krause
 *
 */
public class RPN {

	/*
	 * supported Oparations, that can be applied to numbers
	 */
	public static final String ADD = "+";
	public static final String SUBTRACT = "-";
	public static final String MULTIPLY = "*";
	public static final String DIVIDE = "/";

	public static void main(String[] args) {
		// stack, which holds the numbers
		Stack<Fractional> numbers = new Stack<Fractional>();

		// catch any IllegalArgumentException and message the user, that something wrong was being entered.
		try {
			// for-each loop for convenience
			for(String argument : args) {
				switch(argument) {
				case ADD:
					executeOperation(numbers, (Fractional q, Fractional r) -> {q.add(r); return q;});
					break;
				case SUBTRACT:
					executeOperation(numbers, (Fractional q, Fractional r) -> {r.sub(q); return r;});
					break;
				case MULTIPLY:
					executeOperation(numbers, (Fractional q, Fractional r) -> {q.mul(r); return q;});
					break;
				case DIVIDE:
					executeOperation(numbers, (Fractional q, Fractional r) -> {r.div(q); return r;});
					break;
				default:
					// if the argument is not an operation, then try parsing it to a number.
					// if the argument is not a number, that means that the given argument was an unknown argument.
					// an IllegalArgumentException gets thrown by Long.parseLong(String) and the user gets messaged.
					long number = Long.parseLong(argument);
					if(number < 0) {
						throw new IllegalArgumentException("Only positive numbers are allowed!");
					}

					// otherwise the positive number gets pushed onto the stack
					numbers.push(new Rational(number, 1));
					break;
				}
			}

			if(numbers.size() != 1) {
				System.out.println("The calculation is not finished! The following values are still on the stack:");
				for(Fractional f : numbers) {
					System.out.println(f);
				}

			} else {
				System.out.println("Result: " + numbers.pop());
			}

		} catch (IllegalArgumentException e) {
			System.out.println("Invalid argument:" + " " +  e.getMessage());
		}
	}

	/**
	 * Pops the first two elements of the stack and executes the operation on these elements and pushes it back.
	 * There needs to be at least two elements on the stack.
	 * Note: This method will only work with {@link Fractional Fractionals}!
	 * @param stack Stack with {@link Fractional Fractionals}
	 * @param operation A {@link BiFunction} where the given function is executed on the first two elements on the stack.
	 * @throws IllegalArgumentException if there is no or only one element on the Stack.
	 */
	private static void executeOperation(Stack<Fractional> stack, BiFunction<Fractional, Fractional, Fractional> operation) throws IllegalArgumentException {
		if(stack.size() < 2) {
			throw new IllegalArgumentException("There are too few elements on the stack to execute an operation!");
		}

		stack.push(operation.apply(stack.pop(), stack.pop()));
	}
}
