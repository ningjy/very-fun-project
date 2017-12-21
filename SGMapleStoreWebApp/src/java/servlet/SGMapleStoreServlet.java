package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import ejb.SGMapleStoreRemote;
import java.util.ArrayList;
        
public class SGMapleStoreServlet extends HttpServlet {
    @EJB
    private SGMapleStoreRemote smsr;
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
                if(smsr.empLogin(empNRIC, empPassword)){
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
            else if(pageAction.equals("goToNewCustomer")) {
                request.setAttribute("employeeNRIC", userNRIC);
                pageAction = "NewCustomer";
            }
            else if(pageAction.equals("goToNewEmployee")) {
                request.setAttribute("employeeNRIC", userNRIC);
                pageAction = "NewEmployee";
            }
            else if(pageAction.equals("goToCustomerList")) {
                request.setAttribute("employeeNRIC", userNRIC);
                request.setAttribute("custList", (ArrayList)smsr.viewCustomerList());
                pageAction = "CustomerList";
            }
            else if(pageAction.equals("goToCustomerDetails")) {
                pageAction = "CustomerDetails";
            }
            else if(pageAction.equals("createCustomer")) {
                request.setAttribute("employeeNRIC", userNRIC);
                createCustomer(request);
                request.setAttribute("custList", (ArrayList)smsr.viewCustomerList());
                pageAction = "CustomerList";
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
        String custSalutation = request.getParameter("custSalutation");
        String custFirstName = request.getParameter("custFirstName");
        String custLastName = request.getParameter("custLastName");
        String custEmail = request.getParameter("custEmail");
        String custContactNum = request.getParameter("custContactNum");
        
        String custBillingStreet = request.getParameter("custBillingStreet");
        String custBillingCity = request.getParameter("custBillingCity");
        String custBillingState = request.getParameter("custBillingState");
        String custBillingZipCode = request.getParameter("custBillingZipCode");
        String custBillingCountry = request.getParameter("custBillingCountry");
        
        String custShippingStreet = request.getParameter("custShippingStreet");
        String custShippingCity = request.getParameter("custShippingCity");
        String custShippingState = request.getParameter("custShippingState");
        String custShippingZipCode = request.getParameter("custShippingZipCode");
        String custShippingCountry = request.getParameter("custShippingCountry");
        String custNotes = request.getParameter("custNotes");
        
        smsr.createCustomer(custSalutation, custFirstName, custLastName, custEmail, custContactNum, 
                custBillingStreet, custBillingCity, custBillingState, custBillingZipCode, custBillingCountry, 
                custShippingStreet, custShippingCity, custShippingState, custShippingZipCode, custShippingCountry, custNotes);
    }
}
