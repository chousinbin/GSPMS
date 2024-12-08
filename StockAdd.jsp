<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>药品入库</title>
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
        <a href="DrugQuery.jsp">药品查询</a>
        <a href="DrugAdd.jsp">添加药品</a>
        <a href="dashboard.jsp">操作主页</a>
        <a href="index.jsp">退出登录</a>
        <h1>药品入库</h1>

        <form action="StockAdd" method="POST">
            <input type="hidden" name="id" value="${drug.id}">
            <label>药品厂家:</label>
            <input type="text" name="manufacturer" value="${drug.manufacturer}" readonly>
            <label>药品名称:</label>
            <input type="text" name="name" value="${drug.name}" readonly>
            <label>药品品牌:</label>
            <input type="text" name="brand" value="${drug.brand}" readonly>
            <label>药品产地:</label>
            <input type="text" name="origin" value="${drug.origin}" readonly>
            <label>药品批号:</label>
            <input type="text" name="batch_number" value="">
            <label>生产日期:</label>
            <input type="date" name="production_date" value="">
            <label>过期日期:</label>
            <input type="date" name="expiration_date" value="">
            <label>入库数量:</label>
            <input type="text" name="quantity" value="">
            <label>药品进价:</label>
            <input type="text" name="purchase_price" value="">
            <label>药品售价:</label>
            <input type="text" name="sale_price" value="">

            <input type="submit" value="添加库存">
        </form>
    </div>
</body>
</html>