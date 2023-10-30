<%-- Document : login Created on : Oct 4, 2023, 3:48:27 PM Author : VuDuc --%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <jsp:include page="head.jsp">
            <jsp:param name="title" value="Log In"/>
        </jsp:include>
        <link rel="stylesheet" href="<%out.print(request.getContextPath());%>/public/assets/css/login.css">
        <link rel="stylesheet" href="<%out.print(request.getContextPath());%>/public/assets/css/toast.css">
    </head>

    <body>
        <div id="main">
            <div class="box">
                <form action="/Login" method="post">

                    <h2>Log in</h2>
                    <div class="inputBox">
                        <input type="text" required="required" name="username">
                        <span>Username</span>
                        <i></i>
                    </div>
                    <div class="inputBox">
                        <input type="password" required="required" name="password">
                        <span>Password</span>
                        <i></i>
                    </div>
                    <div class="links">
                        <a href="#">Forgot Password?</a>

                    </div>
                    <input type="submit" value="Log In">

                    <p id="message"></p>

                </form>

                <div class="accountLogin">
                    <h6> or </h6>
                    <div class="loginWithGG">
                        <a href="/SignUp">Sign Up</a>
                    </div>
                </div>
                <script>
                    // Đợi 5 giây trước khi xóa div
                    setTimeout(function () {
                        var messageDiv = document.getElementById('message');
                        messageDiv.parentNode.removeChild(messageDiv);
                    }, 5000);
                </script>
            </div>
        </div>
        <%@include file="foot.jsp" %>
        
        <%@include file="popUpMessage.jsp" %>
    </body>

</html>
