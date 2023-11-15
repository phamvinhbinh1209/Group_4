<%-- 
    Document   : OrderList
    Created on : Oct 17, 2023, 4:39:35 PM
    Author     : HP
--%>

<%@page import="Models.Account"%>
<%@page import="DAOs.AccountDAO"%>
<%@page import="DAOs.AdminDAOs"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
       <jsp:include page="head.jsp">
            <jsp:param name="title" value="UserList"/>
        </jsp:include>
    <head>
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
                    <div class="add-search-feature">
                        <div class="row add-search-control">
                            <div class="col-md-8 d-flex">
                                <a class="mb-1" style="border-radius: 10px; display: inline-block; padding: 10px 20px; background-color: #007bff; color: #fff; text-decoration: none;" href="/Admin/addNewUser">Add New+</a>
                                <div class=" show-proper mt-2">
                                    <p class="show-proper-p">Show</p>
                                    <div class="show-proper-select">
                                        <select id="quantity" style="border-radius: 10px; border: 2px solid black;">
                                            <option value="1">1</option>
                                            <option value="2">2</option>
                                            <option value="3">3</option>
                                            <option value="4">4</option>
                                            <option value="5">5</option>
                                            <option value="6">6</option>
                                            <option value="7">7</option>
                                            <option value="8">8</option>
                                            <option value="9">9</option>
                                            <option value="10">10</option>
                                        </select>
                                    </div>
                                    <p class="show-proper-p">Order</p>
                                </div>
                            </div>
                            <div class="col-md-4 search-box">
                                <div class="text-right">
                                    <input style="border-radius: 10px; border: 2px solid black;" type="text"
                                           class="form-search" id="tableSearch" placeholder="Search" />
                                    <!--<input type="text" id="" placeholder="Tìm kiếm...">-->
                                    <button id="search-button" style="border-radius: 10px;"><span><i
                                                class="fa-solid fa-magnifying-glass"></i></span></button>
                                </div>
                            </div>
                        </div>
                    </div>


                    <div class="table-content">
                        <table class="table" id="product-table">
                            <!-- Header của bảng -->
                            <thead>
                                <tr>
                                    <th>AccountID</th>
                                    <th>Avatar</th>
                                    <th>Username</th>
                                    <th>Password</th>
                                    <th>Email</th>
                                    <th>FirstName</th>                      
                                    <th>LastName</th>
                                    <th>Gender</th>
                                    <th>Birthday</th>
                                    <th>Role</th>
                                    <th>Active</th>
                                </tr>
                            </thead>
                            <tbody id="table-body">
                                <!-- Bảng dữ liệu sẽ được thêm vào đây -->
                                <!-- tr gốc -->            
                                <!-- Các hàng dữ liệu khác -->
                                <%
                                    AdminDAOs dao = new AdminDAOs();
                                    ResultSet rs = dao.GetAllUser();
                                    while (rs.next()) {
                                %>
                                <tr>                                   
                                    <td><%= rs.getInt("AccountID")%></td>
                                    <td style="max-width: 20px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
                                        <%= rs.getString("avatar")%>
                                    </td>
                                    <td><%= rs.getString("username")%></td>
                                    <td style="max-width: 100px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;"><%= rs.getString("password")%></td>
                                    <td style="max-width: 100px; overflow: hidden; white-space: nowrap; text-overflow: ellipsis;"><%= rs.getString("email")%></td>
                                    <td><%= rs.getString("firstName")%></td>
                                    <td><%= rs.getString("lastName")%></td>
                                    <td><%= rs.getString("gender")%></td>
                                    <td><%= rs.getDate("birthday")%></td>
                                    <td><%= rs.getInt("role")%></td>
                                    <td class="d-flex">                                 
                                        <a onclick="return confirm('Are you sure Delete Account?')" class="fa-solid fa-trash fa-xl mr-2 mt-2 " href="/Admin/deleteUser/<%= rs.getInt("AccountID")%>" style="color: black;"></a>
                                        <a class="fa-solid fa-pen-to-square fa-xl mt-2" href="/Admin/updateUser/<%= rs.getInt("AccountID")%>" style="color: black;"></a>
                                    </td>
                                    <!-- Ô trống -->
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
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
