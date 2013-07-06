package model;

import java.util.ArrayList;

public class Territory {
	
	private String name;
	private Player playerOwned;
	private int numArmies;
    private ArrayList<Territory> adjacentTerritories;
    private int marginLeft, marginTop;
	private ArrayList polygons;

	/**
	 * Creates a territory
	 * 
	 * @param name is the name of the territory
	 */
	public Territory(String name) {
		this.name=name;
	}
	
	/**
	 * Used if placing location of territory in html
	 * based on absolute margins.
	 */
	public Territory(String name, 
			int marginLeft, int marginTop, ArrayList polygons) {
		this(name);
		this.marginLeft=marginLeft;
		this.marginTop=marginTop;
		this.polygons=polygons;
	}
	
	
	/**
	 * This method is used to give this territory a different owner.
	 * @param player is the player to give the territory to.
	 */
	public void setPlayerOwned(Player player) {
		playerOwned=player;
	}
	
	/**
	 * This method is used to get the player who owns the territory.
	 * 
	 * @return
	 */
	public Player getPlayerOwned() {
		return playerOwned;
	}
	
	/**
	 * 
	 * @return name is the name of the territory.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * This method is used to change how many armies occupy the territory.
	 * 
	 * @param num is the number to change by
	 */
	public void changeNumArmies(int num) {
		numArmies+=num;
	}
    
	
	/**
	 * 
	 * @return the number of armies occupying the territory.
	 */
	public int getNumArmies() {
		return numArmies;
	}

    /**
	 * @return the marginLeft
	 */
	public int getMarginLeft() {
		return marginLeft;
	}


	/**
	 * @return the marginTop
	 */
	public int getMarginTop() {
		return marginTop;
	}


	/**
    * Transfer armies between two adjacent territories
    *
    * @param t the territory to move to
    * @param armiesToMove the number of armies to move
    *
    */
    public void moveArmies(Territory t, int armiesToMove) {
        if (adjacentTerritories.contains(t)) {
            changeNumArmies(-armiesToMove);
            t.changeNumArmies(armiesToMove);
        }
        else {
            // say something about not being able to move to that territory
        }
    }
	
	public void setAdjacentTerritories(ArrayList<Territory> neighbors){
	
		adjacentTerritories = new ArrayList<Territory>();
		adjacentTerritories = neighbors;
		
	}
	
	public ArrayList<Territory> getAdjacentTerritories(){
		return adjacentTerritories;
	}
	
}
