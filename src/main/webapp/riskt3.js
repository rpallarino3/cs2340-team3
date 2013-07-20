/*
 * This file uses jquery to process ajax. 
 * USE: To include this file to any page, first include jquery.js
 * DEPENDENCY: jquery.js and kinetic.js
 */
var sessionIsNew = false;
var gameCreated = false;
var showGame = function() {
    $("#createGameDiv").hide();
    $("#gameplayDiv").show();
    //$.get(".api/game/risk", function(data, status) {
          //Risk.riskData = data;
          //Risk.drawTerritories();
          //Risk.updateRiskState();
    //}); 
}
var hideGame = function() {
    $("#createGameDiv").show();
    $("#gameplayDiv").hide();
}
$(document).ready(function() {
    $.get("./api/game/status", function(data, status) {
        sessionIsNew = data.isNew;
        gameCreated = data.gameCreated;
        if(gameCreated) { //game already created, hide the createGameDiv
            showGame();
        } else {
            hideGame();
        }
    });
    $("#startGame").click(function() {
        var players = [];
        $("#createPlayerForm input").each(function() {
            players.push({"name": $(this).val()});
        });
        alert(JSON.stringify(players));
        $.post("./api/player/makeGame",{"data" : JSON.stringify(players)},function(data, status) {
            $("#createGameDiv").hide();
            $("#gameplayDiv").show();
            //Risk.riskData = data;
            alert(JSON.stringify(data));
            //Risk.drawTerritories();
            //Risk.updateRiskState();
        });
        return false;
    });
    
    //integrate bottom method to kinetic.js and riskLogic.js
    $(".mapButton").click(function() {
        var territoryName = $(this).attr('id');
        $.get("./api/game/"+territoryName, function(data, status) {
            Risk.riskData = data;
            RIsk.drawTerritories();
            Risk.updateRiskState();
        });
        alert("mapButton");
        return false;
    });
});
