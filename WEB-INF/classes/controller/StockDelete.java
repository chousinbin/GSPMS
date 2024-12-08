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

public class StockDelete extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String stockId = request.getParameter("stockId");
        // 执行数据库更新操作
        DB db = new DB();
        String sql = "DELETE FROM stock WHERE stock_id = " + stockId;
        
        try {
            db.excuteU(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 更新成功后，可以重定向回查询页面或显示成功消息
        response.sendRedirect("StockQuery");
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws 
    ServletException, IOException {
        doPost(request, response);
    }
}
