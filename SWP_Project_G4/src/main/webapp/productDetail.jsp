
<%@page import="Models.Size"%>
<%@page import="java.util.List"%>
<%@page import="DAOs.CartDAO"%>
<%@page import="Models.Products"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Product Detail</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
            crossorigin="anonymous"
            />
        <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
            integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
            crossorigin="anonymous"
            referrerpolicy="no-referrer"
            />
        <link rel="stylesheet" 
              href="<%out.print(request.getContextPath());%>/public/assets/css/home.css"/>
        <link rel="stylesheet" 
              href="<%out.print(request.getContextPath());%>/public/assets/css/productdetail.css"/>
    </head>
    <body>

        
       
        <div class="small-container">
            <a class="fa-solid fa-circle-left" href="/Home" style="width: 80px; height: 80px;color: black; margin-left: 10px"></a>
            <%
                CartDAO cartDAO = new CartDAO();
                Products pr = (Products) session.getAttribute("productInformation");
                int ProductID = pr.getProductID();
                int Price = pr.getPrice();

                session.setAttribute("ProductID", ProductID);
                session.setAttribute("Price", Price);
                List<Size> sizeList = cartDAO.getSizeListByProductID(ProductID);
            %>
            <form method="post" action="UserController">
                <div class="row">
                    <div class="col-12 col-md-6">
                        <img name="Image" src="<%=pr.getImage()%>" width="50%" style="margin-left: 180px; margin-top: 50px;"> 
                    </div>
                    <div class="col-12 col-md-6">
                        <h2 style="display: none;" name="ProductID"><%= pr.getProductID()%></h2>
                        <h1 name="ProductName"><%= pr.getProductName()%></h1>
                        <h4 name="Price" >Price : <%= pr.getPrice()%> VND</h4>
                        <div class="select-container">
                            <div class="select-wrapper">
                                <label for="size">Size</label>
                                <select name="NumSize" id="size">
                                    <%   for (Size item : sizeList) {
                                            if (cartDAO.getProductQuantity(item.getNumSize(), pr.getProductID()) > 0) {
                                    %>
                                    <option value="<%= item.getNumSize()%>"><%= item.getNumSize()%></option>
                                    <%
                                            }
                                        }
                                    %>
                                </select>
                            </div>                                   
                            <div class="select-wrapper">
                                <label for="quantity">Quantity</label>
                                <select name="Quantity" id="quantity">
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
                        </div>   
                        <%
                            String message = (String) session.getAttribute("QuantityError");

                            if (message != null) {
                        %>
                        <h4 style="color:red;"><%= message%></h4>
                        <%
                                session.removeAttribute("QuantityError");
                            }
                        %>
                        <br>
                        <h3>Product Description</h3>
                        <p name="Description"><%=pr.getDescription()%></p>
                        <input type="submit" value="Add To Cart" name="btnAddToCart">
                    </div>
                </div>
            </form>
        </div>

        <div id="footer">
            <div class="contact row">
                <div class="introduction col-md-4">
                    <div class="logo-name">
                        <img src="https://ananas.vn/wp-content/themes/ananas/fe-assets/images/svg/Store.svg"
                             alt="" />
                    </div>
                </div>
                <div class="head help col-md-2">
                    PRODUCT
                    <div>
                        <p>Men's shoes</p>
                        <p>Women's shoes</p>
                        <p>Shoes Sale-off</p>
                    </div>

                    ANANAS SOCIAL
                    <div class="social-media">
                        <i class="fa-brands fa-instagram"></i>
                        <i class="fa-brands fa-facebook"></i>
                        <i class="fa-brands fa-youtube"></i>
                    </div>
                </div>
                <div class="head help col-md-2">
                    HELP
                    <p>Help</p>
                    <p>Discuss</p>
                    <p>Contact us</p>
                </div>
                <div class="head pay col-md-2">
                    PAYMENT METHODS
                    <p>Paypal</p>
                    <p>Visa</p>
                    <div class="bocongthuong">
                        <img src="http://online.gov.vn/Content/EndUser/LogoCCDVSaleNoti/logoSaleNoti.png" alt="" />
                    </div>
                </div>
                <div class="head info col-md-2">
                    INFORMATION
                    <p>About us</p>
                    <p>Hotline</p>
                    <p>0987 234 662</p>
                </div>
            </div>
            <div class="copyright">
                <p>Copyright by GR4 © 2023. All Rights Reserved.</p>
            </div>
        </div>
    </body>

</html>