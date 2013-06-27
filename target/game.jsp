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
		
			<script src="http://d3lp1msu2r81bx.cloudfront.net/kjs/js/lib/kinetic-v4.5.4.min.js"></script>
			<script defer="defer">
			  function writeMessage(messageLayer, message) {
				var context = messageLayer.getContext();
				messageLayer.clear();
				context.font = '18pt Calibri';
				context.fillStyle = 'black';
				context.fillText(message, 10, 25);
			  }
			  var stage = new Kinetic.Stage({
				container: 'container',
				width: 1000,
				height: 1000
			  });
			  var shapesLayer = new Kinetic.Layer();
			  var messageLayer = new Kinetic.Layer();
			  
			//westUS
			  var westUS = new Kinetic.Polygon({
				points: [230.375,204.125,228.25,243.75,211.125,244.5,209.125,254,202.189,253.989,201.667,257,200,259.333,200,264.333,
		197.875,267.375,193,268.667,193.163,270.805,191.333,272,194.5,275.25,194.25,279.333,187.667,279.333,186.184,275.626,
		183.667,274.333,182.75,271.75,181.167,271.167,178.667,272.667,170.5,273,168,274.333,165.667,274.333,162,271,
		160.25,268,147.833,267.833,145,261.667,142.917,258.417,140.333,253.333,139.608,247.258,138.083,244.167,138.083,238.25,
		137.667,231,139.083,222.417,139.75,215.25,141.292,210.833,140.542,208.542,139.125,207.375,134.125,200.583,134.292,198.917,
		136.125,199.167,140.333,204.208,141.417,207.292,140.792,208.458,141.5,210.583,144.167,210,144.917,208.167,142.083,201.667],
				fill: '#DCFFFF',
				stroke: 'black',
				strokeWidth: 1
			  });

			  westUS.on('mouseover', function() {
		//	  	westUS.setScale(1.1);
				westUS.setFill('#8CFFFF');
				westUS.draw();
				stage.draw();
				writeMessage(messageLayer, 'Mouseover westUS');
			  });
			  westUS.on('mouseout', function() {
		//		westUS.setScale(1);
				westUS.setFill('#DCFFFF');
				westUS.draw();
				stage.draw();
				writeMessage(messageLayer, 'Mouseout westUS');
			  });
			  westUS.on('mousedown', function() {
				document.location.href = "http://www.w3schools.com";
				writeMessage(messageLayer, 'Mousedown westUS');
			  });
			  westUS.on('mouseup', function() {
				writeMessage(messageLayer, 'Mouseup westUS');
			  });

			  shapesLayer.add(westUS);
			  
			  stage.add(shapesLayer);
			  stage.add(messageLayer);
			</script>
	
		</div>
		
		<div class="console" id="console">
			<%@ include file="/console.jsp" %>
		</div>
	</div>

	



</body>
</html>
