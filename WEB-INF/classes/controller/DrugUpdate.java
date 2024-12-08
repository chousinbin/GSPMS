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

public class DrugUpdate extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String drugId = request.getParameter("id");

        if (drugId != null) {
            DB db = new DB();
            String sql = "SELECT * FROM drugs WHERE id = " + drugId;
            
            try {
                
                ResultSet rs = db.excuteQ(sql);
                
                if (rs.next()) {
                    // ���� Drug ������������
                    Drug drug = new Drug();
                    drug.setId(String.valueOf(rs.getInt("id")));
                    drug.setManufacturer(rs.getString("manufacturer"));
                    drug.setName(rs.getString("name"));
                    drug.setBrand(rs.getString("brand"));
                    drug.setOrigin(rs.getString("origin"));
                    // drug.setBatchNumber(rs.getString("batch_number"));
                    // drug.setProductionDate(rs.getDate("production_date").toString());
                    // drug.setExpirationDate(rs.getDate("expiration_date").toString());

                    // �� drug ���󴫵ݵ� JSP ҳ��
                    request.setAttribute("drug", drug);

                    // ת���� DrugModify.jsp ҳ��
                    RequestDispatcher dispatcher = request.getRequestDispatcher("DrugUpdate.jsp");
                    dispatcher.forward(request, response);
                } else {
                    // ���û���ҵ��� id ��Ӧ��ҩƷ�����Դ���������ת������ҳ��
                    response.sendRedirect("errorPage.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            response.sendRedirect("errorPage.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String id = request.getParameter("id");
        String manufacturer = request.getParameter("manufacturer");
        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        String origin = request.getParameter("origin");
        // String batchNumber = request.getParameter("batch_number");
        // String productionDate = request.getParameter("production_date");
        // String expirationDate = request.getParameter("expiration_date");

        // ���������� Drug ����
        Drug drug = new Drug();
        drug.setId(id);
        drug.setManufacturer(manufacturer);
        drug.setName(name);
        drug.setBrand(brand);
        drug.setOrigin(origin);
        // drug.setBatchNumber(batchNumber);
        // drug.setProductionDate(productionDate);
        // drug.setExpirationDate(expirationDate);

        // ִ�����ݿ���²���
        DB db = new DB();
        String sql = "UPDATE drugs SET manufacturer=?, name=?, brand=?, origin=? WHERE id=?";
        
        
        try {
            PreparedStatement stmt = db.getCon().prepareStatement(sql);
            stmt.setString(1, drug.getManufacturer());
            stmt.setString(2, drug.getName());
            stmt.setString(3, drug.getBrand());
            stmt.setString(4, drug.getOrigin());
            // stmt.setString(5, drug.getBatchNumber());
            // stmt.setDate(6, Date.valueOf(LocalDate.parse(drug.getProductionDate())));
            // stmt.setDate(7, Date.valueOf(LocalDate.parse(drug.getExpirationDate())));
            stmt.setInt(5, Integer.parseInt(drug.getId()));
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ���³ɹ��󣬿����ض���ز�ѯҳ�����ʾ�ɹ���Ϣ
        response.sendRedirect("DrugQuery");
    }
}
