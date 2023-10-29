<%@page import="Models.Account"%>
<!DOCTYPE html>
<html>
    <head>
        <title>User Profile</title>
    </head>
    <body>
        <h1>Edit User Profile</h1>
        <%
            Account acc = (Account) session.getAttribute("userInformation");
        %>

        <form method="post" action="AdminController">
            <div class="mt-4">ID<input class="ml-2" readonly="true" type="text" name="ID" value="<%= acc.getAccountID()%>" id=""></div>
            <div class="row">
                <div class="col-md-6">
                    <div class="mt-4">Avatar<input style=" margin-left: 50px; width: 200px; height: 30px;" type="text"
                                                   name="Avatar" value="<%= acc.getAvatar()%>" id=""></div>
                    <div class="mt-4">Username<input class="ml-4" style="width: 200px; height: 30px;" type="text"
                                                     name="Username" value="<%= acc.getUsername()%>" id=""></div>
                    <div class="mt-4">Password<input style="margin-left: 30px;width: 200px; height: 30px;" type="text"
                                                     name="Password" value="<%= acc.getPassword()%>" id=""></div>
                    <div class="mt-4">Email<input style="margin-left: 58px;width: 200px; height: 30px;" type="text"
                                                  name="Email" value="<%= acc.getEmail()%>" id=""></div>
                </div>
                <div class="col-md-6">
                    <div class="mt-4">FirstName<input style="margin-left: 20px; width: 200px; height: 30px;"
                                                      type="text" name="FirstName" value="<%= acc.getFirstName()%>" id=""></div>
                    <div class="mt-4">LastName<input style="margin-left: 22px; width: 200px; height: 30px;" type="text"
                                                     name="LastName" value="<%= acc.getLastName()%>" id=""></div>
                    <div class="mt-4">Gender<input style="margin-left: 40px;width: 200px; height: 30px;"
                                                   type="text" name="Gender" value="<%= acc.getGender()%>" id=""></div>
                    <div class="mt-4">Birthday<input style="margin-left: 35px; width: 200px; height: 30px;"
                                                     type="date" name="Birthday" value="<%= acc.getBirthday()%>" id=""></div>
                    <div class="mt-4">Role<input style="margin-left: 63px; width: 200px; height: 30px;" type="text"
                                                 name="Role" value="<%= acc.getRole()%>" id=""></div>
                    <div class="mt-4" style="text-align: right; margin-right: 140px"> <input type="submit" value="UpdateUser" name="btnUpdateUser"></div>
                </div>
            </div>
        </form>
    </body>
</html>