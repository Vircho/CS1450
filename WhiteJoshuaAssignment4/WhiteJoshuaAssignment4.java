/*
 * Joshua White
 * CS 1450 (T/R)
 * Due 09/28/23
 * 
 * Create a Team object that holds Player objects that are read from team.txt,
 * including their ranking, spot, and name, then print the Player objects in a table.
 * Then put the Player objects in myTeam into an ArrayList, excluding null spots.
 * Then use Collections.sort() to sort the Player objects from highest rank to
 * lowest and print the players in ArrayList using toString.
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class WhiteJoshuaAssignment4 {

	public static void main(String[] args) throws IOException {
		
		//Create Team Object with it's name and size from the file Team.txt
		File teamFile = new File("Team.txt");
		Scanner scanTeam = new Scanner(teamFile);
		
		String teamName = scanTeam.next();
		int availableSpots = scanTeam.nextInt();
		
		Team myTeam = new Team(teamName, availableSpots);
		
		
		//Read the file for players, create Player object, and add Player to roster
		while(scanTeam.hasNext()) {

			int ranking = scanTeam.nextInt();
			int rosterSpot = scanTeam.nextInt();
			String name = scanTeam.nextLine();
			Player readPlayer = new Player(myTeam.getName(), ranking, name);
			
			myTeam.addPlayer(rosterSpot, readPlayer);
			
		}
		scanTeam.close();
	
		
		//Display a table of the team
		System.out.println("--------------------------");
		System.out.println(myTeam.getName() + " Roster");
		System.out.println("--------------------------");
		System.out.println("Spot              Name    ");
		System.out.println("--------------------------");
		myTeam.displayTeam();
		
		
		//Display players by their ranking
		printRosterByRanking(myTeam);
		
	}//main
	
	
	
	
	
	public static void printRosterByRanking(Team team) {
		
		//Place players from team into ArrayList sortedRoster
		ArrayList<Player> sortedRoster = new ArrayList<>();
		
		for (int i = 0; i < team.numberSpots(); i++) {
			
			if (team.getPlayer(i) != null) {
				sortedRoster.add(team.getPlayer(i));
			}
		}
		
		
		//Sort players by ranking
		Collections.sort(sortedRoster);
		
		
		//Display players by ranking
		System.out.println("");
		System.out.println("--------------------------");
		System.out.println(team.getName() + " Roster by ranking");
		System.out.println("---------------------------------------------");
		System.out.println("Team Name \tRank \t \t Name");
		System.out.println("---------------------------------------------");
		for (int i = 0; i < sortedRoster.size(); i++) {
			System.out.println(sortedRoster.get(i));
		}
		
		
		
		
	}//printRosterByRanking
	
}//WhiteJoshuaAssignment4








class Team {
	
	//Instance Variables
	private String name;
	private int numberSpots;
	private Player[] roster;
	
	//Constructor
	public Team (String name, int numberSpots) {
		this.name = name;
		this.numberSpots = numberSpots;
		roster = new Player[numberSpots];
	}
	
	//Getters
	public String getName() {
		return name;
	}
	
	public int numberSpots() {
		return numberSpots;
	}
	
	//addPlayer - Places incoming player object in roster array in the specified spot
	public Player addPlayer(int spot, Player player) {
		roster[spot] = player;
		return roster[spot];
	}
	
	//getPlayer - Returns the player stored in roster array in specified spot
	public Player getPlayer(int Spot) {
		return roster[Spot];
	}
	
	//displayTeam - Displays nicely formatted version of team’s roster
	public void displayTeam() {
		
		//For as many spots, if null, print empty space, else, print player
		for (int i = 0; i < numberSpots; i++) {
			
			if (roster[i] == null) {
			    System.out.println(i + "\t" + " -----");
			}
			else {
				System.out.println(i + "\t" + roster[i].getName());
			}
			
		}//End of for-loop
	}
	
}//Team





class Player implements Comparable<Player>{
	
	//Instance Variables
	private String team;
	private int ranking;
	private String name;
	
	//Constructor
	public Player(String team, int ranking, String name) {
		this.team = team;
		this.ranking = ranking;
		this.name = name;
	}
	
	//Getters
	public String getTeam() {
		return team;
	}
	
	public int getRanking() {
		return ranking;
	}
	
	public String getName() {
		return name;
	}
	
	//toString - Returns string with player’s team, ranking, and name. 
	@Override
	public String toString() {
		return String.format("%s\t%d\t\t%-10s", team, ranking, name);
	}
	
	//compareTo - Compares two players based on their ranking
	//& returns integer value (-1, 0, 1) based on result of comparing two players.
	@Override
	public int compareTo(Player otherPlayer) {
		if (this.ranking < otherPlayer.ranking) {
			return -1;
		}
		else if (this.ranking > otherPlayer.ranking) {
			return 1;
		}
			return 0;
		
	}
	
}//Player

/*
 *Help Gotten:
 *Andrew (Math Center) - How to use toString to print sorted roster
 */