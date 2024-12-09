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
                <th>ID</th>
                <th>药品厂家</th>
                <th>药品名称</th>
                <th>药品品牌</th>
                <th>药品产地</th>
                <!-- <th>批号</th>
                <th>生产日期</th>
                <th>到期日期</th> -->
                <th>操作</th>
            </tr>
        </thead>

        <tbody>
            <c:forEach var="drug" items="${drugList}">
                <tr>
                    <td>${drug.id}</td>
                    <td>${drug.manufacturer}</td>
                    <td>${drug.name}</td>
                    <td>${drug.brand}</td>
                    <td>${drug.origin}</td>
                    <!-- <td>${drug.batchNumber}</td>
                    <td>${drug.productionDate}</td>
                    <td>${drug.expirationDate}</td> -->
                    
                    <td>
                        <form action="DrugUpdate" method="GET" style="display:inline;">
                            <input type="hidden" name="id" value="${drug.id}">
                            <button type="submit">修改</button>
                        </form>
                        <form action="DrugDelete" method="POST" style="display:inline;">
                            <input type="hidden" name="id" value="${drug.id}">
                            <button type="submit" class="delete">删除</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
