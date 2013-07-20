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
        }
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
            });

            territoryPoly.on('mouseover', function(){
                territoryPoly.setFill('#C6F7F1');
                territoryPoly.draw();
                writeMessage(currentTerri.name);
            });

            var territoryLabel = new Kinetic.Label({
                x: currentTerri.offsetX,
                y: currentTerri.offsetY,
                opacity: 0.75
            });

            territoryLabel.add(new Kinetic.Text({
                text: currentTerri.armies,
                fontFamily: 'Calibri',
                fontSize: 18,
                padding: 5,
                fill: 'black',
                scale: 0.8
            }));

            territoryPoly.on('click', territoryClick(currentTerri.name));

            Risk.Territories[currentTerri.name]= {
                name: currentTerri.name,
                poly: territoryPoly,
                label: territoryLabel,
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
        
        Risk.stage.add(Risk.mapLayer);
        Risk.stage.add(Risk.labelLayer);
        Risk.stage.add(Risk.messageLayer);
    },
    drawTerritories : function() {
        for(var key in Risk.Territories) {
            var territory = Risk.Territories[key];
            Risk.mapLayer.add(territory.poly);
            Risk.labelLayer.add(
        };
        Risk.stage.draw();
    },
    redrawTerritory: function(terriName) {
    },
    redrawTerritories : function() {
    },
    updateRiskState : function() {
    },
    writeMessage : function(message) {
        if(messageLayer==null) return;
        var context = messageLayer.getContext();
        messageLayer.clear();
        context.font = '18pt Calibri';
        context.fillStyle = '#1F1A1F';
        context.fillText(message, messageXOffSet, messageYOffSet);
    }
}
