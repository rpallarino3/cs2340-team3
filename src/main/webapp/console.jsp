<%@ page import="etc.*" %>

<% RiskStatus console = (RiskStatus) request.getAttribute("console"); %>
<% if(console!=null) { %>
	<% out.println(console.toString("<p>","</p>")); %>
<% } %>

