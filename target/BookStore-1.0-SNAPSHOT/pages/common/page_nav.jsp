<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DCM
  Date: 2021/10/30
  Time: 19:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--分页条--%>
<div id="page_nav">
    <c:if test="${requestScope.pageBooks.pageNo > 1}">
        <a href="${requestScope.pageBooks.url}&pageNo=1">首页</a> &nbsp;
        <a href="${requestScope.pageBooks.url}&pageNo=${requestScope.pageBooks.pageNo - 1}">上一页</a> &nbsp;
    </c:if>
    <%--多重判断。要求显示连续的5个页面--%>
    <c:choose>
        <%--情况1：总页码小于等于5，页码的范围是1-总页码--%>
        <c:when test="${requestScope.pageBooks.pageTotal <= 5}">
            <c:forEach begin="1" end="${requestScope.pageBooks.pageTotal}" var="i">
                <c:if test="${i == requestScope.pageBooks.pageNo}">
                    <span style="color:red">[${i}] </span> &nbsp;
                </c:if>
                <c:if test="${i != requestScope.pageBooks.pageNo}">
                    <a href="${requestScope.pageBooks.url}&pageNo=${i}" style="color:black">${i}</a> &nbsp;
                </c:if>
            </c:forEach>
        </c:when>
        <%--情况2：总页码大于5--%>
        <c:when test="${requestScope.pageBooks.pageTotal > 5}">
            <c:choose>
                <%--情况2.1：总页码大于5，前3页--%>
                <c:when test="${requestScope.pageBooks.pageNo <= 3}">
                    <c:forEach begin="1" end="5" var="i">
                        <c:if test="${i == requestScope.pageBooks.pageNo}">
                            <span style="color:red">[${i}] </span> &nbsp;
                        </c:if>
                        <c:if test="${i != requestScope.pageBooks.pageNo}">
                            <a href="${requestScope.pageBooks.url}&pageNo=${i}"
                               style="color:black">${i}</a> &nbsp;
                        </c:if>
                    </c:forEach>
                </c:when>
                <%--情况2.2：总页码大于5，后3页--%>
                <c:when test="${requestScope.pageBooks.pageNo >= requestScope.pageBooks.pageTotal - 3}">
                    <c:forEach begin="${requestScope.pageBooks.pageTotal - 4}"
                               end="${requestScope.pageBooks.pageTotal}" var="i">
                        <c:if test="${i == requestScope.pageBooks.pageNo}">
                            <span style="color:red">[${i}] </span> &nbsp;
                        </c:if>
                        <c:if test="${i != requestScope.pageBooks.pageNo}">
                            <a href="${requestScope.pageBooks.url}&pageNo=${i}"
                               style="color:black">${i}</a> &nbsp;
                        </c:if>
                    </c:forEach>
                </c:when>
                <%--情况2.3：总页码大于5，前3页和后3页之间--%>
                <c:otherwise>
                    <c:forEach begin="${requestScope.pageBooks.pageNo - 2}"
                               end="${requestScope.pageBooks.pageNo + 2}" var="i">
                        <c:if test="${i == requestScope.pageBooks.pageNo}">
                            <span style="color:red">[${i}] </span> &nbsp;
                        </c:if>
                        <c:if test="${i != requestScope.pageBooks.pageNo}">
                            <a href="${requestScope.pageBooks.url}&pageNo=${i}"
                               style="color:black">${i}</a> &nbsp;
                        </c:if>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>
    <c:if test="${requestScope.pageBooks.pageNo < requestScope.pageBooks.pageTotal}">
        <a href="${requestScope.pageBooks.url}&pageNo=${requestScope.pageBooks.pageNo + 1}">下一页</a> &nbsp;
        <a href="${requestScope.pageBooks.url}&pageNo=${requestScope.pageBooks.pageTotal}">末页</a>
    </c:if>
    共${requestScope.pageBooks.pageTotal}页，${requestScope.pageBooks.pageTotalCount}条记录
    到第<input value="${requestScope.pageBooks.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">
    <script type="text/javascript">
        $(function () {
            $("#searchPageBtn").click(function () {
                var pageNo = $("#pn_input").val();
                location.href = "${pageScope.basePath}${requestScope.pageBooks.url}&pageNo=" + pageNo;
            });
        });
    </script>
</div>