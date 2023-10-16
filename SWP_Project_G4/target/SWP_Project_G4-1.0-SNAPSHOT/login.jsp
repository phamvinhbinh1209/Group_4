<%-- 
    Document   : login
    Created on : Oct 4, 2023, 3:48:27 PM
    Author     : VuDuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
              integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
              integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="<%out.print(request.getContextPath());%>/public/assets/css/login.css">
        <link rel="stylesheet" href="<%out.print(request.getContextPath());%>/public/assets/css/toast.css">
        <title>Login Page</title>
    </head>
    <body>
        <div id="main">
            <div class="box">
                <form action="/Login" method="post">

                    <h2>Log in</h2>
                    <div class="inputBox">
                        <input type="text" required="required" name="username">
                        <span>Userame</span>
                        <i></i>
                    </div>
                    <div class="inputBox">
                        <input type="password" required="required" name="password">
                        <span>Password</span>
                        <i></i>
                    </div>
                    <div class="links">
                        <a href="#">Forgot Password?</a>
                        <a href="./signup.jsp">Signup</a>
                    </div>
                    <input type="submit" value="Login">

                    <p id="message"></p>

                </form>

                <div class="accountLogin">
                    <h6> or </h6>
                    <div class="loginWithGG">
                        <a
                            href="https://accounts.google.com/o/oauth2/auth?scope=https://www.googleapis.com/auth/userinfo.profile%20https://www.googleapis.com/auth/userinfo.email&redirect_uri=http://localhost:8080/SWP391_Project_G4/user/loginWithGG&response_type=code
                            &client_id=246255507082-vpebidclj199n0sgg035cos2ijabjrmg.apps.googleusercontent.com&approval_prompt=force">
                            <img src="<%out.print(request.getContextPath());%>/public/assets/imgs/logogoogle.png" alt="">
                            <p>Login with Google</p>
                        </a>
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
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
                    integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
            crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
                    integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
            crossorigin="anonymous"></script>
            <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
                    integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
                    integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
                    integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
            <%@include file="popUpMessage.jsp" %>
    </body>
</html>
