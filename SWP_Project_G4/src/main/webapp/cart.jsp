<%-- 
    Document   : cart
    Created on : Oct 30, 2023, 12:14:07 AM
    Author     : DELL
--%>

<%@page import="DAOs.ProductDAO"%>
<%@page import="Models.Products"%>
<%@page import="DAOs.AccountDAO"%>
<%@page import="Models.Account"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="DAOs.CartDAO"%>
<%@page import="Models.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

    <head>
        <title>Shopping Cart</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta http-equiv="x-ua-compatible" content="ie=edge" />
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="node_modules/bootstrap/dist/css/bootstrap.min.css" />
        <link rel="stylesheet" href="node_modules/font-awesome/css/font-awesome.min.css" />
        <link rel="stylesheet" href="node_modules/bootstrap-social/bootstrap-social.css" />

        <link rel="stylesheet" type="text/css" href="<%out.print(request.getContextPath());%>/public/assets/css/cart.css"/>
    </head>

    <body>
        <div class="wrapper">
            <main>
                <div class="content">
                    <h1 class="page-content">SHOPPING CART</h1>
                </div>

                <div class="small-container cart-page">
                    <table>
                        <tr>
                            <th>Product</th>
                            <th>Size</th>                      
                            <th>Quantity</th>
                            <th></th>
                            <th>Price</th>
                        </tr>
                        <%  AccountDAO accDAO = new AccountDAO();
                            ProductDAO productDAO = new ProductDAO();
                            CartDAO cartDAO = new CartDAO();
                            String username = (String) session.getAttribute("acc");
                            Account acc = accDAO.GetAccountUser(username);
                            CartDAO dao = new CartDAO();
                            if (acc != null) {
                                List<Cart> carts = dao.getCartsByUserID(acc.getAccountID());
                                int totalPrice = 0;
                                for (Cart item : carts) {
                                    totalPrice += item.getTotalPrice();
                                }
                                session.setAttribute("totalPrice", totalPrice);
                                for (Cart item : carts) {
                                    int Quantity = cartDAO.getProductQuantity(item.getSize(), item.getProductID());
                                    Products product = productDAO.getProduct(item.getProductID());
                        %>
                        <form action="Cart" method="post">
                            <tr>
                                <td>
                                    <div class="cart-info">
                                        <input type="hidden" name="CartID" value="<%= item.getCartID()%>">
                                        <img srcset="<%= product.getImage()%>">
                                        <div>
                                            <p><% out.print(product.getProductName());%></p>
                                            <small><%out.print(item.getPrice());%> VND</small>
                                            <br>
                                            <a href="Cart/Delete/<%= item.getCartID()%>">Remove</a>
                                        </div>
                                    </div>
                                </td>
                                <td><h3><%out.print(item.getSize());%></h3> <input type="hidden" name="NumSize" value="<%= item.getSize()%>"></td>

                                <td>
                                    <input name="Quantity" id="Quantity" type="number" min="1" max="<%= Quantity%>" value="<%= item.getQuantity()%>" oninput="checkValue()">
                                    <input type="submit" value="Update" name="btnUpdate" style="width:70px;height:30px">
                                    <div id="error-message" style="color: red;"></div>
                                </td>

                                <td>
                                   <!-- <input type="checkbox" class="product-checkbox" data-price="<%= item.getTotalPrice()%>" cartID="<%= item.getCartID()%>"> -->
                                    <!-- Rest of the product information -->
                                </td>
                                <td><%out.print(item.getTotalPrice());%> VND</td>
                            </tr>
                        </form>  
                        <%
                            }

                        %>
                    </table>

                    <div class="total-price">
                        <table>                
                            <tr>
                                <td>Total Price</td>
                                <td><%= totalPrice%> VND</td>
                            </tr>
                        </table>
                    </div>
                    <%
                        }
                    %>
                    <div class="continue-payment">
                        <a href="/Home">RETURN TO PURCHASE</a>
                        <a href="/Payment">CONTINUE PAYMENT</a>
                    </div>

                </div>
            </main>


        </div>


    </body>
    <script>
        document.addEventListener("DOMContentLoaded", function () {
            var checkboxes = document.querySelectorAll(".product-checkbox");
            var totalAmount = 0;

            checkboxes.forEach(function (checkbox) {
                checkbox.addEventListener("change", function () {
                    var price = parseFloat(checkbox.getAttribute("data-price"));
                    if (checkbox.checked) {
                        totalAmount += price;
                    } else {
                        totalAmount -= price;
                    }

                    // Hiển thị tổng tiền
                    var totalAmountElement = document.getElementById("totalAmount");
                    totalAmountElement.textContent = totalAmount + " VND";
                });
            });
        });

        function checkValue() {
            var inputElement = document.getElementById("Quantity");
            var errorMessageElement = document.getElementById("error-message");
            var value = parseInt(inputElement.value);
            var max = parseInt(inputElement.getAttribute("max"));
            var min = parseInt(inputElement.getAttribute("min"));

            if (value > max) {
                errorMessageElement.textContent = "";
                inputElement.setCustomValidity("Currently there are only " + max + " products left in the system. Please update the quantity");
            } else if (value < min) {
                errorMessageElement.textContent = "";
                inputElement.setCustomValidity("The value cannot be lower than " + min);
            } else {
                errorMessageElement.textContent = "";
                inputElement.setCustomValidity("");
            }
        }
    </script>

</html>
