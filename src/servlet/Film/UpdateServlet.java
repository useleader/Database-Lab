package servlet.Film;/*
 * @author   yan
 * @time     2023/12/8
 * @project  Database-Lab
 * @product  IntelliJ IDEA

 */

import dao.FilmDao;
import entity.Film;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;


@WebServlet("/movielist/update")
public class UpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String director = request.getParameter("director");
        String photo = request.getParameter("photo");
        Time duration = Time.valueOf(request.getParameter("duration"));
        String region = request.getParameter("region");

        Film film = new Film();
        film.setId(id);
        film.setName(name);
        film.setDescription(description);
        film.setDirector(director);
        film.setPhoto(photo);
        film.setDuration(duration);
        film.setRegion(region);


        FilmDao filmDao = new FilmDao();
        //调用修改接口
        int count = filmDao.update(film);
        String str=null;
        if(count>0){
            str="修改电影信息成功";
        }else{
            str="修改电影信息失败";
        }

        PrintWriter out = response.getWriter();
        out.print("<script>");
        out.print("alert('" +str+ "');");
        // TODO 路径配置
        out.print("location.href='selectAll'");
        out.print("</script>");
        out.close();

    }
}
