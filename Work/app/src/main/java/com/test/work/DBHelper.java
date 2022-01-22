package com.test.work;

import java.sql.*;

public class DBHelper {

    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://47.107.68.210:3306/FirstTest?user=root&password=&useSSL=false&useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    private static String username = "root";
    private static String password = "123456";
    private static Connection conn;

    /**
     * 连接MySQL数据库
     * @return
     */
//    public static Connection getConn(){
//         conn = null;
//        try {
//            Class.forName(driver);
//            conn = DriverManager.getConnection(url, username, password);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return conn;
//    }

    public static Connection linkDatabase()
    {
        new Thread(new Runnable() {
            @Override
            public void run() {

                    try {
                        Thread.sleep(1000);  // 每隔0.1秒尝试连接
                    } catch (InterruptedException e) {
                        System.out.println(e);
                    }
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        conn = DriverManager.getConnection(url,username,password);
                        System.out.println("连接结果");
                        if(conn!=null)
                        {
                            System.out.println("连接成功");
                        }
                        else
                        {
                            System.out.println("连接失败");
                        }
                    }catch (Exception e)
                    {
                        System.out.println("连接失败");
                        System.out.println(e);
                    }
                try {
                    Statement statement = conn.createStatement();
                    String sql="select * from test1";
                    ResultSet resultSet = statement.executeQuery(sql);
                    while (resultSet.next()){
                        System.out.println(resultSet.getInt(1)+":"+resultSet.getString(2)+":"+resultSet.getString(3));
                    }
                }catch (Exception e){
                    System.out.println(e);
                }
                }

        }).start();
        return conn;
    }

    /**
     * 关闭数据库
     * */

    public static void closeAll(Connection conn, PreparedStatement ps){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 关闭数据库
     * */

    public static void closeAll(Connection conn, PreparedStatement ps, ResultSet rs){
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}


