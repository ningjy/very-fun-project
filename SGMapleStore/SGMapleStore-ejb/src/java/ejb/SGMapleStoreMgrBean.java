package ejb;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import entity.EmployeeEntity;
import entity.CustomerEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@Stateless
public class SGMapleStoreMgrBean implements SGMapleStoreRemote {
    @PersistenceContext
    private EntityManager em;
    
    private EmployeeEntity eEntity;
    private CustomerEntity cEntity;
    
    @Override
    public void createCustomer(String custSalutation, String custFirstName, String custLastName, String custEmail, 
            String custContactNum, String custBillingStreet, String custBillingCity, String custBillingState, 
            String custBillingZipCode, String custBillingCountry, String custShippingStreet, String custShippingCity, 
            String custShippingState, String custShippingZipCode, String custShippingCountry, String custNotes) {
        cEntity = new CustomerEntity();
        cEntity.createCustomer(custSalutation, custFirstName, custLastName, custEmail, custContactNum, 
                custBillingStreet, custBillingCity, custBillingState, custBillingZipCode, custBillingCountry, 
                custShippingStreet, custShippingCity, custShippingState, custShippingZipCode, custShippingCountry, custNotes);
        em.persist(cEntity);
    }
    
    @Override
    public List<Vector> viewCustomerList(){
        Query q = em.createQuery("SELECT c FROM Customer c");
        List<Vector> customerList = new ArrayList<Vector>();
        
        for(Object o: q.getResultList()){
            CustomerEntity custE = (CustomerEntity) o;
            Vector custVec = new Vector();
            
            custVec.add(custE.getCustSalutation());
            custVec.add(custE.getCustFirstName());
            custVec.add(custE.getCustLastName());
            custVec.add(custE.getCustEmail());
            custVec.add(custE.getCustContactNum());
            custVec.add(custE.getCustBillingCity());
            custVec.add(custE.getCustBillingState());
            custVec.add(custE.getCustBillingCountry());
            customerList.add(custVec);
        }
        return customerList;
    }
    
    @Override
    public boolean empLogin(String empNRIC, String empPassword) {
        /* Must perform hashing here, not on the servlet side. Otherwise will produce different hash values */
        String hashedPassword = "";
        try{ hashedPassword = encodePassword(empPassword); }
        catch(NoSuchAlgorithmException ex){ ex.printStackTrace(); }

        eEntity = new EmployeeEntity();
        try{
            Query q = em.createQuery("SELECT e FROM Employee e WHERE e.empNRIC = :empNRIC");
            q.setParameter("empNRIC", empNRIC);
            eEntity = (EmployeeEntity)q.getSingleResult();
        }
        catch(EntityNotFoundException enfe){
            System.out.println("ERROR: Employee cannot be found. " + enfe.getMessage());
            em.remove(eEntity);
            eEntity = null;
        }
        catch(NoResultException nre){
            System.out.println("ERROR: Employee does not exist. " + nre.getMessage());
            em.remove(eEntity);
            eEntity = null;
        }
        
        if(eEntity == null) { return false; }
        //if(eEntity.getEmpPassword().equals(hashedPassword)) { return true; }
        if(eEntity.getEmpPassword().equals(empPassword)) { return true; }    // TO BE REVERTED
        return false;
    }
    
    /* MISCELLANEOUS METHOD HELPERS */
    public String encodePassword(String password) throws NoSuchAlgorithmException {
        String hashedValue = "";
        
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] bytes = md.digest();
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bytes.length; i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            hashedValue = sb.toString();
        }      
        return hashedValue;
    }
}
