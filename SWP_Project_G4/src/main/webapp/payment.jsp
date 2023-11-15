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
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="node_modules/bootstrap-social/bootstrap-social.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css">

        <link rel="stylesheet" type="text/css" href="<%out.print(request.getContextPath());%>/public/assets/css/payment.css" />
    </head>

    <body>
<div class="d-flex align-items-center">
    <a class="fa-solid fa-circle-left" href="/Cart" style="width: 50px; height: 50px;color: black;"></a>
    <h1 class="mx-auto">Payment Information</h1>
</div>

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
            <div style="display: flex;">

                <div style="flex: 1; padding: 20px;">
                    <!-- Phần bên phải: Nhập thông tin khách hàng -->
                    <h2>User Information</h2>
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
                </div><div style="flex: 1; padding: 20px; display: flex; flex-direction: column;">
                    <h2>Order</h2>
                    <ul>
                        <%
                            for (Cart item : cartList) {
                                Products product = productDAO.getProduct(item.getProductID());
                        %>
                        <li>
                            <div style="display: flex; justify-content: space-between;">
                                <span><%= product.getProductName()%></span>
                                <span><%= item.getPrice()%> VND</span>
                            </div>
                            <div style="display: flex; justify-content: space-between;">
                                <span>Size: <%= item.getSize()%></span>
                                <span>x<%= item.getQuantity()%></span>
                            </div>
                        </li>
                        <%
                            }
                        %>
                    </ul>
                    <div style="display: flex; justify-content: space-between; margin-top: 20px;">
                        <span>Total:</span>
                        <span><%= totalPrice%> VND</span>
                    </div>
                    <input type="submit" value="Order" name="btnOrder" style="width: 150px; height: 50px; margin: 20px auto;">
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