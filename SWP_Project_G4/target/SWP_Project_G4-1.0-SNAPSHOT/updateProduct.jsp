




<%@page import="Models.Account"%>
<%@page import="DAOs.AccountDAO"%>
<%@page import="Models.Products"%>
<%-- 
    Document   : OrderList
    Created on : Oct 17, 2023, 4:39:35 PM
    Author     : HP
--%>
<%@page import="Models.ImportSource"%>
<%@page import="Models.Order"%>
<%@page import="DAOs.AdminDAOs"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="head.jsp">
            <jsp:param name="title" value="Update Product"/>
        </jsp:include>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Navbar với Bootstrap</title>
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
        <div class="container-fluid myheader">
            <div class="row">
                <div class="col-md-2">
                    <div class="row mt-2">
                        <div class="col-md-3 ">
                            <img src="<%out.print(request.getContextPath());%>/public/assets/imgs/Logo.png" alt="" style="height: 50px; width: 50px;" />
                        </div>
                        <div class="col-md-9 py-3">ADMIN</div>
                    </div>
                </div>



                <div class="col-md-2" style="margin-left: 800px">
                    <div class="row">
                        <div class="col-md-6">
                            <div class="row">
                                <div class="col-md-4 py-1">
                                    <i class="fa-solid fa-user fa-xl mt-md-4"></i>
                                </div>
                                <div class="col-md-8 py-3">
                                    <a><%= acc.getUsername()%></a>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="row">
                                <div class="col-md-4 py-1">
                                    <i class="fa-solid fa-right-from-bracket fa-xl mt-md-4"></i>
                                </div>
                                <div class="col-md-8 py-3">
                                    <a href="/Login">Logout</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="container-fluid">
            <div class="row">
                <div class="col-md-2 list-group" style="background-color: grey">
                    <div class="row row-cols-1 px-md-4">
                        <div class="row-3">
                            <div class="col-md-3 home">
                                <i class="fa-solid fa-house fa-xl" style="padding-top: 18px;"></i>
                            </div>
                            <div class="col-md-9 py-2">
                                <a href="/Admin/WareHouse">WareHouse</a>
                            </div>
                        </div>
                        <div class="row-3">
                            <div class="col-md-3 user">
                                <i class="fa-solid fa-user fa-xl" style="padding-top: 18px; padding-left: 3px;"></i>
                            </div>
                            <div class="col-md-9 py-2">
                                <a href="/Admin/userList">User List</a>
                            </div>
                        </div>
                        <div class="row-3">
                            <div class="col-md-3 order">
                                <i class="fa-solid fa-cart-shopping fa-xl" style="padding-top: 18px;"></i>
                            </div>
                            <div class="col-md-9 py-2">
                                <a href="/Admin">Order List</a>
                            </div>
                        </div>
                        <div class="row-3">
                            <div class="col-md-3 import">
                                <i class="fa-solid fa-industry fa-xl" style="padding-top: 18px; padding-left: 2px;"></i>
                            </div class="import">
                            <div class="col-md-9 py-2">
                                <a href="/Admin/ImportSource">Import Source</a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-10">
                    <a class="fa-solid fa-circle-left" href="/Admin/WareHouse" style="width: 50px; height: 50px;color: black;"></a>
                    <div class="update-form" style="padding: 80px">
                        <h1 class="title">UPDATE PRODUCT</h1>
                        <%
                            Products pr = (Products) session.getAttribute("productInformation");
                        %>
                        <form method="post" action="AdminController">
                            <fieldset>
                                <div class="row inputblock">
                                    <div class="col-md-6">
                                        ProductID:
                                        <input  type="number" required="" name="paraProductID" readonly="true" style="margin-left: 50px;" value="<%= pr.getProductID()%>"><br>
                                        Image:
                                        <input  type="text" required="" name="paraImage" style="margin-left: 78px; margin-top: 10px;" value="<%= pr.getImage()%>"><br>
                                        ProductName:
                                        <input  type="text" required="" name="paraProductName" style="margin-left: 25px; margin-top: 10px;" value="<%= pr.getProductName()%>"><br>
                                        CategoryID:
                                        <input  type="text" required="" name="paraCategoryID" style="margin-left: 44px; margin-top: 10px;" value="<%= pr.getCategoryID()%>"><br>
                                    </div>
                                    <div class="col-md-6">
                                        BrandID:
                                        <input  type="number" required="" name="paraBrandID" style="margin-left: 35px;" value="<%= pr.getBrandID()%>"><br>
                                        Price:
                                        <input  type="number" required="" name="paraPrice" style="margin-left: 57px; margin-top: 10px;" value="<%= pr.getPrice()%>"><br>
                                        Description:
                                        <input  type="text" required="" name="paraDescription" style="margin-left: 10px; margin-top: 10px;" value="<%= pr.getDescription()%>"><br>
                                        <div class="submitinf" style="margin-left: 160px; margin-top: 10px;">
                                            <input type="reset" value="Clear" name="btnSubmitUpdate">
                                            <input type="submit" value="Confirm" name="btnSubmitUpdate">
                                        </div>
                                    </div>

                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script>
  // Đợi 5 giây trước khi xóa div
            setTimeout(function () {
                var messageDiv = document.getElementById('message');
                messageDiv.parentNode.removeChild(messageDiv);
            }, 2000);
        </script>
        <%@include file="foot.jsp" %>
        <%@include file="popUpMessage.jsp" %>
        <script src="<%out.print(request.getContextPath());%>/public/assets/js/adminOrderList.js"></script>
    </body>
</html>
