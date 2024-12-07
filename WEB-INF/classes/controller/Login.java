package controller;
import model.DB;
import model.User;
import java.sql.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Login extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws 
    ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        DB db = new DB();
        try (Connection conn = db.getCon()) {
            String sql = "SELECT role FROM user WHERE name = ? AND pwd = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");
                User user = new User(username, password, role);
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("dashboard.jsp");
            } else {
                request.setAttribute("error", "Invalid username or password.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException("Database error", e);
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws 
    ServletException, IOException {
        doPost(request, response);
    }
}
