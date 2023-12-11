package util;/*
 * @author   yan
 * @time     2023/12/7
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import java.sql.*;

public class DBConnectionUtil {
    // 在类进行实例加载时执行的一次性初始化操作，
    // 简单来说就是被 **static修饰的代码块** 会在该类首次被加载的时候执行，
    // 并且只执行一次
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // 打印异常信息在程序中出错的位置及原因
            e.printStackTrace();
        }
    }

    //
    public static Connection getConnection(){
        Connection conn = null;
        try {
            String url = "jdbc:mysql://localhost:3306/imdb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
            String username = "root";
            String password = "root";
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn, PreparedStatement pstmt, ResultSet rs){
        try{
            if(rs != null){
                // rs 不为空，说明已经有一个数据库查询结果了，现在需要关闭该查询结果。
                rs.close();
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}