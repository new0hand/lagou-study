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
<div>
    <p style="float: right" ><a href="/resume/create">新建</a></p>
</div>
<a href="/logout">退出</a>
<div>
    <table border="1" width="500" align="center">
        <tr>
            <th>编号</th>
            <th>姓名</th>
            <th>地址</th>
            <th>手机</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${list}" var="resume" varStatus="id">
            <tr>
                <td>${resume.id}</td>
                <td>${resume.name}</td>
                <td>${resume.address}</td>
                <td>${resume.phone}</td>
                <td><a href="/resume/edit/${resume.id}">编辑</a> <a href="/resume/delete/${resume.id}">删除</a></td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
