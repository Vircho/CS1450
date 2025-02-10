import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * Joshua White
 * CS 1450 (T/R)
 * Due 12/05/23
 * Assignment 10
 * Use a binary tree to organize parrots on ID and then traverse the tree using level-order and inorder to print different
 * parts of the parrots, like their songPhrase and their names
 */
public class WhiteJoshuaAssignment10 {

	public static void main(String[] args) throws IOException {
		
		//File variables
		File parrotsFile = new File("parrots.txt");
		Scanner scanParrots = new Scanner(parrotsFile);
		
		//Create Tree
		BinaryTree binaryTree = new BinaryTree();
		
		//Place parrots into tree
		while (scanParrots.hasNext()) {
			
			int readID = scanParrots.nextInt();
			String readName = scanParrots.next().trim();
			String readPhrase = scanParrots.nextLine().trim();
			Parrot thisParrot = new Parrot(readID, readName, readPhrase);
			
			binaryTree.insert(thisParrot);
		}
		
		//Traverse the tree in level order
		System.out.println("Thank you for you help! Here is a little song for you!");
		System.out.println("======================================================");
		binaryTree.levelOrder();
		System.out.println("======================================================");
		
		//Visit the leaf nodes to print the parrot's names
		System.out.println("Oh yeah, we never gave you our names did we?          ");
		System.out.println("======================================================");
		binaryTree.visitLeaves();
		System.out.println("======================================================");
		
		
		scanParrots.close();
	}//main

}//WhiteJoshuaAssignment10

class Parrot implements Comparable<Parrot> {
	
	//Private data fields
	private int id;
	private String name;
	private String songPhrase;
	
	//Constructor
	public Parrot(int id, String name, String songPhrase) {
		this.id = id;
		this.name = name;
		this.songPhrase = songPhrase;
	}
	
	//Getters
	public String getName() {
		return name;
	}
	
	public String getSongPhrase() {
		return songPhrase;
	}
	
	//compareTo - compares two parrots on ID
	@Override
	public int compareTo(Parrot otherParrot) {
		
		if (this.id > otherParrot.id) {
			return 1;
		}
		else if (this.id < otherParrot.id) {
			return -1;
		}
		else {
			return 0;
		}
	}
	
}//Parrot

class BinaryTree {
	
	//Private data fields
	private TreeNode root;
	
	//Constructor
	public BinaryTree() {
		root = null;
	}
	
	//insert - Adds parrot to tree, returns whether parrot was added
	public boolean insert(Parrot parrotToAdd) {
		boolean added = false;
		
		//Add in the first parrot
		if (root == null) {
			root = new TreeNode(parrotToAdd);
			return true;
		}
		
		//Initialize searcherLeaf & lastLeaf
		TreeNode lastLeaf = null;
		TreeNode searcherLeaf = root;
		
		//Start looking for the parrots place
		while (searcherLeaf != null) {
			
			if (parrotToAdd.compareTo(searcherLeaf.parrot) > 0) {
				lastLeaf = searcherLeaf;
				searcherLeaf = searcherLeaf.right;
			}
			
			else if (parrotToAdd.compareTo(searcherLeaf.parrot) < 0) {
				lastLeaf = searcherLeaf;
				searcherLeaf = searcherLeaf.left;
			}
			
		}
		
		//Add the parrot once it's spot is found
		if (parrotToAdd.compareTo(lastLeaf.parrot) > 0) {
			lastLeaf.right = new TreeNode(parrotToAdd);
			added = true;
		}
		else if (parrotToAdd.compareTo(lastLeaf.parrot) < 0) {
			lastLeaf.left = new TreeNode(parrotToAdd);
			added = true;
		}
		
		return added;
	}
	
	//levelOrder - Prints the parrots' phrases in level order, or left to right on each level of the tree
	public void levelOrder() {
		
		//Run if there is a tree to look through
		if (root != null) {
			
			//Initialize the queue to store the leaves
			Queue<TreeNode> leafQueue = new LinkedList<>();
			leafQueue.offer(root);
			
			//Run until there are no more leaves in the queue
			while(!leafQueue.isEmpty()) {
				
				//Set the searcherLeaf to the first leaf in the queue and print it
				TreeNode searcherLeaf = leafQueue.remove();
				System.out.println(searcherLeaf.parrot.getSongPhrase());
				
				//Add the next leaves to the queue in the proper order
				if (searcherLeaf.left != null) {
					leafQueue.offer(searcherLeaf.left);
				}
				if (searcherLeaf.right != null) {
					leafQueue.offer(searcherLeaf.right);
				}
			}	
		}
	}
	
	//visitLeaves(public) - Calls the private method of the same name
	public void visitLeaves() {
		visitLeaves(root);
	}
	
	//visitLeaves(private) - Go down the tree and print the names of the birds using inorder traversal
	private void visitLeaves(TreeNode aNode) {
		
		if (aNode != null) {
			visitLeaves(aNode.left);
			System.out.println(aNode.parrot.getName());
			visitLeaves(aNode.right);
		}
	}
	
	
	class TreeNode {
		
		//Private Data Fields
		private Parrot parrot;
		private TreeNode left;
		private TreeNode right;
		
		//Constructor
		public TreeNode(Parrot parrot) {
			this.parrot = parrot;
			left = null;
			right = null;
		}
	}//TreeNode
	
}//BinaryTree
/*
 *Help gotten:
 *Andrew - Math Center: Helped fix insert method
 *Mason - Peer: Helped fix insert method
 */