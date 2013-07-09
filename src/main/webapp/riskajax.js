/*
 * This file uses jquery to process ajax. 
 * USE: To include this file to any page, first include jquery.js
 * DEPENDENCY: jquery.js
 */
$(document).ready(function() {
    $('#testbutton').click(function() {
        $.get("./api/test", function(data, status) {
            alert("status: "+status+"\ndata: "+data.data);
        });
    });
});
