package cs2340.team3.controller;

import java.io.IOException; 	 
import java.util.ArrayList;
import java.util.Hashtable;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs2340.team3.model.Game;
import cs2340.team3.model.Player;
import cs2340.team3.model.Territory;

@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/game","/game/*" })
public class GameServlet extends HttpServlet {

 
	private Game game;
	private ArrayList<Player> players;
	private int territoriesLeft=Game.TERRITORIES;
	private Hashtable<String, Territory> territories;
	

    /**
     * Called when HTTP method is GET 
     * (e.g., from an <a href="...">...</a> link).
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("In doGet()");
       
        //if the game hasn't been set yet.
        if (game==null) {
        	initialGame(request);
        	game.setStage(Game.PICK);
        }
        
        else if(game.getStage()==Game.PICK) {
        	pickTerritories(request);
        	System.out.println(game.getCurrentPlayer().getName()+
        			", please pick a territory!");
        }
        
        
        else if(game.getStage()==Game.INITIAL_REINFORCE) {
        	//TODO
        	System.out.println(game.getCurrentPlayer().getName()+
        			", please reinforce your territories!");
        }
        
        
        //forward the request
        forward(request,response);
        
        
    }
    
    /**
     * This is what should happen when the game is first started
     * i.e. when players have been chosen and clicked on 
     * "start playing!"
     * @param request
     */
    private void initialGame(HttpServletRequest request) {
    	game= (Game)request.getSession().getAttribute("game");
    	players=game.getPlayers();
    	territories=game.getTerritories();
    	System.out.println("Time to pick your territories!");
    	System.out.println(game.getCurrentPlayer().getName()+
    			", please pick a territory!");
    }
    
    /**
     * This should be looped through until there are no more
     * territories left to assign. This allows a player to click
     * on a territory, and choose that territory as their own.
     * 
     * @param request
     */
    private void pickTerritories(HttpServletRequest request) {
    	//gets the name of the territory, used in the hash map
    	String territoryName=request.getPathInfo();
    	territoryName=territoryName.substring(1,territoryName.length());
    	Territory territory = territories.get(territoryName);
    	
    	//if the territory doesn't belong to anyone
    	if (territory.getPlayerOwned()==null) {
    		
    		territory.setPlayerOwned(game.getCurrentPlayer());
    		territory.changeNumArmies(1);
    		territory.getPlayerOwned().changeNumArmies(-1);
    		territory.getPlayerOwned().changeNumTerritories(1);
    		
    		System.out.println(territoryName+" was taken by "+
    				game.getCurrentPlayer().getName()+"!");
    		game.nextTurn();
    		territoriesLeft--;
    	}
    	
    	//if there are no more territories to assign
    	if(territoriesLeft==0) {
    		game.setStage(Game.INITIAL_REINFORCE);
    		game.resetTurn();
    		
    		//this is what would be printed to the console once it's created
    		System.out.println("All territories have been conquered! " + 
    				game.getCurrentPlayer().getName()+", please reinforce your armies!");
    	}
    }
    
    /**
     * this should happen at the end of every doGet method.
     * Basically, the new game is passed into the request
     * as an attribute, and the request if forwarded.
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws ServletException
     */
    private void forward(HttpServletRequest request, 
    		HttpServletResponse response) 
    				throws IOException, ServletException {
    	request.setAttribute("game", game);
    	RequestDispatcher dispatcher = 
    			getServletContext().getRequestDispatcher("/game.jsp");
    	dispatcher.forward(request,response);
    }

}