<%--
  Created by IntelliJ IDEA.
  User: zhuhaofu
  Date: 2020/4/23
  Time: 3:35 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/resume/update">
    <input type="hidden" name="id" value="${resume.id}">
    name:<br>
    <input type="text" name="name" value="${resume.name}">
    <br>
    address:<br>
    <input type="text" name="address" value="${resume.address}">
    phone:<br>
    <input type="text" name="phone" value="${resume.phone}">
    <br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
