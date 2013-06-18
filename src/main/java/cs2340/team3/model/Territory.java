package cs2340.team3.model;

public class Territory {
	
	private String name;
	private Player playerOwned; 
	private int numArmies;      
	private String style; //style tag for css based on
	                      //game_board.jpg
	/**
	 * Creates a territory
	 * 
	 * @param name is the name of the territory
	 */
	public Territory(String name) {
		this.name=name;
	}
	
	public Territory(String name,String style) {
		this.name=name;
		this.style=style;
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
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @param style the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}
}
