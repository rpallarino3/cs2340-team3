var Risk = {
    riskData : null,
    Territories : {},
    
    stage : null,
    mapLayer : null,
    labelLayer: null,
    messageLayer: null,

    messageXOffSet: 30,
    messageYOffSet: 25,

    setUpTerritories: function() {
        for(id in territoryNames) {

        }
    },
    drawTerritories : function() {
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
