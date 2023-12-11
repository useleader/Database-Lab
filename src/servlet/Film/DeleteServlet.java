package servlet.Film;/*
 * @author   yan
 * @time     2023/12/8
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

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
        response.setContentType("text/html;charset=utf-8");

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
        PrintWriter out = response.getWriter();
        out.print("<script>");
        out.print("alert('"+str+"');");
//        TODO 确定href
        out.print("location.href='/movielist'");
        out.print("</script>");
        out.print("success");
        out.close();

    }
}
