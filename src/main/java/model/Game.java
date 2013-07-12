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
  private RiskMapNetwork mapNetwork;
  private ArrayList<Territory> asia;
  private ArrayList<Territory> northAmerica;
  private ArrayList<Territory> southAmerica;
  private ArrayList<Territory> australia;
  private ArrayList<Territory> europe;
  private ArrayList<Territory> africa;
  private Hashtable<String,Territory> territories;
  private int turn=0;
  private int stage; //please read java doc on setstage()
  private int attackStage;
  private RiskStatus console;
  
  private Territory currentTerritory;
  private Territory attackingTerritory;
  private Territory defendingTerritory;
  
  public static final int TERRITORIES=39; //number of territories in the game
  
  private int territoriesLeft=TERRITORIES;
  private int playersReinforcedCompletely = 0;
  
  private boolean displayAddArmiesOption = false;
  private boolean readyToAddArmiesOption = false;
  private int numArmiesToAdd = 0;
  
  //possible stages of the game
  public static final int PICK=0;
  public static final int INITIAL_REINFORCE=1;
  public static final int REINFORCE=2;
  public static final int ATTACK=3;
  public static final int FORTIFY=4;
  
  //possible stages of the attack stage
  public static final int CONTINUE_ATTACK = 0;
  public static final int SELECT_ATTACKING_TERRITORY = 1;
  public static final int SELECT_DEFENDING_TERRITORY = 2;
  public static final int ARMIES_TO_ATTACK = 3;
  public static final int ARMIES_TO_DEFEND = 4;
  public static final int DIE_ROLL = 5;
  public static final int FORTIFY_CAPTURED = 6;
  
  
  
  /**
   * Creates a Game object with a certain number of players and territories.
   * 
   * @param players
   * @param territories
   */
  public Game(ArrayList<Player> players) {
	  this.players = players;
	  setTurnOrder();
	  setColor();
	  mapNetwork = new RiskMapNetwork();
	  territories = mapNetwork.getTerritories();
	  asia = mapNetwork.getAsia();
	  northAmerica = mapNetwork.getNorthAmerica();
	  southAmerica = mapNetwork.getSouthAmerica();
	  australia = mapNetwork.getAustralia();
	  europe = mapNetwork.getEurope();
	  africa = mapNetwork.getAfrica();
	  console=new RiskStatus();
	  
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

        colors.put(0, "#7D536D"); //452E3C
        colors.put(1, "#FF3D5A");
        colors.put(2, "#FFB969");
        colors.put(3, "#EAF27E");
        colors.put(4, "#3B8C88");
        colors.put(5, "#8A2621");
        
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
	
	public Territory getCurrentTerritory(){
		return currentTerritory;
	}
	
	private void setCurrentTerritory(Territory currentTerritory){
		this.currentTerritory = currentTerritory;
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
    
    public int getAttackStage() {
        return attackStage;
    }
    
    public void setAttackStage(int attackStage) {
        this.attackStage=attackStage;
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

					//nextTurn();
					territoriesLeft--;

		            console.append("There are " + territoriesLeft
		                    + " territories left.");
		
                    nextTurn();
				}

				// if there are no more territories to assign
				if (territoriesLeft == 0) {
					setStage(INITIAL_REINFORCE);
					resetTurn();

					// this is what would be printed to the console once it's created
		            console.append("All territories have been taken!");
					console.append("" + getCurrentPlayer().getName() + ", please pick any of your territories to reinforce your armies!");
				}
		
	}

	/**
	 * This should be looped through until all of the starting armies have been
	 * place on territories owned by the player placing the army.
	 * 
	 * @param territory
	 */
	public void initialReinforce(Territory territory) {
		

		
		if (territory.getPlayerOwned() == getCurrentPlayer()) {
		
			setCurrentTerritory(territory);
			
			if (getCurrentPlayer().getArmiesAvailable() != 0) {
				
				if (checkReadyToAddArmies()){
					
					territory.changeNumArmies(numArmiesToAdd);
					territory.getPlayerOwned().changeNumArmies(-numArmiesToAdd);

					console.append(getCurrentPlayer().getName()
							+ ", has added " + numArmiesToAdd + " armies to " + territory.getName());
							
					if (territory.getPlayerOwned().getArmiesAvailable()==0
						&& territory.getPlayerOwned().areAllArmiesDistributed()==false){
						
						territory.getPlayerOwned().setAllArmiesDistributed(true);
						playersReinforcedCompletely++;
						console.append(getCurrentPlayer().getName() 
							+ " has distributed all his armies.");
					}
					
					setReadyToAddArmies(false);
					
				} else {
				
					setOptionToAddArmies(true);
				
				}
				
			} 
			
		}else {
			console.append("You do not own that territory.");
			return;
		}		
		
		if (!checkDisplayAddArmiesOption() && !checkReadyToAddArmies()) {
			if (playersReinforcedCompletely == getPlayers().size()) {
					setStage(REINFORCE);
					resetTurn();
					console.append("All armies have been distributed."
							+ " Let the game commence! " + getCurrentPlayer().getName() +
							", please reinforce your armies!");
					awardArmies();
			} else {
				
				nextTurn();
				
				while (getCurrentPlayer().getArmiesAvailable() == 0){
					nextTurn();
				}
				
				console.append(getCurrentPlayer().getName()
							+ ", please pick any of your territories to reinforce your armies!");
				
			}
		}
		
	}
	
	public boolean checkReadyToAddArmies(){
		return readyToAddArmiesOption;	
	}
	
	public boolean checkDisplayAddArmiesOption(){
		return displayAddArmiesOption;
	}
	
	public void setReadyToAddArmies(boolean trueFalse){
		readyToAddArmiesOption = trueFalse;	
	}
	
	public void setOptionToAddArmies(boolean trueFalse){
		displayAddArmiesOption = trueFalse;
	}
	
	public int getNumArmiesToAdd(){
		return numArmiesToAdd;
	}
	
	public void setNumArmiesToAdd(int numArmiesToAdd){
		this.numArmiesToAdd = numArmiesToAdd;
		setOptionToAddArmies(false);
		setReadyToAddArmies(true);
	}

 
    public void awardArmies() {
    	Player player=getCurrentPlayer();
    	
        int armiesToAdd;
        armiesToAdd = player.getNumTerritories() / 3;
        if(armiesToAdd < 3)
        	armiesToAdd=3;
   
            console.append(player.getName() + " has been awarded " + armiesToAdd + " armies!");
            player.changeNumArmies(armiesToAdd);
        

        addAustraliaArmies();
        addNorthAmericaArmies();
        addSouthAmericaArmies();
        addEuropeArmies();
        addAsiaArmies();
        addAfricaArmies();
    }
    
   
    private void addAustraliaArmies() {
    	Player player=getCurrentPlayer();

        boolean controlled = true;
        for (int i = 0; i < australia.size(); i++) {
            if (australia.get(i).getPlayerOwned() != player) {
                controlled = false;
            }
        }
        if (controlled) {
            
        	console.append(player.getName() + " has been awarded 2 extra armies for controlling Australia!"); 
            player.changeNumArmies(2);
        }
    }
    
    private void addNorthAmericaArmies() {
    	Player player=getCurrentPlayer();

        boolean controlled = true;
        for (int i = 0; i < northAmerica.size(); i++) {
            if (northAmerica.get(i).getPlayerOwned() != player) {
                controlled = false;
            }
        }
        if (controlled) {
            console.append(player.getName() + " has been awarded 5 extra armies for controlling North America!");
            player.changeNumArmies(5);
        }
    }
    
    private void addSouthAmericaArmies() {
    	Player player=getCurrentPlayer();

        boolean controlled = true;
        for (int i = 0; i < southAmerica.size(); i++) {
            if (southAmerica.get(i).getPlayerOwned() != player) {
                controlled = false;
            }
        }
        if (controlled) {
            console.append(player.getName() + " has been awarded 2 extra armies for controlling South America!");

            player.changeNumArmies(2);
        }
    }
    
    private void addEuropeArmies() {
    	Player player=getCurrentPlayer();

        boolean controlled = true;
        for (int i = 0; i < europe.size(); i++) {
            if (europe.get(i).getPlayerOwned() != player) {
                controlled = false;
            }
        }
        if (controlled) {
            console.append(player.getName() + " has been awarded 5 extra armies for controlling Europe!");
            player.changeNumArmies(5);
        }
    }
    
    private void addAfricaArmies() {
    	Player player=getCurrentPlayer();
        boolean controlled = true;
        for (int i = 0; i < africa.size(); i++) {
            if (africa.get(i).getPlayerOwned() != player) {
                controlled = false;
            }
        }
        if (controlled) {
            console.append(player.getName() + " has been awarded 3 extra armies for controlling Africa!");
            player.changeNumArmies(3);
        }
    }
    
    private void addAsiaArmies() {
    	Player player=getCurrentPlayer();

        boolean controlled = true;
        for (int i = 0; i < asia.size(); i++) {
            if (asia.get(i).getPlayerOwned() != player) {
                controlled = false;
            }
        }
        if (controlled) {
            console.append(player.getName() + " has been awarded 7 extra armies for controlling Asia!");
            player.changeNumArmies(7);
        }
    }
    
    public void reinforce(Territory territory) {
    	Player player=getCurrentPlayer();
    	if(player.getArmiesAvailable()==0){
    		awardArmies();
    	}
        if (territory.getPlayerOwned() == player) {
            player.changeNumArmies(-1);
            territory.changeNumArmies(1);
            console.append(player.getName() + " has added an army to " + territory.getName() + "!");

        }
        else {
            console.append("You do not control that territory.");
        }
   
        if(getCurrentPlayer().getArmiesAvailable()==0) {
        	setStage(ATTACK);
        	setAttackStage(SELECT_ATTACKING_TERRITORY);
        	console.append(player.getName() + " Select a territory to attack with.");

        }
    }
    
    public void attack(Territory territory){
    	/*if (game.getAttackStage() == Game.CONTINUE_ATTACK) {
        game.setAttackStage(Game.SELECT_ATTACKING_TERRITORY);
    }*/
   
    	
  /*else */if (getAttackStage() == SELECT_ATTACKING_TERRITORY) {
	  			int adjacentTerritoryCount=0;
	  			for(int i=0;i<territory.getAdjacentTerritories().size();i++){
	  				ArrayList<Territory>adjacentTerritories=territory.getAdjacentTerritories();
  		
	  				if(adjacentTerritories.get(i).getPlayerOwned()==getCurrentPlayer()){
	  					adjacentTerritoryCount++;
	  				}
	  			}
  	
	  			
  	
	  			
	  			if(territory.getPlayerOwned()==getCurrentPlayer()){
	  				
	  				if(adjacentTerritoryCount==territory.getAdjacentTerritories().size()){
		  				console.append("You cannot attack with a territory where all adjacent territories are owned by you.");
		  			}
	  				else if(territory.getNumArmies()<2){
		  				console.append("You must have at least 2 armies in a territory to attack with");
		  			}
	  				else{
		  				attackingTerritory=territory;
		  				setAttackStage(Game.SELECT_DEFENDING_TERRITORY);
		  				console.append(getCurrentPlayer().getName()+", choose a territory adjacent to " 
		  						+ attackingTerritory.getName() +" to attack.");
	  				}
	  			}
	  			else{
	  				console.append("You must attack with a territory that you own.");
	  			}
	  			
    	}
    
		else if (getAttackStage() == Game.SELECT_DEFENDING_TERRITORY) {
			if(territory.getPlayerOwned()==getCurrentPlayer()){
				console.append("you can't attack your own territory!");
			}
			else if(attackingTerritory.getAdjacentTerritories().contains(territory)){
				defendingTerritory=territory;
				setAttackStage(Game.ARMIES_TO_ATTACK);
				console.append(getCurrentPlayer().getName()+", how many armies do you wish to attack with?");
			}
			else{
				console.append("You must attack a territory adjacent to " + attackingTerritory.getName()+"!");
			}
		}
   
		else if (getAttackStage() == Game.ARMIES_TO_ATTACK) {
		}
   
		else if (getAttackStage() == Game.ARMIES_TO_DEFEND) {
		}
   
		else if (getAttackStage() == Game.DIE_ROLL) {
		}
    }
    
    

	public RiskStatus getConsole() {
		return console;
	}


	public void resetAttackingTerritory() {
		attackingTerritory=null;
	}
	public void resetDefendingTerritory() {
		defendingTerritory=null;
	}
    
    
    
}