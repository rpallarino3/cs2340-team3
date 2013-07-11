package model;

import java.util.LinkedList;
import java.util.Random;
import java.util.ArrayList;

/**
 * A player object for the implementation of the game Risk.
 * 
 * @author Richard Bordianu
 *
 */
public class Player {

	private String name;
	private int armiesAvailable; // number of armies available to distribute
	private int numTerritories;  // number of territories owned by the player
    private boolean armiesDistributed = false; // if all of the armies have been distributed
	private String color="black";
    private ArrayList<Integer> dieRolls  = new ArrayList<Integer>();
	
	/**
	 * The player starts with a specific amount of armies,
	 * depending on how many players there are.
	 * 
	 * @param name is the name of the player
	 * @param numArmies is the initial amount of armies owned
	 * 		  depends on number of players.
	 */
	public Player(String name, int armiesAvailable) {
		this.name=name;
		this.armiesAvailable=armiesAvailable;
	}
	
	public Player(String name) {
		this.name=name;
	}
	
	/**
	 * This method is used to increment the amount of armies available
	 * for distribution.
	 * 
	 * @param num is the number to change by
	 */
	public void changeNumArmies(int num) {
		armiesAvailable+=num;
	}
	
	/**
	 * This method is used to increment the amount of territories
	 * the player owns.
	 * 
	 * @param num is the number to change by
	 */
	public void changeNumTerritories(int num) {
		numTerritories+=num;
	}
	
	/**
	 * @return the name of the player
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * 
	 * @return number of armies available for distribution.
	 */
	public int getArmiesAvailable() {
		return armiesAvailable;
	}
	
	/**
	 * 
	 * @return number of territories the player owns.
	 */
	public int getNumTerritories() {
		return numTerritories;
	}
    
    public boolean areAllArmiesDistributed() {
        return armiesDistributed;
    }
    
    public void setAllArmiesDistributed(boolean b) {
        this.armiesDistributed = b;
    }

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
    
    public void roll(int numRolls) {
        dieRolls.clear();
        Random rando = new Random();
        for(int i = 0; i < numRolls; i++) {
            dieRolls.add(rando.nextInt(6) + 1);
        }
    }
    
    public ArrayList<Integer> getDieRolls() {
        return dieRolls;
    }
}
