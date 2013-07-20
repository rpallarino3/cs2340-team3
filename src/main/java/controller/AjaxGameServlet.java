package controller;

import java.io.IOException;   
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.util.Hashtable;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.*;

import model.*;
import etc.*;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = { "/api/game/*", "/api/player/*" })
public class AjaxGameServlet extends HttpServlet {

	private Game game;
    private RiskStatus console;
	private Hashtable<String, Territory> territories;

	/**
	 * Called when HTTP method is GET (e.g., from an <a href="...">...</a>
	 * link).
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
            HttpSession session = request.getSession(true);
            Gson gson = new Gson();
            response.setContentType("application/json");
            PrintWriter responseWriter = response.getWriter();
            String servletPath = request.getServletPath();
            String apiFunc = request.getPathInfo().replace("/","");
            Game game = (Game) session.getAttribute("game");
            if(!servletPath.contains("api/game"))
                return;
            if(apiFunc.contains("status")) {
                responseWriter.write("{\"isNew\":"+gson.toJson(session.isNew())+",\"gameCreated\":"+gson.toJson(game!=null)+"}");
                return;
            }
            if(game==null)
                return;
            int currentStage = game.getStage();
            if(currentStage == Game.PICK) {
                String territoryName = apiFunc;
                Territory territory = territories.get(territoryName);
                game.pickTerritories(territory);
                returnRiskUpdates(response, game);
            }
	}

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
            HttpSession session = request.getSession(true);
            String servletPath = request.getServletPath();
            String apiFunc = request.getPathInfo();
            //if(apiFunc
            String postData = request.getParameter("data");
            JsonParser parser = new JsonParser();
            JsonArray playersJson = parser.parse(postData).getAsJsonArray();
            ArrayList<Player> players = new ArrayList<Player>();
            for(JsonElement playerJson : playersJson) {
                String name = playerJson.getAsJsonObject().get("name").getAsString();
                if(name==null || name.isEmpty())
                    continue;
                players.add(new Player(name));
            }
            if(players.size()<3) return;
            int armySize = 40 - (players.size() - 2) * 5;
            for(Player player : players) {
                player.changeNumArmies(armySize);
            }
            game = new Game(players);
            session.setAttribute("game", game);
            returnRiskUpdates(response, game);
    }

    private String readPostRequestDataAsString(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            BufferedReader reader = request.getReader();
            while((line = reader.readLine()) != null)
                sb.append(line);
        } catch (Exception e) { }
        return sb.toString();
    }

    private void returnRiskUpdates(HttpServletResponse response, Game game) {
        JsonObject reply = AjaxSupport.makeReplyJsonPackage(game.getPlayers(), game.getTerritoriesAsArrayList(), game);
        System.out.println("pre json");
        Gson gson = new Gson();
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch(Exception e) {
            System.out.println("error getting response writer!");
        }
        if(writer==null) return;
        response.setContentType("application/json");
        writer.write(gson.toJson(reply));
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
}
