<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>SG MapleStore - Employee Details</title>
        
        <link href="css/commoninfrastructure/baselayout/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/baselayout/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/weblayout/EmployeeDetailsCSS.css" rel="stylesheet" type="text/css">
        
        <script src="js/commoninfrastructure/basejs/jquery.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/webjs/EmployeeDetailsJS.js" type="text/javascript"></script>
    </head>
    <body>
        <%
            ArrayList<String> employeeInfoArr = (ArrayList) request.getAttribute("employeeInfo");
            String empFirstName, empLastName, empCreationDate;
            empFirstName = empLastName = empCreationDate = "";

            if (employeeInfoArr != null) {
                empFirstName = (String)employeeInfoArr.get(0);
                empLastName = (String)employeeInfoArr.get(1);
                empCreationDate = (String)employeeInfoArr.get(2);
            }
        %>
        <section class="dashboard section">
            <div class="container">
                <div class="row">
                    <div class="col-sm-4 col-md-4 col-lg-4">
                        <div class="sidebar">
                            <div class="widget user-dashboard-profile">
                                <div class="profile-thumb">
                                    <img src="images/user/user-thumb.jpg" class="rounded-circle">
                                </div>
                                <h5 class="text-center"><%= empFirstName %>&nbsp;<%= empLastName %></h5>
                                <p>Joined&nbsp;<%= empCreationDate %></p>
                                <a href="user-profile.html" class="btn btn-main-sm">Edit Profile</a>
                            </div>
                            <div class="widget user-dashboard-menu">
                                <ul class="sidebar-menu">
                                    <li class="active"><a href="#contactAddressPane" role="tab" data-toggle="tab"><i class="fa fa-address-book"></i>&nbsp;Contact Address</a></li>
                                    <li><a href="#transactionsPane" role="tab" data-toggle="tab"><i class="fa fa-exchange"></i>&nbsp;Transactions</a></li>
                                    <li><a href="#recentHistoryPane" role="tab" data-toggle="tab"><i class="fa fa-history"></i>&nbsp;Recent History</a></li>
                                    <li><a href="#transactionsPane" role="tab" data-toggle="tab"><i class="fa fa-trash"></i>&nbsp;Delete Account</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-8 col-md-8 col-lg-8 contentArea" style="margin-left: 22px;">
                        <div class="widget dashboard-container active" id="contactAddressPane">
                            <h3 class="widget-header">Contact Address</h3>
                        </div>
                        <div class="widget dashboard-container subContentArea" id="transactionsPane">
                            <h3 class="widget-header">Transactions</h3>
                        </div>
                        <div class="widget dashboard-container subContentArea" id="recentHistoryPane">
                            <h3 class="widget-header">Recent History</h3>
                        </div>
                    </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>