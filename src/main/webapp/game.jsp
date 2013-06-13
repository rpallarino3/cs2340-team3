<%@ page import="cs2340.team3.model.Player" %>
<%@ page import="cs2340.team3.model.Game" %>
<%@ page import="java.util.*" %>

<% Game game = (Game) request.getAttribute("game"); %>
<% ArrayList<Player> players= game.getPlayers(); %>  
<html>
<head>
<title>Divide and Conquer</title>
</head>


<table border = "1">
<tr>
<th> Turn Order </th>
<th> Player </th>
<th> # of Armies </th>

<% for (int i=0;i<players.size(); i++) { %>
<tr>
<td><% out.println(i+1); %></td>
<td> <% out.println(players.get(i).getName()); %></td>
<td> <% out.println(players.get(i).getArmiesAvailable()); %> </td>
</tr>
<% } %>


</table>
</html>