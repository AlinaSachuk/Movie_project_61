<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All movies!!!</title>
</head>
<body>
There are your movies:
<%=request.getAttribute("movieList")%>
</body>
</html>
