package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import ejb.CommonInfrastructureRemote;
import ejb.WarehouseTransportRemote;

public class SGMapleStoreServlet extends HttpServlet {
    @EJB
    private CommonInfrastructureRemote cir;
    @EJB
    private WarehouseTransportRemote wtr;
    String userNRIC = "";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            RequestDispatcher dispatcher;
            ServletContext servletContext = getServletContext();
            
            // FOR HANDLING THE HIDDEN FIELD
            String pageAction = request.getParameter("pageTransit");
            
            if(pageAction.equals("loginToSys")) {
                String empNRIC = request.getParameter("empNRIC");
                String empPassword = request.getParameter("empPassword");
                if(cir.empLogin(empNRIC, empPassword)){
                    userNRIC = empNRIC;
                    request.setAttribute("employeeNRIC", userNRIC);
                    pageAction = "WarehouseDashboard";
                }
                else{
                    request.setAttribute("sysMessage", "Incorrect NRIC or password. Please try again.");
                    System.out.println("test3");
                    pageAction = "WarehouseLogin";
                }
            }
            else if(pageAction.equals("goToLogout")) {
                userNRIC = "";
                pageAction = "WarehouseLogin";
            }
            else if(pageAction.equals("goToDashboard")) {
                request.setAttribute("employeeNRIC", userNRIC);
                pageAction = "WarehouseDashboard";
            }
            else if(pageAction.equals("goToNewContact")) {
                request.setAttribute("employeeNRIC", userNRIC);
                pageAction = "NewContact";
            }
            else if(pageAction.equals("goToNewEmployee")) {
                request.setAttribute("employeeNRIC", userNRIC);
                pageAction = "NewEmployee";
            }
            else if(pageAction.equals("goToContactList")) {
                request.setAttribute("employeeNRIC", userNRIC);
                request.setAttribute("contactList", (ArrayList)cir.viewContactList());
                pageAction = "ContactList";
            }
            else if(pageAction.equals("goToContactDetails")) {
                pageAction = "ContactDetails";
            }
            else if(pageAction.equals("goToNewItemGroup")) {
                request.setAttribute("employeeNRIC", userNRIC);
                pageAction = "NewItemGroup";
            }
            else if(pageAction.equals("goToNewItem")) {
                request.setAttribute("employeeNRIC", userNRIC);
                pageAction = "NewItem";
            }
            else if(pageAction.equals("goToQuantityAdjustment")) {
                request.setAttribute("employeeNRIC", userNRIC);
                pageAction = "QuantityAdjustment";
            }
            else if(pageAction.equals("goToPriceAdjustment")) {
                request.setAttribute("employeeNRIC", userNRIC);
                pageAction = "PriceAdjustment";
            }
            else if(pageAction.equals("createContact")) {
                request.setAttribute("employeeNRIC", userNRIC);
                createCustomer(request);
                request.setAttribute("contactList", (ArrayList)cir.viewContactList());
                pageAction = "ContactList";
            }
            else if(pageAction.equals("goToInvoiceList")) {
                request.setAttribute("invoiceList", (ArrayList)wtr.viewInvoiceList());
                pageAction = "InvoiceList";
            }
            dispatcher = servletContext.getNamedDispatcher(pageAction);
            dispatcher.forward(request, response);
        }
        catch(Exception ex) {
            log("Exception in SGMapleStoreServlet: processRequest()");
            ex.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() { return "SG MapleStore Servlet"; }
    
    private void createCustomer(HttpServletRequest request){
        String contactSalutation = request.getParameter("contactSalutation");
        String contactFirstName = request.getParameter("contactFirstName");
        String contactLastName = request.getParameter("contactLastName");
        String contactEmail = request.getParameter("contactEmail");
        String contactPhone = request.getParameter("contactPhone");
        String contactType = request.getParameter("contactType");
        
        String contactBillingAttn = request.getParameter("contactBillingAttn");
        String contactBillingAddress = request.getParameter("contactBillingAddress");
        String contactBillingCity = request.getParameter("contactBillingCity");
        String contactBillingState = request.getParameter("contactBillingState");
        String contactBillingZipCode = request.getParameter("contactBillingZipCode");
        String contactBillingCountry = request.getParameter("contactBillingCountry");
        String contactBillingFax = request.getParameter("contactBillingFax");
        String contactBillingPhone = request.getParameter("contactBillingPhone");
        
        String contactShippingAttn = request.getParameter("contactShippingAttn");
        String contactShippingAddress = request.getParameter("contactShippingAddress");
        String contactShippingCity = request.getParameter("contactShippingCity");
        String contactShippingState = request.getParameter("contactShippingState");
        String contactShippingZipCode = request.getParameter("contactShippingZipCode");
        String contactShippingCountry = request.getParameter("contactShippingCountry");
        String contactShippingFax = request.getParameter("contactShippingFax");
        String contactShippingPhone = request.getParameter("contactShippingPhone");
        
        String contactUsername = request.getParameter("contactUsername");
        String contactPassword = request.getParameter("contactPassword");
        String contactNotes = request.getParameter("contactNotes");
        
        cir.createContact(contactSalutation, contactFirstName, contactLastName, contactEmail, contactPhone, contactType, 
                contactBillingAttn, contactBillingAddress, contactBillingCity, contactBillingState, contactBillingZipCode, 
                contactBillingCountry, contactBillingFax, contactBillingPhone, contactShippingAttn, contactShippingAddress, 
                contactShippingCity, contactShippingState, contactShippingZipCode, contactShippingCountry, contactShippingFax, 
                contactShippingPhone, contactUsername, contactPassword, contactNotes);
    }
}
