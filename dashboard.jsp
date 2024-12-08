<%@ page import="model.User" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <title>主页</title>
</head>
<body>
    <% 
        // 从 session 中获取 User 对象
        User user = (User) session.getAttribute("user");
        if (user != null) {
            String username = user.getUsername();
            String role = user.getRole();
    %>
    <h1>欢迎你，<%= username %></h1>
    <h2>角色：<%= role %></h2>
    <ul>
        <% if ("admin".equals(role) || "cashier".equals(role)) { %>
            <li><a href="DrugAdd.jsp">药品添加</a></li>
        <% } %>

        <% if ("admin".equals(role) || "cashier".equals(role)) { %>
            <li><a href="DrugQuery.jsp">药品查询</a></li>
        <% } %>

        <% if ("admin".equals(role) || "stock".equals(role)) { %>
            <li><a href="StockDrugQuery.jsp">药品入库</a></li>
        <% } %>

        <% if ("admin".equals(role) || "cashier".equals(role)) { %>
            <li><a href="sales.jsp">库存盘点</a></li>
        <% } %>

        <% if ("admin".equals(role) || "cashier".equals(role)) { %>
            <li><a href="sales.jsp">出售药品</a></li>
        <% } %>
        
    </ul>
    <a href="index.jsp">退出登录</a>
    <% 
        } else {
            // 如果没有 User 对象，可能是 session 过期或没有登录
            out.println("Session expired. Please log in again.");
        }
    %>
</body>
</html>
