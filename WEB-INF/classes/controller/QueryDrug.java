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

        // �������ݿ�
        DB db = new DB();
        String sql = "SELECT * FROM stock";
        ResultSet rs = db.excuteQ(sql);

        // ��� HTML ���
        PrintWriter out = response.getWriter();
        
        out.println("<html>");
        out.println("<head><title>�豸��ѯ</title></head>");
        out.println("<body bgcolor=pink>");
        out.println("<h1>�豸����ϵͳ</h1>");
        out.println("<a href=\"Sinbin.jsp\">���˵�</a><br>");
        out.println("<table border=\"1\">");
        out.println("<tr>");
        out.println("<th>�豸���</th>");
        out.println("<th>�豸����</th>");
        out.println("<th>ʹ������</th>");
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
            e.printStackTrace(); // ���߽��������쳣����
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}