package controller;

import java.io.IOException;   
import java.util.Hashtable;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import etc.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/game", "/game/*" })
public class GameServlet extends HttpServlet {

	private Game game;
    private RiskStatus console;
	private Hashtable<String, Territory> territories;
	private Territory attackingTerritory;
	private Territory defendingTerritory;

	/**
	 * Called when HTTP method is GET (e.g., from an <a href="...">...</a>
	 * link).
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		System.out.println("In doGet()");

		// if the game hasn't been set yet.
		if (game == null) {
			initialGame(request);
			game.setStage(Game.PICK);
		}
		
		//initial picking territory stage
		else if (game.getStage() == Game.PICK) {
			// gets the name of the territory, used in the hash map
			String territoryName = request.getPathInfo();
			territoryName = territoryName.substring(1, territoryName.length());
			Territory territory = territories.get(territoryName);

			game.pickTerritories(territory);

			if (game.getStage() == Game.PICK) {
				console.append(game.getCurrentPlayer().getName()
						+ ", please pick a territory!");
			}
		}
		
		//intitial territory reinforcing stage
		else if (game.getStage() == Game.INITIAL_REINFORCE) {
			String territoryName = request.getPathInfo();
			territoryName = territoryName.substring(1, territoryName.length());
			Territory territory = territories.get(territoryName);

			game.initialReinforce(territory);

			if (game.getStage() == Game.INITIAL_REINFORCE) {
				console.append(game.getCurrentPlayer().getName()
						+ ", please reinforce your territories!");
			}
		}
        
		//reinforcing stage
        else if (game.getStage() == Game.REINFORCE) {
           
                String territoryName = request.getPathInfo();
                territoryName = territoryName.substring(1, territoryName.length());
                Territory territory = territories.get(territoryName);
                game.reinforce(territory);

               
       }


		else if (game.getStage() == Game.ATTACK) {
			String territoryName = request.getPathInfo();
   			territoryName = territoryName.substring(1, territoryName.length());
   			Territory territory = territories.get(territoryName);	
   			if(game.getAttackStage()==Game.SELECT_ATTACKING_TERRITORY ||
   					game.getAttackStage()==Game.SELECT_DEFENDING_TERRITORY){
   				
   				game.selectAttackTerritories(territory);
   			}
		}
        
        else if (game.getStage() == Game.FORTIFY) {

            //game.setArmiesAwarded(false);
            console.append("Fortify");
            game.setStage(Game.REINFORCE);
            game.nextTurn();
        }

		// forward the request
		forward(request, response);

	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		
		System.out.println("In doPost()");
		
		String operation = (String) request.getParameter("operation");
		if(operation.equalsIgnoreCase("fortify")){
			game.setStage(Game.FORTIFY);
			game.resetAttackingTerritory();
			game.resetDefendingTerritory();
			console.append(game.getCurrentPlayer().getName() + ", Please fortify a territory if you wish.");
		}
		else if(operation.equalsIgnoreCase("selectArmies")){
			String numRollsString=request.getParameter("numArmies");
			int numRolls=Integer.parseInt(numRollsString);
			game.firstDieRoll(numRolls);
		}
		else if(operation.equalsIgnoreCase("keepRolling")){
			game.dieRoll();
		}
		else if(operation.equalsIgnoreCase("stopRolling")){
			game.setAttackStage(Game.SELECT_ATTACKING_TERRITORY);
        	console.append(game.getCurrentPlayer().getName() + ", please select a territory to attack with.");
		}

		forward(request,response);
	}
	/**
	 * This is what should happen when the game is first started i.e. when
	 * players have been chosen and clicked on "start playing!"
	 * 
	 * @param request
	 */
	private void initialGame(HttpServletRequest request) {
		game = (Game) request.getSession().getAttribute("game");
		console=game.getConsole();
		territories = game.getTerritories();
        console.append("Time to pick your territories!");
        console.append(game.getCurrentPlayer().getName()
                + ", please pick a territory!");
	}


	/**
	 * this should happen at the end of every doGet method. Basically, the new
	 * game is passed into the request as an attribute, and the request if
	 * forwarded.
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @throws ServletException
	 */
	private void forward(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		request.setAttribute("game", game);
        request.setAttribute("console", console);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/game.jsp");
		dispatcher.forward(request, response);
	}

}