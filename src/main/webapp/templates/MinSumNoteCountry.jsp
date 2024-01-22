<%--
  Created by IntelliJ IDEA.
  User: sindy
  Date: 17.01.2024
  Time: 22:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Страна с минимальным колличеством блакнотов</title>
</head>
<body>
<%@ page import="java.util.List" %>
<%@ page import="org.klozevitz.classwork.model.Notepad" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>all</title>
  <link rel="stylesheet" href="styles/style.css">
</head>
<body>
<div class="container">
  <div class="modal">

    <div class="tableDiv">
      <p>Страна с Минимальным колличеством блакнотов:<b><%=request.getAttribute("minNoteOfCountry")%></b></p>
    </div>
  </div>
</div>
</body>
</html>

</body>
</html>
