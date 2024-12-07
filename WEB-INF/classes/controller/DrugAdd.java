package controller;
import model.Drug;
import model.DB;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.Date;
import java.time.LocalDate;


public class DrugAdd extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws 
    ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        
        // ��ȡrequest��Ϣ
        Drug drug = new Drug();
        drug.setManufacturer(request.getParameter("manufacturer"));
        drug.setName(request.getParameter("name"));
        drug.setBrand(request.getParameter("brand"));
        drug.setOrigin(request.getParameter("origin"));
        drug.setBatchNumber(request.getParameter("batchNumber"));
        drug.setProductionDate(request.getParameter("productionDate"));
        drug.setExpirationDate(request.getParameter("expirationDate"));
        


        // �ж�Ҫ���Ƿ�Ϊ��
        // if (equNo.equals("") || equNo == null || equName.equals("") || equName == null ||
        //         equAgeSt.equals("") || equAgeSt == null ) {
        //     response.sendRedirect("Sinbin.jsp");
        //     return;
        // }

        // int equAge = Integer.parseInt(equAgeSt);
        
        //�������ݿ�
        DB db = new DB();
        String sql = 
        "INSERT INTO " + 
        "drugs (manufacturer, name, brand, origin, batch_number, production_date, expiration_date) " +  
        "VALUES (?, ?, ?, ?, ?, ?, ?)";
        Object[] params = new Object[] { 
            drug.getManufacturer(), 
            drug.getName(),
            drug.getBrand(),
            drug.getOrigin(),
            drug.getBatchNumber(),
            Date.valueOf(LocalDate.parse(drug.getProductionDate())),
            Date.valueOf(LocalDate.parse(drug.getExpirationDate()))
        };

        int res = db.excuteU(sql, params);
        if (res <= 0) {
            request.setAttribute("message", "���ʧ�ܣ������������ݣ�");
            RequestDispatcher dispatcher = request.getRequestDispatcher("DrugAdd.jsp");
            dispatcher.forward(request, response);
            return;
        } else {
            request.setAttribute("message", "ҩƷ��ӳɹ���");
            RequestDispatcher dispatcher = request.getRequestDispatcher("DrugAdd.jsp");
            dispatcher.forward(request, response);
        }
        

        // ��myBean�洢��session��
        HttpSession session = request.getSession();
        session.setAttribute("Drug", drug);

        // ����
        RequestDispatcher dispatcher = request.getRequestDispatcher("DrugAdd.jsp");
        dispatcher.forward(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws 
    ServletException, IOException {
        doPost(request, response);
    }
}