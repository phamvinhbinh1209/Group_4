
<%@page import="Models.Products"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Home</title>
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
              href="<%out.print(request.getContextPath());%>/public/assets/css/product-detail.css"/>
    </head>
    <body>
        <header>
            <div class="header">
                <a href="#">Order History</a>
                <a href="#">
                    <i class="fa-solid fa-cart-shopping"></i>
                    <span>Cart</span>
                </a>
                <a href="./login.jsp" class="user">
                    <i class="fa-solid fa-user"></i>
                    <span>Login</span>
                </a>
            </div>
        </header>
        <div class="jumbotron">
            <div class="container">
                <div class="row headerContent">
                    <div class="col-12 col-md-2">
                        <img class="logo" src="./public/assets/imgs/Logo.png" alt="" />
                    </div>

                    <div class="col-12 col-md-7 textHeader">
                        <p>DISCOVER YOUR OWN STYLE</p>
                    </div>

                    <div class="col-12 col-md-3 form-group">
                        <span class="search-icon border-0" id="search-addon">
                            <i class="fas fa-search"></i>
                        </span>
                        <input
                            type="search"
                            class="form-control rounded"
                            placeholder="Search"
                            />
                    </div>
                </div>
            </div>
        </div>
        <hr />
        <div class="small-container">
            <%
                Products pr = (Products) session.getAttribute("productInformation");
            %>
            <form method="post" action="UserController">
                <div class="row">
                    <div class="col-12 col-md-6">
                        <img name="Image" src="<%= pr.getImage()%>" width="50%" style="margin-left: 180px; margin-top: 50px;"> 
                    </div>
                    <div class="col-12 col-md-6">
                        <p>Home</p>
                        <h1 name="ProductName"><%= pr.getProductName()%></h1>
                        <h4 name="Price" >Price : <%= pr.getPrice()%> VND</h4>
                        <input type="number" value="1">
                        <br>
                        <input type="submit" value="AddToCart" name="btnAddToCart">
                        <h3>Product Detail</h3>
                        <p name="Description"><%= pr.getDescription()%></p>
                    </div>
                </div>
            </form>
        </div>

        <div id="footer">
            <div class="contact row">
                <div class="introduction col-md-4">
                    <div class="logo-name">
                        <img
                            src="https://ananas.vn/wp-content/themes/ananas/fe-assets/images/svg/Store.svg"
                            alt=""
                            />
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
                        <img
                            src="http://online.gov.vn/Content/EndUser/LogoCCDVSaleNoti/logoSaleNoti.png"
                            alt=""
                            />
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
