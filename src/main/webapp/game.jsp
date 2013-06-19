<<<<<<< HEAD
<%@ page import="model.Player" %>
<%@ page import="model.Game" %>
=======
<%@ page import="model.*" %>
>>>>>>> 1edb66f5a62c9e45046df84f20ceeb451fb05971
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
			<!-- North America -->
			<%if(game.getTerritories().get("Alaska").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Alaska");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 20px; margin-top: 100px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Alaska">Alaska <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Northwest").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Northwest");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 100px; margin-top: 100px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Northwest">Northwest <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Greenland").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Greenland");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 280px; margin-top: 70px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Greenland">Greenland <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Alberta").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Alberta");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 100px; margin-top: 150px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Alberta">Alberta <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Ontario").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Ontario");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 180px; margin-top: 160px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Ontario">Ontario <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("EasternCanada").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("EasternCanada");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 250px; margin-top: 145px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/EasternCanada">Eastern Canada <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("WestUS").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("WestUS");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 100px; margin-top: 200px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/WestUS">West US <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("EastUS").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("EastUS");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 190px; margin-top: 220px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/EastUS">East US <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("CentralAmerica").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("CentralAmerica");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 100px; margin-top: 250px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/CentralAmerica">Central America <%=armies%> </a>
			</div>

			<!-- South America -->
			<%if(game.getTerritories().get("Arawak").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Arawak");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 160px; margin-top: 320px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Arawak">Arawak <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Baniva").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Baniva");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 180px; margin-top: 390px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Baniva">Baniva <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Kariri").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Kariri");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 250px; margin-top: 370px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Kariri">Kariri <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Ika").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Ika");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 190px; margin-top: 460px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Ika">Ika <%=armies%> </a>
			</div>

			<!-- Africa -->
			<%if(game.getTerritories().get("Egypt").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Egypt");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 400px; margin-top: 290px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Egypt">Egypt <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Dinka").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Dinka");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 350px; margin-top: 340px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Dinka">Dinka <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Xhosa").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Xhosa");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 430px; margin-top: 350px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Xhosa">Xhosa <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Ngbandi").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Ngbandi");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 390px; margin-top: 405px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Ngbandi">Ngbandi <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Sakalava").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Sakalava");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 395px; margin-top: 470px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Sakalava">Sakalava <%=armies%> </a>
			</div>
			
			<!-- Europe -->
			<%if(game.getTerritories().get("Britain").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Britain");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 340px; margin-top: 160px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Britain">Britain <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Spain").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Spain");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 330px; margin-top: 220px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Spain">Spain <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("RomanEmpire").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("RomanEmpire");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 390px; margin-top: 215px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/RomanEmpire">Roman<br />Empire <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Germany").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Germany");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 380px; margin-top: 180px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Germany">Germany <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Sweden").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Sweden");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 380px; margin-top: 100px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Sweden">Sweden <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Ukraine").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Ukraine");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 450px; margin-top: 170px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Ukraine">Ukraine <%=armies%> </a>
			</div>

			<!-- Asia -->
			<%if(game.getTerritories().get("Kurdish").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Kurdish");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 580px; margin-top: 100px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Kurdish">Kurdish <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Pashtun").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Pashtun");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 680px; margin-top: 100px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Pashtun">Pashtun <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Tuvans").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Tuvans");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 760px; margin-top: 100px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Tuvans">Tuvans <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Buryats").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Buryats");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 540px; margin-top: 130px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Buryats">Buryats <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Khakas").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Khakas");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 670px; margin-top: 190px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Khakas">Khakas <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("India").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("India");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 565px; margin-top: 300px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/India">India <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Puyuma").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Puyuma");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 650px; margin-top: 150px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Puyuma">Puyuma <%=armies%> </a>
			</div>
			
			 <%if(game.getTerritories().get("China").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("China");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 630px; margin-top: 250px; color: <%=color%>">
			<a style="color:<%=color%>" href="/riskT3/game/China">China <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Seediq").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Seediq");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 520px; margin-top: 200px; color: <%=color%>">
			<a style="color:<%=color%>" href="/riskT3/game/Seediq">Seediq <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Korea").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Korea");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 630px; margin-top: 315px; color: <%=color%>">
			<a style="color:<%=color%>" href="/riskT3/game/Korea">Korea <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("MiddleEast").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("MiddleEast");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 470px; margin-top: 270px; color: <%=color%>">
			<a style="color:<%=color%>" href="/riskT3/game/MiddleEast">Middle<br />East <%=armies%> </a>
			</div>

			<!-- Australia -->
			<%if(game.getTerritories().get("WesternAustralia").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("WesternAustralia");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 630px; margin-top: 450px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/WesternAustralia">Western<br />Australia <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("EasternAustralia").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("EasternAustralia");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 730px; margin-top: 450px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/EasternAustralia">Eastern<br />Australia <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("Singapore").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("Singapore");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 650px; margin-top: 380px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/Singapore">Singapore <%=armies%> </a>
			</div>
			
			<%if(game.getTerritories().get("NewZealand").getPlayerOwned()!=null) {
				territory=game.getTerritories().get("NewZealand");
				color=territory.getPlayerOwned().getColor(); 
				armies=territory.getNumArmies();
			 }else {color="black"; armies=0;} %>
			<div style="margin-left: 730px; margin-top: 380px; color: <%=color%>">
				<a style="color:<%=color%>" href="/riskT3/game/NewZealand">New Zealand <%=armies%> </a>
			</div>
			
			
			
			
		</div>
	</div>

</body>
</html>