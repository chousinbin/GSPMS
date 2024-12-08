package controller;

import model.DB;
import model.Drug;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class StockQuery extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String sql;

        String keyword = request.getParameter("keyword");
        if (keyword == null || keyword == "") {
            sql = 
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
            "ORDER BY drugs.name ASC";
        } else {
            sql = "SELECT " +
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
            "WHERE " + 
            "(manufacturer LIKE '%" + keyword + "%' OR " + 
            "name LIKE '%" + keyword + "%' OR " + 
            "brand LIKE '%" + keyword + "%' OR " + 
            "origin LIKE '%" + keyword + "%') " + 
            "ORDER BY drugs.name ASC";
        }

        List<Drug> drugList = new ArrayList<>();
        try {
            DB db = new DB();

            ResultSet rs = db.excuteQ(sql);

            while (rs.next()) {
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
                drugList.add(drug);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("drugList", drugList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("StockQueryResult.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
