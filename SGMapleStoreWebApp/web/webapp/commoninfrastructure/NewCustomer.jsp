<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>SG MapleStore - New Customer</title>
        
        <!-- Cascading Style Sheet (CSS) -->
        <link href="css/commoninfrastructure/baselayout/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/baselayout/basetemplate.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/baselayout/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/weblayout/CommonCSS.css" rel="stylesheet" type="text/css">
        <link href="css/commoninfrastructure/weblayout/NewCustomerCSS.css" rel="stylesheet" type="text/css">
        
        <!-- Java Script (JS) -->
        <script src="js/commoninfrastructure/basejs/bootstrap.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/basejs/jquery.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/basejs/metisMenu.min.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/basejs/jquery.newsTicker.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/webjs/CommonJS.js" type="text/javascript"></script>
        <script src="js/commoninfrastructure/webjs/NewCustomerJS.js" type="text/javascript"></script>
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
                    <h4 class="page-header" style="font-size: 20px; padding-left: 20px;">New Customer</h4>
                </div>
                <div class="col-lg-12 contentBody">
                    <form action="SGMapleStore" method="POST">
                        <table border="0" class="tableConfig">
                            <tr>
                                <td style="width: 20%;">Customer Name</td>
                                <td style="width: 13%;">
                                    <select class="form-control" name="custSalutation">
                                        <option value="">-- Salutation --</option>
                                        <option value="Miss">Miss</option>
                                        <option value="Mr.">Mr.</option>
                                        <option value="Mrs.">Mrs.</option>
                                    </select>
                                </td>
                                <td style="width: 2%;">&nbsp;</td>
                                <td style="width: 18%;"><input class="form-control" name="custFirstName" placeholder="First Name" /></td>
                                <td style="width: 2%;">&nbsp;</td>
                                <td style="width: 18%;"><input class="form-control" name="custLastName" placeholder="Last Name" /></td>
                                <td style="width: 27%;">&nbsp;</td>
                            </tr>
                            <tr>
                                <td>Customer Email</td>
                                <td colspan="3"><input class="form-control" name="custEmail" /></td>
                                <td colspan="3">&nbsp;</td>
                            </tr>
                            <tr>
                                <td>Customer Contact Number</td>
                                <td colspan="3"><input class="form-control" name="custContactNum" /></td>
                                <td colspan="3">&nbsp;</td>
                            </tr>
                        </table><br/>
                        
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#addressTab"><strong>Address</strong></a></li>
                            <li><a href="#notesTab"><strong>Notes</strong></a></li>
                        </ul>
                        
                        <div class="tab-content">
                            <div class="tab-pane active" id="addressTab">
                                <table border="0" class="tableConfig">
                                    <tr>
                                        <td colspan="2"><strong>Billing Address</strong></td>
                                        <td>&nbsp;</td>
                                        <td colspan="2">
                                            <strong>Shipping Address</strong>&nbsp;&nbsp;
                                            <a href="javascript:copyBillingAdd();"><i class="fa fa-copy fa-fw"></i>&nbsp;Copy billing address</a>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td style="width: 12%;">Street</td>
                                        <td style="width: 25%;"><textarea class="form-control" id="custBillingStreet" name="custBillingStreet"></textarea></td>
                                        <td style="width: 8%;">&nbsp;</td>
                                        <td style="width: 12%;">Street</td>
                                        <td style="width: 25%;"><textarea class="form-control" id="custShippingStreet" name="custShippingStreet"></textarea></td>
                                        <td style="width: 16%;">&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>City</td>
                                        <td><input class="form-control" id="custBillingCity" name="custBillingCity" /></td>
                                        <td>&nbsp;</td>
                                        <td>City</td>
                                        <td><input class="form-control" id="custShippingCity" name="custShippingCity" /></td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>State</td>
                                        <td><input class="form-control" id="custBillingState" name="custBillingState" /></td>
                                        <td>&nbsp;</td>
                                        <td>State</td>
                                        <td><input class="form-control" id="custShippingState" name="custShippingState" /></td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>Zip Code</td>
                                        <td><input class="form-control" id="custBillingZipCode" name="custBillingZipCode" /></td>
                                        <td>&nbsp;</td>
                                        <td>Zip Code</td>
                                        <td><input class="form-control" id="custShippingZipCode" name="custShippingZipCode" /></td>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr>
                                        <td>Country</td>
                                        <td><input class="form-control" id="custBillingCountry" name="custBillingCountry" /></td>
                                        <td>&nbsp;</td>
                                        <td>Country</td>
                                        <td><input class="form-control" id="custShippingCountry" name="custShippingCountry" /></td>
                                        <td>&nbsp;</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="tab-pane hide" id="notesTab">
                                <table border="0" style="width: 800%;">
                                    <tr><td>Notes (For Internal Use)</td></tr>
                                    <tr><td colspan="4"><textarea name="custNotes"></textarea></td><td>&nbsp;</td></tr>
                                </table>
                            </div>
                        </div><br/>
                        
                        <table border="0" class="tableConfig">
                            <tr>
                                <td>
                                    <input type="hidden" name="pageTransit" value="createCustomer"/>
                                    <button class="btn btn-primary" type="submit" value="submit">Create Customer</button>&nbsp;&nbsp;
                                    <button class="btn btn-default" onclick="location.href='SGMapleStore?pageTransit=goToDashboard'" type="button">Cancel</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>