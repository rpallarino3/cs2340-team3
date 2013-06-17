package cs2340.team3.controller;

import java.io.IOException; 	
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.TreeMap;
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
	private int territoriesLeft=game.TERRITORIES;
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
        }
        
        //if there are still territories left to assign
        else if(territoriesLeft!=0) {
        	pickTerritories(request);
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
    	for(int i=0;i<players.size();i++)
    		System.out.println(players.get(i).getName());
    }
    
    /**
     * This should be looped through until there are no more
     * territories left to assign. This allows a player to click
     * on a territory, and choose that territory as their own.
     * 
     * @param request
     */
    private void pickTerritories(HttpServletRequest request) {
    	String territoryName=request.getPathInfo();
    	System.out.println(territoryName);
    	territoryName=territoryName.substring(1,territoryName.length());
    	Territory territory = territories.get(territoryName);
    	if (territory.getPlayerOwned()==null) {
    		territory.setPlayerOwned(game.getCurrentPlayer());
    		game.nextTurn();
    		territoriesLeft--;
    		System.out.println(territory.getPlayerOwned().getName());
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
