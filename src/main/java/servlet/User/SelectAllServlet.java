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
import java.util.List;

@WebServlet("/user/selectAll") // 指定特定的URL路径与该Servlet类关联
public class SelectAllServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response); // get请求的处理都跳到post请求
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        UserDao ud = new UserDao();
        List<User> users = ud.selectAll();

        // 属性usersList的值为users
        request.setAttribute("usersList", users);

        // 将当前的 request 和 response 对象传递给名为 "selectAll.jsp" 的JSP页面。
        // 这会导致控制权从当前Servlet转移到 "selectAll.jsp" 页面，
        // 且 "selectAll.jsp" 可以处理该请求并生成响应
        request.getRequestDispatcher("selectAll.jsp").forward(request, response);
    }
}