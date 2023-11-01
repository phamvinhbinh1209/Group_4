<%-- 
    Document   : orderList
    Created on : Oct 18, 2023, 3:48:22 PM
    Author     : Hung
--%>
<%@page import="Models.Product"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.sql.ResultSet"%>
<%@page import="DAOs.AdminDAOs"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="node_modules/bootstrap-social/bootstrap-social.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"
              integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="<%out.print(request.getContextPath());%>/public/assets/css/adminOrder.css">

    </head>
    <body>
        <div class="container-fluid myheader">
            <div class="row">
                <div class="col-md-2">
                    <div class="row">
                        <div class="col-md-4" style="padding-left: 18px; margin-top: 2px;">
                            <img src="public/assets/imgs/logo.png" alt="" style="height: 50px; width: 70px;" />
                        </div>
                        <div class="col-md-8" style="font-size: x-large; padding-top: 10px;">ADMIN</div>
                    </div>
                </div>

                <div class="col-md-8 shope-inform">
                    <div class="row display">
                        <div class="col-md-2 inform">PRODUCT</div>
                        <div class="col-md-2 inform">PRODUCT</div>
                        <div class="col-md-2 inform">PRODUCT</div>
                        <div class="col-md-2 inform">PRODUCT</div>
                    </div>
                </div>

                <div class="col-md-2">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="row">
                                <div class="col-md-4 py-1">
                                    <i class="fa-solid fa-user fa-xl mt-md-4"></i>
                                </div>
                                <div class="col-md-8 py-3">
                                    <a href="link-to-profile-page">Name</a>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="row">
                                <div class="col-md-4 py-1">
                                    <i class="fa-solid fa-right-from-bracket fa-xl mt-md-4"></i>
                                </div>
                                <div class="col-md-8 py-3">
                                    <a href="link-to-logout-page">Logout</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container-fluid mybody">
            <div class="row">
                <div class="col-md-2 list-group" style="background-color: grey; max-height: 500px;">
                    <div class="row row-cols-1 px-md-4">
                        <div class="row-3" style="display: flex;">
                            <div class="col-md-2 home">
                                <i class="fa-solid fa-house fa-xl" style="padding-top: 18px; padding-left: 0px;"></i>
                            </div>
                            <div class="col-md-10 py-2">
                                <a href="link-to-home-page">Home</a>
                            </div>
                        </div>

                        <div class="row-3" style="display: flex;">
                            <div class="col-md-2 user">
                                <i class="fa-solid fa-user fa-xl" style="padding-top: 18px; padding-left: 3px;"></i>
                            </div>
                            <div class="col-md-10 py-2">
                                <a href="link-to-user-list">User List</a>
                            </div>
                        </div>

                        <div class="row-3" style="display: flex;">
                            <div class="col-md-2 order" >
                                <i class="fa-solid fa-cart-shopping fa-xl" style="padding-top: 18px; padding-right: 1px"></i>
                            </div>
                            <div class="col-md-10 py-2">
                                <a href="link-to-order-list">Order List</a>
                            </div>
                        </div>

                        <div class="row-3" style="display: flex;">
                            <div class="col-md-2 import">
                                <i class="fa-solid fa-industry fa-xl" style="padding-top: 18px; padding-left: 1px;"></i>
                            </div class="import">
                            <div class="col-md-10 py-2">
                                <a href="link-to-import-source">Import Source</a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-10">
                    <h1>UPDATE PRODUCT</h1>
                    <%
                        Product pr = (Product) session.getAttribute("productInformation");
                    %>
                    <form method="post" action="AdminController">
                        <fieldset>
                            <div class="row">
                                <div class="col-md-6">
                                    Input ID:
                                    <input type="number" required="" name="paraID" readonly="true" style="margin-left: 50px" value="<%= pr.getID()%>"><br>
                                    Product Name:
                                    <input type="text" required="" name="paraProductName" style="margin-left: 5px" value="<%= pr.getProductName()%>"><br>
                                    Brand:
                                    <input type="text" required="" name="paraBrand" style="margin-left: 66px" value="<%= pr.getBrand()%>"><br>
                                    Color:
                                    <input type="text" required="" name="paraColor" style="margin-left: 69px" value="<%= pr.getColor()%>"><br>
                                </div>
                                <div class="col-md-6">
                                    Size:
                                    <input type="number" required="" name="paraSize" style="margin-left: 38px" value="<%= pr.getSize()%>"><br>
                                    Quantity:
                                    <input type="number" required="" name="paraQuantity" style="margin-left: 5px" value="<%= pr.getQuantity()%>"><br>
                                    Price:
                                    <input type="number" required="" name="paraPrice" style="margin-left: 32px" value="<%= pr.getPrice()%>"><br>
                                </div>
                                <input type="reset" value="Clear" name="btnSubmitUpdate">
                                <input type="submit" value="Confirm" name="btnSubmitUpdate">
                            </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
        <script src="<%out.print(request.getContextPath());%>/public/assets/js/adminOrder.js"></script>
    </body>

    <footer>
        <div class="container-fluid">
            <div class="pagination" style="display: flex; justify-content: center; align-items: center;">
                <button id="prevPage" style="margin-right: 10px; border-radius: 8px; border: 1px solid black;">Previous</button>
                <div class="page-numbers" style="display: flex; align-items: center;">
                    <button class="page-number">1</button>
                    <button class="page-number">2</button>
                    <button class="page-number">3</button>
                </div>
                <button id="nextPage" style="margin-left: 10px; border-radius: 8px; border: 1px solid black;">Next</button>
            </div>
        </div>
    </footer>
</html>

