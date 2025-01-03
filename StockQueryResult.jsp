<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>药品查询结果</title>
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
    <!-- <p>drugList大小: ${drugList.size()}</p> -->
    <table>
        <thead>
            <tr>
                <th>药品ID</th>
                <th>库存ID</th>
                <th>药品厂家</th>
                <th>药品名称</th>
                <th>药品品牌</th>
                <th>药品产地</th>
                <th>批号</th>
                <th>生产日期</th>
                <th>到期日期</th>
                <th>库存数量</th>
                <th>进货价格</th>
                <th>零售价格</th>
                <th>操作</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach var="drug" items="${drugList}">
                <tr>
                    <td>${drug.id}</td>
                    <td>${drug.stockId}</td>
                    <td>${drug.manufacturer}</td>
                    <td>${drug.name}</td>
                    <td>${drug.brand}</td>
                    <td>${drug.origin}</td>
                    <td>${drug.batchNumber}</td>
                    <td>${drug.productionDate}</td>
                    <td>${drug.expirationDate}</td>
                    <td>${drug.quantity}</td>
                    <td>${drug.purchasePrice}</td>
                    <td>${drug.salePrice}</td>
                    
                    <td>
                        <form action="StockDelete" method="POST" style="display:inline;">
                            <input type="hidden" name="stockId" value="${drug.stockId}">
                            <button type="submit" class="delete">删除</button>
                        </form>
                        <form action="DrugSale" method="GET" style="display:inline;">
                            <input type="hidden" name="stockId" value="${drug.stockId}">
                            <button type="submit" >出售</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
