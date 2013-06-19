package java.controller;

import java.io.IOException; 
import java.util.ArrayList;
import java.util.Hashtable;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.model.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/game", "/game/*" })
public class GameServlet extends HttpServlet {

	private Game game;
	private ArrayList<Player> players;
	private int territoriesLeft = Game.TERRITORIES;
	private Hashtable<String, Territory> territories;
	private int playersReinforcedCompletely = 0;

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
			pickTerritories(request);
			if (game.getStage() == Game.INITIAL_REINFORCE)
				System.out.println(game.getCurrentPlayer().getName()
						+ ", please pick a territory!");
		}

		else if (game.getStage() == Game.INITIAL_REINFORCE) {
			initialReinforce(request);
			if (game.getStage() == Game.INITIAL_REINFORCE)
				System.out.println(game.getCurrentPlayer().getName()
						+ ", please reinforce your territories!");
		}

		else if (game.getStage() == Game.ATTACK) {
			System.out.println(game.getCurrentPlayer().getName()
					+ ", choose which territory to attack!");
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
		players = game.getPlayers();
		territories = game.getTerritories();
		System.out.println("Time to pick your territories!");
		System.out.println(game.getCurrentPlayer().getName()
				+ ", please pick a territory!");
	}

	/**
	 * This should be looped through until there are no more territories left to
	 * assign. This allows a player to click on a territory, and choose that
	 * territory as their own.
	 * 
	 * @param request
	 */
	private void pickTerritories(HttpServletRequest request) {
		// gets the name of the territory, used in the hash map
		String territoryName = request.getPathInfo();
		territoryName = territoryName.substring(1, territoryName.length());
		Territory territory = territories.get(territoryName);

		// if the territory doesn't belong to anyone
		if (territory.getPlayerOwned() == null) {

			territory.setPlayerOwned(game.getCurrentPlayer());
			territory.changeNumArmies(1);
			territory.getPlayerOwned().changeNumArmies(-1);
			territory.getPlayerOwned().changeNumTerritories(1);

			System.out.println(territoryName + " was taken by "
					+ game.getCurrentPlayer().getName() + "!");
			game.nextTurn();
			territoriesLeft--;
			System.out.println("There are " + territoriesLeft
					+ " territories left.");
		}

		// if there are no more territories to assign
		if (territoriesLeft == 0) {
			game.setStage(Game.INITIAL_REINFORCE);
			game.resetTurn();

			// this is what would be printed to the console once it's created
			System.out.println("All territories have been conquered! "
					+ game.getCurrentPlayer().getName()
					+ ", please reinforce your armies!");
		}
	}

	/**
	 * This should be looped through until all of the starting armies have been
	 * place on territories owned by the player placing the army.
	 * 
	 * @param request
	 */
	private void initialReinforce(HttpServletRequest request) {
		if (game.getCurrentPlayer().getArmiesAvailable() != 0) {
			String territoryName = request.getPathInfo();
			territoryName = territoryName.substring(1, territoryName.length());
			Territory territory = territories.get(territoryName);
			if (territory.getPlayerOwned() == game.getCurrentPlayer()) {
				territory.changeNumArmies(1);
				territory.getPlayerOwned().changeNumArmies(-1);
				System.out.println(game.getCurrentPlayer().getName()
						+ ", has added an army to " + territory.getName());
			} else {
				System.out.println("You do not own that territory.");
				return;
			}
			if (game.getCurrentPlayer().getArmiesAvailable() == 0
					&& game.getCurrentPlayer().getArmiesDistributed() == false) {
				game.getCurrentPlayer().setArmiesDistributed(true);
				playersReinforcedCompletely++;
				System.out.println(playersReinforcedCompletely);
			}
			if (playersReinforcedCompletely == game.getPlayers().size()) {
				game.setStage(Game.ATTACK);
				game.resetTurn();
				System.out.println("All armies have been distributed. "
						+ game.getCurrentPlayer().getName()
						+ ", pick a territory to attack!");
			}
			game.nextTurn();
		}
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
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher("/game.jsp");
		dispatcher.forward(request, response);
	}

}
