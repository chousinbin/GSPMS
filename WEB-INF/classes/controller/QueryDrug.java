package controller;
import model.DB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class QueryDrug extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        // 插入数据库
        DB db = new DB();
        String sql = "SELECT * FROM stock";
        ResultSet rs = db.excuteQ(sql);

        // 输出 HTML 表格
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head><title>设备查询</title></head>");
        out.println("<body bgcolor=pink>");
        out.println("<h1>设备管理系统</h1>");
        out.println("<a href=\"Sinbin.jsp\">主菜单</a><br>");
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<th>设备编号</th>");
        out.println("<th>设备名称</th>");
        out.println("<th>使用年限</th>");
        out.println("</tr>");
        out.println("</thead>");

        try {
            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("equNo") + "</td>");
                out.println("<td>" + rs.getString("equName") + "</td>");
                out.println("<td>" + rs.getString("equAge") + "</td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException e) {
            e.printStackTrace(); // 或者进行其他异常处理
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}