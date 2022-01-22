<%@ page import="java.net.InetAddress" %>
<%@ page import="DatabaseCon.DataBaseConnection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Date" %>
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%
    InetAddress addr = InetAddress.getLocalHost();
    System.out.println("local host:"+addr);
    String username =request.getParameter("username");
    String comments=request.getParameter("comments");
    String imageid=request.getParameter("imageid");
    String position=request.getParameter("position");
    String time=request.getParameter("time");
    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
    Connection conn= DataBaseConnection.getConnection();


    PreparedStatement pstmt2 =conn.prepareStatement("insert into comment(username,comments,time,position,imageid) values(?,?,?,?,?)");
    pstmt2.setString(1,username);
    pstmt2.setString(2,comments);
    Date date=new Date(System.currentTimeMillis());
    pstmt2.setDate(3, date);
    pstmt2.setString(4,position);
    pstmt2.setString(5,imageid);
    System.out.println(position);
    int affected=0;
    try {
        affected = pstmt2.executeUpdate();
    }catch (Exception e){
        System.out.println(e);
    }
    if(affected==1){
        out.println("发布成功");
    }else{
        out.println("发布失败");
    }
    pstmt2.close();
    conn.close();
%>

