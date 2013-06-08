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
@WebServlet(urlPatterns={
        "/players", // GET
        "/create", // POST 
        "/update/*", // PUT
        "/delete/*", // DELETE
    })
public class PlayerServlet extends HttpServlet {

 
	ArrayList<Player> players = new ArrayList<>();
	Game game = null;
	
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("In doPost()");
        // Handle the hidden HTML form field that simulates
        // HTTP PUT and DELETE methods.
        String operation = (String) request.getParameter("operation");
        // If form didn't contain an operation field and
        // we're in doPost(), the operation is POST
        if (null == operation) operation = "POST";
        System.out.println("operation is " + operation);
        if (operation.equalsIgnoreCase("PUT")) {
            System.out.println("Delegating to doPut().");
            doPut(request, response);
        } else if (operation.equalsIgnoreCase("DELETE")) {
            System.out.println("Delegating to doDelete().");
            doDelete(request, response);
        } 
        else if(operation.equalsIgnoreCase("submit")) {
        	
        	doSumbit(request, response);
        }
        else {
            String name = request.getParameter("name");
            
            if(players.size()<6 && !(name.isEmpty())) {
            	players.add(players.size(), new Player(name));
            }
            
            for(int i=0; i<players.size(); i++) 
			System.out.println(players.get(i).getName());
            
            
            request.setAttribute("players", players);
            RequestDispatcher dispatcher = 
                getServletContext().getRequestDispatcher("/players.jsp");
            dispatcher.forward(request,response);
        }
    } 	

    /**
     * Called when HTTP method is GET 
     * (e.g., from an <a href="...">...</a> link).
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("In doGet()");
        request.setAttribute("players", players);
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/players.jsp");
        dispatcher.forward(request,response);
    }

    protected void doPut(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("In doPut()");
        String name = (String) request.getParameter("name");
        int id = getId(request);
        players.set(id, new Player(name));
        request.setAttribute("players", players);
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/players.jsp");
        dispatcher.forward(request,response);
    }

    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("In doDelete()");
        int id = getId(request);
        players.remove(id);
        request.setAttribute("players", players);
        RequestDispatcher dispatcher = 
            getServletContext().getRequestDispatcher("/players.jsp");
        dispatcher.forward(request,response);
    }

    private int getId(HttpServletRequest request) {
        String uri = request.getPathInfo();
        // Strip off the leading slash, e.g. "/2" becomes "2"
        String idStr = uri.substring(1, uri.length()); 
        return Integer.parseInt(idStr);
    }
    
    protected void doSumbit(HttpServletRequest request,
    						HttpServletResponse response)
    		throws IOException, ServletException {
    	
    	int armySize=40- (players.size()-2) * 5;
    	for(int i=0; i<players.size(); i++) {
    		players.get(i).changeNumArmies(armySize);
    	}
    	if(game==null) game = new Game(players);
    	getServletContext().setAttribute("game",game);
    	request.setAttribute("game",game);
    	request.getSession().setAttribute("game",game);
    	//RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/game.jsp");
    	//dispatcher.forward(request, response);
    	response.sendRedirect("/riskT3/game");
    }

}
