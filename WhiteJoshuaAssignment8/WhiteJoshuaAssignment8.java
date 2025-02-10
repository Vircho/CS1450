/*
 * Joshua White
 * CS 1450 (T/R)
 * Due 11/02/23
 * Assignment 8
 * Read a key and a code from files to decode the parrot's message using ArrayLists, Queues, and Iterators
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class WhiteJoshuaAssignment8 {
	public static void main (String[] args) throws IOException {
		
		//STEP 1//
		//Open files for reading
		File codeGridFile = new File("CodeGrid.txt");
		File messageFile = new File("Message.txt");
		
		Scanner scanCodeGrid = new Scanner(codeGridFile);
		Scanner scanMessage = new Scanner(messageFile);
		
		
		//Create CodeMachine
		int codeRows = scanCodeGrid.nextInt();
		int codeColumns = scanCodeGrid.nextInt();
		
		CodeMachine codeMachine = new CodeMachine(codeRows, codeColumns);
		
		
		//Create and fill ArrayList with key
		ArrayList<Character> keyList = new ArrayList<>();
		scanCodeGrid.nextLine();
		
		while(scanCodeGrid.hasNext()) {
			String fileLine = scanCodeGrid.nextLine().trim();
			keyList.add(fileLine.charAt(0));
		}
		
		
		//Create Iterator for ArrayList
		Iterator<Character> keyListIterator = keyList.iterator();
		
		
		//Call loadCodeGrid
		codeMachine.loadCodeGrid(keyListIterator);
		
		
		//Call printCodeGrid
		codeMachine.printCodeGrid();
		
		
		
		
		
		//STEP 2//
		//Create Queue that will store CodeElement objects
		Queue<CodeElement> codeElementQueue = new LinkedList<>();
		
		
		//Fill Queue with encoded message in Message.txt
		while (scanMessage.hasNext()) {
			
			int elementRow = scanMessage.nextInt();
			int elementColumn = scanMessage.nextInt();
			CodeElement currentElement = new CodeElement(elementRow, elementColumn);
			codeElementQueue.offer(currentElement);
			scanMessage.nextLine();
			
		}
		
		
		//Create Iterator for Queue
		Iterator<CodeElement> codeElementQueueIterator = codeElementQueue.iterator();
		
		
		
		
		
		//STEP 3//
		//Call decode
		Iterator<Character> decodedMessageIterator = codeMachine.decode(codeElementQueueIterator);
		
		
		//Print decoded message
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("Final Message                                     ");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		while (decodedMessageIterator.hasNext()) {
			System.out.print(decodedMessageIterator.next());
		}
		
		
		//Close Scanners
		scanCodeGrid.close();
		scanMessage.close();
		
	}//main

}//WhiteJoshuaAssignment8

class CodeElement {
	
	//Instance Variables
	private int row;
	private int column;
	
	//Constructor
	public CodeElement (int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	//Getters
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
}//CodeElement

class CodeMachine {
	
	//Instance Variables
	private char[][] codeGrid;
	private int numRows;
	private int numColumns;
	
	//Constructor
	public CodeMachine(int numRows, int numColumns) {
		this.numRows = numRows;
		this.numColumns = numColumns;
		codeGrid = new char[numRows][numColumns];
	}
	
	//loadCodeGrid - Load codeGrid with the characters in characterIterator
	public void loadCodeGrid(Iterator<Character> characterIterator) {
		
		while (characterIterator.hasNext()) {
			
			for (int i = 0; i < numRows; i++) {
				
				for (int j = 0; j < numColumns; j++) {
				
					codeGrid[i][j] = characterIterator.next();	
					
				}
			}
	
		}
		
	}
	
	//decode - Decodes message in iterator by comparing the rows/columns of CodeElement with the characters in codeGrid
	public Iterator<Character> decode(Iterator<CodeElement> messageIterator) {
		
		//Create ArrayList of Character's
		ArrayList<Character> decodedMessage = new ArrayList<>();
		
		//Go through the Iterator, find the char at the codeElement's row & column location, and add it to ArrayList
		while (messageIterator.hasNext()) {
			
			CodeElement thisElement = messageIterator.next();
			char messagePiece = codeGrid[thisElement.getRow()][thisElement.getColumn()];
			decodedMessage.add(messagePiece);
			
		}
		
		//Make Iterator for decodedMessage & return it
		Iterator<Character> decodedMessageIterator = decodedMessage.iterator();
		return decodedMessageIterator;
		
	}
	
	//printCodeGrid - Print the codeGrid array
	public void printCodeGrid() {
	
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("Code Grid                                         ");
		System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		for (int i = 0; i < numRows; i++) {
			
			for (int j = 0; j < numColumns; j++) {
				System.out.print(codeGrid[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("");
		
	}
}
/*
 * Help Gotten:
 * Mason - Peer: Helped with reading files
 * Andrew - Math Center: Helped with problem filling the keyList
 */