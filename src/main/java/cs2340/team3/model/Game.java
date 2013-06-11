package cs2340.team3.model;

import java.util.LinkedList;


/**
 * This is the implentation of the Risk-based game
 * 
 * @author Richard
 *
 */
public class Game {
	
	private LinkedList<Player> players;
	private Territory[] territories;
	
	/**
	 * Creates a Game object with a certain number of players and territories.
	 * 
	 * @param players
	 * @param territories
	 */
	public Game(LinkedList<Player> players, Territory[] territories) {
		this.players=players;
		this.territories=territories;
	}
}
