<%-- 
    Document   : orderList
    Created on : Oct 18, 2023, 3:48:22 PM
    Author     : Hung
--%>

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
                                <a href="/AdminController/Home">Home</a>
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
                                <a href="adminOrder.jsp">Order List</a>
                            </div>
                        </div>

                        <div class="row-3" style="display: flex;">
                            <div class="col-md-2 import">
                                <i class="fa-solid fa-industry fa-xl" style="padding-top: 18px; padding-left: 1px;"></i>
                            </div class="import">
                            <div class="col-md-10 py-2">
                                <a href="/AdminController/ImportSource">Import Source</a>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-10">
                    <div class="row admin-feature">
                        <div class="col-md-9">
                            <div class="row">
                                <div class="col-md-3" style="margin-top: 11px">
                                    <a href="/AdminController/AddNewProduct" class="rounded-button" style="padding: 4px;">Add New</a>
                                </div>
                                <div class="col-md-9 show-proper">
                                    <div class="row">
                                        <p class="show-proper-p" style="margin-top: 8px; margin-right: 6px;">Show</p>
                                        <div class="show-proper-select">
                                            <select class="rounded-button" style="height: 30px; margin-top: 6px" id="quantity">
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
                                        <p class="show-proper-p" style="margin-top: 8px; margin-left: 5px;">Order</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-3 search-box">
                            <div class="input-group">
                                <div class="text-right">
                                    <input style="border-radius: 20px; border: 2px solid black; height: 30px; margin-top: 6px" type="text"
                                           class="form-search" id="tableSearch" placeholder="Search" />
                                    <!--<input type="text" id="" placeholder="Tìm kiếm...">-->
                                    <button id="search-button" style="border-radius: 10px; height: 30px;"><span><i
                                                class="fa-solid fa-magnifying-glass"></i></span></button>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="table-content">
                        <table class="table" id="product-table">
                            <!-- Header của bảng -->
                            <thead id="table-header">
                                <tr>
                                    <th>ID</th>
                                    <th>Product Name</th>
                                    <th>Brand</th>
                                    <th>Color</th>
                                    <th>Size</th>
                                    <th>Quantity</th>
                                    <th>Price</th>
                                    <th>Picture</th>
                                    <th></th>
                                </tr>
                            </thead>

                            <tbody id="table-body">
                                <!-- Body của  -->
                                <%
                                    AdminDAOs dao = new AdminDAOs();
                                    ResultSet rs = dao.GetAllProduct();
                                    while (rs.next()) {
                                %>
                                <tr>
                                    <td><%= rs.getInt("ID")%></td>
                                    <td><%= rs.getString("ProductName")%></td>
                                    <td><%= rs.getString("Brand")%></td>
                                    <td><%= rs.getString("Color")%></td>
                                    <td><%= rs.getInt("Size")%></td>
                                    <td><%= rs.getInt("Quantity")%></td>
                                    <td><%= rs.getInt("Price")%></td>
                                    <td>
                                        <img style="height: 60px; width: 70px" src=<%= rs.getString("Picture")%>>
                                    </td>
                                    <td class="d-flex">
                                        <a onclick="return confirm('Are you sure to delete?')" href="/AdminController/DeleteProduct/<%= rs.getInt("ID")%>">
                                            <i class="fa-solid fa-trash fa-xl mr-2 mt-2 xoaSapXep" alt="Xóa Sản Phẩm Đã Chọn"></i></a>
                                        <a href="/AdminController/UpdateProduct/<%= rs.getInt("ID")%>">
                                            <i class="fa-solid fa-pen-to-square fa-xl mt-2" alt="Chỉnh Sửa"></i></a>
                                    </td>
                                    <!-- Ô trống -->
                                </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>

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
                </div>
            </div>
        </div>
        <script src="<%out.print(request.getContextPath());%>/public/assets/js/adminOrder.js"></script>
    </body>
</html>
