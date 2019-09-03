<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Phone book</title>
</head>
<body>
<h2>Owners list</h2>
<c:if test="${ownerslist ne null}">
    <table>
        <c:forEach items="${ownerslist}" var="owner">
            <tr>
                <td><c:out value="${owner.name}"/></td>
                <td><c:out value="${owner.phoneNumber}"/></td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
