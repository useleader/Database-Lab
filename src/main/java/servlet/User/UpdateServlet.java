package servlet.User;/*
 * @author   yan
 * @time     2023/12/7
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import dao.UserDao;
import entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/user/update")
public class UpdateServlet extends HttpServlet {

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

        int id = Integer.valueOf(request.getParameter("id"));
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);

        UserDao ud = new UserDao();
        //调用修改接口
        int count = ud.update(user);
        String str=null;
        if(count>0){
            str="修改用户信息成功";
        }else{
            str="修改用户信息失败";
        }

        PrintWriter out = response.getWriter();
        out.print("<script>");
        out.print("alert('" +str+ "');");
        out.print("location.href='selectAll'");
        out.print("</script>");
        out.close();

    }
}