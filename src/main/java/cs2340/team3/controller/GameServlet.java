package cs2340.team3.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.TreeMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cs2340.team3.model.Player;

@WebServlet(urlPatterns={
        "/players", // GET
        "/create", // POST 
        "/update/*", // PUT
        "/delete/*" // DELETE
    })
public class GameServlet extends HttpServlet {

    TreeMap<Integer, Player> players = new TreeMap<>();

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
        } else {
            String name = request.getParameter("name");
            if(players.size()<6)
            	players.put(players.size(), new Player(name));
            
            System.out.println(players.get(0));
            System.out.println(players.get(1));
            System.out.println(players.get(2));
            System.out.println(players.get(3));
            System.out.println(players.get(4));
            System.out.println(players.get(5));
            System.out.println(players.get(6));
            System.out.println(players.get(7));
            
            
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
        players.put(id, new Player(name));
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

}
