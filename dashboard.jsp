<%@ page import="model.User" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>主页</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            color: #333;
        }
        header {
            background-color: #3c503e;
            color: white;
            padding: 10px 20px;
            text-align: center;
        }
        h1, h2 {
            margin: 20px 0;
            text-align: center;
        }
        ul {
            list-style-type: none;
            padding: 0;
            margin: 20px auto;
            max-width: 400px;
        }
        ul li {
            margin: 10px 0;
        }
        ul li a {
            display: block;
            padding: 10px 15px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            text-align: center;
            transition: background-color 0.3s;
        }
        ul li a:hover {
            background-color: #45a049;
        }
        .message {
            text-align: center;
            margin: 20px;
        }
    </style>
</head>
<body>
    <% 
        User user = (User) session.getAttribute("user");
        if (user != null) {
            String username = user.getUsername();
            String role = user.getRole();
    %>
    <header>
        <h1>欢迎你，<%= username %></h1>
        <h2>角色：<%= role %></h2>
    </header>
    <ul>
        <% if ("admin".equals(role) || "cashier".equals(role)) { %>
            <li><a href="DrugAdd.jsp">药品添加</a></li>
        <% } %>
        <% if ("admin".equals(role) || "cashier".equals(role)) { %>
            <li><a href="DrugQuery.jsp">药品管理</a></li>
        <% } %>
        <% if ("admin".equals(role) || "stock".equals(role)) { %>
            <li><a href="StockDrugQuery.jsp">药品入库</a></li>
        <% } %>
        <% if ("admin".equals(role) || "cashier".equals(role)) { %>
            <li><a href="StockQuery.jsp">库存销售</a></li>
        <% } %>
        <% if ("admin".equals(role) || "cashier".equals(role)) { %>
            <li><a href="index.jsp">退出登录</a></li>
        <% } %>
    </ul>
    <% 
        } else {
    %>
    <div class="message">
        <p>Session 已过期，请重新登录。</p>
        <a href="index.jsp" style="display: inline-block; padding: 10px 15px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 5px;">重新登录</a>
    </div>
    <% 
        }
    %>
</body>
</html>
