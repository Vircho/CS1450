/*
 * Joshua White
 * CS1450 (T/R)
 * Due 09/07/23
 * Assignment 2
 * Make Shark objects from file, then place them in an Aquarium object
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class WhiteJoshuaAssignment2 {
	public static void main(String[] args) throws IOException {
	
		//Open Sharks.txt for reading
		File sharksFile = new File ("Sharks.txt");
		Scanner scanSharks = new Scanner (sharksFile);
		
		
		//Create polymorphic array to store sharks
		Shark[] allSharks;
		allSharks = new Shark[scanSharks.nextInt()];
		
		
		//Read the file, create our shark objects
		for (int i = 0; i < allSharks.length; i++) {
			String type = scanSharks.next();
			int age = scanSharks.nextInt();
			String name = scanSharks.nextLine();
			
			//Iterate and create a specific shark object based on what is in the file
			switch(type) { 
			case "greatwhite":
				allSharks[i] = new GreatWhite(age, name);
				break;
			case "hammerhead":
				allSharks[i] = new Hammerhead(age, name); 
				break;
			case "tiger":
				allSharks[i] = new Tiger(age, name);
				break;
			case "zebra":
				allSharks[i] = new Zebra(age, name);
				break;
			}
		}
		
		
		//Display all the sharks
		System.out.println("============================================================");
		System.out.printf("Type	\t Age \t Name \t Physical Description \n");
		System.out.println("============================================================");
		for (int i = 0; i < allSharks.length; i++) {
			System.out.printf(allSharks[i].getType() + "	" + allSharks[i].getAge() + "	" + allSharks[i].getName()
					+ "	" + allSharks[i].physicalDescription() + "\n");
		}
		
		
		//Create Aquarium
		Aquarium aquarium = new Aquarium();
		aquarium.fillAquarium(allSharks);
		aquarium.printAquariumDetails();
		
	} //main
		
}//WhiteJoshuaAssignment2



class Shark {
	
	//Private data fields
	private String type;
	private int age;
	private String name;
	
	//Constructor
	public Shark(String type, int age, String name) {
		this.type = type;
		this.age = age;
		this.name = name;
	} 
	
	//Getters
	public String getType() {
		return type;
	}
	public int getAge() {
		return age;
	}
	public String getName() {
		return name;
	} 
	
	//Returns the description of shark in String
	public String physicalDescription() {
		return "";
	}
	
} //Shark



class GreatWhite extends Shark {
	
	//Constructor
	public GreatWhite(int age, String name) {
		super("Great White", age, name);
	}
	
	//Returns physical description for GreatWhite
	@Override
	public String physicalDescription() {
		return "Torpedo-shaped body with a white-colored underside";
	}
	
} //GreatWhite



class Hammerhead extends Shark {
	
	//Constructor
	public Hammerhead(int age, String name) {
		super("Hammerhead", age, name);
	}
	
	//Returns physical description for Hammerhead
	@Override
	public String physicalDescription() {
		return "Flattened head that laterally extends into a hammer shape";
	}
	
} //Hammerhead



class Tiger extends Shark {
	
	//Constructor
	public Tiger(int age, String name) {
		super("Tiger", age, name);
	}
	
	//Returns physical description for Tiger
	@Override
	public String physicalDescription() {
		return "Tiger-like stripes that fade as shark matures";
	}
	
} //Tiger



class Zebra extends Shark {
	
	//Constructor
	public Zebra(int age, String name) {
		super("Zebra", age, name);
	}
	
	//Returns physical description for Zebra
	@Override
	public String physicalDescription() {
		return "Yellowish stripes on dark body that change to dark spots";
	}
	
} //Zebra



class Aquarium {
	
	//Private data fields
	private int numTiger = 0;
	private int numZebra = 0;
	private Shark[] aquariumSharks;
	
	
	public void fillAquarium(Shark[] sharks) {
		
		//Set size of aquariumSharks array since it will be smaller than sharks array
		//And also figure out how many specific tiger/zebra's there are while we're here
		int amountOfValidSharks = 0;
		for (int i = 0; i < sharks.length; i++) {;
			
			switch (sharks[i].getType()) {
			case "Tiger":
				amountOfValidSharks++;
				numTiger++;
				break;
			case "Zebra":
				amountOfValidSharks++;
				numZebra++;
				break;
			
			}
					
		}
		aquariumSharks = new Shark[amountOfValidSharks];
		
		
		//Now that we know what size aquariumSharks should be, we can look through
		//the sharks again to add only tiger & zebra
		int nextOpenSpotInAquariumSharkArray = 0;
		
		for (int i = 0; i < sharks.length; i++) {
			if (sharks[i].getType() == "Tiger" || sharks[i].getType() == "Zebra") {
				aquariumSharks[nextOpenSpotInAquariumSharkArray] = sharks[i];
				nextOpenSpotInAquariumSharkArray++;
			}
			else {
				
			}
		}
		
	}//fillAquarium
	
	
	//Print the aquarium once it's built
	public void printAquariumDetails() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Shark Aquarium");
		System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Tiger Sharks in Aquarium: " + numTiger);
		System.out.println("Zebra Sharks in Aquarium: " + numZebra);
		System.out.println("");
		
		//loop and print
		for (int i = 0; i < aquariumSharks.length; i++) {
			System.out.println(aquariumSharks[i].getType() + "	" + aquariumSharks[i].getName());
		}	
	}
	
}//Aquarium

/*
 * Help gotten:
 * 
 * Math Center, error when making subclasses
 * 
 * Math Center, Problem when calling a Shark class,
 * realized on own that I was putting the Shark classes/subclasses inside
 * WhiteJoshuaAssignment2
 * 
 */