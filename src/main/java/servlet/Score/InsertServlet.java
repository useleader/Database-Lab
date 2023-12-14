package servlet.Score;/*
 * @author   yan
 * @time     2023/12/8
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import dao.ScoreDao;
import entity.Score;
import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/score/insert")
public class InsertServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*"); // 允许所有的ip的可以访问
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE"); // 允许访问的方式
        response.setHeader("Access-Control-Max-Age", "3600");  // 设置超时时间
        response.setHeader("Access-Control-Allow-Headers", "token, Accept, Origin, X-Requested-With, Content-Type, Last-Modified");
        response.setContentType("application/json; charset=utf-8");


        Integer film_id = Integer.valueOf(request.getParameter("film_id"));
        Integer user_id = Integer.valueOf(request.getParameter("user_id"));
        Float number = Float.valueOf(request.getParameter("number"));

        Score s = new Score(film_id,user_id,number);

        //快捷键是ctrl+alt+v
        ScoreDao sd = new ScoreDao();
        //调用添加接口
        int count = sd.insert(s);
        try {
            String jsonStr = JSON.toJSONString(count);
            response.getWriter().write(jsonStr);
        } catch (JSONException e) {
            System.out.println("Exception thrown  :" + e);
        }
    }
}

