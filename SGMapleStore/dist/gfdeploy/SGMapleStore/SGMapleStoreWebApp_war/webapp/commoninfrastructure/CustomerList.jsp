<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Vector"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>SG MapleStore - Customer List</title>
        
        <!-- Cascading Style Sheet (CSS) -->
        <link href="css/commoninfrastructure/baselayout/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/baselayout/basetemplate.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/baselayout/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/baselayout/iziModal.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/datatable/dataTables.bootstrap.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/datatable/dataTables.responsive.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/datatable/jquery.dataTables.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/weblayout/CommonCSS.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/weblayout/CustomerListCSS.css" rel="stylesheet" type="text/css">
        
        <!-- Java Script (JS) -->
        <script src="js/commoninfrastructure/basejs/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/basejs/jquery.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/basejs/metisMenu.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/basejs/jquery.newsTicker.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/basejs/iziModal.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/datatable/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/datatable/dataTables.bootstrap.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/datatable/dataTables.responsive.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/webjs/CommonJS.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/webjs/CustomerListJS.js" type="text/javascript"></script>
    </head>
    <body onload="establishTime(); setInterval('updateTime()', 1000)">
        <div id="wrapper">
            <nav class="navbar navbar-default navbar-fixed-top" role="navigation" style="margin-bottom: 0;">
                <a class="navbar-brand" href="SGMapleStore?pageTransit=goToDashboard">
                    SG MapleStore
                </a>
                
                <!-- Top Navigation -->
                <div id="pageAnnouncement">
                    <div class="ccr-last-update">
                        <div class="update-ribon"><strong>Notification:</strong></div>
                        <span class="update-ribon-right"></span>
                        <div class="update-news-text">
                            <ul id="latestUpdate" class="newsticker">
                                <li><strong>System maintenance will be carried out at 15 Jan 2018, 00:00:00 (SGT).</strong></li>
                                <li><strong>Welcome to SG MapleStore!</strong></li>
                                <li><strong>Stay tune to our latest update via "Latest System Update".</strong></li>
                            </ul>
                        </div>
                        <div class="update-right-border">
                            <i class="fa fa-clock-o"></i>&nbsp;&nbsp;<strong><span id="clock"></span></strong>
                        </div>
                    </div>
                </div>
                <ul class="nav navbar-top-links navbar-right">
                    <li><a href="SGMapleStore?pageTransit=goToProfile"><i class="fa fa-user"></i>&nbsp;&nbsp;My Profile</a></li>
                    <li class="divider"></li>
                    <li><a href="SGMapleStore?pageTransit=goToLogout"><i class="fa fa-sign-out"></i>&nbsp;&nbsp;Logout</a></li>
                </ul>
            
                <!-- Left Navigation -->
                <div class="navbar-default sidebar">
                    <div class="sidebar-nav navbar-collapse">
                        <ul class="nav" id="side-menu">
                            <li>
                                <table border="0" style="margin: 12px 0px 12px 5px;" width="95%">
                                    <tr>
                                        <td rowspan="2" style="text-align: right;"><img src="images/ProfileImage.png" /></td>
                                        <td colspan="2" valign="middle" style="padding-left: 10px;"><strong>Hello <%= request.getAttribute("employeeNRIC")%>!</strong></td>
                                    </tr>
                                    <tr>
                                        <td style="padding-left: 10px;">
                                            <form action="SGMapleStore" method="POST">
                                                <input type="hidden" name="pageTransit" value="goToProfile"/>
                                                <button type="submit" class="btn btn-success btn-xs">My Profile</button>
                                            </form>
                                        </td>
                                        <td>
                                            <form action="SGMapleStore" method="POST">
                                                <input type="hidden" name="pageTransit" value="goToProfile"/>
                                                <button type="submit" class="btn btn-primary btn-xs">Contact Us</button>
                                            </form>
                                        </td>
                                    </tr>
                                </table>
                            </li>
                            <li><a href="SGMapleStore?pageTransit=goToDashboard"><i class="fa fa-home fa-fw"></i>&nbsp;&nbsp;Dashboard</a></li>
                            <li>
                                <a href="#"><i class="fa fa-users fa-fw"></i>&nbsp;&nbsp;Contacts<span class="fa arrow"></span></a>
                                <ul class="nav nav-second-level">
                                    <li><a href="SGMapleStore?pageTransit=goToCustomerList"><i class="fa fa-address-book fa-fw"></i>&nbsp;&nbsp;Customer List</a></li>
                                    <li><a href="SGMapleStore?pageTransit=goToNewCustomer"><i class="fa fa-user-plus fa-fw"></i>&nbsp;&nbsp;New Customer</a></li>
                                    <li><a href="SGMapleStore?pageTransit=goToNewEmployee"><i class="fa fa-user-plus fa-fw"></i>&nbsp;&nbsp;New Employee</a></li>
                                </ul>
                            </li>
                            <li><a href="SGMapleStore?pageTransit=goToCarComputationHist"><i class="fa fa-tags fa-fw"></i>&nbsp;&nbsp;Item Groups</a></li>
                            <li>&nbsp;</li>
                            <li><a href="SGMapleStore?pageTransit=goToFirstHouse"><i class="fa fa-shopping-cart fa-fw"></i>&nbsp;&nbsp;Sales Orders</a></li>
                            <li><a href="SGMapleStore?pageTransit=goToFirstHouse"><i class="fa fa-cube fa-fw"></i>&nbsp;&nbsp;Packages</a></li>
                            <li><a href="SGMapleStore?pageTransit=goToFirstHouse"><i class="fa fa-file-text fa-fw"></i>&nbsp;&nbsp;Invoices</a></li>
                            <li><a href="SGMapleStore?pageTransit=goToFirstHouse"><i class="fa fa-shopping-bag fa-fw"></i>&nbsp;&nbsp;Purchase Orders</a></li>
                            <li><a href="SGMapleStore?pageTransit=goToFirstHouse"><i class="fa fa-list-alt fa-fw"></i>&nbsp;&nbsp;Bills</a></li>
                            <li>&nbsp;</li>
                            <li><a href="SGMapleStore?pageTransit=goToFirstHouse"><i class="fa fa-line-chart fa-fw"></i>&nbsp;&nbsp;Reports</a></li>
                        </ul>
                    </div>
                </div>
            </nav>

            <!-- Content Space -->
            <div id="page-wrapper">
                <div class="col-lg-12 contentHeader">
                    <h4 class="page-header" style="font-size: 20px; padding-left: 20px;">Customer List</h4>
                </div>
                <div class="col-lg-12 tableContentBody">
                    <table class="table table-striped table-bordered table-hover" id="custListTable">
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Email</th>
                                <th>Contact Number</th>
                                <th>Residential Country</th>
                                <th>Residential District</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Vector> customerList = (ArrayList) request.getAttribute("custList");
                                if(customerList.isEmpty()){
                            %>
                            <tr>
                                <td colspan="5" style="text-align: center;">There are no expense history records available.</td>
                            </tr>
                            <%
                                }
                                else {
                                    for(int i = 0; i <= customerList.size()-1; i++){
                                        Vector v = customerList.get(i);
                                        String custSalutation = String.valueOf(v.get(0));
                                        String custFirstName = String.valueOf(v.get(1));
                                        String custLastName = String.valueOf(v.get(2));
                                        String custEmail = String.valueOf(v.get(3));
                                        String custContactNum = String.valueOf(v.get(4));
                                        String custBillingCity = String.valueOf(v.get(5));
                                        String custBillingState = String.valueOf(v.get(6));
                                        String custBillingCountry = String.valueOf(v.get(7));
                            %>
                            <tr>
                                <td><%= custSalutation %>&nbsp;<%= custFirstName %>&nbsp;<%= custLastName %></td>
                                <td><%= custEmail %></td>
                                <td><%= custContactNum %></td>
                                <td><%= custBillingCountry %></td>
                                <td><%= custBillingCity %>&nbsp;(<%= custBillingState %>)</td>
                                <%      }   %>
                                <%  }   %>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <div id="modal-iframe" class="iziModal"></div>
    </body>
</html>