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
        // ִ�����ݿ���²���
        DB db = new DB();
        String sql = "DELETE FROM drugs WHERE id = " + drugId;
        
        try {
            db.excuteU(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ���³ɹ��󣬿����ض���ز�ѯҳ�����ʾ�ɹ���Ϣ
        response.sendRedirect("DrugQuery");
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws 
    ServletException, IOException {
        doPost(request, response);
    }
}
