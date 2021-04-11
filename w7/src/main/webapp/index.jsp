<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.w7.model.Book" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>

<%
    String logout_href = request.getContextPath() + "/logout";
%>

<a href="index.jsp">Index</a>
<a href=<%=logout_href%>>Logout</a>
<form method="POST" action=${pageContext.request.contextPath}/index>
    <input type="text" name="search" placeholder="Search..">
    <button type="submit"> Tìm kiếm</button>
</form>

<%
    List<Book> list = (List<Book>) request.getAttribute("books");
    if(list != null) {
        out.println("<table>");
        out.println("<tr>");
        out.println("<th>Id</th>");
        out.println("<th>Name</th>");
        out.println("<th>Publisher</th>");
        out.println("<th>Price</th>");
        out.println("<th></th>");
        out.println("<th></th>");
        out.println("</tr>");
        for (int i = 0; i < list.size(); i++) {
            Book book = (Book) list.get(i);
            String edit_href = request.getContextPath() + "/edit?id=" + book.getId();
            String delete_href = request.getContextPath() + "/delete?id=" + book.getId();
            out.println("<tr>");
            out.println("<td>" + book.getId() + "</td>");
            out.println("<td>" + book.getName() + "</td>");
            out.println("<td>" + book.getPublisher() + "</td>");
            out.println("<td>" + book.getPrice() + "</td>");
            out.println("<td><a href=" + edit_href + ">Sửa</a></td>");
            out.println("<td><a href=" + delete_href + ">Xóa</a></td>");
        }
        out.println("</table>");
    }

%>



</body>
</html>
