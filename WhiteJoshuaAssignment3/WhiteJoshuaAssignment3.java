/*
 * Joshua White
 * CS1450 (T/R)
 * Due  09/15/23
 * Assignment 3
 * Read insects from file, print them, find certain insects, and print those
 */
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class WhiteJoshuaAssignment3 {

	public static void main(String[] args) throws IOException {
		
		//Create array
		Insect[] insectArray;
		
		//Open & read file for array size
		File insectFile = new File("Insects.txt");
		Scanner scanInsects = new Scanner(insectFile);
		insectArray = new Insect[scanInsects.nextInt()];
		
		//Read insect info
		for (int i = 0; i < insectArray.length; i++) {
			String type = scanInsects.next();
			int deScore = scanInsects.nextInt();
			int prScore = scanInsects.nextInt();
			int buScore = scanInsects.nextInt();
			int poScore = scanInsects.nextInt();
			String name = scanInsects.nextLine();
			
			//Make insect
			switch (type) {
			
			case "honeybee":
				insectArray[i] = new Honeybee(name, buScore, poScore);
				break;
			case "ladybug":
				insectArray[i] = new Ladybug(name, prScore, poScore);
				break;
			case "ant":
				insectArray[i] = new Ant(name, deScore, buScore, buScore);
				break;
			case "prayingmantis":
				insectArray[i] = new PrayingMantis(name, prScore);
				break;
				
			}//End of switch
		}//End of for
		scanInsects.close();
		
		//Display insects
		System.out.println("````````````");
		System.out.println("All Insects:");
		System.out.println("````````````");
		for (int i = 0; i < insectArray.length; i++) {
			displayInsect(insectArray[i]);
		}
		
		//Find which are both predators and are pollinators
		System.out.println("````````````````````````````````````````");
		System.out.println("Insects that are predators & pollinators");
		System.out.println("````````````````````````````````````````");
		ArrayList<Insect> predatorAndPollinatorInsects = findPredatorsPollinators(insectArray);
		for (int i = 0; i < predatorAndPollinatorInsects.size(); i++) {
			System.out.println("Name: " + predatorAndPollinatorInsects.get(i).getName());
			System.out.println("Type: " + predatorAndPollinatorInsects.get(i).getType());
			System.out.println("Pred: " + ((Predator)predatorAndPollinatorInsects.get(i)).predator());
			System.out.println("Pred: " + ((Pollinator)predatorAndPollinatorInsects.get(i)).pollinate());
			System.out.println("");
		}
		
		//Find which insect has highest total scores
		Insect bestInsect = findMostAble(insectArray);
		System.out.println("````````````````````");
		System.out.println("The Most Able Insect");
		System.out.println("````````````````````");
		displayInsect(bestInsect);
	
	}//main
	
	public static void displayInsect(Insect insect) { //Display the insects in insectArray
		
		//Start printing
		System.out.println(insect.getName() + " the " + insect.getType() + ":");
		System.out.println(insect.purpose());
		
		//Figure out what each insect is capable of
		if (insect instanceof Decomposer) {
			System.out.println("Decompose ability: " + ((Decomposer)insect).decompose());
		}
		if (insect instanceof Predator) {
			System.out.println("Predator ability: " + ((Predator)insect).predator());
		}
		if (insect instanceof Builder) {
			System.out.println("Builder ability: " + ((Builder)insect).build());
		}
		if (insect instanceof Pollinator) {
			System.out.println("Pollinator ability: " + ((Pollinator)insect).pollinate());
		}
		
		//Finish printing
		System.out.println("");
		
}//displayInsect
	
	
	
	
	
public static ArrayList<Insect> findPredatorsPollinators(Insect[] insects) { //Find which insects are preds and polls
		
	//Make ArrayList
	ArrayList<Insect> insectsPredPoll = new ArrayList<>();
	
	//Loop through insects to find the ones we want
	for (int i = 0; i < insects.length; i++) {
		if (insects[i] instanceof Predator && insects[i] instanceof Pollinator) {
			insectsPredPoll.add(insects[i]);
		}
	}
	
	return insectsPredPoll;
	
}//findPredatorsPollinators
	




public static Insect findMostAble(Insect[] insects) {
	
	//set up variables
	int addedScore = 0;
	int highestFoundScore = 0;
	int ownerOfHighest = 0;

	//loop through insects and add up all it's scores
	for (int i = 0; i < insects.length; i++) {
		
		if (insects[i] instanceof Decomposer) {
			addedScore = addedScore + ((Decomposer)insects[i]).decompose();
		}
		if (insects[i] instanceof Predator) {
			addedScore = addedScore + ((Predator)insects[i]).predator();
		}
		if (insects[i] instanceof Builder) {
			addedScore = addedScore + ((Builder)insects[i]).build();
		}
		if (insects[i] instanceof Pollinator) {
			addedScore = addedScore + ((Pollinator)insects[i]).pollinate();
		}
		
		//if this insect is better than the last best, this is new best
		if (addedScore > highestFoundScore) {
			highestFoundScore = addedScore;
			ownerOfHighest = i;
		}
		
		//reset it to count the scores of next insect
		addedScore = 0;
		
	}//End of for loop
	
	return insects[ownerOfHighest];
	
}//findMostAble





}//WhiteJoshuaAssignment3





interface Decomposer {
	
	int decompose();
	
}//Decomposer

interface Predator {
	
	int predator();
	
}//Predator

interface Builder {
	
	int build();
	
}//Builder

interface Pollinator {
	
	int pollinate();
	
}//Pollinator





abstract class Insect {
	
	//private data fields
	private String name;
	private String type;
	
	//Getters
	public String getName() {
		return name;
	}
	
	public String getType() {
		return type;
	}
	
	
	//Setters
	public void setName(String name) {
		this.name = name;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	
	//methods
	public abstract String purpose();
	
	
}//Insect





class Honeybee extends Insect implements Builder, Pollinator {
	
	//Instance Variables
	int build;
	int pollinate;
	
	//Constructor
	public Honeybee(String name, int build, int pollinate) {
		setName(name);
		setType("Honeybee");
		this.build = build;
		this.pollinate = pollinate;
	}
	
	//Methods
	
	@Override
	public int build() {
		return build;
	}
	
	@Override
	public int pollinate() {
		return pollinate;
	}
	
	@Override
	public String purpose() {
		return "I produce honey and pollinate 35% of the crops!\n"
				+ "Without me, 1/3 of the food you eat would not be available!";
	}
	
}//Honeybee





class Ladybug extends Insect implements Predator, Pollinator{
	
	//Instance Variables
	int predator;
	int pollinate;
	
	//Constructor
	public Ladybug (String name, int predator, int pollinate) {
		setName(name);
		setType("Ladybug");
		this.predator = predator;
		this.pollinate = pollinate;
	}
	
	//Methods
	@Override
	public int predator() {
		return predator;
	}
	
	@Override
	public int pollinate() {
		return pollinate;
	}
	
	@Override
	public String purpose() {
		return "Named after the Virgin Mary, I'm considered good luck if I land on you!\n"
				+ "I'm a pest control expert eating up to 5,000 plant pests during my lifespan.";
	}
	
}//Ladybug





class Ant extends Insect implements Decomposer, Predator, Builder {
	
	//Instance Variables
	int decompose;
	int predator;
	int build;
	
	//Constructor
	public Ant (String name, int decompose, int predator, int build) {
		setName(name);
		setType("Ant");
		this.decompose = decompose;
		this.predator = predator;
		this.build = build;
	}
	
	//Methods
	@Override
	public int decompose() {
		return decompose;
	}
	
	@Override
	public int predator() {
		return predator;
	}
	
	@Override
	public int build() {
		return build;
	}
	
	@Override
	public String purpose() {
		return "Don't squash me, I'm an ecosystem engineer!\n"
				+ "Me and my 20 million friends accelerate\n"
				+ "decomposition of dead wood, aerate soil,\n"
				+ "improve drainage, and eat insects like ticks and termites!";
	}
	
}//Ant





class PrayingMantis extends Insect implements Predator{
	
	//Instance Variables
	int predator;
	
	//Constructor
	public PrayingMantis (String name, int predator) {
		setName(name);
		setType("Praying Mantis");
		this.predator = predator;
	}
	
	//Methods
	@Override
	public int predator() {
		return predator;
	}
	
	@Override
	public String purpose() {
		return "I'm an extreme predator quick enough to catch a fly. Release me in a\n"
				+ "garden and I'll eat beetles, grasshoppers, crickets, and even pesky moths.";
	}
	
}//PrayingMantis