<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>药品查询</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 20px;
        }
        h2 {
            text-align: center;
        }
        form {
            width: 50%;
            margin: 20px auto;
            text-align: center;
        }
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
        }
        table th, table td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }
        table th {
            background-color: #f9f9f9;
        }
        button {
            padding: 5px 10px;
            border: none;
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
            border-radius: 3px;
        }
        button.delete {
            background-color: #f44336;
        }
    </style>
</head>
<body>
    <center>
        <a href="DrugQuery.jsp">药品查询</a>
        <a href="DrugQuery.jsp">药品管理</a>
        <a href="StockDrugQuery.jsp">药品入库</a>
        <a href="StockQuery.jsp">库存销售</a>
        <a href="dashboard.jsp">主页</a>
        <a href="index.jsp">退出登录</a>
    </center>
    
    <h2>药品查询</h2>
    <form action="StockDrugQuery" method="GET">
        <label for="keyword">搜索药品（名称、品牌、生产商等）：</label>
        <input type="text" name="keyword" id="keyword" placeholder="请输入关键词">
        <button type="submit">查询</button>
    </form>
</body>
</html>
