/*
 * Joshua White
 * CS 1450 (T/R)
 * Due 10/02/23
 * Assignment 5
 * 
 * Create both regular and generic Stacks and fill them with int/String contents read from files. 
 * Then rearrange them based on their value compared to pre-defined final variables. Then sort them
 * from smallest to largest value, printing most of the steps along the way.
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class WhiteJoshuaAssignment5 {
	
	
	public static void main(String[] args) throws IOException {
		
		//Constants
		final int REARRANGE_NUMBER = 15;
		final String REARRANGE_STRING = "Durango";

		
		//Task 1 -  Create a stack, fill with values, add 0 after each even value, print the stack
		int [] values = {10, 1, 42, 15, 62, 8, 17, 2};
		
		Stack<Integer> valuesStack = new Stack<>();
		
		//Put all numbers in values into valuesStack
		for (int i = 0; i < values.length; i++) {
			valuesStack.push(values[i]);
		}
		
		//Add zeros after each even value in the stack
		addZeroAfterEvenValues(valuesStack);
		
		//print the values stack now that it has zeros in it.
		printStack(valuesStack);
		
		
		
		//Task 2 - Create 2 generic stacks, fill with values, rearrange, sort, then print stacks
		
		//Create all the stacks, file objects, and scanners needed
		GenericStack<Integer> genericStack1 = new GenericStack<>();
		GenericStack<Integer> genericStack2 = new GenericStack<>();
		File numbersOneFile = new File("numbers1.txt");
		File numbersTwoFile = new File("numbers2.txt");
		Scanner scanNumbersOne = new Scanner (numbersOneFile);
		Scanner scanNumbersTwo = new Scanner (numbersTwoFile);
		
		
		//Scan the files and put them into generic stacks
		while(scanNumbersOne.hasNext()) {
			genericStack1.push(scanNumbersOne.nextInt());
		}
		
		while(scanNumbersTwo.hasNext()) {
			genericStack2.push(scanNumbersTwo.nextInt());
		}
		
		//Print the generic stacks
		System.out.println("Values read from file and put into genericStack1");
		System.out.println("================================================");
		GenericStack.printStack(genericStack1);
		
		System.out.println("Values read from file and put into genericStack2");
		System.out.println("================================================");
		GenericStack.printStack(genericStack2);
		
		//Rearrange the genericStacks
		GenericStack.rearrangeStacks(genericStack1, genericStack2, REARRANGE_NUMBER);
		
		//Sort the stacks from smallest to biggest & print them
		GenericStack<Integer> sortedStack1 = GenericStack.sortStack(genericStack1);
		System.out.println("Sorted Generic Stack 1:");
		System.out.println("================================================");
		GenericStack.printStack(sortedStack1);
		
		GenericStack<Integer> sortedStack2 = GenericStack.sortStack(genericStack2);
		System.out.println("Sorted Generic Stack 2:");
		System.out.println("================================================");
		GenericStack.printStack(sortedStack2);
		
		
		
		
		
		
		//Repeat for String files
	
		//Create stacks, files, and scanners for String
		GenericStack<String> genericStack3 = new GenericStack<>();
		GenericStack<String> genericStack4 = new GenericStack<>();
		File citiesOneFile = new File("cities1.txt");
		File citiesTwoFile = new File("cities2.txt");
		Scanner scanCitiesOne = new Scanner (citiesOneFile);
		Scanner scanCitiesTwo = new Scanner (citiesTwoFile);
		
		//Scan files and fill String Stacks
		while(scanCitiesOne.hasNext()) {
			genericStack3.push(scanCitiesOne.nextLine());
		}
				
		while(scanCitiesTwo.hasNext()) {
			genericStack4.push(scanCitiesTwo.nextLine());
		}
		
		//Print the generic String Stacks
		System.out.println("Values read from file and put into genericStack3");
		System.out.println("================================================");
		GenericStack.printStack(genericStack3);
				
		System.out.println("Values read from file and put into genericStack4");
		System.out.println("================================================");
		GenericStack.printStack(genericStack4);
		
		//Rearrange the generic String Stacks
		GenericStack.rearrangeStacks(genericStack3, genericStack4, REARRANGE_STRING);
				
		//Sort the stacks from smallest to biggest & print them
		GenericStack<String> sortedStack3 = GenericStack.sortStack(genericStack3);
		System.out.println("Sorted Generic Stack 3:");
		System.out.println("================================================");
		GenericStack.printStack(sortedStack3);
		
		GenericStack<String> sortedStack4 = GenericStack.sortStack(genericStack4);
		System.out.println("Sorted Generic Stack 4:");
		System.out.println("================================================");
		GenericStack.printStack(sortedStack4);

		
		
		
		//Close all the scanners once they are no longer needed
		scanNumbersOne.close();
		scanNumbersTwo.close();
		scanCitiesOne.close();
		scanCitiesTwo.close();
		
	}//main
	
	public static void addZeroAfterEvenValues (Stack<Integer> stack) {
		
		//Fill stackWithZeros with the stack but with 0's after each even
		Stack<Integer> stackWithZeros = new Stack<>();
		while (!stack.isEmpty()) {
			int topOfStack = stack.peek();
			
			if (topOfStack % 2 == 0) {
				stackWithZeros.push(topOfStack);
				stackWithZeros.push(0);
				stack.pop();
			}
			else {
				stackWithZeros.push(topOfStack);
				stack.pop();
			}
		}
		
		//Fill the now empty stack with the values in stackWithZeros
		while (!stackWithZeros.isEmpty()) {
			int topOfZeroStack = stackWithZeros.peek();
			stack.push(topOfZeroStack);
			stackWithZeros.pop();
		}
			
	}//addZeroAfterEvenValues

	public static void printStack (Stack<Integer> stack) {
		
		//Print the stack that was sent in
		Stack<Integer> preserveStack = new Stack<>();
		System.out.println("================================================");
		System.out.println("Stack with 0's after even values");
		System.out.println("================================================");
		while (!stack.isEmpty()) {
			int numberToPrint = stack.peek();
			System.out.println(numberToPrint);
			preserveStack.push(numberToPrint);
			stack.pop();
		}
		System.out.println("================================================");
		
		
		//Remove the zeros from the stack
		while (!preserveStack.isEmpty()) {
			int topOfPreserveStack = preserveStack.peek();
			stack.push(topOfPreserveStack);
			preserveStack.pop();
		}

	}//printStack
	
}//WhiteJoshuaAssignment5





class GenericStack<E> {
	
	
	//Private Data Fields
	private ArrayList<E> list = new ArrayList<E>();
	
	
	//Constructor
	public GenericStack() {
		
	}
	
	
	//isEmpty - Indicates if the stack (ArrayList) is currently empty.
	public boolean isEmpty() {
		return list.isEmpty();
	}
	
	
	//getSize - Returns the number of objects currently on the stack.
	public int getSize () {
		return list.size();
	}
	
	
	//peek - Returns the object on the top of the stack, does NOT remove that object.
	public E peek() {
		return list.get(getSize() - 1);
	}
	
	
	//pop - Removes the object on the top of the stack (i.e., end of the ArrayList)
	public E pop() {
		return list.remove(getSize() - 1);
	}
	
	//push - Adds an object to the top of the stack (i.e., end of the ArrayList)
	public void push(E value) {
		list.add(value);
	}
	
	
	//printStack - Print the generic stacks
	public static <E> void printStack (GenericStack<E> stack) {
		
		//Create a Stack to make sure we don't lose the values when popping
		GenericStack<E> preserveStack = new GenericStack<>();
		
		//Print the stack
		while (!stack.isEmpty()) {
			E valueToPrint = stack.peek();
			System.out.println(valueToPrint);
			preserveStack.push(valueToPrint);
			stack.pop();
		}
		System.out.println("================================================");
		
		//Return stack to normal
		while (!preserveStack.isEmpty()) {
			E topOfPreserveStack = preserveStack.peek();
			stack.push(topOfPreserveStack);
			preserveStack.pop();
		}
	}//printStack
	
	
	//Organizes stack1 and stack2 so that stack1 only contains values less than rearrangeValue and stack2 only contains values
	//greater than or equal to rearrangeValue
	public static <E extends Comparable<E>> void rearrangeStacks(GenericStack<E> stack1, GenericStack<E> stack2, E rearrangeValue) {
		
		//Create stack to hold the combined values of both stacks
		GenericStack<E> combinedStacks = new GenericStack<>();
		
		//While there's still stuff inside both stacks, combine them into one stack
		while (!stack1.isEmpty()) {
			E valueInStack1 = stack1.peek();
			
			combinedStacks.push(valueInStack1);
			stack1.pop();
		}
		while (!stack2.isEmpty()) {
			E valueInStack2 = stack2.peek();
			
			combinedStacks.push(valueInStack2);
			stack2.pop();
		}
		
		//Put the stuff in the combinedStack back into stacks 1 & 2.
		//Put value in stack1 if value is smaller than rearrangeValue, in 2 otherwise
		while (!combinedStacks.isEmpty()) {
			E valueInCombinedStack = combinedStacks.peek();
			
			if (valueInCombinedStack.compareTo(rearrangeValue) < 0) {
				stack1.push(valueInCombinedStack);
				combinedStacks.pop();
			}
			else if (valueInCombinedStack.compareTo(rearrangeValue) >= 0) {
				stack2.push(valueInCombinedStack);
				combinedStacks.pop();
			}
			
		}
	}//rearrangeStacks
	
	
	public static <E extends Comparable<E>> GenericStack<E> sortStack (GenericStack<E> unsortedStack) {
		
		//Create stack to hold the sorted values
		GenericStack<E> sortedStack = new GenericStack<>();
		
		//Keep sorting until the original stack is empty.
		while (!unsortedStack.isEmpty()) {
			
			//Take the top of the stack and put it aside
			E tempVal = unsortedStack.peek();
			unsortedStack.pop();
		
			//If there's nothing in the sorted stack, the put-aside value will be placed automatically
			//Otherwise, it will check the top of the sorted stack to see if the placed-aside value
			//is bigger than whats at the top. If it it, it will put everything smaller than the
			//placed-aside value back into the sorted stack.
			while (!sortedStack.isEmpty() && sortedStack.peek().compareTo(tempVal) <= -1) {
				unsortedStack.push(sortedStack.peek());
				sortedStack.pop();
			}
			
			//After all the smaller numbers are put back, it will put the put-aside value into it's
			//proper spot in the stack. It will also do this if it didn't need to put back anything.
			sortedStack.push(tempVal);
		}
		
		
		return sortedStack;
	}//sortStack
	
}//GenericStack

/*
 *Help Gotten:
 *Math Center - James: reminded me to fill out the overriden methods
 *Peer - Unknown name: Helped me know what to put in the overriden methods
 *Math Center - Andrew: Helped me figure out how to sort the stacks
 *
 *
 *
 */