<%@ page import="java.net.InetAddress" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="DatabaseCon.DataBaseConnection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=utf-8" language="java" errorPage="" %>
<%
    InetAddress addr = InetAddress.getLocalHost();
    System.out.println("local host:"+addr);
    List<String> listname = new ArrayList<>();
    List<String> listcomments = new ArrayList<>();
    List<String> listtime = new ArrayList<>();
    List<String> listposition = new ArrayList<>();
    List<String> listimageid=new ArrayList<>();
    Connection conn= DataBaseConnection.getConnection();
    PreparedStatement pstmt =conn.prepareStatement("select * from comment order by num desc limit 0,10; ");
    ResultSet res=pstmt.executeQuery();
    while(res.next()){
        String username=res.getString(2);
        String comments=res.getString(3);
        Date time=res.getDate(4);
        String position=res.getString(5);
        String image=res.getString(6);
        listname.add(username);
        listcomments.add(comments);
        listtime.add(time.toString());
        listposition.add(position);
        listimageid.add(image);
    }
    res.close();
    conn.close();
    System.out.println("{username:"+listname.toString()+",comments:"+listcomments.toString()+",time:"+listtime.toString()+",position:"+listposition.toString()+",imageid:"+listimageid.toString()+"}");
    out.println("{username:"+listname.toString()+",comments:"+listcomments.toString()+",time:"+listtime.toString()+",position:"+listposition.toString()+",imageid:"+listimageid.toString()+"}");
%>

