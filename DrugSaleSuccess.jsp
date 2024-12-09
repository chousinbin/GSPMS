<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>售药详细</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form-container {
            background-color: #fff;
            padding: 20px 30px;
            border-radius: 10px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            text-align: center;
            max-width: 500px;
            width: 100%;
        }
        .form-container h1 {
            margin-bottom: 20px;
            color: #333;
        }
        .form-container label {
            display: block;
            margin: 10px 0 5px;
            text-align: left;
        }
        .form-container input[type="text"],
        .form-container input[type="date"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-container input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #6bbf59;
            color: #fff;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .form-container input[type="submit"]:hover {
            background-color: #5da349;
        }
        .message {
            color: green;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <center>
            <a href="DrugQuery.jsp">药品查询</a>
            <a href="DrugQuery.jsp">药品管理</a>
            <a href="StockDrugQuery.jsp">药品入库</a>
            <a href="StockQuery.jsp">库存销售</a>
            <a href="dashboard.jsp">主页</a>
            <a href="index.jsp">退出登录</a>
        </center>
        <h1>售药详情</h1>
        <!-- <jsp:useBean id="drug" class="model.Drug" scope="request" /> -->

        <form action="StockQuery" method="POST">
            <input type="hidden" name="id" value="${drug.id}" readonly>
            <label>药品厂家:</label>
            <input type="text" name="manufacturer" value="${drug.manufacturer}" readonly>
            <label>药品名称:</label>
            <input type="text" name="name" value="${drug.name}" readonly>
            <label>药品品牌:</label>
            <input type="text" name="brand" value="${drug.brand}" readonly>
            <label>药品产地:</label>
            <input type="text" name="origin" value="${drug.origin}" readonly>
            <label>药品单价:</label>
            <input type="text" name="batch_number" value="${drug.salePrice}" readonly>
            <label>药品数量:</label>
            <input type="text" name="production_date" value="${drug.quantity}" readonly>
            <label>药品总价:</label>
            <input type="text" name="expiration_date" value="${drug.saleSumPrice}" readonly style="font-weight: bold; color: green; font-size: xx-large;">
            <input type="submit" value="完成">
        </form>
    </div>
</body>
</html>
