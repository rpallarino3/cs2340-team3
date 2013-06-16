package cs2340.team3.controller;

import java.io.IOException; 	
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.TreeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs2340.team3.model.Game;
import cs2340.team3.model.Player;

@SuppressWarnings("serial")
@WebServlet(urlPatterns={"/game" })
public class GameServlet extends HttpServlet {

 
	Game game;
	ArrayList<Player> players;
	

    /**
     * Called when HTTP method is GET 
     * (e.g., from an <a href="...">...</a> link).
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("In doGet()");
        game= (Game)request.getSession().getAttribute("game");
        players=game.getPlayers();
        for(int i=0;i<players.size();i++)
        	System.out.println(players.get(i).getName());
        request.setAttribute("game", game);
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/game.jsp");
        dispatcher.forward(request,response);
    }

}
