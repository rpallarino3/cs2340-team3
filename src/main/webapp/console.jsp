<%@ page import="etc.*" %>

<% RiskStatus console = (RiskStatus) request.getAttribute("console"); %>
<% if(console!=null) { %>
<div class="console">
    <% out.println(console.toString("<br>")); %>
</div>
<% } %>
