<%@ page import="java.net.InetAddress" %>
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%
    InetAddress addr = InetAddress.getLocalHost();
    System.out.println("local host:"+addr);
    String name = request.getParameter("name");
    String pass = request.getParameter("pass");
    if (name.equals("test")
            && pass.equals("123")) {
        session.setAttribute("user", name);
        out.println("恭喜您，登录成功！");
    } else {
        out.println("对不起，用户名、密码不符合！");
    }
%>

