<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
              integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
        <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
              integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
              crossorigin="anonymous" referrerpolicy="no-referrer" />
        <link rel="stylesheet" href="./public/assets/css/toast.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
        <title>${param.title}</title>
        
        <link rel="stylesheet" href="<%out.print(request.getContextPath());%>/public/assets/css/signup.css">
    </head>

    <body>
        <div id="main">
            <div class="box">
                <form action="${contextPath}/user/signup" method="post" id="signUpForm" class="needs-validation">
                    <input type="text" required="required" name="avatar" value="${user.avatar}" style="display: none">

                    <h2>Sign up</h2>
                    <div class="inputBox">
                        <label class="form-label">Username</label>
                        <input class="form-control" id="username" type="text" placeholder="Enter your username"
                               required="required" name="username">
                        <i></i>
                    </div>
                    <div class="inputBox">
                        <label class="form-label">Email</label>
                        <input class="form-control" id="email" type="text" placeholder="Enter your email"
                               required="required" name="email" value="email">
                        <i></i>
                    </div>
                    <div class="inputBox-name">
                        <div class="inputBox">
                            <label class="form-label">First Name</label>
                            <input class="form-control" type="text" placeholder="First name" required="required"
                                   name="firstName" value="firstName">
                            <i></i>
                        </div>
                        <div class="inputBox">
                            <label class="form-label">Last Name</label>
                            <input class="form-control" type="text" placeholder="Last name" required="required"
                                   name="lastName" value="lastName">
                            <i></i>
                        </div>
                    </div>

                    <div class="inputBox date">
                        <label for="">Birthday</label>
                        <input class="form-control" type="date" value="2023-10-09" required="required" name="birthday">
                        <i></i>
                    </div>

                    <div class="inputBox">
                        <label class="form-label">Password</label>
                        <input class="form-control" type="password" placeholder="Enter your password" required="required"
                               name="password">
                        <i></i>
                    </div>

                    <input type="submit" value="Register">

                </form>

            </div>
        </div>
        <script src="${contextPath}/public/assets/js/signup.js"></script>
    </body>

</html>