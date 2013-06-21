package model;

import java.util.ArrayList;  
import java.util.Hashtable;
import java.util.Random;
import etc.RiskStatus;

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
  private RiskStatus console;
  
  
  public static final int TERRITORIES=39; //number of territories in the game
  
  private int territoriesLeft=TERRITORIES;
  private int playersReinforcedCompletely = 0;

  
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
	  console=new RiskStatus();
	  
  }
  
  private void setNorthAmerica() {
	  territories.put("Alaska",new Territory("Alaska",20,100));
	  territories.put("Northwest",new Territory("Northwest",100,100));
	  territories.put("Greenland",new Territory("Greenland",280,70));
	  territories.put("Alberta",new Territory("Alberta",100,150));
	  territories.put("Ontario",new Territory("Ontario",180,160));
	  territories.put("EasternCanada",new Territory("Eastern Canada",250,145));
	  territories.put("WestUS",new Territory("West US",100,200));
	  territories.put("EastUS",new Territory("East US",190,220));
	  territories.put("CentralAmerica",new Territory("Central America",100,250));
	  
  }
  
  private void setSouthAmerica() {
	  territories.put("Arawak", new Territory("Arawak",160,320));
	  territories.put("Kariri", new Territory("Kariri",250,370));
	  territories.put("Baniva", new Territory("Baniva",180,390));
	  territories.put("Ika", new Territory("Ika",190,460));
  }
  
  private void setEurope() {
	  
	  territories.put("Sweden", new Territory("Sweden",380,100));
	  territories.put("Britain", new Territory("Britain",340,160));
	  territories.put("Germany", new Territory("Germany",380,180));
	  territories.put("Ukraine", new Territory("Ukraine",450,170));
	  territories.put("Spain", new Territory("Spain",330,220));
	  territories.put("RomanEmpire", new Territory("Roman Empire",390,215));
  }
  private void setAfrica() {
	  territories.put("Egypt", new Territory("Egypt",400,290));
	  territories.put("Dinka", new Territory("Dinka",350,340));
	  territories.put("Xhosa", new Territory("Xhosa",430,350));
	  territories.put("Ngbandi", new Territory("Ngbandi",390,405));
	  territories.put("Sakalava", new Territory("Sakalava",395,470));
  }
  private void setAsia() {
	  territories.put("Kurdish", new Territory("Kurdish",580,100));
	  territories.put("Pashtun", new Territory("Pashtun",680,100));
	  territories.put("Buryats", new Territory("Buryats",540,130));
	  territories.put("Puyuma", new Territory("Puyuma",650,150));
	  territories.put("Seediq", new Territory("Seediq",520,200));
	  territories.put("Khakas", new Territory("Khakas",670,190));
	  territories.put("China", new Territory("China",630,250));
	  territories.put("MiddleEast", new Territory("Middle East",470,270));
	  territories.put("India", new Territory("India",565,300));
	  territories.put("Korea", new Territory("Korea",630,315));
	  territories.put("Tuvans", new Territory("Tuvans",760,100));
  }
  
  private void setAustralia() {
	  territories.put("Singapore", new Territory("Singapore",650,380));
	  territories.put("NewZealand", new Territory("New Zealand",730,380));
	  territories.put("WesternAustralia", new Territory("Western Australia",610,470));
	  territories.put("EasternAustralia", new Territory("Eastern Australia",730,450));
	  
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

	/**
	 * This should be looped through until there are no more territories left to
	 * assign. This allows a player to click on a territory, and choose that
	 * territory as their own.
	 * 
	 * @param territory
	 */
	public void pickTerritories(Territory territory) {
		// if the territory doesn't belong to anyone
				if (territory.getPlayerOwned() == null) {

					territory.setPlayerOwned(getCurrentPlayer());
					territory.changeNumArmies(1);
					territory.getPlayerOwned().changeNumArmies(-1);
					territory.getPlayerOwned().changeNumTerritories(1);
		            
		            console.append(territory.getName() + " was taken by "
		                    + getCurrentPlayer().getName() + "!");

					nextTurn();
					territoriesLeft--;

		            console.append("There are " + territoriesLeft
		                    + " territories left.");
					System.out.println("There are " + territoriesLeft
							+ " territories left.");
				}

				// if there are no more territories to assign
				if (territoriesLeft == 0) {
					setStage(INITIAL_REINFORCE);
					resetTurn();

					// this is what would be printed to the console once it's created
		            console.append("All territories have been conquered! "
		                    + getCurrentPlayer().getName()
		                    + ", please reinforce your armies!");
				}
		
	}

	/**
	 * This should be looped through until all of the starting armies have been
	 * place on territories owned by the player placing the army.
	 * 
	 * @param territory
	 */
	public void initialReinforce(Territory territory) {
		if (getCurrentPlayer().getArmiesAvailable() != 0) {
			
			if (territory.getPlayerOwned() == getCurrentPlayer()) {
				territory.changeNumArmies(1);
				territory.getPlayerOwned().changeNumArmies(-1);

                console.append(getCurrentPlayer().getName()
                        + ", has added an army to " + territory.getName());
				System.out.println(getCurrentPlayer().getName()
						+ ", has added an army to " + territory.getName());
			} else {
                console.append("You do not own that territory.");
				System.out.println("You do not own that territory.");
				return;
			}
			if (getCurrentPlayer().getArmiesAvailable() == 0
					&& getCurrentPlayer().getArmiesDistributed() == false) {
				getCurrentPlayer().setArmiesDistributed(true);
				playersReinforcedCompletely++;
                console.append(playersReinforcedCompletely);
				System.out.println(playersReinforcedCompletely);
			}
			if (playersReinforcedCompletely == getPlayers().size()) {
				setStage(ATTACK);
				resetTurn();
                console.append("All armies have been distributed. "
                        + getCurrentPlayer().getName()
                        + ", pick a territory to attack!");
				System.out.println("All armies have been distributed. "
						+ getCurrentPlayer().getName()
						+ ", pick a territory to attack!");
			}
			nextTurn();
		}
		
	}

	public RiskStatus getConsole() {
		return console;
	}
}