<%@ page import="java.net.InetAddress" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="DatabaseCon.DataBaseConnection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%
    InetAddress addr = InetAddress.getLocalHost();
    System.out.println("local host:"+addr);
    Connection conn= DataBaseConnection.getConnection();
    PreparedStatement pstmt =conn.prepareStatement("select username,password,userword,imageid from user where account=?");
    String email = request.getParameter("email");
    String pwd = request.getParameter("pwd");
    pstmt.setString(1,email);
    ResultSet res=pstmt.executeQuery();
    if(res.next()){
        String username=res.getString(1);
        String password=res.getString(2);
        String userword=res.getString(3);
        String imageid=res.getInt(4)+"";
        if(password.equals(pwd)){
            System.out.println(imageid);
            out.println("{name:"+username+",userword:'"+userword+"',imageId:"+imageid+",password:"+password+"}");
        }else{
            out.println("密码不正确");
        }
    } else{
        out.println("账号尚未注册");
    }
//    if (name.equals("test")
//            && pass.equals("123")) {
//        session.setAttribute("user", name);
//        out.println("恭喜您，登录成功！");
//    } else {
//        out.println("对不起，用户名、密码不符合！");
//    }
%>

