<%@ page import="model.Drug" %>
<%@ page import="java.util.List" %>
<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>药品出售</title>
</head>
<body>
    <h1>药品出售</h1>
    <form action="sales.jsp" method="post">
        <table border="1">
            <thead>
                <tr>
                    <th>药品名称</th>
                    <th>价格</th>
                    <th>库存</th>
                    <th>购买数量</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Drug> drugList = (List<Drug>) request.getAttribute("QueryDrug");
                    for (Drug drug : drugList) {
                %>
                <tr>
                    <td><%= drug.getName() %></td>
                    <td><%= drug.getPrice() %></td>
                    <td><%= drug.getStock() %></td>
                    <td><input type="number" name="quantity_<%= drug.getId() %>" min="1" max="<%= drug.getStock() %>" required></td>
                    <td><button type="submit" name="drugId" value="<%= drug.getId() %>">购买</button></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </form>
</body>
</html>
