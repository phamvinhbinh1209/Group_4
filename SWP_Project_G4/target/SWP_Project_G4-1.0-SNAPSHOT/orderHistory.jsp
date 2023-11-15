<%-- 
    Document   : OrderList
    Created on : Oct 17, 2023, 4:39:35 PM
    Author     : HP
--%>

<%@page import="java.util.List"%>
<%@page import="DAOs.OrderDAO"%>
<%@page import="Models.Account"%>
<%@page import="Models.Order"%>
<%@page import="DAOs.AccountDAO"%>
<%@page import="DAOs.AdminDAOs"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order History</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%out.print(request.getContextPath());%>/public/assets/css/adminOrderList.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="node_modules/bootstrap-social/bootstrap-social.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

    </head>
    <body>


        <div class="table-content">
            <table class="table" id="product-table">
                <!-- Header của bảng -->
                <a class="fa-solid fa-circle-left" href="/Home" style="width: 50px; height: 50px;color: black;"></a>
                <thead>
                    <tr>
                        <th>OrderID</th>
                        <th>OrderDate</th>
                        <th>Address</th>
                        <th>Phone</th>
                        <th>TotalPrice</th>
                        <th>Delivery</th>
                        <th>Status</th>
                        <th></th>
                    </tr>
                </thead>

                <tbody id="table-body">
                    <!-- Bảng dữ liệu sẽ được thêm vào đây -->
                    <!-- tr gốc -->            
                    <!-- Các hàng dữ liệu khác -->
                    <%
                        List<Order> orderList = (List<Order>) session.getAttribute("orderInformation");
                        if (orderList != null && !orderList.isEmpty()) {
                            for (Order od : orderList) {
                    %>
                    <tr>                                   
                        <td><%= od.getOrderID()%></td>
                        <td><%= od.getDate()%></td>
                        <td><%= od.getAddress()%></td>
                        <td><%= od.getPhone()%></td>
                        <td><%= od.getTotalPrice()%></td>
                        <td><%= od.getDeliveryDate()%></td>
                        <td><%= od.getStatus()%></td>
                        <td class="d-flex">          
                            <a class="fa-solid fa-eye" href="/Order/ViewOrderDetail/<%= od.getOrderID()%>" style="color: black;"></a>
                        </td>
                    </tr>
                    <%
                        }
                    } else {
                    %>
                    <!-- Hiển thị thông báo nếu không có dữ liệu -->
                    <tr>
                        <td colspan="10">Không có đơn hàng để hiển thị.</td>
                    </tr>
                    <%
                        }
                    %>


                </tbody>
            </table>
        </div>

    </body>
</html>
