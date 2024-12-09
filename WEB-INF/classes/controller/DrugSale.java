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

public class DrugSale extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String stockId = request.getParameter("stockId");

        if (stockId != null) {
            DB db = new DB();
            String sql = 
            "SELECT " +
            "drugs.id as drug_id, " + 
            "stock.stock_id as stock_id, " +
            "drugs.manufacturer, " + 
            "drugs.name, " + 
            "drugs.brand, " + 
            "drugs.origin, " + 
            "stock.batch_number, " + 
            "stock.production_date, " + 
            "stock.expiration_date, " + 
            "stock.quantity, " + 
            "stock.purchase_price, " + 
            "stock.sale_price " + 
            "FROM stock " + 
            "INNER JOIN drugs " + 
            "ON stock.drug_id = drugs.id " +
            "WHERE stock_id = " + stockId;
            
            try {
                
                ResultSet rs = db.excuteQ(sql);
                
                if (rs.next()) {
                    // 创建 Drug 对象并设置属性
                    Drug drug = new Drug();
                    drug.setId(String.valueOf(rs.getInt(1)));
                    drug.setStockId(rs.getInt(2));
                    drug.setManufacturer(rs.getString(3));
                    drug.setName(rs.getString(4));
                    drug.setBrand(rs.getString(5));
                    drug.setOrigin(rs.getString(6));
                    drug.setBatchNumber(rs.getString(7));
                    drug.setProductionDate(rs.getDate(8).toString());
                    drug.setExpirationDate(rs.getDate(9).toString());
                    drug.setQuantity(rs.getInt(10));
                    drug.setPurchasePrice(rs.getFloat(11));
                    drug.setSalePrice(rs.getFloat(12));

                    // 将 drug 对象传递到 JSP 页面
                    request.setAttribute("drug", drug);

                    // 转发到 DrugModify.jsp 页面
                    RequestDispatcher dispatcher = request.getRequestDispatcher("DrugSale.jsp");
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

        String stockId = request.getParameter("stock_id");
        String manufacturer = request.getParameter("manufacturer");
        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        String origin = request.getParameter("origin");
        String saleQuantity = request.getParameter("sale_quantity");
        String salePrice = request.getParameter("sale_price");

        Drug drug = new Drug();
        drug.setQuantity(Integer.parseInt(saleQuantity));
        drug.setSalePrice(Float.parseFloat(salePrice));
        drug.setManufacturer(manufacturer);
        drug.setName(name);
        drug.setBrand(brand);
        drug.setOrigin(origin);
        drug.setSaleSumPrice(Integer.parseInt(saleQuantity) * Float.parseFloat(salePrice));

        // 执行数据库更新操作
        DB db = new DB();
        String checkStockSql = "SELECT quantity FROM stock WHERE stock_id = ?";
        String updateStockSql = "UPDATE stock SET quantity = quantity - ? WHERE stock_id = ?";

        try (PreparedStatement checkStmt = db.getCon().prepareStatement(checkStockSql);
            PreparedStatement updateStmt = db.getCon().prepareStatement(updateStockSql)) {

            // 检查库存
            checkStmt.setInt(1, Integer.parseInt(stockId));
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int currentQuantity = rs.getInt(1);
                if (currentQuantity < Integer.parseInt(saleQuantity)) {
                    request.setAttribute("message", "库存不足，销售失败！");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("DrugSale.jsp");
                    dispatcher.forward(request, response);
                    return;  // 如果库存不足，停止继续执行
                }
            } else {
                request.setAttribute("message", "药品不存在！");
                RequestDispatcher dispatcher = request.getRequestDispatcher("DrugSale.jsp");
                dispatcher.forward(request, response);
                return;  // 如果药品不存在，停止继续执行
            }

            // 执行库存更新
            updateStmt.setInt(1, Integer.parseInt(saleQuantity));
            updateStmt.setInt(2, Integer.parseInt(stockId));
            updateStmt.executeUpdate();

            // 将 drug 对象传递到 JSP 页面
            request.setAttribute("drug", drug);
            // 转发到 DrugSaleSuccess.jsp 页面
            request.getRequestDispatcher("DrugSaleSuccess.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "数据库操作失败：" + e.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }
}
