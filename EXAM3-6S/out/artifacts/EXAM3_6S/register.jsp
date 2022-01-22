<%@ page import="java.net.InetAddress" %>
<%@ page import="DatabaseCon.DataBaseConnection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%
    InetAddress addr = InetAddress.getLocalHost();
    System.out.println("local host:"+addr);
    String email = request.getParameter("email");
    String pwd = request.getParameter("pwd");
    String username =request.getParameter("username");
    String userword=request.getParameter("userword");
    String imageId=request.getParameter("imageId");
    Connection conn= DataBaseConnection.getConnection();
    PreparedStatement pstmt1 =conn.prepareStatement("select * from user where account=?");
    pstmt1.setString(1,email);
    ResultSet res=pstmt1.executeQuery();
    if(res.next()){
        out.println("该账号已经被注册，请重新输入账号");
    }else{
        PreparedStatement pstmt2 =conn.prepareStatement("insert into user(account,username,password,userword,imageid) values(?,?,?,?,?)");
        pstmt2.setString(1,email);
        pstmt2.setString(2,username);
        pstmt2.setString(3,pwd);
        pstmt2.setString(4,userword);
        pstmt2.setInt(5,Integer.parseInt(imageId));
        int affected=pstmt2.executeUpdate();
        if(affected==1){
            out.println("注册成功");
        }else{
            out.println("注册失败");
        }
    }

%>

