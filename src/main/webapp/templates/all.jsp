<%@ page import="java.util.List" %>
<%@ page import="org.klozevitz.classwork.model.Notepad" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>all</title>
    <link rel="stylesheet" href="../styles/style.css">
</head>
<body>
    <div class="container">
        <div class="modal">
            <%List<String> headers = (List<String>) request.getAttribute("headers");%>
            <%List<Notepad> all = (List<Notepad>) request.getAttribute("all");%>

            <div class="tableDiv">
                <table class="responseTable">
                    <tr>
                        <%for (String field: headers) {%>
                        <th class="cell"> <%=field%> </th>
                        <%}%>
                    </tr>

                    <%for (Notepad notepad : all) {%>
                    <tr>
                        <td class="cell"><%=notepad.getId()%></td>
                        <td class="cell"><%=notepad.getBrand()%></td>
                        <td class="cell"><%=notepad.getModel()%></td>
                        <td class="cell"><%=notepad.getPagesAmount()%></td>
                        <td class="cell"><%=notepad.getCover()%></td>
                        <td class="cell"><%=notepad.getCountry()%></td>
                        <td class="cell"><%=notepad.getType()%></td>
                    </tr>
                    <%}%>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
