<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>药品信息修改</title>
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
    <a href="DrugQuery.jsp">药品查询</a>
    <a href="DrugAdd.jsp">添加药品</a>
    <a href="dashboard.jsp">操作主页</a>
    <a href="index.jsp">退出登录</a>
    <h2>药品信息修改</h2>

    <form action="DrugUpdate" method="POST">
        <input type="hidden" name="id" value="${drug.id}">
        生产厂家: <input type="text" name="manufacturer" value="${drug.manufacturer}">
        <br>药品名称: <input type="text" name="name" value="${drug.name}">
        <br>药品品牌: <input type="text" name="brand" value="${drug.brand}">
        <br>药品产地: <input type="text" name="origin" value="${drug.origin}">
        <br>产品批号: <input type="text" name="batch_number" value="${drug.batchNumber}">
        <br>生产日期: <input type="text" name="production_date" value="${drug.productionDate}">
        <br>过期日期: <input type="text" name="expiration_date" value="${drug.expirationDate}">
        <!-- 其他字段 -->
        <br><button type="submit">修改</button>
    </form>
</body>
</html>