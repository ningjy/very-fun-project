package entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "Supplier")
public class SupplierEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long suppID;
    private String suppSalutation;
    private String suppFirstName;
    private String suppLastName;
    private String suppEmail;
    private String suppContactNum;
    
    private String suppCompanyName;
    private String suppCompanyWebsite;
    private String suppCurrency;
    
    private String suppBillingStreet;
    private String suppBillingCity;
    private String suppBillingState;
    private String suppBillingZipCode;
    private String suppBillingCountry;
    
    private String suppShippingStreet;
    private String suppShippingCity;
    private String suppShippingState;
    private String suppShippingZipCode;
    private String suppShippingCountry;
    private String suppNotes;
    
    /* GETTER METHODS */
    public Long getSuppID() { return suppID; }
    public String getSuppSalutation() { return suppSalutation; }
    public String getSuppFirstName() { return suppFirstName; }
    public String getSuppLastName() { return suppLastName; }
    public String getSuppEmail() { return suppEmail; }
    public String getSuppContactNum() { return suppContactNum; }
    
    public String getSuppCompanyName() { return suppCompanyName; }
    public String getSuppCompanyWebsite() { return suppCompanyWebsite; }
    public String getSuppCurrency() { return suppCurrency; }
    
    public String getSuppBillingStreet() { return suppBillingStreet; }
    public String getSuppBillingCity() { return suppBillingCity; }
    public String getSuppBillingState() { return suppBillingState; }
    public String getSuppBillingZipCode() { return suppBillingZipCode; }
    public String getSuppBillingCountry() { return suppBillingCountry; }
    public String getSuppShippingStreet() { return suppShippingStreet; }
    public String getSuppShippingCity() { return suppShippingCity; }
    public String getSuppShippingState() { return suppShippingState; }
    public String getSuppShippingZipCode() { return suppShippingZipCode; }
    public String getSuppShippingCountry() { return suppShippingCountry; }
    public String getSuppNotes() { return suppNotes; }
    
    /* SETTER METHODS */
    public void setSuppID(Long suppID) { this.suppID = suppID; }
    public void setSuppSalutation(String suppSalutation) { this.suppSalutation = suppSalutation; }
    public void setSuppFirstName(String suppFirstName) { this.suppFirstName = suppFirstName; }
    public void setSuppLastName(String suppLastName) { this.suppLastName = suppLastName; }
    public void setSuppEmail(String suppEmail) { this.suppEmail = suppEmail; }
    public void setSuppContactNum(String suppContactNum) { this.suppContactNum = suppContactNum; }
    
    public void setSuppCompanyName(String suppCompanyName) { this.suppCompanyName = suppCompanyName; }
    public void setSuppCompanyWebsite(String suppCompanyWebsite) { this.suppCompanyWebsite = suppCompanyWebsite; }
    public void setSuppCurrency(String suppCurrency) { this.suppCurrency = suppCurrency; }
    
    public void setSuppBillingStreet(String suppBillingStreet) { this.suppBillingStreet = suppBillingStreet; }
    public void setSuppBillingCity(String suppBillingCity) { this.suppBillingCity = suppBillingCity; }
    public void setSuppBillingState(String suppBillingState) { this.suppBillingState = suppBillingState; }
    public void setSuppBillingZipCode(String suppBillingZipCode) { this.suppBillingZipCode = suppBillingZipCode; }
    public void setSuppBillingCountry(String suppBillingCountry) { this.suppBillingCountry = suppBillingCountry; }
    public void setSuppShippingStreet(String suppShippingStreet) { this.suppShippingStreet = suppShippingStreet; }
    public void setSuppShippingCity(String suppShippingCity) { this.suppShippingCity = suppShippingCity; }
    public void setSuppShippingState(String suppShippingState) { this.suppShippingState = suppShippingState; }
    public void setSuppShippingZipCode(String suppShippingZipCode) { this.suppShippingZipCode = suppShippingZipCode; }
    public void setSuppShippingCountry(String suppShippingCountry) { this.suppShippingCountry = suppShippingCountry; }
    public void setSuppNotes(String suppNotes) { this.suppNotes = suppNotes; }
}
