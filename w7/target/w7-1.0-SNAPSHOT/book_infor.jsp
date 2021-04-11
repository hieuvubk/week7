<%@ page import="com.example.w7.model.Book" %><%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 4/10/2021
  Time: 4:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit</title>
</head>
<body>
<%
    Book book = (Book) request.getAttribute("book");
%>

<form method="POST" action="${pageContext.request.contextPath}/edit">
    <div class="container">
        <%
            request.setAttribute("id", book.getId());
            System.out.println(request.getAttribute("id"));
        %>
        <label>Name : </label>
        <input type="text" name="name" value=<%=book.getName()%>>
        <label>Publisher : </label>
        <input type="text" name="publisher" value=<%=book.getPublisher()%>>
        <label>Price : </label>
        <input type="text"  name="price" value=<%=book.getPrice()%>>
        <button type="submit">Sửa</button>
    </div>
</form>
</body>
</html>
