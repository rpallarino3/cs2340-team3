<%@ page import="model.Player" %>
<%@ page import="model.Game" %>
<%@ page import="java.util.*" %>

<% Game game = (Game) request.getAttribute("game"); %>
<% ArrayList<Player> players= game.getPlayers(); %>  
<html>
<head>
<link rel="stylesheet" type="text/css" href="/riskT3/style.css">
<title>Divide and Conquer</title>
</head>


<body class="main">
	<div class="container">
		<div class="left">
			<table border = "1">
			<tr>
				<th> Turn Order </th>
				<th> Player </th>
				<th> # of Armies </th>
			</tr>

			<% for (int i=0;i<players.size(); i++) { %>
			<tr>
				<td><% out.println(i+1); %></td>
				<td> <% out.println(players.get(i).getName()); %></td>
				<td> <% out.println(players.get(i).getArmiesAvailable()); %> </td>
			</tr>
			<% } %>
			</table>
		</div>

		<div class="map">
			<div style="margin-left: 20px; margin-top: 100px;"><a href="/riskT3/game/Alaska">Alaska</a></div>
			<div style="margin-left: 100px; margin-top: 100px;"><a href="/riskT3/game/Northwest">Northwest</a></div>
			<div style="margin-left: 250px; margin-top: 80px;"><a href="/riskT3/game/Greenland">Greenland</a></div>
			
			<div style="margin-left: 100px; margin-top: 150px;"><a href="/riskT3/game/Alberta">Alberta</a></div>
			<div style="margin-left: 180px; margin-top: 160px;"><a href="/riskT3/game/Ontario">Ontario</a></div>
			<div style="margin-left: 250px; margin-top: 145px;"><a href="/riskT3/game/EasternCanada">Eastern Canada</a></div>

			<div style="margin-left: 100px; margin-top: 200px;"><a href="/riskT3/game/WestUS">West US</a></div>
			<div style="margin-left: 190px; margin-top: 220px;"><a href="/riskT3/game/EastUS">East US</a></div>

			<div style="margin-left: 100px; margin-top: 250px;"><a href="/riskT3/game/CentralAmerica">Central America</a></div>
		</div>
	</div>

</body>
</html>