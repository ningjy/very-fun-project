package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Customer")
public class CustomerEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long custID;
    private String custSalutation;
    private String custFirstName;
    private String custLastName;
    private String custEmail;
    private String custContactNum;
    
    private String custBillingStreet;
    private String custBillingCity;
    private String custBillingState;
    private String custBillingZipCode;
    private String custBillingCountry;
    
    private String custShippingStreet;
    private String custShippingCity;
    private String custShippingState;
    private String custShippingZipCode;
    private String custShippingCountry;
    private String custNotes;
    
    /* MISCELLANEOUS METHODS */ 
    public void createCustomer(String custSalutation, String custFirstName, String custLastName, String custEmail, 
            String custContactNum, String custBillingStreet, String custBillingCity, String custBillingState, 
            String custBillingZipCode, String custBillingCountry, String custShippingStreet, String custShippingCity, 
            String custShippingState, String custShippingZipCode, String custShippingCountry, String custNotes) {
        this.custSalutation = custSalutation;
        this.custFirstName = custFirstName;
        this.custLastName = custLastName;
        this.custEmail = custEmail;
        this.custContactNum = custContactNum;
        this.custBillingStreet = custBillingStreet;
        this.custBillingCity = custBillingCity;
        this.custBillingState = custBillingState;
        this.custBillingZipCode = custBillingZipCode;
        this.custBillingCountry = custBillingCountry;
        this.custShippingStreet = custShippingStreet;
        this.custShippingCity = custShippingCity;
        this.custShippingState = custShippingState;
        this.custShippingZipCode = custShippingZipCode;
        this.custShippingCountry = custShippingCountry;
        this.custNotes = custNotes;
    }
    
    /* GETTER METHODS */
    public Long getCustID() { return custID; }
    public String getCustSalutation() { return custSalutation; }
    public String getCustFirstName() { return custFirstName; }
    public String getCustLastName() { return custLastName; }
    public String getCustEmail() { return custEmail; }
    public String getCustContactNum() { return custContactNum; }
    public String getCustBillingStreet() { return custBillingStreet; }
    public String getCustBillingCity() { return custBillingCity; }
    public String getCustBillingState() { return custBillingState; }
    public String getCustBillingZipCode() { return custBillingZipCode; }
    public String getCustBillingCountry() { return custBillingCountry; }
    public String getCustShippingStreet() { return custShippingStreet; }
    public String getCustShippingCity() { return custShippingCity; }
    public String getCustShippingState() { return custShippingState; }
    public String getCustShippingZipCode() { return custShippingZipCode; }
    public String getCustShippingCountry() { return custShippingCountry; }
    public String getCustNotes() { return custNotes; }
    
    /* SETTER METHODS */
    public void setCustID(Long custID) { this.custID = custID; }
    public void setCustSalutation(String custSalutation) { this.custSalutation = custSalutation; }
    public void setCustFirstName(String custFirstName) { this.custFirstName = custFirstName; }
    public void setCustLastName(String custLastName) { this.custLastName = custLastName; }
    public void setCustEmail(String custEmail) { this.custEmail = custEmail; }
    public void setCustContactNum(String custContactNum) { this.custContactNum = custContactNum; }
    public void setCustBillingStreet(String custBillingStreet) { this.custBillingStreet = custBillingStreet; }
    public void setCustBillingCity(String custBillingCity) { this.custBillingCity = custBillingCity; }
    public void setCustBillingState(String custBillingState) { this.custBillingState = custBillingState; }
    public void setCustBillingZipCode(String custBillingZipCode) { this.custBillingZipCode = custBillingZipCode; }
    public void setCustBillingCountry(String custBillingCountry) { this.custBillingCountry = custBillingCountry; }
    public void setCustShippingStreet(String custShippingStreet) { this.custShippingStreet = custShippingStreet; }
    public void setCustShippingCity(String custShippingCity) { this.custShippingCity = custShippingCity; }
    public void setCustShippingState(String custShippingState) { this.custShippingState = custShippingState; }
    public void setCustShippingZipCode(String custShippingZipCode) { this.custShippingZipCode = custShippingZipCode; }
    public void setCustShippingCountry(String custShippingCountry) { this.custShippingCountry = custShippingCountry; }
    public void setCustNotes(String custNotes) { this.custNotes = custNotes; }
}
