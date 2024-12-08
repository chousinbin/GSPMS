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
                    // 创建 Drug 对象并设置属性
                    Drug drug = new Drug();
                    drug.setId(String.valueOf(rs.getInt("id")));
                    drug.setManufacturer(rs.getString("manufacturer"));
                    drug.setName(rs.getString("name"));
                    drug.setBrand(rs.getString("brand"));
                    drug.setOrigin(rs.getString("origin"));
                    // drug.setBatchNumber(rs.getString("batch_number"));
                    // drug.setProductionDate(rs.getDate("production_date").toString());
                    // drug.setExpirationDate(rs.getDate("expiration_date").toString());

                    // 将 drug 对象传递到 JSP 页面
                    request.setAttribute("drug", drug);

                    // 转发到 DrugModify.jsp 页面
                    RequestDispatcher dispatcher = request.getRequestDispatcher("DrugUpdate.jsp");
                    dispatcher.forward(request, response);
                } else {
                    // 如果没有找到该 id 对应的药品，可以处理错误或跳转到其他页面
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

        // 创建并设置 Drug 对象
        Drug drug = new Drug();
        drug.setId(id);
        drug.setManufacturer(manufacturer);
        drug.setName(name);
        drug.setBrand(brand);
        drug.setOrigin(origin);
        // drug.setBatchNumber(batchNumber);
        // drug.setProductionDate(productionDate);
        // drug.setExpirationDate(expirationDate);

        // 执行数据库更新操作
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

        // 更新成功后，可以重定向回查询页面或显示成功消息
        response.sendRedirect("DrugQuery");
    }
}
