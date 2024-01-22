<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../styles/style.css">
</head>
<body>
    <div class="container">
        <div class="modal">
            <form method="post" action="${pageContext.request.contextPath}/add">
                <%List<String> types = (List<String>) request.getAttribute("types");%>
                <%List<String> covers = (List<String>) request.getAttribute("covers");%>
                <input type="text" class="requestInput" name="brand" placeholder="brand">
                <input type="text" class="requestInput" name="model" placeholder="model">
                <input type="text" class="requestInput" name="pagesAmount" placeholder="pagesAmount">
                <select class="requestInput" name="cover">
                    <%for (String cover: covers) {%>
                    <option><%=cover%></option>
                    <%}%>
                </select>
                <input type="text" class="requestInput" name="country" placeholder="country">
                <select class="requestInput" name="type">
                    <%for (String type: types) {%>
                    <option><%=type%></option>
                    <%}%>
                </select>
                <input type="submit" value="add" class="requestButton">
            </form>
            <span>
            </span>
        </div>
    </div>
<script src="../scripts/submitButtonScript.js"></script>
</body>
</html>
