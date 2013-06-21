<%@ page import="model.*" %>
<%@ page import="java.util.*" %>

<% Game game = (Game) request.getAttribute("game"); %>
<% ArrayList<Player> players= game.getPlayers(); %>  
<%Territory territory; %>
<%String color="black"; %>
<%int armies; %>


<html>
<head>
<STYLE>
A{text-decoration:none; background-color:white;}
</STYLE>
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
				<th> Color </th>
			</tr>

			<% for (int i=0;i<players.size(); i++) { %>
			<tr style="color:<%= players.get(i).getColor() %>">
				<td><% out.println(i+1); %></td>
				<td> <% out.println(players.get(i).getName()); %></td>
				<td> <% out.println(players.get(i).getArmiesAvailable()); %> </td>
				<td> <% out.println(players.get(i).getColor()); %> </td>
			</tr>
			<% } %>
			</table>
		</div>
		
		
		 
		<div class="map">
		
			<% Enumeration keys=game.getTerritories().keys(); 
		   String key;
		   int top,left;%>
		
		<%while(keys.hasMoreElements()) {
		  	key=(String)keys.nextElement();
			territory=game.getTerritories().get(key);
			left=territory.getMarginLeft();
			top=territory.getMarginTop();
		   	if (territory.getPlayerOwned()!=null) {
				color=territory.getPlayerOwned().getColor();
				armies=territory.getNumArmies();
			}else {
				color="black";
				armies=0;
			} %>
		   	<div style="margin-left: <%=left%>px; margin-top: <%=top%>px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/<%=key%>"> 
				<%=territory.getName() %> <%=armies%> </a>
			</div>
		   	
		<% } %>	 
	
		</div>
		
		<div class="console" id="console">
			<%@ include file="/console.jsp" %>
		</div>
	</div>

	



</body>
</html>
