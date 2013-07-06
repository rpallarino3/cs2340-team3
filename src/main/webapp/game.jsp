<%@ page import="model.*" %>
<%@ page import="java.util.*" %>

<% Game game = (Game) request.getAttribute("game"); %>
<% ArrayList<Player> players= game.getPlayers(); %>  
<%Territory territory; %>
<%String color="black"; %>
<%int armies; %>

<!-- <DOCTYPE HTML> -->
<html>
  <head>
    <style>
      body {
        margin: 0px;
        padding: 0px;
      }
    </style>
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
    <div id="map"></div>
    <script src="http://d3lp1msu2r81bx.cloudfront.net/kjs/js/lib/kinetic-v4.5.4.min.js"></script>
    <script defer="defer">	
	
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

	
      function writeMessage(messageLayer, message) {
        var context = messageLayer.getContext();
        messageLayer.clear();
        context.font = '18pt Calibri';
        context.fillStyle = 'black';
        context.fillText(message, 10, 25);
      }
	  
	  //extras
	  var Iceland = [438.333,143.498,438.333,141.332,439.5,139.832,436.667,138.665,434.833,137.665,432.5,137.665,432.5,139.998,430.667,140.582,430.667,139.498,427.833,138.998,427.833,140.582,425.667,140.582,424.375,138.498,422.667,137.665,420.833,139.832,419.5,139.498,417.167,138.665,416.333,139.832,416.333,141.332,415,140.582,415,137.665,414.167,135.998,412.167,135.998,411.333,137.332,411.333,139.748,409.833,138.998,408.833,137.498,407.333,139.748,407.333,142.125,408.833,143.498,406.667,143.498,405.833,144.665,407.167,145.998,409.667,145.998,409.5,146.998,408.167,147.832,408.167,148.665,409.834,148.665,410.667,148.665,409,150.165,409.333,152,412.333,152,415,150.998,416.5,152,418.5,152,419.333,152.833,420.333,153.998,421.833,152.833,422.667,153.998,424.375,152,425.667,152.833,428.167,153.998,429.167,152,431.5,152,433.667,150.832,434.5,148.998,437.333,148.665,439.5,146.998,440.333,146.665,440.333,144.998,439.667,143.665];
	  
	  var stage = new Kinetic.Stage({
        container: 'map',
        width: 1000,
        height: 650
      });
      var shapesLayer = new Kinetic.Layer();
      var messageLayer = new Kinetic.Layer();	   
	  
	for (var i=0; i<territoryShapesKeys.length; i++){
		(function() {
			//territoryShapes[territoryShapesKeys[i]],
			  var currentTerritory = territoryShapesKeys[i];
			  var territoryPoly = new Kinetic.Polygon({
				points: territoryShapes[territoryShapesKeys[i]],
				fill: '<%=color%>',
				stroke: 'black',
				strokeWidth: 1
			  });

			  territoryPoly.on('mouseover', function() {
		//	  	territoryPoly.setScale(1.1);
				territoryPoly.setFill('#8CFFFF');
				territoryPoly.draw();
				stage.draw();
				//writeMessage(messageLayer, ('Mouseover ' + currentTerritory));
			  });
			  territoryPoly.on('mouseout', function() {
		//		territoryPoly.setScale(1);
				territoryPoly.setFill('#DCFFFF');
				territoryPoly.draw();
				stage.draw();
				writeMessage(messageLayer, ('Mouseout <%=territory.getName() %>'));
			  });
			  territoryPoly.on('mousedown', function() {
				document.location.href = ('/riskT3/game/<%=key%>');
		//		writeMessage(messageLayer, ('Mousedown '+ currentTerritory));
			  });
			  territoryPoly.on('mouseup', function() {
		///		writeMessage(messageLayer, ('Mouseup '+ currentTerritory));
			  });
			  
			  shapesLayer.add(territoryPoly);
			  }())
	}
	
      stage.add(shapesLayer);
      stage.add(messageLayer);
	  
		   	
		<% } %>	 

    </script>
	
		<div class="console" id="console">
			<%@ include file="/console.jsp" %>
		</div>
	</div>
  </body>
</html>