<%@ page import="com.tms.domain.Actor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Actor actorJsp = (Actor) request.getAttribute("actor");%>
<html>
<head>
    <title>Actor info</title>
</head>
<body>
<h1> Hello, this is your Actor!</h1>
<h3>Actor id:  <%=actorJsp.getId()%></h3>
<h3>Actor first name:  <%=actorJsp.getFirstName()%></h3>
<h3>Actor last name:  <%=actorJsp.getLastName()%></h3>
<h3>Actor age:  <%=actorJsp.getAge()%></h3>
<h3>Actor biography:  <%=actorJsp.getBiography()%></h3>
</body>
</html>
