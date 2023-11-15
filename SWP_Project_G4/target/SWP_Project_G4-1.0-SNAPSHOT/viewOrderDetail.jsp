<%-- 
    Document   : OrderList
    Created on : Oct 17, 2023, 4:39:35 PM
    Author     : HP
--%>

<%@page import="Models.Products"%>
<%@page import="DAOs.ProductDAO"%>
<%@page import="Models.OrderDetail"%>
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
        <title>Order Detail</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="<%out.print(request.getContextPath());%>/public/assets/css/adminOrderList.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="node_modules/bootstrap-social/bootstrap-social.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

    </head>
    <body>


        <%
            AccountDAO accDAO = new AccountDAO();
            String username = (String) request.getSession().getAttribute("acc");
            Account acc = accDAO.GetAccountUser(username);
        %>
        <div class="table-content">
            <table class="table" id="product-table">
                <!-- Header của bảng -->
                <a class="fa-solid fa-circle-left" href="/Order/OrderHistory/<%= acc.getAccountID()%>" style="width: 50px; height: 50px;color: black;"></a>
                <thead>
                    <tr>
                        <th>ProductID</th>
                        <th>Quantity</th>
                        <th>Size</th>   
                        <th>Price Product</th>
                    </tr>
                </thead>

                <tbody id="table-body">
                    <!-- Bảng dữ liệu sẽ được thêm vào đây -->
                    <!-- tr gốc -->            
                    <!-- Các hàng dữ liệu khác -->
                    <%
                        List<OrderDetail> orderDetail = (List<OrderDetail>) session.getAttribute("orderDetailsInformation");
                        if (orderDetail != null && !orderDetail.isEmpty()) {
                            for (OrderDetail odt : orderDetail) {
                    %>
                    <tr>        

                        <td>
                            <% ProductDAO prDAO = new ProductDAO();
                                Products pr = prDAO.getProduct(odt.getProductID());%>
                            <img src="<%= pr.getImage()%>" style="width: 50px; height: 50px;" />
                            <p><%= pr.getProductName()%></p>
                        </td>
                        <td><%= odt.getQuantity()%></td>
                        <td><%= odt.getSize()%></td>
                        <td><%= pr.getPrice()%></td>
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
