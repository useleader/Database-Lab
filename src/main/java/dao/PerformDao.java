package dao;/*
 * @author   yan
 * @time     2023/12/8
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import entity.Perform;
import entity.Tag;
import entity.User;
import util.DBConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PerformDao {
    public int insert(Perform perform){
        int count = 0;
        Connection conn = DBConnectionUtil.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int id = 0;
        try{
            pstmt = conn.prepareStatement("insert into perform(film_id, actor_id) values (?, ?);");  // 问号？是占位符
            // 传递参数
            pstmt.setInt(1, perform.getFilm_id());
            pstmt.setInt(2, perform.getActor_id());
            // 执行insert操作，并获取被更新的行数
            count = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnectionUtil.closeConnection(conn, pstmt, rs);
        }
        return count;
    }

}
