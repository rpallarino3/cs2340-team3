<%@ page import="model.Player" %>
<%@ page import="java.util.*" %>

<% ArrayList<Player> players = 
   (ArrayList<Player>) request.getAttribute("players"); %>

<html>
<head>
<title>Game of Risk</title>
<link rel="stylesheet" type="text/css" href="/riskT3/style.css">
</head>
<body>
<div class="startContainer">

<h2>Select Players (3-6)</h2>

<table>
<tr>
<th>Player Name</th>
</tr>
<% for (Player player: players) { %>
<% int id=players.indexOf(player); %>
<tr>
<form action="/riskT3/update/<%= id %>" method="POST">
  <!-- hidden operation element to simulate HTTP PUT method in server -->
  <input type="hidden" name="operation" value="PUT"/>
  <td><input type="text" name="name" value="<%= player.getName() %>"/></td>
  <td><input type="submit" value="Update"/></td>
</form>
<td>
  <form action="/riskT3/delete/<%= id %>" method="POST">
  <!-- hidden operation element to simulate HTTP DELETE method in server -->
    <input type="hidden" name="operation" value="DELETE"/>
    <input type="submit" value="Delete"/>
  </form>
 </td>
</tr>
<% } %>
<tr>
<%if (players.size() < 6) { %>
<form action="/riskT3/create" method="POST">
  <td><input type="text" name="name"/></td>
  <td><input type="submit" value="Add"/></td>
</form>
<% } %>
<td></td> <!-- empty cell to align with previous cells -->
</tr>

</table>

<% if(players.size()>=3) { %>
<form action="/riskT3/players" method="POST">
	<input type="hidden" name="operation" value="submit"/>
 	<input type="submit" value="Start Playing!"/>
</form>
<% } %>

</div>

</body>
</html>