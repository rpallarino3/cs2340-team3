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

		else if (game.getStage() == Game.INITIAL_REINFORCE) {
			// gets the name of the territory, used in the hash map
			String territoryName = request.getPathInfo();
			territoryName = territoryName.substring(1, territoryName.length());
			Territory territory = territories.get(territoryName);
			
			game.initialReinforce(territory);
			
			if (game.getStage() == Game.INITIAL_REINFORCE) {
				console.append(game.getCurrentPlayer().getName()
						+ ", please reinforce your territories!");
			}
		}

		else if (game.getStage() == Game.ATTACK) {
			console.append(game.getCurrentPlayer().getName()
					+ ", choose which territory to attack!");
		}
        
        else if (game.getStage() == Game.FORTIFY) {
        }

		// forward the request
		forward(request, response);

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
