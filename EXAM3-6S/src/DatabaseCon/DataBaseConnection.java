package DatabaseCon;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {
    public static Connection getConnection(){
        Connection con=null;
        String url="jdbc:mysql://localhost:3306/work?user=root&password=&useSSL=true";
        String user="root";
        String password="123456";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection(url,user,password);
        }catch (Exception e){
            System.out.println("加载驱动器异常");
            e.printStackTrace();
        }
        return con;
    }
}
