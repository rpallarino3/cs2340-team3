package controller;

import java.io.IOException; 	
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Game;
import model.Player;

@SuppressWarnings("serial")
@WebServlet(urlPatterns={
        "/players", // GET
        "/create", // POST 
        "/update/*", // PUT
        "/delete/*" // DELETE
    })
public class PlayerServlet extends HttpServlet {

 
	ArrayList<Player> players = new ArrayList<>();
	Game currentGame = null;
	
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
        System.out.println("In doPost()");
        String operation = (String) request.getParameter("operation");

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
    	if(currentGame==null) currentGame = new Game(players);
    	getServletContext().setAttribute("game",currentGame);
    	request.setAttribute("game",currentGame);
    	request.getSession().setAttribute("game",currentGame);
    	response.sendRedirect("/riskT3/game");
    }

}
