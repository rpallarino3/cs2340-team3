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
  private ArrayList<Territory> territories;
    private Random rand;
  
  /**
   * Creates a Game object with a certain number of players and territories.
   * 
   * @param players
   * @param territories
   */
  public Game(ArrayList<Player> players) {
	  turnOrder = new ArrayList<Player>();
	  rand=new Random();
	  this.players=players;
	  setTurnOrder();
	  
	  territories.add(new Territory("Alaska"));
	  territories.add(new Territory("Northwest"));
	  territories.add(new Territory("Greenland"));
	  territories.add(new Territory("Alberta"));
	  territories.add(new Territory("Ontraio"));
	  territories.add(new Territory("Eastern Canada"));
	  territories.add(new Territory("West US"));
	  territories.add(new Territory("East US"));
	  territories.add(new Territory("Central America"));
	  
  }
    
    private void setTurnOrder() {
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