/*
 * Joshua White
 * CS1450 (T/R)
 * Due 10/26/23
 * Assignment 7
 * Create Teams & Players and simulate an escape room game where the Players in the Team will be brought from a waiting Queue
 * to the EscapeRoom then, given a score and placed into a results Queue, printing the steps along the way
 */

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class WhiteJoshuaAssignment7 {
	public static void main(String[] args) throws IOException {
		
		//Create the Team7 object
		File team7File = new File("Team7.txt");
		Scanner scanTeam = new Scanner(team7File);
		String teamName = scanTeam.nextLine();
		int availableSpots = scanTeam.nextInt();
		Team7 myTeam = new Team7(teamName, availableSpots);
		
		//Load Team7 with Players from file
		while (scanTeam.hasNext()) {
			
			int ranking = scanTeam.nextInt();
			int spot = scanTeam.nextInt();
			String playerName = scanTeam.nextLine().trim();
			Player7 readPlayer = new Player7(myTeam.getName(), ranking, playerName);
			myTeam.addPlayer(spot, readPlayer);
			
		}
		scanTeam.close();
		
		//Print all players after loading myTeam
		myTeam.displayTeam();
		
		
		//Create GameController & Game objects
		GameController gameController = new GameController();
		Game game = new Game();
		
		
		//Move all players into game using GameContoller
		gameController.movePlayersIntoGame(myTeam, game);
		
		//Start the game
		gameController.simulateGame(game);
		
		//Display the results of the game once it's completed
		gameController.displayResults(game);
		
		//If the game is over, print that it is
		if (gameController.isGameOver(game)) {
			System.out.println("The game has concluded and everyone had a great time!");
		}
		
	}//main
}//WhiteJoshuaAssignment7


class Team7 {
	
	//Instance Variables
	private String name;
	private int numberSpots;
	private Player7[] roster;
	
	//Constructor
	public Team7 (String name, int numberSpots) {
		this.name = name;
		this.numberSpots = numberSpots;
		roster = new Player7[numberSpots];
	}
	
	//Getters
	public String getName() {
		return name;
	}
	
	public int numberSpots() {
		return numberSpots;
	}
	
	//addPlayer7 - Places incoming Player object in roster array in the specified spot
	public Player7 addPlayer(int spot, Player7 player) {
		roster[spot] = player;
		return roster[spot];
	}
	
	//getPlayer7 - Returns the Player stored in roster array in specified spot
	public Player7 getPlayer(int Spot) {
		return roster[Spot];
	}
	
	//displayTeam - Displays nicely formatted version of Team’s roster
	public void displayTeam() {
		
		//For as many spots, if null, print empty space, else, print Player
		for (int i = 0; i < numberSpots; i++) {
			
			if (roster[i] == null) {
			    System.out.println(i + "\t" + " -----");
			}
			else {
				System.out.println(i + "\t" + roster[i].getName());
			}
			
		}//End of for-loop
	}
	
}//Team7


class Player7 implements Comparable<Player7>{
	
	//Instance Variables
	private int score;
	
	private String team;
	private int ranking;
	private String name;
	
	//Constructor
	public Player7(String team, int ranking, String name) {
		score = 0;
		
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
	
	public int getScore() {
		return score;
	}
	
	//Setters
	public void setScore(int score) {
		this.score = score;
	}
	
	//toString - Returns string with Player7’s Team7, ranking, and name. 
	@Override
	public String toString() {
		return String.format("%s\t%d\t\t%-10s", team, ranking, name);
	}
	
	//compareTo - Compares two Players based on their ranking
	//& returns integer value (-1, 0, 1) based on result of comparing two Player7s
	@Override
	public int compareTo(Player7 otherPlayer) {
		if (this.getScore() < otherPlayer.getScore()) {
			return 1;
		}
		else if (this.getScore() > otherPlayer.getScore()) {
			return -1;
		}
			return 0;
		
	}
	
}//Player7


class EscapeRoom {
	
	
	//hash - returns hash of the key
	private int hash(String key) {
		
		int hash = 0;
		for (int i = 0; i < key.length(); i++) {
			hash += key.charAt(i);
			hash += (hash << 10);
			hash ^= (hash >> 6);
		}
		
		hash += (hash << 3);
		hash ^= (hash >> 11);
		hash += (hash << 15);
		return Math.abs(hash);
	}
	
	//tryToEscape - Simulates the player trying to escape by giving them a score between 1 and 100 based on the hash of their
	//name and score combined.
	public int tryToEscape(String playerName, int playerRanking) {
		
		String key = playerName + playerRanking;
		int score = hash(key) % 101;
		
		return score;
	}

}//EscapeRoom


class Game {
	
	//Instance Variables
	Queue<Player7> waitingToPlayQ;
	PriorityQueue<Player7> resultsQ;	
	EscapeRoom escapeRoom;
	
	
	//Constructor
	public Game() {
		waitingToPlayQ = new LinkedList<>();
		resultsQ = new PriorityQueue<>();
		escapeRoom = new EscapeRoom();
	}
	
	
	//isWaitingToPlayQEmpty - returns true when Queue waitingToPlayQ is empty, false otherwise
	public boolean isWaitingToPlayQEmpty() {
		return waitingToPlayQ.isEmpty();
	}
	
	
	//addPlayerToWaitingToPlayQ - adds a Player7 to Queue waitingToPlayQ
	public void addPlayerToWaitingToPlayQ(Player7 player) {
		waitingToPlayQ.offer(player);
	}
	
	
	//removePlayer7FromWaitingToPlayQ - removes Player7 from Queue waitingToPlayQ and returns the Player7
	public Player7 removePlayerFromWaitingToPlayQ() {
		return waitingToPlayQ.remove();
	}
	
	
	//Overriden PriorityQueue methods
	public boolean isEmpty() {
		return resultsQ.isEmpty();
	}
	
	public void add(Player7 value) {
		resultsQ.offer(value);
	}
	
	public Player7 remove() {
		return resultsQ.remove();
	}
	
	
	//peekResultsQ - returns Player7 in Queue resultsQ with highest score
	public Player7 peekResultsQ() {
		return resultsQ.peek();
	}
	
	
	//tryToEscape - returns the score the Player7 obtained inside EscapeRoom; calls tryToEscape in EscapeRoom
	public int tryToEscape(String playerName, int playerRanking) {
		return escapeRoom.tryToEscape(playerName, playerRanking);
	}
	
	
}//Game


class GameController {
	
	
	//movePlayersIntoGame - Moves all players from a Team into the Game's waitingToPlayQ
	public void movePlayersIntoGame(Team7 team, Game game) {
		
		int teamSize = team.numberSpots();
		for (int i = 0; i < teamSize; i++) {
			
			if (team.getPlayer(i) != null) {
				
			    game.addPlayerToWaitingToPlayQ(team.getPlayer(i));
	
				System.out.println("Moved into Game: " + team.getPlayer(i).getName() 
				+ " from roster spot " + i);
			}
		}
		
	}
	
	
	//simulateGame -
	public void simulateGame(Game game) {
		
		//Start the game by setting up the top of the table
		System.out.println("---------------------------------");
		System.out.println("Starting Game...                 ");
		System.out.println("---------------------------------");
		System.out.println("Player \t Score \t Current Leader");
		System.out.println("---------------------------------");
		
		
		while (!game.waitingToPlayQ.isEmpty()) {
			
			//Set up variables to store the Player's attributes in for organizational purposes
			String currentlyPlayingName;
			int currentlyPlayingRank;
			int currentlyPlayingScore;
			
			//Fill in the Name & Rank variables with the player currently at the end of the waitingToPlayQ
			currentlyPlayingName = game.waitingToPlayQ.peek().getName();
			currentlyPlayingRank = game.waitingToPlayQ.peek().getRanking();
			
			//Find the current player's score by calling tryToEscape
			currentlyPlayingScore = game.tryToEscape(currentlyPlayingName, currentlyPlayingRank);
			
			//Set the current players score that they got from trying to escape
			game.waitingToPlayQ.peek().setScore(currentlyPlayingScore);
			
			//Bring the player into the resultsQ after they are done playing
			game.resultsQ.offer(game.waitingToPlayQ.peek());
			
			//Print the results of the last player's game
			System.out.println(game.waitingToPlayQ.peek().getName() + "\t"
			+ game.waitingToPlayQ.peek().getScore() + "\t" + game.peekResultsQ().getName());
			
			//Remove the player from waitingToPlayQ
			game.waitingToPlayQ.remove();
		}
		
	}
	
	
	//displayResults - displays the results of the game after it is over
	public void displayResults(Game game) {
		System.out.println("FINAL ESCAPE ROOM RESULTS");
		System.out.println("-------------------------");
		System.out.println("Name \t Score            ");
		System.out.println("-------------------------");
		while (!game.resultsQ.isEmpty()) {
			
			System.out.println(game.peekResultsQ().getName() + "\t"
			+ game.peekResultsQ().getScore());
			game.resultsQ.remove();
			
		}
	}
	
	
	//isGameOver - returns whether the game is over or not, which is based on whether the waiting queue is empty or not
	public boolean isGameOver(Game game) {
		return game.isWaitingToPlayQEmpty() && game.resultsQ.isEmpty();
	}
	
	
}//GameController

/*
 * Help Gotten:
 * Math Center - Change Player and Team names to avoid conflict with Assignment 4, where they were previously defined
 * Math Center - PriorityQueue was prioritizing lowest scores first, change compareTo
 */