<%--
  Created by IntelliJ IDEA.
  User: sindy
  Date: 17.01.2024
  Time: 22:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Task2</title>
</head>
<body>
<div>
  <div>
    <h3>Task2</h3>
    <p>
    <a href="${pageContext.request.contextPath}/all">Полная таблица</a>
    </p>
    <p>
      <a href="${pageContext.request.contextPath}/showingCountries">Страны</a>
    </p>
    <p>
      <a href="${pageContext.request.contextPath}/numberNotesOfCountries">Колличество блакнотов в каждой стране</a>
    </p>
    <p>
      <a href="${pageContext.request.contextPath}/numberNotesOfBrand">Колличество блакнотов у каждого производителя</a>
    </p>

  </div>
</div>
</body>
</html>
