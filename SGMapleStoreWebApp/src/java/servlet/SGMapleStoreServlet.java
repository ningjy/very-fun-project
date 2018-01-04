package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import ejb.CommonInfrastructureRemote;
import ejb.WarehouseTransportRemote;
import java.util.List;
import java.util.Vector;

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
            else if(pageAction.equals("createContact")) {
                request.setAttribute("employeeNRIC", userNRIC);
                createCustomer(request);
                request.setAttribute("contactList", (ArrayList)cir.viewContactList());
                pageAction = "ContactList";
            }
            else if(pageAction.equals("goToContactDetails")) {
                String contactIdentifier = request.getParameter("contactIdentifier");
                request.setAttribute("contactInfo", getContactDetails(contactIdentifier));
                pageAction = "ContactDetails";
            }
            else if(pageAction.equals("goToItem")) {
                request.setAttribute("employeeNRIC", userNRIC);
                pageAction = "Item";
            }
            else if(pageAction.equals("goToNewCompositeItem")) {
                request.setAttribute("employeeNRIC", userNRIC);
                pageAction = "NewCompositeItem";
            }
            else if(pageAction.equals("createCompositeItem")) {
                request.setAttribute("employeeNRIC", userNRIC);
                if(createCompositeItemRecord(request)) {
                    request.setAttribute("successMessage", "Composite item has been created successfully.");
                }
                else {
                    request.setAttribute("errorMessage", "One or more fields are invalid. Please check again.");
                }
                pageAction = "NewCompositeItem";
            }
            else if(pageAction.equals("goToQuantityAdjustment")) {
                request.setAttribute("employeeNRIC", userNRIC);
                pageAction = "QuantityAdjustment";
            }
            else if(pageAction.equals("adjustQuantity")) {
                request.setAttribute("employeeNRIC", userNRIC);
                if(createItemInventoryLog(userNRIC, request)) {
                    request.setAttribute("successMessage", "Quantity adjustment(s) has been logged successfully.");
                }
                else {
                    request.setAttribute("errorMessage", "One or more quantity adjustment records are invalid. Please check the inventory log.");
                }
                pageAction = "QuantityAdjustment";
            }
            else if(pageAction.equals("goToInventoryLogList")) {
                request.setAttribute("employeeNRIC", userNRIC);
                request.setAttribute("inventoryLogList", (ArrayList)wtr.viewInventoryLogList());
                pageAction = "InventoryLogList";
            }
            else if(pageAction.equals("goToInvoiceList")) {
                request.setAttribute("invoiceList", (ArrayList)wtr.viewInvoiceList());
                pageAction = "InvoiceList";
            }
            else if(pageAction.equals("goToCheckout")) {
                pageAction = "Checkout";
            }
            else if(pageAction.equals("goToContactUs")) {
                pageAction = "ContactUs";
            }
            else if(pageAction.equals("goToErrorPage")) {
                pageAction = "ErrorPage";
            }
            else if(pageAction.equals("goToHomepage")) {
                pageAction = "Homepage";
            }
            else if(pageAction.equals("goToProductCategory")) {
                pageAction = "ProductCategory";
            }
            else if(pageAction.equals("goToProductComparison")) {
                pageAction = "ProductComparison";
            }
            else if(pageAction.equals("goToProductDetails")) {
                pageAction = "ProductDetails";
            }
            else if(pageAction.equals("goToProductWishlist")) {
                pageAction = "ProductWishlist";
            }
            else if(pageAction.equals("goToShoppingCart")) {
                pageAction = "ShoppingCart";
            }
            else if(pageAction.equals("goToStoreFAQ")) {
                pageAction = "StoreFAQ";
            }
            else if(pageAction.equals("goToStoreLogin")) {
                pageAction = "StoreLogin";
            }
            else if(pageAction.equals("goToTermsCondition")) {
                pageAction = "TermsCondition";
            }
            else if(pageAction.equals("goToTrackOrder")) {
                pageAction = "TrackOrder";
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
        try {
            ArrayList<Vector> retrievedItemList = (ArrayList)wtr.getItemListingNames();
            JSONObject responseDetailsJson = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            
            for(int i = 0; i <= retrievedItemList.size()-1; i++){
                Vector v = retrievedItemList.get(i);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("itemName", String.valueOf(v.get(0)));
                jsonObject.put("itemSKU", String.valueOf(v.get(1)));
                jsonObject.put("itemQuantityAvail", String.valueOf(v.get(2)));
                jsonObject.put("itemSellingPrice", String.valueOf(v.get(3)));
                jsonArray.put(jsonObject);
            }
            responseDetailsJson.put("itemDetails", jsonArray);
            response.setContentType("application/json");
            response.getWriter().print(responseDetailsJson);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
    
    private ArrayList<String> getContactDetails(String contactIdentifier) {
        ArrayList<String> contactDetailsArr = new ArrayList();
        Vector userInfoVec = cir.getContactInfo(contactIdentifier);
        
        contactDetailsArr.add((String)userInfoVec.get(0)); // First Name
        contactDetailsArr.add((String)userInfoVec.get(1)); // Last Name
        contactDetailsArr.add((String)userInfoVec.get(2)); // Contact Creation Date
        return contactDetailsArr;
    }
    
    private boolean createItemInventoryLog(String userNRIC, HttpServletRequest request){
        boolean logCreationStatus = false;
        String logDate = request.getParameter("logDate");
        String logReason = request.getParameter("logReason");
        String logDescription = request.getParameter("logDescription");
        String[] itemNameArr = request.getParameterValues("itemName");
        String[] itemSKUArr = request.getParameterValues("itemSKU");
        String[] itemQtyArr = request.getParameterValues("itemQuantityAvail");
        String[] itemQtyAdjustArr = request.getParameterValues("itemQuantityAdjust");
        
        logCreationStatus = wtr.createInventoryLog(userNRIC, logDate, logReason, logDescription, itemNameArr, 
                itemSKUArr, itemQtyArr, itemQtyAdjustArr);
        return logCreationStatus;
    }
    
    private boolean createCompositeItemRecord(HttpServletRequest request){
        boolean compCreationStatus = false;
        String compositeName = request.getParameter("compositeName");
        String compositeSKU = request.getParameter("compositeSKU");
        String compositeSellPrice = request.getParameter("compositeSellPrice");
        String compositeRebundleLvl = request.getParameter("compositeRebundleLvl");
        String compositeDescription = request.getParameter("compositeDescription");
        
        String[] itemNameArr = request.getParameterValues("itemName");
        String[] itemSKUArr = request.getParameterValues("itemSKU");
        String[] itemQtyRequiredArr = request.getParameterValues("itemQuantityRequired");
        
        compCreationStatus = wtr.createCompositeItem(compositeName, compositeSKU, compositeSellPrice, compositeRebundleLvl, 
                compositeDescription, itemNameArr, itemSKUArr, itemQtyRequiredArr);
        return compCreationStatus;
    }
}
