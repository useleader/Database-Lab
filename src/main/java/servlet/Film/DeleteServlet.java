package servlet.Film;/*
 * @author   yan
 * @time     2023/12/8
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import dao.FilmDao;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/movielist/delete_movie")
public class DeleteServlet extends HttpServlet {

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

        Enumeration<String> a = request.getParameterNames();

        int id = Integer.parseInt(request.getParameter("id"));

        FilmDao fd = new FilmDao();
        int count = fd.delete(id);

        String str = null;
        if(count > 0){
            str = "电影删除成功";
        }else {
            str = "电影删除失败";
        }

        // 向客户端发送响应
        try {
            String jsonStr = JSON.toJSONString(str);
            response.getWriter().write(jsonStr);
        } catch (JSONException e) {
            System.out.println("Exception thrown  :" + e);
        }

    }
}
