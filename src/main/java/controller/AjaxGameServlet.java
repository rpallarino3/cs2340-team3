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
            game = (Game) session.getAttribute("game");
            if(!servletPath.contains("api/game"))
                return;
            if(apiFunc.contains("status")) {
                responseWriter.write("{\"isNew\":"+gson.toJson(session.isNew())+",\"gameCreated\":"+gson.toJson(game!=null)+"}");
                return;
            }
            else if(apiFunc.contains("risk")){
                if(game!=null)
                    returnRiskUpdates(response, game);
                return;
            }
            else {
                String territoryName = apiFunc;
                Territory territory = game.getTerritories().get(territoryName);
                if(game==null) {
                    try{
                        response.sendError(HttpServletResponse.SC_FORBIDDEN);
                    }catch(Exception e){}
                return;
                }
                //initial picking territory stage
                else if(game.getStage() == Game.PICK) {
                    game.pickTerritories(territory);
                    if(game.getStage() == Game.PICK) {
                        game.getConsole().append(game.getCurrentPlayer().getName() + ", please pick a territory!");
                    }
                }
                //initial territory reinforcing stage
                else if(game.getStage() == Game.INITIAL_REINFORCE) {
                    System.out.println("i'm in");
                    if(game.checkReadyToAddArmies()) {
                        game.initialReinforce(game.getCurrentTerritory());
                    } else {
                       game.initialReinforce(territory);
                    }
                }
                //reinforcing stage
                else if(game.getStage() == Game.REINFORCE) {
                    game.reinforce(territory);
                }
                //attacking stage
                else if(game.getStage() == Game.ATTACK) {
                    if(game.getAttackStage()==Game.SELECT_ATTACKING_TERRITORY || game.getAttackStage()==Game.SELECT_DEFENDING_TERRITORY) {
                        game.selectAttackTerritories(territory);
                    }
                }
                //XXX not done - fortifying stage
                else if(game.getStage() == Game.FORTIFY) {
                    
                }
            }
            returnRiskUpdates(response, game);
	}

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
            String servletPath = request.getServletPath();
            if(servletPath.contains("api/player"))
                handlePostPlayers(request, response);
            else
                handlePostTerritory(request, response);
    }
    
    private void handlePostTerritory(HttpServletRequest request, HttpServletResponse response) {
            HttpSession session = request.getSession(true);
            game = (Game) session.getAttribute("game");
            System.out.println("in post");
            if(game==null) return;
            //initial reinforcement input
            if(game.getStage()==Game.INITIAL_REINFORCE) {
                System.out.println("handling Init");
                int numArmiesToAdd = game.getNumArmiesToAdd();
                String userInput = request.getParameter("input");
                System.out.println(userInput);
                try {
                    numArmiesToAdd = Integer.parseInt(userInput);
                    if(userInput!=null && numArmiesToAdd > 0 && numArmiesToAdd <= game.getCurrentPlayer().getArmiesAvailable() ){
                        game.setNumArmiesToAdd(numArmiesToAdd);
                        System.out.println("forwarding");
                        doGet(request, response);
                    }
                    else {
                        System.out.println("error at input");
                        game.getConsole().append("Please type in a valid number of armies");
                    }
                }catch(Exception e) {
                    System.out.println("error in try");
                    game.getConsole().append("Please type in a valid number of armies");
                }
            }
    }
    
    private void handlePostPlayers(HttpServletRequest request, HttpServletResponse response) {
            HttpSession session = request.getSession(true);
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
            if(players.size()<3) {
                try{
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST);
                }catch(Exception e) {}
                return;
            }
            int armySize = 40 - (players.size() - 2) * 5;
            for(Player player : players) {
                player.changeNumArmies(armySize);
            }
            game = new Game(players);
            game.getConsole().append(game.getCurrentPlayer().getName() + ", please pick a territory!");
            session.setMaxInactiveInterval(-1);//session time-out never
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
        Gson gson = new Gson();
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
        } catch(Exception e) {
            System.out.println("error getting response writer!");
            return;
        }
        response.setContentType("application/json");
        writer.write(gson.toJson(reply));
    }
}
