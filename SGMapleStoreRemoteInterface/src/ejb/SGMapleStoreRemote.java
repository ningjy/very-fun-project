package ejb;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface SGMapleStoreRemote {
    public void createCustomer(String custSalutation, String custFirstName, String custLastName, String custEmail, 
            String custContactNum, String custBillingStreet, String custBillingCity, String custBillingState, 
            String custBillingZipCode, String custBillingCountry, String custShippingStreet, String custShippingCity, 
            String custShippingState, String custShippingZipCode, String custShippingCountry, String custNotes);
    public List<Vector> viewCustomerList();
    
    public boolean empLogin(String empNRIC, String empPassword);
}
