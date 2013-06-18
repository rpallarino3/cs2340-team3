<%@ page import="cs2340.team3.model.Player" %>
<%@ page import="cs2340.team3.model.Game" %>
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
			<!-- North America -->
			<div style="margin-left: 20px; margin-top: 100px;"><a href="/riskT3/game/Alaska">Alaska</a></div>
			<div style="margin-left: 100px; margin-top: 100px;"><a href="/riskT3/game/Northwest">Northwest</a></div>
			<div style="margin-left: 280px; margin-top: 70px;"><a href="/riskT3/game/Greenland">Greenland</a></div>
			<div style="margin-left: 100px; margin-top: 150px;"><a href="/riskT3/game/Alberta">Alberta</a></div>
			<div style="margin-left: 180px; margin-top: 160px;"><a href="/riskT3/game/Ontario">Ontario</a></div>
			<div style="margin-left: 250px; margin-top: 145px;"><a href="/riskT3/game/EasternCanada">Eastern Canada</a></div>
			<div style="margin-left: 100px; margin-top: 200px;"><a href="/riskT3/game/WestUS">West US</a></div>
			<div style="margin-left: 190px; margin-top: 220px;"><a href="/riskT3/game/EastUS">East US</a></div>
			<div style="margin-left: 100px; margin-top: 250px;"><a href="/riskT3/game/CentralAmerica">Central America</a></div>

			<!-- South America -->
			<div style="margin-left: 160px; margin-top: 320px;"><a href="/riskT3/game/Arawak">Arawak</a></div>
			<div style="margin-left: 180px; margin-top: 390px;"><a href="/riskT3/game/Baniva">Baniva</a></div>
			<div style="margin-left: 250px; margin-top: 370px;"><a href="/riskT3/game/Kariri">Kariri</a></div>
			<div style="margin-left: 190px; margin-top: 460px;"><a href="/riskT3/game/Ika">Ika</a></div>

			<!-- Africa -->
			<div style="margin-left: 400px; margin-top: 290px;"><a href="/riskT3/game/Egypt">Egypt</a></div>
			<div style="margin-left: 350px; margin-top: 340px;"><a href="/riskT3/game/Dinka">Dinka</a></div>
			<div style="margin-left: 430px; margin-top: 350px;"><a href="/riskT3/game/Xhosa">Xhosa</a></div>
			<div style="margin-left: 390px; margin-top: 405px;"><a href="/riskT3/game/Ngbandi">Ngbandi</a></div>
			<div style="margin-left: 395px; margin-top: 470px;"><a href="/riskT3/game/Sakalava">Sakalava</a></div>
			
			<!-- Europe -->
			<div style="margin-left: 340px; margin-top: 160px;"><a href="/riskT3/game/Britain">Britain</a></div>
			<div style="margin-left: 330px; margin-top: 220px;"><a href="/riskT3/game/Spain">Spain</a></div>
			<div style="margin-left: 390px; margin-top: 215px;"><a href="/riskT3/game/RomanEmpire">Roman<br />Empire</a></div>
			<div style="margin-left: 380px; margin-top: 180px;"><a href="/riskT3/game/Germany">Germany</a></div>
			<div style="margin-left: 380px; margin-top: 100px;"><a href="/riskT3/game/Sweden">Sweden</a></div>
			<div style="margin-left: 450px; margin-top: 170px;"><a href="/riskT3/game/Ukraine">Ukraine</a></div>

			<!-- Asia -->
			<div style="margin-left: 580px; margin-top: 100px;"><a href="/riskT3/game/Kurdish">Kurdish</a></div>
			<div style="margin-left: 680px; margin-top: 100px;"><a href="/riskT3/game/Pashtun">Pashtun</a></div>
			<div style="margin-left: 760px; margin-top: 100px;"><a href="/riskT3/game/Tuvans">Tuvans</a></div>
			<div style="margin-left: 540px; margin-top: 130px;"><a href="/riskT3/game/Buryats">Buryats</a></div>
			<div style="margin-left: 670px; margin-top: 190px;"><a href="/riskT3/game/Khakas">Khakas</a></div>
			<div style="margin-left: 565px; margin-top: 300px;"><a href="/riskT3/game/India">India</a></div>
			<div style="margin-left: 650px; margin-top: 150px;"><a href="/riskT3/game/Puyuma">Puyuma</a></div>
			
			<div style="margin-left: 630px; margin-top: 250px;"><a href="/riskT3/game/China">China</a></div>
			<div style="margin-left: 520px; margin-top: 200px;"><a href="/riskT3/game/Seediq">Seediq</a></div>
			<div style="margin-left: 630px; margin-top: 315px;"><a href="/riskT3/game/Korea">Korea</a></div>
			<div style="margin-left: 470px; margin-top: 270px;"><a href="/riskT3/game/MiddleEast">Middle<br />East</a></div>

			<!-- Australia -->
			<div style="margin-left: 630px; margin-top: 450px;"><a href="/riskT3/game/WestAustralia">Western<br />Australia</a></div>
			<div style="margin-left: 730px; margin-top: 450px;"><a href="/riskT3/game/EasternAustralia">Eastern<br />Australia</a></div>
			<div style="margin-left: 650px; margin-top: 380px;"><a href="/riskT3/game/Singapore">Singapore</a></div>
			<div style="margin-left: 730px; margin-top: 380px;"><a href="/riskT3/game/NewZealand">New Zealand</a></div>
			
			
			
			
		</div>
	</div>

</body>
</html>