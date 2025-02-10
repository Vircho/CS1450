import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/*
 * Joshua White
 * CS 1450 (T/R)
 * Due 11/14/23
 * Assignment 9
 * Build a singly-LinkedList, manipulate it, then change it into a doubly-LinkedList, printing along the way
 */
public class WhiteJoshuaAssignment9 {
	public static void main(String[] args) throws IOException {
		
		
		//Create a LinkedList
		WordLinkedList wordsLinkedList = new WordLinkedList();
		
		//Fill LinkedList with words in file Words.txt
		File wordsFile = new File("Words.txt");
		Scanner scanWords = new Scanner(wordsFile);
		
		while (scanWords.hasNext()) {
			
			String wordBeingRead;
			wordBeingRead = scanWords.nextLine();
			
			Word nextWord = new Word(wordBeingRead);
			wordsLinkedList.addInFront(nextWord);
		}
		
		
		//Print the LinkedList
		System.out.println("Word \t Abecedarian? ");
		System.out.println("=====================");
		wordsLinkedList.printList();
		
		
		//Remove all non-abecedarian words
		int removedWords;
		
		removedWords = wordsLinkedList.removeNonAbecedarianWords();
		System.out.println("=====================");
		System.out.println("Words Removed: " + removedWords);
		System.out.println("=====================");
		wordsLinkedList.printList();
		
		
		//Sort the LinkedList
		wordsLinkedList.bubbleSort();
		System.out.println("=====================");
		System.out.println("LinkedList After Sort");
		System.out.println("=====================");
		wordsLinkedList.printList();
		
		
		//Build a double LinkedList
		DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
		doubleLinkedList.build(wordsLinkedList);
		System.out.println("=====================");
		System.out.println("Double Linked List:  ");
		System.out.println("=====================");
		doubleLinkedList.printListBackwards();
		System.out.println("=====================");
		System.out.println("Word Linked List Size");
		System.out.println("=====================");
		System.out.println(wordsLinkedList.getSize());
	    
		
		
		
		scanWords.close();
	}//main

	
}//WhiteJoshuaAssignment9

//Represents one word
class Word {
	
	
	//Private Data Fields
	private String letters;
	private boolean abecedarian;
	
	
	//Constructor
	public Word (String letters) {
		this.letters = letters;
		abecedarian = abecedarianTest();
	}
	
	
	//Getters
	public boolean isAbecedarian() {
		return abecedarian;
	}
	
	
	//abecedarianTest - Tests if the word is abecedarian
	private boolean abecedarianTest() {
		
		//Set up variables to use
		boolean testSucceed = true;
		String wordBeingTested = this.letters;
		
		//For as long as there are letters in the word
		for (int i = 1; i < wordBeingTested.length(); i++) {
			
			//If the last character is lower in the alphabet than this character, the test is still succeeding
			//Else if the last character is higher, it's no longer abecedarian and the test failed.
			if (wordBeingTested.charAt(i - 1) <= wordBeingTested.charAt(i) && testSucceed == true) {
				testSucceed = true; 
			}
			else if (wordBeingTested.charAt(i - 1) > wordBeingTested.charAt(i)) {
				testSucceed = false;
			}
		}
		return testSucceed;
		
	}
	
	
	//print - Print a Word object
	public String print() {
		return String.format("%s\t\t%b", letters, abecedarian);
	}
	
	
	//compareTo - Compares two words on their alphabetical order
	public int compareTo(Word otherWord) {
		
		if (this.letters.compareTo(otherWord.letters) > 0) {
			return 1;
		}
		else if (this.letters.compareTo(otherWord.letters) < 0) {
			return -1;
		}
		else {
			return 0;
		}
		
	}
}//Word


//A singly linked list constructed from nodes containing words
class WordLinkedList {
	
	
	//Private Data Fields
	private Node head;
	private int size;
	
	
	//Constructor
	public WordLinkedList() {
		head = null;
		size = 0;
	}
	
	//Getters
	public int getSize() {
		return size;
	}
	
	
	//addInFront - Adds a Word object to the head of the LinkedList
	public void addInFront(Word wordToAdd) {
		Node frontNode = new Node(wordToAdd, head);
		head = frontNode;
		size++;
	}
	
	
	//removeNonAbecedarianWords - Removes any Words that failed the abecedarianTest from the LinkedList 
	public int removeNonAbecedarianWords() {
		
		//Set up the variables
		int numberOfRemovedWords = 0;
		Node temp = head;
		Node previous = null;
		int activeSize = getSize();
		
		//For as long as the list is
		for (int i = 0; i < activeSize; i++) {
			
			//If the node's word fails the abecedarian test
			if (temp.word.isAbecedarian() == false) {
				
				//If the previousNode is null, then the non-abecedarian word is at the
				//head of the list, and the head needs to be replaced
				if (previous == null) {
					head = head.next;
					size--;
				}
				
				//But if it's actually in the list,
				//then we can replace this one normally
				else {
					previous.next = temp.next;
					size--;
				}
				
				//Once removed, increase number of removedWords
				numberOfRemovedWords++;
			}
			
			//If the test is successful, then we just need to
			//prepare for the next run through
			else {
				previous = temp;
			}
			temp = temp.next;
		}
		
		return numberOfRemovedWords;
	}
	
	
	//removeFirstNode - Remove the head of the LinkedList and return the word in it
	public Word removeFirstNode() {
		
		Word wordInFirstNode = head.word;
		head = head.next;
		size--;
		return wordInFirstNode;
		
	}
	
	
	//bubbleSort - performs a bubble sort on the WordLinkedList
	public void bubbleSort() {
		
		int activeSize = getSize();
		
		for (int i = 0; i < activeSize; i++) {
			Node current = head;
			
			while (current != null && current.next != null) {
				
				if (current.word.compareTo(current.next.word) > 0) {
					swapNodeData(current, current.next);
				}
				current = current.next;
			}
			
		}

	}
	
	
	//swapNodeData - Swaps the stuff in two Nodes with eachother
	public void swapNodeData(Node node1, Node node2) {
		Word newWord = node2.word;
		node2.word = node1.word;
		node1.word = newWord;
	}
	
	
	//printList - Prints the LinkedList
	public void printList() {
		
		Node temp = head;
		while (temp != null) {
			System.out.println(temp.word.print());
			temp = temp.next;
		}
		
	}
	
	
	class Node {
		
		
		//Private Data Fields
		private Word word;
		private Node next;
		
		
		//Constructor
		public Node(Word word, Node next) {
			this.word = word;
			this.next = next;
		}
		
		
	}//Node
	
}//WordLinkedList

class DoubleLinkedList {
	
	
	//Private Data Fields
	private DoubleNode head;
	private DoubleNode tail;
	
	
	//Constructor
	public DoubleLinkedList() {
		head = null;
		tail = null;
	}
	
	
	//build - build a double LinkedList from the single LinkedList
	public void build(WordLinkedList wordList) {
		
		while (wordList.getSize() > 0)	 {
			
			//temp equals the next word in the wordList
			DoubleNode temp = new DoubleNode(wordList.removeFirstNode());
			
			//if double LinkedList is empty, set the head & tail
			if (head == null) {
				head = temp;
				tail = temp;
			}
			else {
				
				//set the new tail
				DoubleNode newTail = tail;
				
				//find where the tail should be
				while (newTail != null) {
					newTail = newTail.next;
				}
				
				//set the tail & prepare for next run through
				if (newTail == null) {
					temp.previous = tail;
		            tail.next = temp;
		            tail = temp;
				}
			}
			
			temp = temp.next;
		}
		
		
		
	}
	
	
	//printListBackwards - Print from the tail to the head
	public void printListBackwards() {
		
		DoubleNode temp = tail;
		
		while (temp != null) {
			System.out.println(temp.word.print());
			temp = temp.previous;
		}
		
	}
	
	
	 class DoubleNode {
		
		
		//Private Data Fields
		private Word word;
		private DoubleNode next;
		private DoubleNode previous;
		
		
		//Constructor
		public DoubleNode(Word word) {
			this.word = word;
			next = null; 
			previous = null;
		}
		
	}//Node
	
}//DoubleLinkedList
/*
 *Help Gotten:
 *Andrew - Math Center: Helped figure out proper construction of LinkedList class
 *Andrew - Math Center: Helped build the removeNonAbecedarian method
 *Mason - Peer: Helped fix my compareTo method
 */