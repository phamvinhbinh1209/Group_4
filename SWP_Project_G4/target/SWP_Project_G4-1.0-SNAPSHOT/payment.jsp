<%-- 
    Document   : payment
    Created on : Oct 30, 2023, 12:17:21 AM
    Author     : DELL
--%>

<%@page import="DAOs.ProductDAO"%>
<%@page import="Models.Products"%>
<%@page import="java.util.List"%>
<%@page import="Models.Cart"%>
<%@page import="Models.Cart"%>
<%@page import="DAOs.CartDAO"%>
<%@page import="Models.Account"%>
<%@page import="DAOs.AccountDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <title>Payment</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta http-equiv="x-ua-compatible" content="ie=edge" />
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.min.css" />
        <link rel="stylesheet" href="node_modules/font-awesome/css/font-awesome.min.css" />
        <link rel="stylesheet" href="node_modules/bootstrap-social/bootstrap-social.css" />

        <link rel="stylesheet" type="text/css" href="<%out.print(request.getContextPath());%>/public/assets/css/payment.css" />
    </head>

    <body>
        <h1>Payment Information</h1>
        <%
            ProductDAO productDAO = new ProductDAO();
            CartDAO cartDAO = new CartDAO();
            AccountDAO accDAO = new AccountDAO();
            String username = (String) session.getAttribute("acc");
            Account acc = accDAO.GetAccountUser(username);
            if (acc != null) {
                List<Cart> cartList = cartDAO.getCartsByUserID(acc.getAccountID());
                int totalPrice = (int) session.getAttribute("totalPrice");
        %>
        <form action="Payment" method="post">
            <div style="display: flex; ">

                <div style="flex: 1; padding: 20px; margin-left: 100px">
                    <!-- Phần bên phải: Nhập thông tin khách hàng -->
                    <h2 class="section-title">User Information</h2>
                    <form action="process_payment.jsp" method="post">
                        <!-- Đây là ví dụ các trường thông tin khách hàng -->
                        <label for="name">Full Name:</label>
                        <input type="text" id="name" name="name" value="<%= acc.getLastName()%> <%= acc.getFirstName()%>" required><br><br>

                        <label for="email">Email:</label>
                        <input type="email" id="email" name="email" value="<%= acc.getEmail()%>"required><br><br>

                        <label for="phone">Phone:</label>
                        <input type="text" id="Phone" name="Phone" maxlength="10" required><br><br>
                        <label for="address">Address:</label>
                        <input type="text" id="address" name="address" required><br><br>
                        <input type="hidden" name="timestamp" id="timestamp"><!-- comment -->
                        <input type="hidden" name="Status" id="status" value="Wait"></form>
                </div>
                <div style="flex: 1; padding: 20px; display: flex; flex-direction: column; margin-right: 100px">
                    <h2 class="section-title">Order List</h2>
                    <ul>
                        <%
                            for (Cart item : cartList) {
                                Products product = productDAO.getProduct(item.getProductID());
                        %>
                        <li>
                            <div style="display: flex; justify-content: space-between;">
                                <span class="product-name"><%= product.getProductName()%></span>
                                <span class="product-price"><%= item.getPrice()%> VND</span>
                            </div>
                            <div style="display: flex; justify-content: space-between;">
                                <span class="product-details">Size: <%= item.getSize()%></span> <span> x<%= item.getQuantity()%></span>
                            </div>
                        </li>
                        <%
                            }
                        %>
                    </ul>
                    <div class="total-section" style="display: flex; justify-content: space-between; margin-top: 20px;">
                        <span class="total-label">Total:</span>
                        <span class="total-price"><%= totalPrice%> VND</span>
                    </div>
                    <input type="submit" value="Order" name="btnOrder" class="order-button" style="width: 150px; height: 50px; margin: 20px auto;">
                </div>

            </div>
        </form>
        <%
            }
        %>         
    </body>
    <script>

    </script>
</html>