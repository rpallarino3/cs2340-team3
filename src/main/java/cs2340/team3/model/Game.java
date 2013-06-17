package cs2340.team3.model;

import java.util.ArrayList;  
import java.util.Hashtable;
import java.util.Random;


/**
 * This is the implentation of the Risk-based game
 * 
 * @author Richard
 *
 */
public class Game {
  
  private ArrayList<Player> players;
  private Hashtable<String,Territory> territories;
  private int turn=0;
  private int stage; //please read java doc on setstage()
  
  
  public static final int TERRITORIES=9; //number of territories in the game
  
  //possible stages of the game
  public static final int PICK=0;
  public static final int INITIAL_REINFORCE=1;
  public static final int REINFORCE=2;
  public static final int ATTACK=3;
  public static final int FORTIFY=4;
  
  /**
   * Creates a Game object with a certain number of players and territories.
   * 
   * @param players
   * @param territories
   */
  public Game(ArrayList<Player> players) {
	  this.players=players;
	  setTurnOrder();
	  territories=new Hashtable<String,Territory>();
	  
	  territories.put("Alaska",new Territory("Alaska"));
	  territories.put("Northwest",new Territory("Northwest"));
	  territories.put("Greenland",new Territory("Greenland"));
	  territories.put("Alberta",new Territory("Alberta"));
	  territories.put("Ontario",new Territory("Ontario"));
	  territories.put("EasternCanada",new Territory("Eastern Canada"));
	  territories.put("WestUS",new Territory("West US"));
	  territories.put("EastUS",new Territory("East US"));
	  territories.put("CentralAmerica",new Territory("Central America"));
  }
    
    private void setTurnOrder() {
    	Random rand= new Random();
    	ArrayList<Player> turnOrder=new ArrayList<Player>();
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

	public Hashtable<String,Territory> getTerritories() {
		return territories;
	}
	
	public Player getCurrentPlayer() {
		return players.get(turn);
	}
	
	/**
	 * @return the stage
	 */
	public int getStage() {
		return stage;
	}

	/**
	 * Possible stages are :
	 * PICK
	 * INITIAL_REINFORCE
	 * REINFORCE
	 * ATTACK
	 * FORTIFY
	 * 
	 * @param stage the stage to set
	 * @throws Exception 
	 */
	public void setStage(int stage){
		this.stage=stage;
	}
	
	/**
	 * Goes to the next Person's turn.
	 */
	public void nextTurn() {
		turn++;
		if(turn>=players.size())
			turn=0;
	}
	
	/**
	 * Resets whose turn it is back to player 1.
	 */
	public void resetTurn() {
		this.turn=0;
	}
}