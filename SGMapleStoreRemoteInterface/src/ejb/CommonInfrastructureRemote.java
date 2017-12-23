package ejb;

import java.util.List;
import java.util.Vector;
import javax.ejb.Remote;

@Remote
public interface CommonInfrastructureRemote {
    public void createContact(String contactSalutation, String contactFirstName, String contactLastName, String contactEmail, 
            String contactPhone, String contactType, String contactBillingAttn, String contactBillingAddress, String contactBillingCity, 
            String contactBillingState, String contactBillingZipCode, String contactBillingCountry, String contactBillingFax, 
            String contactBillingPhone, String contactShippingAttn, String contactShippingAddress, String contactShippingCity, 
            String contactShippingState, String contactShippingZipCode, String contactShippingCountry, String contactShippingFax, 
            String contactShippingPhone, String contactUsername, String contactPassword, String contactNotes);
    public List<Vector> viewContactList();
    
    public boolean empLogin(String empNRIC, String empPassword);
}
