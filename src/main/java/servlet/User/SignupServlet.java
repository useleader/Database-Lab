package servlet.User;/*
 * @author   yan
 * @time     2023/12/13
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import dao.UserDao;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

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


        String name = request.getParameter("name");
        String password = request.getParameter("password");


        User user = new User();

        user.setName(name);
        user.setPassword(password);

        //快捷键是ctrl+alt+v
        UserDao ud = new UserDao();
        //调用添加接口, ans=1, success ; ans=0, failure
        Integer ans = ud.signup(user);

        try {
            String jsonStr = JSON.toJSONString(ans);
            response.getWriter().write(jsonStr);
        } catch (JSONException e) {
            System.out.println("Exception thrown  :" + e);
        }
    }
}