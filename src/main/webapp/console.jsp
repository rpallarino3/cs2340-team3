<%@ page import="etc.*" %>

<% RiskStatus console = (RiskStatus) request.getAttribute("console"); %>
<% if(console!=null) { %>
<div class="consoleOutput">
    <% out.println(console.toString("<br>")); %>
</div>
<% } %>

