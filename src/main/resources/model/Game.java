package cs2340.team3.model;

import java.util.ArrayList;
import java.util.Random;


/**
 * This is the implentation of the Risk-based game
 * 
 * @author Richard
 *
 */
public class Game {
	
	private ArrayList<Player> players;
    private ArrayList<Player> turnOrder;
	private Territory[] territories;
    private Random rand;
	
	/**
	 * Creates a Game object with a certain number of players and territories.
	 * 
	 * @param players
	 * @param territories
	 */
	public Game(ArrayList<Player> players, Territory[] territories) {
        turnOrder = new ArrayList<Player>();
        rand = new Random();
		this.players=players;
		this.territories=territories;
        setTurnOrder(this.players);
	}
    
    private void setTurnOrder(ArrayList<Player> players) {
        int random;
        while (players.size() != 0) {
            random = rand.nextInt(players.size());
            turnOrder.add(players.get(random));
            players.remove(random);
        }
        this.players = turnOrder;
    }
    
    public ArrayList<Player> getPlayers() {
        return players;
    }
}
