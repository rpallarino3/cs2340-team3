/*
 * This file uses jquery to process ajax. 
 * USE: To include this file to any page, first include jquery.js
 * DEPENDENCY: jquery.js and kinetic.js
 */
var sessionIsNew = false;
var gameCreated = false;
$(document).ready(function() {
    $.get("./api/game/status", function(data, status) {
        sessionIsNew = data.isNew;
        gameCreated = data.gameCreated;
        alert("newSession: " + sessionIsNew +"\ngameCreated: "+ gameCreated);
        if(sessionIsNew) {
         //   alert("new data");
        } else {
          //  alert("old data");
        }
    });
    $("#startGame").click(function() {
        var players = [];
        $("#createPlayerForm input").each(function() {
            players.push({"name": $(this).val()});
        });
        alert(JSON.stringify(players));
        $.post("./api/player/makeGame",{"data" : JSON.stringify(players)},function(data, status) {

        });
        return false;
    });
    
    $(".mapButton").click(function() {
        var territoryName = $(this).attr('id');
        $.get("./api/game/"+territoryName, function(data, status) {
            alert(territoryName);
        });
        alert("mapButton");
        return false;
    });
});
