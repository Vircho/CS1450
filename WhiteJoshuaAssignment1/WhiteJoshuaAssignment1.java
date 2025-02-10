/*
 * Joshua White
 * CS1450 (T/R)
 * Due 08/31/23
 * Assignment 1
 * Review loops, arrays and files
 */
import java.util.Arrays;
import java.util.Scanner;
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
public class WhiteJoshuaAssignment1 {
	public static void main(String[] args) throws IOException {
		final int NUM_ROWS = 3;
		final int NUM_COLS = 5;
		
		
		// Create array, sort it, then display the sorted array
		System.out.println("Array:");
		int[] array = {17, 10, 2, 16, 8, 15, 9, 17, 14, 18, 1, 19, 17, 2, 0};
		Arrays.sort(array);
		for (int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
		System.out.println("*************************");
		
		
		
		// Display all values that add to 10 and find the mode of the array
		System.out.println("All pairs that add to 10");
		for (int i = 0; i < array.length; i++) {
			for (int j = i; j < array.length; j++) {
				if (i != j && array[i] + array[j] ==10) {
					System.out.println(array[i] + "(at " + i + ") + " + array[j] + "(at " + j + ") = 10 ");
				}
			}
		}
	
		int amountOfCurrentNumbers = 0;
		int mostNumbersFound = 0;
		int placeOfMostNumbersFound = 0;
		for (int i = 1; i < array.length; i++) {
			if (array[i] == array[i - 1]) {
				amountOfCurrentNumbers++;
			}
			else if (amountOfCurrentNumbers > mostNumbersFound) {
				mostNumbersFound = amountOfCurrentNumbers;
				amountOfCurrentNumbers = 0;
				placeOfMostNumbersFound = i - 1;
			}
		}
		int mode = array[placeOfMostNumbersFound];
		System.out.println("The mode of the array is: " + mode);
		System.out.println("*************************");
	
		
		
		
		
		
		// Create file to store array, write array to it, then close file
		File fileName = new File("assignment1.txt");
		PrintWriter outputFile = new PrintWriter(fileName);
		System.out.println("File is in directory: " + fileName.getAbsolutePath());
		for (int i = 0; i < array.length; i++) {
			outputFile.println(array[i]);
		}
		outputFile.close();
		
		
		
		
		// Reopen the file and write the contents into a 2D array, then print the array
		System.out.println("The contents of the new 2D array is:");
		Scanner readFile = new Scanner (fileName);
		int twoDimensionalArray[][] = new int [NUM_ROWS][NUM_COLS];
		for (int i = twoDimensionalArray.length - 1; i >= 0; i--) {
			for (int j = twoDimensionalArray[i].length - 1; j >= 0; j--) {
				twoDimensionalArray[i][j] = readFile.nextInt();
			}
		}
		readFile.close();
		
		for (int i = 0; i < twoDimensionalArray.length; i++) {
			for (int j = 0; j < twoDimensionalArray[i].length; j++) {
				System.out.print(twoDimensionalArray[i][j] + " ");
				
				if (j == NUM_COLS - 1) {
					System.out.println(); //puts the array into a grid
				}
			}
		}
		
	}//main	
	
} //WhiteJoshuaAssignment1
/*
 * Help gotten:
 * Read over CS1150 assignments to recall how to code with files
*/


