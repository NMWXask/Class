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
  <title>Showing brand</title>
</head>
<body>
<%@ page import="java.util.List" %>
<%@ page import="org.klozevitz.classwork.model.Notepad" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>all</title>
  <link rel="stylesheet" href="styles/style.css">
</head>
<body>
<div class="container">
  <div class="modal">
    <%
      Map<String,Integer> numberNoteBrand = (Map<String,Integer>) request.getAttribute("numberNoteBrand");%>

    <div class="tableDiv">
      <table class="responseTable">
        <tr>

          <th class="cell"> brand </th>
          <th class="cell"> quantity </th>
        </tr>

        <%
          for (Map.Entry<String, Integer> brand : numberNoteBrand.entrySet()) {
            String key = brand.getKey();
            Integer value = brand.getValue();
        %>
        <tr>
          <td class="cell"><%=key%></td>
          <td class="cell"><%=value%></td>
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
