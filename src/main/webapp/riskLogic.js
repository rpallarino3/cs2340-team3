var Risk = {
    riskData : null,
    Territories : {},
    
    stage : null,
    mapLayer : null,
    labelLayer: null,
    messageLayer: null,

    messageXOffSet: 30,
    messageYOffSet: 25,

    territoryClick: function(terriName) {
        $.get(".api/game/"+terriName, function(data, status) {
            Risk.riskData = data;
            Risk.redrawTerritory(terriName);
            Risk.updateRiskState();
        });
    },

    setUpTerritories: function() {
        var territoryInfo = Risk.riskData.territories;
        for(var i in territoryInfo) {
            var currentTerri = territoryInfo[i];
            var territoryPoly = new Kinetic.Polygon({
                points: territoryShapes[currentTerri.name],
                fill: currentTerri.color,
                stroke: '#1F1A1F',
                strokeWidth: 1,
                opacity: 0.5,
                name: currentTerri.name
            });

            var territoryLabel = new Kinetic.Label({
                x: currentTerri.offsetX,
                y: currentTerri.offsetY,
                opacity: 1 
            });

            territoryLabel.add(new Kinetic.Text({
                text: currentTerri.armies,
                fontFamily: 'Calibri',
                fontSize: 18,
                padding: 5,
                fill: 'black',
                scale: 0.8
            }));

            Risk.Territories[currentTerri.name]= {
                name: currentTerri.name,
                poly: territoryPoly,
                label: territoryLabel,
                owner: currentTerri.owner,
                color: currentTerri.color,
                armies: currentTerri.armies
            };
        }
    },
    init: function() {
        Risk.setUpTerritories();

        Risk.stage = new Kinetic.Stage({
            container: 'mapDiv',
            width: 1000,
            height: 650
        });

        Risk.mapLayer = new Kinetic.Layer();
        Risk.labelLayer = new Kinetic.Layer();
        Risk.messageLayer = new Kinetic.Layer();
        
        Risk.mapLayer.on('mouseover', function(evt) {
            var poly = evt.targetNode;
            var name = poly.getName();
            var territory = Risk.Territories[name];
            console.log(Risk.riskData.current.player);
            if(Risk.riskData.current.stage==1) {
                if(territory.owner==Risk.riskData.current.player){
                    poly.setFill("#C6F7F1");
                    Risk.mapLayer.draw();
                    document.body.style.cursor = 'pointer';
                    Risk.writeMessage(name);
                }
            }else {
                poly.setFill("#C6F7F1");
                Risk.mapLayer.draw();
                document.body.style.cursor = 'pointer';
                Risk.writeMessage(name);
            }
        });
        Risk.mapLayer.on('mouseout', function(evt) {
            var poly = evt.targetNode;
            poly.setFill(Risk.Territories[poly.getName()].color);
            Risk.mapLayer.draw();
            document.body.style.cursor = 'default';
            Risk.messageLayer.clear();
        });

        Risk.mapLayer.on('click', function(evt) {
            var poly = evt.targetNode;
            var name = poly.getName().replace(" ","");
            var territory = Risk.Territories[name];
            $.get("./api/game/"+name, function(data, status) {
                if(Risk.riskData.current.stage==1) {//init rein
                    if(territory.owner==Risk.riskData.current.player) 
                        $('#postPopup').fadeIn("fast");
                } else {
                    if(data==null) return;
                    Risk.riskData = data;
                    Risk.redrawTerritories();
                    Risk.updateRiskState();
                }
            });
        });

        Risk.stage.add(Risk.labelLayer);
        Risk.stage.add(Risk.mapLayer);
        Risk.stage.add(Risk.messageLayer);
    },
    drawTerritories : function() {
        for(var key in Risk.Territories) {
            var territory = Risk.Territories[key];
            Risk.mapLayer.add(territory.poly);
            Risk.labelLayer.add(territory.label);
        }
        Risk.stage.draw();
    },
    redrawTerritory: function(terriName) {
    },
    redrawTerritories : function() {
        if(Risk.mapLayer==null) return;
        var territoryInfo = Risk.riskData.territories;
        for(var i in territoryInfo) {
            var territory = Risk.Territories[territoryInfo[i].name];
            territory.color=territoryInfo[i].color;
            territory.armies=territoryInfo[i].armies;
            territory.poly.setFill(territoryInfo[i].color);
            territory.label.getText().setText(territoryInfo[i].armies);
        }
        Risk.stage.draw();
    },
    updateRiskState : function() {
	    $("#playerTable").find("tr:gt(0)").remove();
        var playersInfo = Risk.riskData.players;
        for(var i in playersInfo) {
            if(playersInfo[i].name==Risk.riskData.current.player)
                $("#playerTable tr:last").after("<tr style=\"color:"+playersInfo[i].color+"\"><td>"+ (i*1+1) + "</td><td>"+ playersInfo[i].name + "</td><td>"+ playersInfo[i].armies + "</td><td>\<-</td></tr>");    
            else
                $("#playerTable tr:last").after("<tr style=\"color:"+playersInfo[i].color+"\"><td>"+ (i*1+1) + "</td><td>"+ playersInfo[i].name + "</td><td>"+ playersInfo[i].armies + "</td></tr>");
        }
    },
    writeMessage : function(message) {
        if(Risk.messageLayer==null) return;
        var context = Risk.messageLayer.getContext();
        Risk.messageLayer.clear();
        context.font = '18pt Calibri';
        context.fillStyle = '#1F1A1F';
        context.fillText(message, Risk.messageXOffSet, Risk.messageYOffSet);
    }
}
