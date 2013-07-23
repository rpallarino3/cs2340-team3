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
    $.get("./api/game/risk", function(data, status) {
          Risk.riskData = data;
          Risk.init();
          Risk.drawTerritories();
          console.log("updatingRiskState");
          Risk.updateRiskState();
    }); 
    };
var hideGame = function() {
    $("#createGameDiv").show();
    $("#gameplayDiv").hide();
};
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
            Risk.riskData = data;
            Risk.init();
            Risk.drawTerritories();
            //Risk.updateRiskState();
        });
        return false;
    });
    $("#postSubmit").click(function() {
        var num = $('#postNumber').val();
        console.log(num);
        $.post("./api/game/postIt",{"input":num},function(data, status) {
            $('#postNumber').val("");
            $('#postPopup').fadeOut("fast");
            console.log(data);
            if(data==null) {
                console.log("should not be null");
                return;
            }
            Risk.riskData = data;
            Risk.redrawTerritories();
            Risk.updateRiskState();
        });
        $("#postPopup").fadeOut("fast");
    });
});
