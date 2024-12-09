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
                    // ���� Drug ������������
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

                    // �� drug ���󴫵ݵ� JSP ҳ��
                    request.setAttribute("drug", drug);

                    // ת���� DrugModify.jsp ҳ��
                    RequestDispatcher dispatcher = request.getRequestDispatcher("DrugSale.jsp");
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

        // ִ�����ݿ���²���
        DB db = new DB();
        String checkStockSql = "SELECT quantity FROM stock WHERE stock_id = ?";
        String updateStockSql = "UPDATE stock SET quantity = quantity - ? WHERE stock_id = ?";

        try (PreparedStatement checkStmt = db.getCon().prepareStatement(checkStockSql);
            PreparedStatement updateStmt = db.getCon().prepareStatement(updateStockSql)) {

            // �����
            checkStmt.setInt(1, Integer.parseInt(stockId));
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                int currentQuantity = rs.getInt(1);
                if (currentQuantity < Integer.parseInt(saleQuantity)) {
                    request.setAttribute("message", "��治�㣬����ʧ�ܣ�");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("DrugSale.jsp");
                    dispatcher.forward(request, response);
                    return;  // �����治�㣬ֹͣ����ִ��
                }
            } else {
                request.setAttribute("message", "ҩƷ�����ڣ�");
                RequestDispatcher dispatcher = request.getRequestDispatcher("DrugSale.jsp");
                dispatcher.forward(request, response);
                return;  // ���ҩƷ�����ڣ�ֹͣ����ִ��
            }

            // ִ�п�����
            updateStmt.setInt(1, Integer.parseInt(saleQuantity));
            updateStmt.setInt(2, Integer.parseInt(stockId));
            updateStmt.executeUpdate();

            // �� drug ���󴫵ݵ� JSP ҳ��
            request.setAttribute("drug", drug);
            // ת���� DrugSaleSuccess.jsp ҳ��
            request.getRequestDispatcher("DrugSaleSuccess.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "���ݿ����ʧ�ܣ�" + e.getMessage());
            request.getRequestDispatcher("errorPage.jsp").forward(request, response);
        }
    }
}
