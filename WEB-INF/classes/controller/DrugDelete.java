package controller;

import model.DB;
import model.Drug;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class DrugDelete extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String drugId = request.getParameter("id");
        // 执行数据库更新操作
        DB db = new DB();
        String sql = "DELETE FROM drugs WHERE id = " + drugId;
        
        try {
            db.excuteU(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 更新成功后，可以重定向回查询页面或显示成功消息
        response.sendRedirect("DrugQuery");
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws 
    ServletException, IOException {
        doPost(request, response);
    }
}
