/*
 * This file uses jquery to process ajax. 
 * USE: To include this file to any page, first include jquery.js
 * DEPENDENCY: jquery.js
 */
$(document).ready(function() {
    $('#testbutton').click(function() {
        $.get("./agame/test", function(data, status) {
            alert("status: "+status+"\ndata: "+data.data);
        });
        return false;
    });
    
    $(".mapButton").click(function() {
        var territoryName = this.getId();
        $.get("./agame/"+territoryName, function(data, status) {
            reloadMap(data);
        });
    });
    var reloadMap = function(mapData) {
        for
    };
});
