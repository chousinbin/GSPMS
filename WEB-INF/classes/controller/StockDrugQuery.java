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

public class StockDrugQuery extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String sql;

        String keyword = request.getParameter("keyword");
        if (keyword == null || keyword == "") {
            sql = "select * from drugs";
        } else {
            sql = "SELECT * " + 
            "FROM drugs " + 
            "WHERE " + 
            "manufacturer LIKE '%" + keyword + "%' OR " + 
            "name LIKE '%" + keyword + "%' OR " + 
            "brand LIKE '%" + keyword + "%' OR " + 
            "origin LIKE '%" + keyword + "%'"; 
        }

        List<Drug> drugList = new ArrayList<>();
        try {
            DB db = new DB();

            ResultSet rs = db.excuteQ(sql);

            while (rs.next()) {
                Drug drug = new Drug();
                drug.setId(String.valueOf(rs.getInt(1)));
                drug.setManufacturer(rs.getString(2));
                drug.setName(rs.getString(3));
                drug.setBrand(rs.getString(4));
                drug.setOrigin(rs.getString(5));
                // drug.setBatchNumber(rs.getString(6));
                // drug.setProductionDate(rs.getDate(7).toString());
                // drug.setExpirationDate(rs.getDate(8).toString());
                drugList.add(drug);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("drugList", drugList);

        RequestDispatcher dispatcher = request.getRequestDispatcher("StockDrugQueryResult.jsp");
        dispatcher.forward(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
