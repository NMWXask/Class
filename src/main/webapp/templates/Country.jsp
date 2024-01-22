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
    <title>Showing countries</title>
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
        <%List<String> country = (List<String>) request.getAttribute("country");%>

        <div class="tableDiv">
            <table class="responseTable">
                <tr>

                    <th class="cell"> country </th>
                </tr>

                <%for (String countries : country) {%>
                <tr>
                    <td class="cell"><%=countries%></td>
                </tr>
                <%}%>
            </table>
        </div>
    </div>
</div>
</body>
</html>

</body>
</html>
