<%@ page import="java.net.InetAddress" %>
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%
    InetAddress addr = InetAddress.getLocalHost();
    System.out.println("local host:"+addr);
    Object user = session.getAttribute("user");
    if (user != null && user.toString().trim().equals("test")) {
%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title> 安全资源 </title>
</head>
<body>
安全资源，只有登录用户<br/>
且用户名是test才可访问该资源
</body>
</html>
<%
    } else {
        out.println("您没有被授权访问该页面");
    }
%>

