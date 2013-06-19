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
  
  
  public static final int TERRITORIES=39; //number of territories in the game
  
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
	  setColor();
	  territories=new Hashtable<String,Territory>();
	  setNorthAmerica();
	  setSouthAmerica();
	  setEurope();
	  setAfrica();
	  setAsia();
	  setAustralia();
	  
  }
  
  private void setNorthAmerica() {
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
  
  private void setSouthAmerica() {
	  territories.put("Arawak", new Territory("Arawak"));
	  territories.put("Kariri", new Territory("Kariri"));
	  territories.put("Baniva", new Territory("Baniva"));
	  territories.put("Ika", new Territory("Ika"));
  }
  
  private void setEurope() {
	  
	  territories.put("Sweden", new Territory("Sweden"));
	  territories.put("Britain", new Territory("Britain"));
	  territories.put("Germany", new Territory("Germany"));
	  territories.put("Ukraine", new Territory("Ukraine"));
	  territories.put("Spain", new Territory("Spain"));
	  territories.put("RomanEmpire", new Territory("Roman Empire"));
  }
  private void setAfrica() {
	  territories.put("Egypt", new Territory("Egypt"));
	  territories.put("Dinka", new Territory("Dinka"));
	  territories.put("Xhosa", new Territory("Xhosa"));
	  territories.put("Ngbandi", new Territory("Ngbandi"));
	  territories.put("Sakalava", new Territory("Sakalava"));
  }
  private void setAsia() {
	  territories.put("Kurdish", new Territory("Kurdish"));
	  territories.put("Pashtun", new Territory("Pashtun"));
	  territories.put("Buryats", new Territory("Buryats"));
	  territories.put("Puyuma", new Territory("Puyuma"));
	  territories.put("Seediq", new Territory("Seediq"));
	  territories.put("Khakas", new Territory("Khakas"));
	  territories.put("China", new Territory("China"));
	  territories.put("MiddleEast", new Territory("Middle East"));
	  territories.put("India", new Territory("India"));
	  territories.put("Korea", new Territory("Korea"));
  }
  
  private void setAustralia() {
	  territories.put("Singapore", new Territory("Singapore"));
	  territories.put("NewZealand", new Territory("New Zealand"));
	  territories.put("WesternAustralia", new Territory("Western Australia"));
	  territories.put("EasternAustralia", new Territory("Eastern Australia"));
	  territories.put("Tuvans", new Territory("Tuvans"));
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
    
    private void setColor() {
    	Random rand= new Random();
        int random;
        
        //hashtable of possible colors
        Hashtable<Integer,String> colors=
        		new Hashtable<Integer,String>();
        colors.put(0,"red");
        colors.put(1,"cyan");
        colors.put(2, "lime");
        colors.put(3,"brown");
        colors.put(4, "gold");
        colors.put(5, "pink");
        
        //hashtable of colors already used
        Hashtable<Integer,String> selectedColors=
        		new Hashtable<Integer,String>();
        
        //loops through and gives a color to every player
        for (int i=0; i<players.size(); i++) {
            random = rand.nextInt(6);
            
            while (selectedColors.containsKey(random)){
            	random=rand.nextInt(6);
            }
            
            players.get(i).setColor(colors.get(random));
        	selectedColors.put(random, colors.get(random));
        }
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