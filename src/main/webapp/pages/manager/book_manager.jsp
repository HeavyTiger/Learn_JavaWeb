<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>图书管理</title>
    <%@ include file="/pages/common/head.jsp" %>
</head>

<script type="text/javascript">
    $(function () {
        $("a.deleteClass").click(function () {
            return confirm("您确定要删除书籍《" + $(this).parent().parent().find("td:first").text() + "》吗？");
        })
    })
</script>

<body>

<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif">
    <span class="wel_word">图书管理系统</span>
    <%@ include file="/pages/common/manager_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <th>名称</th>
            <th>价格</th>
            <th>作者</th>
            <th>销量</th>
            <th>库存</th>
            <th colspan="2">操作</th>
        </tr>

        <c:forEach items="${requestScope.pageBooks.items}" var="book">
            <tr>
                <td>${book.name}</td>
                <td>${book.price}</td>
                <td>${book.author}</td>
                <td>${book.sales}</td>
                <td>${book.stock}</td>
                <td><a href="manager/bookServlet?action=getBook&id=${book.id}&pageNo=${requestScope.pageBooks.pageNo}">修改</a>
                </td>
                <td><a class="deleteClass"
                       href="manager/bookServlet?action=deleteBook&id=${book.id}&pageNo=${requestScope.pageBooks.pageNo}">删除</a>
                </td>
            </tr>
        </c:forEach>

        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td><a href="pages/manager/book_edit.jsp?pageNo=${requestScope.pageBooks.pageTotal}">添加图书</a></td>
        </tr>
    </table>
    <%--分页条--%>
    <%@include file="/pages/common/page_nav.jsp" %>
</div>

<%@ include file="/pages/common/foot.jsp" %>
</body>
</html>