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

public class StockAdd extends HttpServlet {
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

                    // 将 drug 对象传递到 JSP 页面
                    request.setAttribute("drug", drug);

                    // 转发到 DrugModify.jsp 页面
                    RequestDispatcher dispatcher = request.getRequestDispatcher("StockAdd.jsp");
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
        String batchNumber = request.getParameter("batch_number");
        String productionDate = request.getParameter("production_date");
        String expirationDate = request.getParameter("expiration_date");
        String quantity = request.getParameter("quantity");
        String purchasePrice = request.getParameter("purchase_price");
        String salePrice = request.getParameter("sale_price");

        // 创建并设置 Drug 对象
        Drug drug = new Drug();
        drug.setId(id);
        drug.setBatchNumber(batchNumber);
        drug.setProductionDate(productionDate);
        drug.setExpirationDate(expirationDate);
        drug.setQuantity(Integer.parseInt(quantity));
        drug.setPurchasePrice(Float.parseFloat(purchasePrice));
        drug.setSalePrice(Float.parseFloat(salePrice));


        // 执行数据库更新操作
        DB db = new DB();
        String sql1 = "INSERT into stock " + 
        "(drug_id, batch_number, production_date, expiration_date, quantity, purchase_price, sale_price) " + 
        "VALUES(?, ?, ?, ?, ?, ?, ?)";

        String sql2 = "INSERT into stock_in " + 
        "(drug_id, batch_number, production_date, expiration_date, quantity, purchase_price, sale_price, stock_in_date) " + 
        "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        
        
        try {
            // 库存
            PreparedStatement stmt1 = db.getCon().prepareStatement(sql1);
            stmt1.setInt(1, Integer.parseInt(drug.getId()));
            stmt1.setString(2, drug.getBatchNumber());
            stmt1.setDate(3, Date.valueOf(LocalDate.parse(drug.getProductionDate())));
            stmt1.setDate(4, Date.valueOf(LocalDate.parse(drug.getExpirationDate())));
            stmt1.setInt(5, drug.getQuantity());
            stmt1.setFloat(6, drug.getPurchasePrice());
            stmt1.setFloat(7, drug.getSalePrice());
            stmt1.executeUpdate();
            // 入库记录
            PreparedStatement stmt2 = db.getCon().prepareStatement(sql2);
            stmt2.setInt(1, Integer.parseInt(drug.getId()));
            stmt2.setString(2, drug.getBatchNumber());
            stmt2.setDate(3, Date.valueOf(LocalDate.parse(drug.getProductionDate())));
            stmt2.setDate(4, Date.valueOf(LocalDate.parse(drug.getExpirationDate())));
            stmt2.setInt(5, drug.getQuantity());
            stmt2.setFloat(6, drug.getPurchasePrice());
            stmt2.setFloat(7, drug.getSalePrice());
            stmt2.setDate(8, Date.valueOf(LocalDate.now()));
            stmt2.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 更新成功后，可以重定向回查询页面或显示成功消息
        response.sendRedirect("StockDrugQuery");
    }
}
